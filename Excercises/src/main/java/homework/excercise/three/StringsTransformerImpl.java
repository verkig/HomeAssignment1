package homework.excercise.three;

import java.util.ArrayList;
import java.util.List;

/**
 * Original impl
 *
 */
public class StringsTransformerImpl implements StringsTransformer{

	private List<String> data = new ArrayList<String>();
	
	public StringsTransformerImpl(List<String> startingData) {
		this.data = startingData;
	}

	private void forEach(StringFunction function) {
		List<String> newData = new ArrayList<String>();
		for (String str : data) {
			newData.add(function.transform(str));
		}
		data = newData;
	}
	
	/**
	 * 1. creating threads according to number of functions is a bad practice as there might be more functions 
	 * 	  then cores in the system, this will cause a lot of context switch
	 * 2. each function is ran in a different thread, meaning that functions can be applied in a different order on the same data,
	 *    it's big mess, strings will be overriden and it's even hard to say what will happen
	 * 3. the threads not actually executed as start method is not called.
	 */
	public List<String> transform(List<StringFunction> functions) throws
		InterruptedException {
		List<Thread> threads = new ArrayList<Thread>();
		for (final StringFunction f : functions) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					forEach(f);
				}
			}));
			}
		for (Thread t : threads) {
			t.join();
		}
		return data;
	}
}

