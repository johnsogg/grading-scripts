
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

public class ObjectsTest {

    public static void main(String[] args) {
	JUnitCore core= new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(ObjectsTest.class);
    }

    Student[] many;
    Objects objects;

    private Student initStudent(String n, int a, String m) {
	Student s = new Student();
	s.name = n;
	s.age = a;
	s.major = m;
	return s;
    }

    @Before
    public void init() {
	objects = new Objects();
	many = new Student[] {
	    initStudent("James Bond", 38, "Bartending"),
	    initStudent("Charlie Parker", 50, "Music"),
	    initStudent("Marky Mark", 20, "Music"),
	    initStudent("Bob Bobson", 21, "Landscape Architecture")
	};
    }

    @Test 
    public void testMakeStudent() {
        String n = "Geronimo";
	int a = 40;
        String m = "Landscape Architecture";
        Student s = objects.makeStudent(n, a, m);
        assertEquals(n, s.name);
        assertEquals(a, s.age);
        assertEquals(m, s.major);
    }

    @Test 
    public void testMakeStudentOlder() {
	for (Student s : many) {
	    int a = s.age;
	    objects.makeStudentOlder(s);
	    assertEquals(a+1, s.age);
	}
    }
    
    @Test 
    public void testCopyStudent() {
	for (Student s : many) {
	    Student s_copy = objects.copyStudent(s);
	    assertNotNull(s_copy);
            assertTrue(s_copy.name == s.name);
            assertTrue(s_copy.age == s.age);
            assertTrue(s_copy.major == s.major);
            assertFalse(s_copy == s);
	}
    }
    
    @Test 
    public void testIsOlder() {
        assertTrue(objects.isOlder(many[0]));  // 38
	assertTrue(objects.isOlder(many[1]));  // 50
        assertFalse(objects.isOlder(many[2])); // 20
        assertFalse(objects.isOlder(many[3])); // 21
    }

    @Test 
    public void testIsMusician() {
        assertFalse(objects.isMusician(many[0]));
        assertTrue(objects.isMusician(many[1]));
        assertTrue(objects.isMusician(many[2]));
        assertFalse(objects.isMusician(many[3]));
    }

    @Test 
    public void testIsOlderMusician() {
        assertFalse(objects.isOlderMusician(many[0]));
        assertTrue(objects.isOlderMusician(many[1]));
        assertFalse(objects.isOlderMusician(many[2]));
        assertFalse(objects.isOlderMusician(many[3]));
    }


}
