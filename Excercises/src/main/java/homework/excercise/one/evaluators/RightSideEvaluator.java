package homework.excercise.one.evaluators;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RightSideEvaluator extends BaseEvaluator<List<String>>{
	
	private List<Operation> orderedOperators = Arrays.asList(new MultiOperation(), 
			 new DevideOperation(), 
			 new AddOperation(), 
			 new MinusOperation());
	
	public RightSideEvaluator(Map<String, Integer> vars) {
		super(vars);
	}
	
	@Override
	public Integer eval(List<String> rightSideExpressions) {
		SimpleEvaluator simpleEvaluator = new SimpleEvaluator(vars);
		
		if (rightSideExpressions.size() == 1) {
			return simpleEvaluator.eval(rightSideExpressions.get(0));
		}
		
		for (Operation operator : orderedOperators) {
			int idx = rightSideExpressions.indexOf(operator.operatorSign());
			if (idx >= 0) {
				if (rightSideExpressions.size() < 3) {
					throw new IllegalArgumentException("not valid assignment expression");
				}
				
				String operand2 = rightSideExpressions.remove(idx+1);
				rightSideExpressions.remove(idx);
				String operand1 = rightSideExpressions.remove(idx-1);
				Integer result = operator.calculate(simpleEvaluator.eval(operand1), simpleEvaluator.eval(operand2));
				rightSideExpressions.add(idx-1, result.toString());
				return eval(rightSideExpressions);
			}
		}
		
		throw new IllegalArgumentException("operator is missing");
	}
	
	private interface Operation{
		public String operatorSign();
		public Integer calculate(Integer operand1, Integer operand2);
	}
	
	private class AddOperation implements Operation{
		public String operatorSign() {
			return "+";
		}
		
		public Integer calculate(Integer operand1, Integer operand2) {
			return operand1+operand2;
		}
	}
	
	private class MinusOperation implements Operation{
		public String operatorSign() {
			return "-";
		}
		
		public Integer calculate(Integer operand1, Integer operand2) {
			return operand1-operand2;
		}
	}
	
	private class MultiOperation implements Operation{
		public String operatorSign() {
			return "*";
		}
		
		public Integer calculate(Integer operand1, Integer operand2) {
			return operand1*operand2;
		}
	}
	
	private class DevideOperation implements Operation{
		public String operatorSign() {
			return "/";
		}
		
		public Integer calculate(Integer operand1, Integer operand2) {
			return operand1/operand2;
		}
	}
}
