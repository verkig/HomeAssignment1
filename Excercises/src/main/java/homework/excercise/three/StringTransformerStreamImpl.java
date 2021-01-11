package homework.excercise.three;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringTransformerStreamImpl implements StringsTransformer{
	private List<String> data = new ArrayList<String>();
	
	public StringTransformerStreamImpl(List<String> startingData) {
		this.data.addAll(startingData);
	}

	/**
	 * Transforms string according to functions in the provided order
	 * Function operation performed concurrently
	 */
	@Override
	public List<String> transform(List<StringFunction> functions) {
		functions.forEach(f -> {
			//parallel stream uses system thread pool
			//if we want a better over number of threads
			//we can use ForkJoinPool
			data = data.parallelStream()
					.map(str -> f.transform(str))
					.collect(Collectors.toList());
		});
		
		return data;
	}
}

