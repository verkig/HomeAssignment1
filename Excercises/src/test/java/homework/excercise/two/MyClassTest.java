package homework.excercise.two;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MyClassTest {
	
	@Test
	public void testEquals() {
		MyClass cls = new MyClass(LocalDateTime.now(), "testedStr", null, null);
		Assert.assertTrue(getTestedClass().equals(cls));
	}
	
	@Test
	public void testNotEquals() {
		MyClass tested = getTestedClass();
		MyClass cls = new MyClass(LocalDateTime.now(), "differentTest", null, null);
		Assert.assertFalse(tested.equals(cls));
		
		Assert.assertFalse(tested.equals(null));

		Assert.assertFalse(tested.equals("differntObject"));
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals("testedStr 1 3 4 5 6 11", getTestedClass().toString());
	}
	
	@Test
	public void testToStringStrOnly() {
		Assert.assertEquals("testedStr", new MyClass(null, "testedStr", null, null).toString());
	}
	
	@Test
	public void testToStringNumsOnly() {
		Assert.assertEquals(" 1 3 4", new MyClass(null, null, Arrays.asList(1L,3L,4L), null).toString());
	}
	
	@Test
	public void testRemoveString() {
		MyClass tested = getTestedClass();

		Assert.assertEquals(Arrays.asList("str1", "str2", "Str"), tested.getStrings());
		tested.removeString("str2");
		Assert.assertEquals(Arrays.asList("str1", "Str"), tested.getStrings());
		tested.removeString("Str");
		Assert.assertEquals(Arrays.asList("str1"), tested.getStrings());
		tested.removeString("str1");
		Assert.assertEquals(Arrays.asList(), tested.getStrings());

	}
	
	@Test
	public void testContainsNumber() {
		Assert.assertTrue(getTestedClass().containsNumber(5));
		Assert.assertTrue(new MyClass(null, null, Arrays.asList(1L), null).containsNumber(1));
		
		Assert.assertFalse(new MyClass(null, null, Arrays.asList(), null).containsNumber(1));
		Assert.assertFalse(new MyClass(null, null, Arrays.asList(0L,3L,5L), null).containsNumber(1));
		Assert.assertFalse(new MyClass(null, null, null, null).containsNumber(1));

	}
	
	@Test
	public void testIsHistoric() {
		Assert.assertTrue(getTestedClass().isHistoric());
		
		MyClass future = new MyClass(LocalDateTime.of(2222, 1, 1, 0, 0), null, null, null);
		Assert.assertFalse(future.isHistoric());

	
	}
	
	private MyClass getTestedClass() {
		List<Long> numbers = Arrays.asList(1L,3L,4L,5L,6L,11L);
		List<String> strings = Arrays.asList("str1", "str2", "Str");
		return new MyClass(LocalDateTime.of(1982, 3, 13, 0, 0), "testedStr", numbers, strings);
	}
}
