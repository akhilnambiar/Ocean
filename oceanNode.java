public class oceanNode {
    
    /**
     *  item references the item stored in the current node.
     *  prev references the previous node in the DList.
     *  next references the next node in the DList.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     
     TYPE
     0=EMPTY
     1=SHARK
     2=FISH
     */
    
    public int type;
    public int number;
    public int hunger;
    public oceanNode prev;
    public oceanNode next;
    
    /**
     *  DListNode1() constructor.
     */
    oceanNode() {
        this(0,1,null,null);
    }
    
    oceanNode(int i) {
        this(i,1,null,null);
    }
    oceanNode(int i, oceanNode node1, oceanNode node2) {
        this(i,1,node1,node2);
    }
    oceanNode(int i, int num, oceanNode node1, oceanNode node2) {
        type = i;
        int number = num;
        prev = node1;
        next = node2;
    }
    oceanNode(int i, int num, int hunger) {
        this(i,num,hunger,null,null);
    }
    oceanNode(int i, int num) {
        this(i,num,0,null,null);
    }
    oceanNode(int i, int num, int hung, oceanNode node1, oceanNode node2) {
        type = i;
        number = num;
        hunger = hung;
        prev = node1;
        next = node2;
    }
    
}