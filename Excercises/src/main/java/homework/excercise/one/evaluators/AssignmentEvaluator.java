package homework.excercise.one.evaluators;

import java.util.List;
import java.util.Map;

public class AssignmentEvaluator extends BaseEvaluator<List<String>>{
	private static final String PLUS_EQUALS = "+=";
	private static final String EQUALS = "=";
	
	public AssignmentEvaluator(Map<String, Integer> vars) {
		super(vars);
	}
	
	@Override
	public Integer eval(List<String> assignmenetExpressions) {
		Integer result = 0;
		
		if (assignmenetExpressions.size() < 3) {
			throw new IllegalArgumentException("not valid assignment expression");
		}
		
		String varName = assignmenetExpressions.get(0); 
		String assignmentOperand = assignmenetExpressions.get(1);
		
		List<String> rightSide = assignmenetExpressions.subList(2, assignmenetExpressions.size());
		RightSideEvaluator rightSideEvaluator = new RightSideEvaluator(vars);
		if (assignmentOperand.equals(EQUALS)) {
			result = rightSideEvaluator.eval(rightSide);
		} else if (assignmentOperand.equals(PLUS_EQUALS)) {
			result = vars.getOrDefault(varName, 0) + rightSideEvaluator.eval(rightSide);
		} else {
			throw new IllegalArgumentException("unsupported assignment operand "+assignmentOperand);
		}
		
		vars.put(varName, result);
		
		return result;
	}
}
