package homework.excercise.one;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class TextCalculatorTest {
	
	private TextCalculator tested = new TextCalculator();
	
	@Test
	public void validTest() {
		List<String> exprassions = Arrays.asList("i = 0"
												,"j = ++i"
												,"x = i++ + 5"
												,"y = 5 + 3 * 10"
												,"i += y");
		
		Map<String, Integer> actual = tested.parse(exprassions);
		
		Map<String, Integer> expected = new HashMap<>();
		expected.put("x", 6);
		expected.put("j", 1);
		expected.put("i", 37);
		expected.put("y", 35);


		Assert.assertTrue(expected.equals(actual));

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void emptyTest() {
		List<String> exprassions = Arrays.asList();
		
		tested.parse(exprassions);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void empty2Test() {
		List<String> exprassions = null;
		
		tested.parse(exprassions);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void empty3Test() {
		List<String> exprassions = Arrays.asList("");
		
		tested.parse(exprassions);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void missingAssignmentTest() {
		List<String> exprassions = Arrays.asList("i 0");
		
		tested.parse(exprassions);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void missingLeftSideOperandTest() {
		List<String> exprassions = Arrays.asList("i = + 0");
		
		tested.parse(exprassions);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void missingRightSideOperandTest() {
		List<String> exprassions = Arrays.asList("i = 5 +");
		
		tested.parse(exprassions);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void missingOperatorTest() {
		List<String> exprassions = Arrays.asList("i = 1 0");
		
		tested.parse(exprassions);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void unexpectedAssignmentSignTest() {
		List<String> exprassions = Arrays.asList("i & 1 + 0");
		
		tested.parse(exprassions);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void unexpectedOperandTest() {
		List<String> exprassions = Arrays.asList("i = 5abc + 0");
		
		tested.parse(exprassions);
	}
}
