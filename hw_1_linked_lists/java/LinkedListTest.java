import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LinkedListTest {

    public static void main(String[] args) {
	JUnitCore core= new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(LinkedListTest.class);
    }
    
    LinkedList list;

    Node buildThreeNodeList(int one, int two, int three) {
	Node top = new Node();
	top.value = one;
	top.next = new Node();
	top.next.value = two;
	top.next.next = new Node();
	top.next.next.value = three;
	top.next.next.next = null;
	return top;
    }

    @Before
    public void init() {
	list = new LinkedList();
    }

    @Test 
    public void testInitNode() {
	System.out.println("testInitNode begin");
	Node top = list.initNode(1);
	assertEquals(1, top.value);
	assertTrue(top.next == null);
	System.out.println("testInitNode end");
    }

    public void expectReport(String actual, String expected) {
    	boolean result = actual.equals(expected + " ") || actual.equals(expected);
	if (!result) {
	    System.out.println("     -------- they are different!");
    	}
	assertTrue("Expected input " + expected + " but got " + actual, result);
    }

    @Test 
    public void testReport() {
	System.out.println("testReport begin");
	list.appendData(4);
	list.appendData(2);
	list.appendData(93);
	list.appendData(0);
	expectReport(list.report(), "4 2 93 0");
    }

    @Test
    public void testAppendData() {
	System.out.println("testAppendData begin");
	list.appendData(4);
	expectReport(list.report(), "4");
	list.appendData(2);
	expectReport(list.report(), "4 2");
	list.appendData(93);
	expectReport(list.report(), "4 2 93");
	list.appendData(0);
	expectReport(list.report(), "4 2 93 0");
	System.out.println("testAppendData end");
    }

    @Test 
    public void testAppend() {
	System.out.println("testAppend begin");
	list.append(list.initNode(4));
	expectReport(list.report(), "4");
	list.append(list.initNode(2));
	expectReport(list.report(), "4 2");
	list.append(list.initNode(93));
	expectReport(list.report(), "4 2 93");
	list.append(list.initNode(0));
	expectReport(list.report(), "4 2 93 0");	
	System.out.println("testAppend end");
    }

    @Test 
    public void testInsertData() {
   	System.out.println("testInsertData begin");
	list.insertData(0, 10);   // 10
	expectReport(list.report(), "10");
	list.insertData(0, 7);    // 7 10
	expectReport(list.report(), "7 10");
	list.insertData(1, 9);    // 7 9 10
	expectReport(list.report(), "7 9 10");
	list.insertData(1, 8);    // 7 8 9 10
	expectReport(list.report(), "7 8 9 10");
	list.insertData(3, 11);   // 7 8 9 11 10   -- thanks Nicholas for finding a bug here
	expectReport(list.report(), "7 8 9 11 10");
	System.out.println("testInsertData end");
    }

    @Test 
    public void testInsert() {
	System.out.println("testInsert begin");
	list.insert(0, list.initNode(10)); // 10
	expectReport(list.report(), "10");
	list.insert(0, list.initNode(7)); // 7 10
	expectReport(list.report(), "7 10");
	list.insert(1, list.initNode(9)); // 7 9 10
	expectReport(list.report(), "7 9 10");
	list.insert(1, list.initNode(8)); // 7 8 9 10
	expectReport(list.report(), "7 8 9 10");
	list.insert(3, list.initNode(11)); // 7 8 9 11 10 -- and here
	expectReport(list.report(), "7 8 9 11 10");
	System.out.println("testInsert end");
    }

    @Test 
    public void testRemove() {
	System.out.println("testRemove begin");
	list.appendData(5);
	list.appendData(10);
	list.appendData(15);
	list.appendData(20);
	expectReport(list.report(), "5 10 15 20");
	// remove middle
	list.remove(2);
	expectReport(list.report(), "5 10 20");
	// remove end
	list.remove(2);
	expectReport(list.report(), "5 10");
	// remove beginning
	list.remove(0);
	expectReport(list.report(), "10");
	System.out.println("testRemove end");
    }

    @Test 
    public void testSize() {
	System.out.println("testSize begin");
	assertEquals(0, list.size());
	list.appendData(5);
	list.appendData(10);
	list.appendData(15);
	list.appendData(20);
	assertEquals("List size should be 4", 4, list.size());
	System.out.println("testSize end");
    }

    @Test 
    public void testContains() {
	System.out.println("testContains begin");
	list.appendData(5);
	list.appendData(-10);
	list.appendData(15);
	assertTrue("List does not contain 5", list.contains(5));
	assertTrue("List does not contain -10", list.contains(-10));
	assertTrue("List does not contain 15", list.contains(15));
	assertFalse("List should not contain 45", list.contains(45));
	assertFalse("List should not contain -45", list.contains(-45));
	System.out.println("testContains end");
    }



}
