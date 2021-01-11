package homework.excercise.one.evaluators;

import java.util.Map;

public class SimpleEvaluator extends BaseEvaluator<String>{

	public SimpleEvaluator(Map<String, Integer> vars) {
		super(vars);
	}

	@Override
	public Integer eval(String exp) {
		Integer result;
		
		if (exp.matches("^-?\\d+$")){
			result = Integer.valueOf(exp);
		} else if (exp.startsWith("++")) {
			String varName = exp.substring(2, exp.length());
			Integer varValue = vars.getOrDefault(varName, 0);
			result = ++varValue;
			vars.put(varName, varValue);
		} else if (exp.endsWith("++")) {
			String varName = exp.substring(0, exp.length()-2);
			Integer varValue = vars.getOrDefault(varName, 0);
			result = varValue++;
			vars.put(varName, varValue);
		} else if (exp.matches("^[A-Za-z]+\\d*[A-Za-z]*$")) {
			result = vars.getOrDefault(exp, 0);
		} else {
			throw new IllegalArgumentException("unexpected expression");
		}
		
		return result;
	}
}
