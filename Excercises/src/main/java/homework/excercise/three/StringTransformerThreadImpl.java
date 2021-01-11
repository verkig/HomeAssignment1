package homework.excercise.three;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class StringTransformerThreadImpl implements StringsTransformer{
	
	//depends on the number of resources (CPU)
	private static final int WORKERS_COUNT = 3;
	//thread pool to execute operation on the data in a parallel manner
	private ExecutorService pool = Executors.newFixedThreadPool(WORKERS_COUNT);

	private List<String> data = new ArrayList<String>();

	
	public StringTransformerThreadImpl(List<String> startingData) {
		this.data.addAll(startingData);
	}

	/**
	 * Transforms string according to functions in the provided order
	 * Function operation performed concurrently
	 */
	@Override
	public List<String> transform(List<StringFunction> functions) throws InterruptedException  {
		//split the data into bulks and each worker will perform it
		int bulkSize = data.size()/WORKERS_COUNT;
		
		//functions will be applied in a sequential order
		//each function will be applied on the data in a parallel manner
		for (StringFunction function : functions) {
			CountDownLatch countDownLatch = new CountDownLatch(WORKERS_COUNT);
			int currStartIdx = 0;		
			
			List<TransformationWorker> workers = new ArrayList<>();
			for (int i = 0; i < WORKERS_COUNT; i++) {			
				int endInx = i == (WORKERS_COUNT-1) ? data.size() : currStartIdx+bulkSize;
				List<String> partialData = data.subList(currStartIdx, endInx);
				
				TransformationWorker worker = new TransformationWorker(partialData, function, countDownLatch);
				pool.execute(worker);
				workers.add(worker);
				
				currStartIdx += bulkSize;
			}
			
			//waiting for all the workers to complete
			countDownLatch.await();
			//at this point all the workers are done
			
			//collect all the results from all the workers and override data
			data = workers.stream().map(w -> w.getResult())
					.flatMap(str -> str.stream())
					.collect(Collectors.toList());
		}
		
		return data;
	}

	/**
	 * Worker performs function on the data provided to him
	 * when workers completes he counts down the CountDownLatch
	 * 
	 * @author Vered A
	 *
	 */
	private class TransformationWorker implements Runnable {
		
		private List<String> workerData = null;
		private StringFunction function = null;
		private CountDownLatch countDownLatch;
		
		public TransformationWorker(List<String> data, StringFunction function, CountDownLatch countDownLatch) {
			this.workerData = data;
			this.function = function;
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public void run() {
			forEach(function);
			countDownLatch.countDown();
		}
		
		private void forEach(StringFunction function) {
			workerData = workerData.stream()
								.map(str -> function.transform(str))
								.collect(Collectors.toList());
		}
		
		public List<String> getResult(){
			return workerData;
		}
	}
	
	
}

