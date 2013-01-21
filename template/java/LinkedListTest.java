import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

public class LinkedListTest {

    public static void main(String[] args) {
	JUnitCore core= new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(LinkedListTest.class);
    }

    LinkedList list;

    @Before
    public void init() {
	list = new LinkedList();
    }

    @Test 
    public void testReport() {

    }

    @Test 
    public void testInitNode() {

    }

    @Test 
    public void testInsertEmpty() {

    }

    @Test 
    public void testInsertStart() {

    }

    @Test 
    public void testInsertEnd() {

    }

    @Test 
    public void testInsertRedundant() {

    }

    @Test 
    public void testRemove() {
	Node top = list.initNode(0);
	Node one = list.initNode(1);
	Node two = list.initNode(2);
	Node three = list.initNode(3);
	top.next = one;
	one.next = two;
	two.next = three;
	
	list.removeData(top, 2);
	assertEquals(top.next, one);
	assertEquals(one.next, three);
	assertNull(three.next);
    }



}
