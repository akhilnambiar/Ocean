/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {
    public final static int EMPTY = 0;
    public final static int SHARK = 1;
    public final static int FISH = 2;

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
    private int size;   //how big is the list
    private oceanNode head; //beginning of the list
    private oceanNode tail;
    private int starveTime; //When fish will starve
    private oceanNode runBegin;  //Where the nextRun should begin (pointer)
    private int xDimStore;
    private int yDimStore;  //Dimensions of the ocean
    //Impliment where head.prev and tail.next is null



  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int starveTime) {
    // Your solution here.
      xDimStore = i;
      yDimStore = j;
      this.starveTime = starveTime;
      oceanNode newNode = new oceanNode(0,i*j);
      insertFront(newNode);
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime,
                           int[] runTypes, int[] runLengths) {
    // Your solution here.
      xDimStore = i;
      yDimStore = j;
      this.starveTime = starveTime;
      for (int x=runTypes.length-1; x>-1;x--) {
          oceanNode newNode = new oceanNode(runTypes[x],runLengths[x]);
          insertFront(newNode);
      }
  }
    
    public void insertFront (oceanNode insNode) {
        if (size==0) {
            head = insNode;
            tail = insNode;
            insNode.prev = null;
            insNode.next = null;
            size++;
            runBegin = head;
        }
        else {
            size++;
            insNode.prev = null;
            insNode.next = head;
            head.prev = insNode;
            head = insNode;
            runBegin = head;
        }
    }

    public void insertEnd (oceanNode insNode) {
        if (size==0) {
            head = insNode;
            tail = insNode;
            insNode.prev = null;
            insNode.next = null;
            size++;
            runBegin = head;
        }
        else {
            size++;
            insNode.prev = tail;
            insNode.next = null;
            tail.next = insNode;
            tail = insNode;
        }
    }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as an
   *  array of two ints), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */
  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    // Your solution here.
      runBegin = head;
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  an array of two ints (constructed here), representing the type and the
   *  size of the run, in that order.
   *  @return the next run in the enumeration, represented by an array of
   *          two ints.  The int at index zero indicates the run type
   *          (Ocean.EMPTY, Ocean.SHARK, or Ocean.FISH).  The int at index one
   *          indicates the run length (which must be at least 1).
   */

  public int[] nextRun() {
    // Replace the following line with your solution.
      int[] result = new int[2];
      if (runBegin==null) {
          return null;
      }
/*      System.out.println("working");
      System.out.println(runBegin==head);
      System.out.println(head);
      System.out.println(runBegin);
      System.out.println(runBegin.next);
      System.out.println(hasLooped); */
      if (runBegin==null) {
          return null;
      }
      result[0] = runBegin.type;
      result[1] = runBegin.number;
      runBegin = runBegin.next;
      return result; 
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    // Replace the following line with your solution.
      Ocean newOcean = new Ocean(xDimStore,yDimStore,starveTime);
      oceanNode curr = head;
      int x=0, y=0;
      for (int i=0; i<size; i++) {
          for (int j=0; j<curr.number; j++) {
              if (curr.type == 0) {
                  newOcean.addEmpty(x,y);
                  x++;
              }
              else if (curr.type == 1) {
                  newOcean.addShark(x,y,curr.hunger);
                  x++;
              }
              else {
                  newOcean.addFish(x,y);
                  x++;
              }
              if (x==xDimStore) {
                  x=0;
                  y++;
              }
          }
          curr = curr.next;
      }
    return newOcean;
  }

/*      Ocean newOcean = new Ocean(xDimStore,yDimStore,starveTime);
      oceanNode curr = head;
      int x=0, y=0;
      while(i<size) {
          for (int j=0; j<curr.number; j++) {
              if (curr.type == 0) {
                  newOcean.addEmpty(x,y);
                  x++;
              }
              else if (curr.type == 1) {
                  newOcean.addShark(x,y,curr.hunger);
                  x++;
              }
              else {
                  newOcean.addFish(x,y);
                  x++;
              }
              if (x==xDimStore) {
                  x=0;
                  y++;
              }
              System.out.println(size);
              System.out.println(curr.number);
              System.out.println("i and j are"+i+","+j);
              System.out.println("x and y are"+x+","+y);
              System.out.println("height is "+newOcean.height());
              System.out.println("width is "+newOcean.width());
          }
          curr = curr.next;
          i++
      }
      return newOcean;
  }*/

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    //Write an insert beginning method
      
      xDimStore = sea.width();
      yDimStore = sea.height();
      this.starveTime = sea.starveTime();
      int runTrack=1;
      int curType=sea.cellContents(0,0);
      int curHung;
      curHung = sea.sharkFeeding(0,0);
      for (int j=0; j<yDimStore; j++) {
          for (int i=0; i<xDimStore; i++) {     //Sharks need a special check
              if (i==0 && j==0) {
                  i++;
              }
              if (sea.cellContents(i,j)==curType) {
                  if (curType==SHARK) {
                      if (sea.sharkFeeding(i,j)==curHung) {
                          runTrack++;
                      }
                      else {
                          oceanNode newNode = new oceanNode(curType,runTrack,curHung);
                          insertEnd(newNode);
                          curType = sea.cellContents(i,j);
                          runTrack=1;
                          if (curType==SHARK) {
                              curHung=sea.sharkFeeding(i,j);
                          }
                      }
                  }
                  else{
                      runTrack++;
                  }
                  //runTrack++; where does this go
              }
              else {    //Possible Shark Condition
                  oceanNode newNode = new oceanNode(curType,runTrack,curHung);
                  insertEnd(newNode);
                  curType = sea.cellContents(i,j);
                  runTrack=1;
                  if (curType==SHARK) {
                      curHung=sea.sharkFeeding(i,j);
                  }
              }
              if (i==xDimStore-1 && j==yDimStore-1) {
                  oceanNode newNode = new oceanNode(curType,runTrack,curHung);
                  insertEnd(newNode);
              }
          }
      }
    check();
  }

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      int point = (y*xDimStore + x);
      int sumTrack = -1;
      oceanNode curr = head;
      oceanNode combine;
      boolean finished=false;
      while (finished==false){
          if (sumTrack+curr.number>point) {
              System.out.println("point is "+point);
              System.out.println("Sum is "+sumTrack);
              System.out.println("number is "+curr.number);
              if (curr.type==EMPTY) {
                  if (curr.number==1) {
                      //If the node is replacing a single empty spot
                      oceanNode newNode = new oceanNode(FISH,1);
                      newNode.next = curr.next;
                      newNode.prev = curr.prev;
                      curr.prev.next = newNode;
                      curr.next.prev = newNode;
                      combine = newNode;
                      finished = true;
                  }
                  else if (sumTrack==point) {
                      //If the node goes at the end
                      curr.number-=1;
                      oceanNode newNode = new oceanNode(SHARK,1,0);
                      newNode.next = curr;
                      newNode.prev = curr.prev;
                      curr.prev = newNode;
                      combine = newNode;
                      finished = true;
                  }
                  else if(curr.number==2){
                      //this means the first of one
                      curr.number-=1;
                      oceanNode newNode = new oceanNode(FISH,1);
                      newNode.next = curr.next;
                      newNode.prev = curr.prev;
                      curr.prev = newNode;
                      combine = newNode;
                      finished = true;
                  }
  /*                else if (curr.number>1 && sumTrack+1==point) { //If the node goes before
                      oceanNode newNode = new oceanNode(FISH,1);
                      curr.number-=1;
                      newNode.next = curr;
                      newNode.prev = curr.prev;
                      curr.prev = newNode;
                      combine = newNode;
                      finished = true;
                  } */
                  else {
                      //This means a node will be split
                      int split = point - sumTrack;
                      int n1 = split;// This is saying how far from the beginning is the split
                      int n2 = curr.number - split-1;
                      oceanNode newNodeOrigin1 = new oceanNode(curr.type, n1, curr.hunger);
                      oceanNode newNodeOrigin2 = new oceanNode(curr.type, n2, curr.hunger);
                      oceanNode newNode = new oceanNode(FISH,1);
                      newNodeOrigin1.next = newNode;
                      newNodeOrigin1.prev = curr.prev;
                      newNode.prev = newNodeOrigin1;
                      newNode.next = newNodeOrigin2;
                      newNodeOrigin2.prev = newNode;
                      newNodeOrigin2.next= curr.next;
                      curr.prev.next = newNodeOrigin1;
                      curr.next.prev = newNodeOrigin2;
                      combine = newNode;
                      finished = true;
                  }
                  if (combine.prev!=null) {
                      if (combine.prev.type==combine.type) {
                          System.out.println("are you here");
                          int total = combine.number+combine.prev.number;
                          oceanNode newNode = new oceanNode(FISH,total);
                          newNode.next = combine.next;
                          newNode.prev = combine.prev.prev;
                          combine.prev.prev.next = newNode;
                          combine.next.prev = newNode;
                          combine = newNode;
                      }
                      
                  }
                  if (combine.next!=null) {
                      if (combine.next.type==combine.type) {
                          int total = combine.number+combine.next.number;
                          oceanNode newNode = new oceanNode(FISH,total);
                          newNode.next = combine.next.next;
                          newNode.prev = combine.prev;
                          combine.prev.next = newNode;
                          if (combine.next.next!=null){
                              combine.next.next.prev = newNode;
                          }
                      }
                  }
              }
              else {
                  finished = true;
              }
          }
          else {
              sumTrack+=curr.number;
              curr=curr.next;
          }
      }
      
    check();
  }
//Two phases, insertion and consolidation
  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      int point = (y*xDimStore + x);
      int sumTrack = 0;
      oceanNode curr = head;
      oceanNode combine;
      boolean finished=false;
      while (finished==false){
          if (sumTrack+curr.number>point) {
              if (curr.type==EMPTY) {
                  if (curr.number==1) {
                      //If the node is replacing a single empty spot
                      oceanNode newNode = new oceanNode(SHARK,1,0);
                      newNode.next = curr.next;
                      newNode.prev = curr.prev;
                      curr.prev.next = newNode;
                      curr.next.prev = newNode;
                      combine = newNode;
                      finished = true;
                  }
                  else if (sumTrack==point) {
                      //If the node goes at the end
                      curr.number-=1;
                      oceanNode newNode = new oceanNode(SHARK,1,0);
                      newNode.next = curr;
                      newNode.prev = curr.prev;
                      curr.prev = newNode;
                      combine = newNode;
                      finished = true;
                  }
/*                  else if (curr.number>1 && sumTrack+1==point) { //If the node goes before
                      System.out.println("we are here");
                      System.out.println("type is "+curr.type);
                      oceanNode newNode = new oceanNode(SHARK,1,0);
                      curr.number-=1;
                      newNode.next = curr;
                      newNode.prev = curr.prev;
                      curr.prev = newNode;
                      combine = newNode;
                      finished = true;
                  } */
                  else {
                      //This means a node will be split
                      int split = point - sumTrack;
                      int n1 = split;// This is saying how far from the beginning is the split
                      int n2 = curr.number - split-1;
                      oceanNode newNodeOrigin1 = new oceanNode(curr.type, n1, curr.hunger);
                      oceanNode newNodeOrigin2 = new oceanNode(curr.type, n2, curr.hunger);
                      oceanNode newNode = new oceanNode(SHARK,1,0);
                      newNodeOrigin1.next = newNode;
                      newNodeOrigin1.prev = curr.prev;
                      newNode.prev = newNodeOrigin1;
                      newNode.next = newNodeOrigin2;
                      newNodeOrigin2.prev = newNode;
                      newNodeOrigin2.next= curr.next;
                      curr.prev.next = newNodeOrigin1;
                      curr.next.prev = newNodeOrigin2;
                      combine = newNode;
                      finished = true;
                  }//FINALIZE EDGE CASES
                  if (combine.prev!=null) {
                      if (combine.prev.type==combine.type && combine.prev.hunger==combine.hunger) {
                          int total = combine.number+combine.prev.number;
                          oceanNode newNode = new oceanNode(SHARK,total,combine.hunger);
                          newNode.next = combine.next;
                          newNode.prev = combine.prev.prev;
                          combine.prev.prev.next = newNode;
                          combine.next.prev = newNode;
                          combine = newNode;
                      }
                  }
                  if (combine.next!=null) {
                      if (combine.next.type==combine.type && combine.next.hunger==combine.hunger) {
                          int total = combine.number+combine.next.number;
                          oceanNode newNode = new oceanNode(SHARK,total,combine.hunger);
                          newNode.next = combine.next.next;
                          newNode.prev = combine.prev;
                          combine.prev.next = newNode;
                          combine.next.next.prev = newNode;
                      }
                  }
              }
              else {
                  finished = true;
              }
          }
          else {
              sumTrack+=curr.number;
              curr=curr.next;
          }
      }
      
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  private void check() {
  }

}
