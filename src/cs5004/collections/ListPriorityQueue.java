package cs5004.collections;

/**
 * Multiple elements in the PQ may have the same priority, which will impact peek() and pop().
 * If there are multiple elements that have the same priority,
 * return the value of the earliest added element.
 * The PQ should be immutable
 */
public class ListPriorityQueue implements PriorityQueue{
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

    /**
     * Node's value getter method
     * @return the Node's value
     */
    public String getValue() {
      return this.value;
    }

    /**
     * Node's priority getter method
     * @return the node's priority
     */
    public Integer getPriority() {
      return this.priority;
    }
  }

  public ListPriorityQueue() {
    this.count = 0;
    this.head = null;
    this.tail = null;
  }

  /**
   * the queue's count getter method
   * @return the queue's current node count
   */
  public int getCount() {
    return this.count;
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
  @Override
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

    else if (priority > this.head.priority) {
      Node newNode = new Node(priority, value);
      this.head.prev = newNode;
      newNode.next = this.head;
      this.head = newNode;
      this.count ++;
    }
    else {
      Node newNode = new Node(priority, value);
      Node temp = this.tail;
      for (int i = this.count; i > 0; i --) {
        if (newNode.priority <= temp.priority) {
          if (temp.next != null) {
            temp.next.prev = newNode;
            newNode.next = temp.next;
          }
          newNode.prev = temp;
          temp.next = newNode;
          this.count++;
          break;
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
  @Override
  public String peek() throws EmptyPriorityQueueException{
    if (this.isEmpty()) {
      throw new EmptyPriorityQueueException();
    }
    return this.head.value;
  }

  /**
   * Calling pop() on an empty PQ
   * @return copy of the PQ without the element with the highest priority
   * @throws EmptyPriorityQueueException
   */
  @Override
  public PriorityQueue pop() throws EmptyPriorityQueueException {
    if (this.isEmpty()) {
      throw new EmptyPriorityQueueException();
    }

    ListPriorityQueue newQueue = new ListPriorityQueue();
    if (this.count > 1) {
      newQueue.head = this.head.next;
      newQueue.head.prev = null;
      newQueue.tail = this.tail;
      newQueue.tail.next = null;
      newQueue.count = this.count - 1;
    }
    else {
      newQueue.count = 0;
      newQueue.head = null;
      newQueue.tail = null;
    }
    return (PriorityQueue) newQueue;
  }


}
