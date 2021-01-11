package homework.excercise.one.evaluators;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseEvaluator<EXP> implements Evaluator<EXP>{
	Map<String,Integer> vars = new HashMap<>();
	
	public BaseEvaluator(Map<String,Integer> vars) {
		this.vars = vars;
	}
}
