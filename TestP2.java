/* SList.java */

/**
 *  The SList class is a singly-linked implementation of the linked list
 *  abstraction.  SLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 **/

public class TestP2 {
    public static void main (String[] args) {
        testEmpty();
        testAfterInsertFront();
        testAfterInsertEnd();
    }
    
    
    /**
     *  testEmpty() tests toString(), isEmpty(), length(), insertFront(), and
     *  insertEnd() on an empty list.  Prints summary information of the tests
     *  and halts the program if errors are detected.
     **/
    
    private static void testEmpty() {
        SList lst1 = new SList();
        SList lst2 = new SList();
        System.out.println();
        System.out.println("Here is a list after construction: "
                           + lst1.toString());
        TestHelper.verify(lst1.toString().equals("[  ]"),
                          "toString on newly constructed list failed");
        
        System.out.println("isEmpty() should be true. It is: " +
                           lst1.isEmpty());
        TestHelper.verify(lst1.isEmpty() == true,
                          "isEmpty() on newly constructed list failed");
        
        System.out.println("length() should be 0. It is: " +
                           lst1.length());
        TestHelper.verify(lst1.length() == 0,
                          "length on newly constructed list failed");
        lst1.insertFront(new Integer(3));
        System.out.println("Here is a list after insertFront(3) to an empty list: "
                           + lst1.toString());
        TestHelper.verify(lst1.toString().equals("[  3  ]"),
                          "InsertFront on empty list failed");
        lst2.insertEnd(new Integer(5));
        System.out.println("Here is a list after insertEnd(5) on an empty list: "
                           + lst2.toString());
        TestHelper.verify(lst2.toString().equals("[  5  ]"),
                          "insertEnd on empty list failed");
    }

  }
