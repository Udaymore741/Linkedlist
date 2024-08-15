public class LinkedList {

    public static class Node {
        int data ;
        Node next ;

        public Node(int data ){
            this.data = data;
            this.next = null ;
        }
    }

    public static Node head ;
    public static Node tail;
    public static int size ;

    // takes constant time to add element i.e 0(1)
    public void addFirst ( int data ){
        // step 1 = create a new node 
        Node newnode = new Node(data);
        size ++;
        if( head == null ){
            head = tail = newnode ;
            return ;
        }
        

        // step 2 = newNode next = head 
        newnode.next = head ;  // this is the linking part of the process 

        // step3 = nead = newNode 
        head = newnode ;
    }


    public void addLast ( int data ){
        // step 1 = create a new node .
        Node newNode = new Node(data);
        size++;
        if( head == null ){
            head = tail = newNode ;
            return;
        }
        // step 2 = tail next = newNode.
        tail.next = newNode ;

        // step 3 = tail = newNode.
        tail = newNode ;

    }

    // TO PRINT THE NODES 
    public void print(){
        // even if you removed the if block it will print null.
        if( head == null ){
            System.out.println(" LL is empty ");
            return;
        }
        Node temp = head ;
        while ( temp != null ){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println();
    }

    public void add(int idx, int data ){

        // Special case when you had to at the head ;
        if( idx == 0){
            addFirst(data);
            return ;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head ;
        int i = 0 ;

        while (i< idx-1) {  // condiion when it reaches at the previous value 
            temp = temp.next ;
            i++;
        }

        // i = idx-1: temp=> prev ;
        newNode.next = temp.next;
        temp.next = newNode ;
    }

    public int removeFirst(){
        if( size == 0){
            System.out.println(" ll is empty ");
        }else if ( size ==1 ){
            int val = head.data ;
            head = tail = null ;
            size = 0 ;          // Here the size is 1 that's why the size== 0 ;while removing node , we must do size--;
            return val ;
        }

        int val = head.data;    // here we are just changing the head 
        head = head.next;      // suppose there are  1->2->3-> and 1 is head 
        size--;               // here the could be any that why we are doing size--; -1 from any number . 
        return val ;        // then we are just making the 2 as a new head 
    }                       // because the linked list strats form head. 
                            // and automatically the 1 st node will be removed by the garbage collector 

    
    public int removeLast(){
        if( size == 0 ){
            System.out.println(" ll is empty ");
            return Integer.MIN_VALUE;
        }else if ( size ==1 ){
            int val = head.data;
            head = tail = null ;
            size = 0 ;
            return val ;
        }

        // loop to reach at the prev node 
        Node prev = head ;
        for( int i = 0 ; i <size-2 ; i++){
            prev = prev.next ;
        }

        int val = prev.next.data ; // tail.data
        prev.next = null ;
        tail = prev ;
        size--;
        return val ;

    }

    public int iterSeaech(int key ){  // time complexity 0(n)
        Node temp = head;
        int i = 0 ; 
        while( temp != null){
            if( temp.data == key ){  // key founf case.
                return i ;
            }
            temp = temp.next;
            i++;
        }

        return -1 ;     // key not found case.
    }

    public void reverse(){ // 0(n)
        Node prev = null;
        Node curr = tail = head ;
        Node next ;

        while( curr != null){
            next = curr.next;
            curr.next = prev ;
            prev = curr ;
            curr = next ;
        }

        head = prev ;
    }

    public void deleteNthFromEnd(int n ){
        // calculate size 
        int sz = 0 ;
        Node temp = head ;
        while (temp != null) {
            temp = temp.next;
            sz++;
        }

        if( n == sz){
            head = head.next;
            return ;
        }
        
        // sz-n
        int i = 1 ;
        int itoFind = sz-n;
        Node prev = head ;
        while (i < itoFind) {
            prev = prev.next;
            i++;
        }

        prev.next = prev.next.next;
        return ;
    }

    // Slow - fast Approach 
    public Node findMid(Node head){
        Node slow = head ;
        Node fast = head ;

        while (fast != null && fast.next != null ) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }

        return slow ; // slow is the middle Node 
    }


    public boolean isPalindrome(){

        // base case 
        if( head == null || head.next == null ){
            return true ;
        }
        
        // Step 1 = to find a mid 
        Node midNode = findMid(head);


        // Step 2 =  reverse 2nd half 
        Node prev = null ;
        Node curr = midNode ;
        Node next ;
        while( curr != null){
            next = curr.next;
            curr.next = prev ;
            prev = curr ;
            curr = next ;
        }
       

        // Step 3 = compare both left and right .
        // To compare both left and right we initializing it
        // right with the prev;         
        // left with the head ;
        Node right = prev ; // right half cha head. 
        Node left = head ; // left half cha head .

        // check left half and right half 
        while ( right != null ){
            if( left.data != right.data){
                return false ;
            }

            left = left.next;
            right = left.next;

        }

        return true ;


    }


    public static boolean isCycle(){
        Node slow = head ;
        Node fast = head ;
        while( fast != null && fast.next != null){
            slow = slow.next ; // +1 
            fast = fast.next.next; // +2 
            if( slow == fast ){ 
                return true ;  // Cycle exists 
            }
        }
        return false;  // cycle does'nt exist
    }


    public static void removeCycle(){
        // detect cycle 
        Node slow = head ;
        Node fast = head ;
        boolean cycle = false ;
        while ( fast != null  && fast.next != null ){
            slow = slow.next ;
            fast = fast.next.next ;
            if( fast == slow ){
                cycle = true ;
                break ;
            }
        }
        if(cycle == false ){
            return ;
        }

        // find meeting point 
        slow = head ;
        Node prev = null ; // last node 
        while( slow == fast ){
            prev = fast ;
            slow = slow.next;
            fast = fast.next;
        }

        // remove cycle 
        prev.next = null ;
    }  
    
    public Node merge( Node head1 , Node head2  ){
        Node mergeLL = new Node(-1);
        Node temp = mergeLL ;

        while (head1 != null && head2 != null ){
            if( head1.data <= head2.data){ 
                temp.next = head1 ;
                head1 = head1.next ;
                temp = temp.next ;
            }else {
                temp.next = head2 ;
                head2 = head2.next ;
                temp = temp.next ;
            }
        }

        while( head1 != null ){
            temp.next = head1 ;
            head1 = head1.next ;
            temp = temp.next ;
        }

        while( head2 != null ){
            temp.next = head2 ;
            head2 = head2.next;
            temp = temp.next;
        }

        return mergeLL.next;
              
    }

    public Node getMid(Node head ){
        Node slow = head ;
        Node fast = head.next ;

        while( fast != null && fast.next != null){
            slow = slow.next ;
            fast = fast.next.next;
        }

        return slow ;// mid  node return 
    }

    public Node mergeSort( Node head ){ // 0( n logn )
        // corner case 
        if( head == null || head.next == null ){
            return head ;
        }

        // find mid
        Node mid = getMid(head);
        // left and right ms  
        Node righthead = mid.next;
        mid.next = null ;
        Node newLeft =  mergeSort(head);
        Node newRight = mergeSort(righthead);

        // merge 

        return merge( newLeft , newRight);
    }

  

    public void zig_zag ( Node head ){
        // calculate mid 
        Node slow = head ;
        Node fast = head.next;
        while( fast != null && fast.next != null ){
            slow = slow.next ;
            fast = fast.next.next ;
        }

        Node mid = slow ;

        // reverse the 2nd half linkedList 
        Node curr = mid.next;
        mid.next = null ;
        Node prev = null ;
        Node next ;

        while ( curr != null){
            next = curr.next;
            curr.next = prev ;
            prev = curr ;
            curr = next  ;
        }

        Node left = head ;
        Node right = prev ;
        Node nextL , nextR;

        // Alternate merging or zig zag marge
        while (left != null && right != null ) {
            nextL = left.next ;
            left.next = right ;
            nextR = right.next ;
            right.next =nextL ;

            left = nextL;
            right = nextR ;
        }


    }

    public static void main(String[] args) {
     
     LinkedList ll = new LinkedList();
     ll.addFirst(1);
     ll.addFirst(2);
     ll.addFirst(3);
     ll.addFirst(4);
     ll.addFirst(5);
     ll.addFirst(6);
    // 5->4->3->2->1
    head = ll.mergeSort(head);
    ll.print();
    ll.zig_zag(head);
    ll.print();
    

    }
    
}