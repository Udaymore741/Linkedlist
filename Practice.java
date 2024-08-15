public class Practice {

    class Node {
        int data ;
        Node next ;
        public Node (int data ){
            this.data = data ;
            this.next = null ; ;
        }

    }

    
    public static Node head ;
    public static Node tail ;
    public static int size ;

    public void addFirst( int data ){

        // step 1 create a new node 

        Node newNode = new Node(data);
        size++;
        //Corner case when its linked list is empty 
        if( head == null){
            head = tail = newNode ;
            return ;
        }

        // step 2 ; newNode.next should be pointing at head.
        newNode.next = head ;

        // step 3 : assign newNode as a head 

        head = newNode ;
    }

    public void addLast ( int data ){
        // step 1 = create a new node 
        Node newNode = new Node(data);
        size++;
         // corner case when its empty 
        if( head == null ){
            head = tail = newNode ;
            return ;
        }

        // step 2 : tail next should be pointing at newNode 
        tail.next = newNode ;

        // step 3 : make the newNode the tail 
        tail = newNode ;
    }

    // To print the linked list 
    public void print(){
        // corner case when its empty 
        if( head == null ){
            System.out.println(" Linked list is empty ");
            return ;
        }

        // create a temp node initialize it with head ;
        Node temp = head ;
        while( temp != null){ // null means till it reaches end of the linked list 
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println();
    }

    public void add(int idx , int data){
        // specical case when you had to add at head .
        if( idx == 0 ){
            addFirst(data);
            return ;
        }
        // create a newNode , temp and i .initialize temp with head and i = 0 ;
        Node newNode = new Node(data); // 
        size++;
        Node temp = head ;
        int i = 0 ;

        while( i < idx-1){
            temp = temp.next;   // loop till it reaches previous node 
            i++;                // increment i and temp.next 
        }

        newNode.next = temp.next;   // 
        temp.next = newNode ;
    }

    public int removeFirst(){
        // corner case when linked list is empty 
        if( size == 0 ){
            System.out.println(" LInked list is empty ");
            return Integer.MIN_VALUE ;

        }else if ( size == 1){
            int val = head.data;
            head = tail = null ;
            size = 0 ;
            return val ;
        }

        int val = head.data;
        head = head.next;
        return val ;
    }

    public int reemoveLast(){
        if( size == 0 ){
            System.out.println(" the ll is empty ");
            return Integer.MIN_VALUE;
        }else if( size==1){
            int val = head.data;
            head = tail = null ;
            size--;
            return val ;
        }

        // loop till we reach at the prev node 
        Node prev = head;
        for( int i = 0 ;i <size-2; i++){
            prev = prev.next;
        }

        int val = tail.data;
        prev.next = null ;
        tail = prev ;
        size--;
        return val ;
    }

    public int iterSearch(int key ){
        Node temp = head ;
        int i = 0 ;
        while( temp != null ){
            if( temp.data == key ){
                return i ;
            }

            temp = temp.next ;
            i++;
        }

        return -1 ;
    }

    


    public static void main(String[] args) {
        Practice ll = new Practice();
        ll.addFirst(2);
        ll.addFirst(1);
        ll.addLast(3);
        ll.addLast(4);
        ll.add(4, 5);
        ll.add(5, 6);
        ll.print();
        System.out.println(ll.iterSearch(6));

    }
}
