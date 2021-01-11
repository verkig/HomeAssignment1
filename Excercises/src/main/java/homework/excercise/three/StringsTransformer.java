package homework.excercise.three;

import java.util.List;

public interface StringsTransformer {

	public List<String> transform(List<StringFunction> functions) throws InterruptedException;
	
	public static interface StringFunction {
		public String transform(String str);
	}	
}
