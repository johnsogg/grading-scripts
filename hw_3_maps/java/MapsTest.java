import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MapsTest {

	public static void main(String[] args) {
		JUnitCore core = new JUnitCore();
		core.addListener(new RetroPrinter());
		core.run(MapsTest.class);
	}

	Maps maps;

	@Before
	public void init() {
		maps = new Maps();
	}

	void runCommonAssertions(Object resultObj, String name, String age,
			String major) {
		assertNotNull(resultObj);
		assertTrue(resultObj instanceof Map);
		Map result = (Map) resultObj;
		assertEquals(3, result.size());
		assertTrue(result.containsKey("name"));
		assertTrue(result.containsKey("age"));
		assertTrue(result.containsKey("major"));
		assertEquals(name, result.get("name"));
		assertEquals(age, result.get("age"));
		assertEquals(major, result.get("major"));
	}

	void runCommonAssertions(Object resultObj, String name, int age,
			String major) {
		assertNotNull(resultObj);
		assertTrue(resultObj instanceof Map);
		Map result = (Map) resultObj;
		assertEquals(3, result.size());
		assertTrue(result.containsKey("name"));
		assertTrue(result.containsKey("age"));
		assertTrue(result.containsKey("major"));
		assertEquals(name, result.get("name"));
		assertEquals(age, result.get("age"));
		assertEquals(major, result.get("major"));
	}

	@Test
	public void testEmptyDictionary() {
		Map result = maps.makeEmptyDictionary();
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testStudentDictionary() {
		Map result = maps.makeStudentDictionary();
		runCommonAssertions(result, "Unknown Name", "Unknown Age",
				"Unknown Major");
	}

	@Test
	public void testRealStudentDictionary() {
		Map result = maps
				.makeRealStudentDictionary("Bob", 20, "Basket Weaving");
		runCommonAssertions(result, "Bob", 20, "Basket Weaving");
		result = maps.makeRealStudentDictionary("Alice", 19, null);
		runCommonAssertions(result, "Alice", 19, null);
	}

	Map makeTestDict(String name, int age, String major) {
		Map ret = new HashMap();
		ret.put("name", name);
		ret.put("age", age);
		ret.put("major", major);
		return ret;
	}

	@Test
	public void testDictionaryAsList() {
		Map stud = makeTestDict("Gandalf", 12832, "Wizardry");
		// Map result = maps.getStudentDictionaryAsList(stud);
		List result = maps.getStudentDictionaryAsList(stud);
		assertTrue(result instanceof List);
		assertEquals(3, result.size());
		assertEquals("Gandalf", result.get(0));
		assertEquals("Wizardry", result.get(1));
		assertEquals(12832, result.get(2));
	}

	@Test
	public void testWhichOlder() {
		Map gandalf = makeTestDict("Gandalf", 12832, "Wizardry");
		Map bilbo = makeTestDict("Bilbo Baggins", 111, "Creative Writing");
		Map frodo = makeTestDict("Frodo Baggins", 33, "Ringbearer");
		Map frodo_twin = makeTestDict("Frodo Baggins Evil Twin", 33, "Dropout");

		Map older = maps.whichStudentIsOlder(bilbo, gandalf);
		runCommonAssertions(older, "Gandalf", 12832, "Wizardry");
		assertEquals(gandalf, older);
		older = maps.whichStudentIsOlder(frodo, bilbo);
		runCommonAssertions(older, "Bilbo Baggins", 111, "Creative Writing");
		assertEquals(bilbo, older);
		older = maps.whichStudentIsOlder(frodo_twin, frodo);
		runCommonAssertions(older, "Frodo Baggins Evil Twin", 33, "Dropout");
		assertEquals(frodo_twin, older);
	}

	@Test
	public void testAreSame() {
		Map bilbo = makeTestDict("Bilbo Baggins", 111, "Creative Writing");
		Map bilbo2 = makeTestDict("Bilbo Baggins", 111, "Creative Writing");
		Map frodo = makeTestDict("Frodo Baggins", 33, "Ringbearer");
		Map frodo_twin = makeTestDict("Frodo Baggins", 33, "Dropout");

		boolean result = maps.areStudentsTheSame(bilbo, frodo);
		assertFalse(result);
		result = maps.areStudentsTheSame(bilbo, bilbo2);
		assertTrue(result);
		result = maps.areStudentsTheSame(bilbo, bilbo);
		assertTrue(result);
		result = maps.areStudentsTheSame(frodo, frodo_twin);
		assertFalse(result);
	}

}
