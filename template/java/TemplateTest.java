import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

public class TemplateTest {

    public static void main(String[] args) {
	JUnitCore core= new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(LinkedListTest.class);
    }

    @Before
    public void init() {

    }

    @Test 
    public void testSomething() {
	//	assertEquals(one.next, three);
	//	assertNull(three.next);
	//      assertTrue(false);
    }


}
