public class DoubleLL {

    public class Node {
        int data ;
        Node next ;
        Node prev ;

        public Node( int data ){
            this.data = data ;
            this.next = null ;
            this.prev = null ;
        } 
    }

    public static Node head ;
    public static Node tail ;
    public static int size ;

    public void addFirst( int data ){
        
        Node newNode = new Node(data);
        size++;
        // corner case 
        if( head == null ){
            head = tail = newNode ;
            return ;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void print(){
        Node temp = head ;
        while( temp != null ){
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addLast(int data ){
        Node newNode = new Node(data);
        size++;
        // corner case 
        if(head == null){
            head = tail = newNode ;
            return ;
        }
        newNode.prev = tail ;
        tail.next = newNode ;
        tail = newNode ;

    }

    public int removeFirst(){
        // corner case 
        if( size == 0 ){
            System.out.println(" double linkedlist is empty ");
            return Integer.MIN_VALUE;
        }else if ( size== 1 ){
            int val = head.data;
            head = tail = null ;
            return val ;
        }

        int val = head.data;
        head = head.next ;
        head.prev = null ;
        return val ;


    }
    public int removeLast(){
        // corner case 
        if( size == 0 ){
            System.out.println(" double linkedlist is empty ");
            return Integer.MIN_VALUE;
        }else if ( size== 1 ){
            int val = head.data;
            head = tail = null ;
            return val ;
        }

        Node temp = head ;
        for( int i = 0 ;i < size-2 ; i++){
            temp = temp.next ;
        }
        int val = tail.data;
        temp.next = null ;
        tail = temp ;
        size--;
        return val ;
    }

    public void revereDll(){
        Node curr = head ;
        Node prev = null ;
        Node next ;
        while( curr != null){
            next = curr.next ;
            curr.next = prev ;
            curr.prev = next ;
            prev = curr ;
            curr = next ;
        }
        head = prev ;


       
    }
    public static void main (String args[]) {
        DoubleLL dll = new DoubleLL();
        dll.addFirst(2);
        dll.addFirst(1);
        dll.addFirst(0);
        dll.addLast(3);
        dll.addLast(4);
        dll.print();
        dll.revereDll();
        dll.print();
       
    }

}
    

