
// Note on the TemplateTest:
//
// You really only need to do two things:
//
// 1. Search/replace TemplateTest with the name of your test.
//
// 2. Implement a bunch of functions that start with test and end with
// the name of the test you want. The names should match those
// provided in ../description-common.json. For example if a test is
// FooThingWorks, your test function should be testFooThingWorks.
//
// Nothing else is required as long as you attach the RetroPrinter to
// your test runner.

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

public class TemplateTest {

    public static void main(String[] args) {
	JUnitCore core= new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(TemplateTest.class);
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
