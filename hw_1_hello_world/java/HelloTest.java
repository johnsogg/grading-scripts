import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;

public class HelloTest {

    public static void main(String[] args) {
	JUnitCore core= new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(HelloTest.class);
    }

    @Before
    public void init() {

    }

    @Test 
    public void testHelloWorld() {

    }



}
