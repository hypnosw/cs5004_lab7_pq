package cs5004.collections;

/**
 * Multiple elements in the PQ may have the same priority, which will impact peek() and pop().
 * If there are multiple elements that have the same priority,
 * return the value of the earliest added element.
 * The PQ should be immutable
 */
public class ListPriorityQueue extends PriorityQueue{
  // private Node node;
  private int count;
  private Node head;
  private Node tail;

  private class Node {
    private Integer priority;
    private String value;
    private Node next;
    private Node prev;

    private Node(Integer priority, String value) {
      this.priority = priority;
      this.value = value;
      this.next = null;
      this.prev = null;
    }
  }

  public ListPriorityQueue() {
    this.count = 0;
    this.head = null;
    this.tail = null;

  }

  /**
   *  Creates and returns an empty PQ.
   * @return the PQ
   */
  public static PriorityQueue createEmpty() {
    return new ListPriorityQueue();
  }

  /**
   * Checks if the PQ is empty. Returns true if the PQ contains no items, false otherwise.
   * @return Returns true if the PQ contains no items, false otherwise.
   */
  public Boolean isEmpty() {
    return this.count == 0;
  }

  /**
   * Adds the given element (the priority and its associated value) to the PQ.
   * The range of acceptable values for our PQ is 1..10.
   *  Your PQ should be ordered from the highest to lowest priority.
   *  If the nodes have the same priority
   *  treat the new node as if its priority is lower than the existing node.
   * For ex. if queue looks like this [ ( 2, "grape") ],
   * then after adding a new node, the queue should be ordered like [ (2, "grape"), (2, "apple") ]
   * @param priority the priority of the node
   * @param value the value contained in the node
   * @throws IllegalArgumentException Any values outside of this range
   * @return the PQ after operations
   */
  public PriorityQueue add(Integer priority, String value) throws IllegalArgumentException{
    if (1 > priority || priority > 10) {
      throw new IllegalArgumentException("Invalid priority value");
    }
    else if (this.isEmpty()) {
      Node newNode = new Node(priority, value);
      this.head = newNode;
      this.tail = newNode;
      this.count ++;
    }
    else {
      Node newNode = new Node(priority, value);
      Node temp = this.tail;
      for (int i = this.count; i > 0; i --) {
        if (newNode.priority < temp.priority) {
          temp.next.prev = newNode; // might get nullpointer if temp.next is null
          newNode.next = temp.next;
          newNode.prev = temp;
          temp.next = newNode;
          this.count ++;
        }
        else {
          temp = temp.prev;
        }
      }
      if (newNode.next == null) {
        this.tail = newNode;
      }
      else if (newNode.prev == null) {
        this.head = newNode;
      }
    }
    return this;
  }

  /**
   * For two positive integers, i and j. If i < j then i has a lower priority than j.
   * After a call to peek() the PQ remains unchanged.
   * @throws EmptyPriorityQueueException calling peek() on an empty PQ
   * @return  the value in the PQ that has the highest priority
   */
  public String peek() throws EmptyPriorityQueueException{

  }

  /**
   * Calling pop() on an empty PQ
   * @return copy of the PQ without the element with the highest priority
   * @throws EmptyPriorityQueueException
   */
  public PriorityQueue pop() throws EmptyPriorityQueueException{

  }
}
