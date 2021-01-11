package homework.excercise.three;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;



public abstract class StringsTransformerBaseTest {

	private Class<? extends StringsTransformer> testedClass = getSpecificImplClass();
	
	public abstract Class<? extends StringsTransformer> getSpecificImplClass();
	
	private StringsTransformer getTestedObject(List<String> args) {
		try {
			Constructor<? extends StringsTransformer> constractor = testedClass.getConstructor(List.class);
			return (StringsTransformer)constractor.newInstance(args);
		} catch (Exception e) {
			System.out.println("not expected");
			return null;
		}
	}
	
	@Test
	public void test1() throws InterruptedException {
		StringsTransformer tested = getTestedObject(Arrays.asList("aaaa", "123","xYz"));
		List<String> actual = tested.transform(Arrays.asList(new UpperCase(), new AddPerfix(), new AddSuffix()));
		
		List<String> expected = Arrays.asList("prefix-AAAA-suffix", "prefix-123-suffix","prefix-XYZ-suffix");
		
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	@Test
	public void test2() throws InterruptedException {
		StringsTransformer tested = getTestedObject(Arrays.asList("aaaa", "123","xYz"));
		List<String> actual = tested.transform(Arrays.asList(new AddPerfix(), new UpperCase(), new AddSuffix()));
		
		List<String> expected = Arrays.asList("PREFIX-AAAA-suffix", "PREFIX-123-suffix","PREFIX-XYZ-suffix");
		
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	@Test
	public void test3() throws InterruptedException {
		StringsTransformer tested = getTestedObject(Arrays.asList("aaaa", "123","xYz"));
		List<String> actual = tested.transform(Arrays.asList(new AddPerfix(), new AddSuffix(), new UpperCase()));
		
		List<String> expected = Arrays.asList("PREFIX-AAAA-SUFFIX", "PREFIX-123-SUFFIX","PREFIX-XYZ-SUFFIX");
		
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	private class UpperCase implements StringTransformerStreamImpl.StringFunction{

		@Override
		public String transform(String str) {
			return str.toUpperCase();
		}
		
	}
	
	private class AddPerfix implements StringTransformerStreamImpl.StringFunction{

		@Override
		public String transform(String str) {
			return "prefix-"+str;
		}
		
	}
	
	private class AddSuffix implements StringTransformerStreamImpl.StringFunction{

		@Override
		public String transform(String str) {
			return str+"-suffix";
		}
	}
}
