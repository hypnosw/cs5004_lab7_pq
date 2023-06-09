package cs5004.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class ListPriorityQueueTest {
  PriorityQueue queue;
  @BeforeEach
  void setUp() {
    queue = ListPriorityQueue.createEmpty();
  }

  @Test
  void testIsEmpty() {
    Assertions.assertTrue(queue.isEmpty());
  }

  /**
   * testing add and peek
   * @throws EmptyPriorityQueueException
   */
  @Test
  void testAdd() throws EmptyPriorityQueueException {
    queue.add(5, "I am 5");
    Assertions.assertFalse(queue.isEmpty());
    Assertions.assertEquals("I am 5", queue.peek());


    queue.add(7, "I am 7");
    Assertions.assertEquals("I am 7", queue.peek());

    queue.add(10, "I am 10");
    Assertions.assertEquals("I am 10", queue.peek());
  }

  @Test
  void testPeek() throws EmptyPriorityQueueException {
    try {
      queue.peek();
      fail("Should throw exception");
    } catch (EmptyPriorityQueueException e) {
    }

    try {
      queue.add(5, "Hi");
      queue.peek();
    } catch (EmptyPriorityQueueException e) {
      fail("Should not throw exception");
    }
  }

  @Test
  void testPop() throws EmptyPriorityQueueException {
//    queue.add(5, "I am 5");
//    queue.add(7, "I am 7");
//    queue.add(10, "I am 10");
//    Assertions.assertEquals("I am 10", queue.peek());
//
//    queue = queue.pop();
//    Assertions.assertEquals("I am 7", queue.peek());
//
//    queue = queue.pop();
//    Assertions.assertEquals("I am 5", queue.peek());
    queue = queue.add(5, "I am 5");
    queue = queue.add(7, "I am 7");
    queue = queue.add(10, "I am 10");

    Assertions.assertEquals("I am 10", queue.peek());

    queue = queue.pop();
    Assertions.assertEquals("I am 7", queue.peek());

    queue = queue.pop();
    Assertions.assertEquals("I am 5", queue.peek());

    // Testing pop on an empty queue
    queue = queue.pop();
    Assertions.assertTrue(queue.isEmpty());
    Assertions.assertThrows(EmptyPriorityQueueException.class, ()-> {
      queue = queue.pop();
    });
  }
}