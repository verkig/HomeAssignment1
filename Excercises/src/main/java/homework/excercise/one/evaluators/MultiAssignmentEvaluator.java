package homework.excercise.one.evaluators;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MultiAssignmentEvaluator extends BaseEvaluator<List<String>>{

	private static final String EXP_SEPARATOR = " ";

	public MultiAssignmentEvaluator(Map<String, Integer> vars) {
		super(vars);
	}
	
	@Override
	public Integer eval(List<String> lines) {
		if (lines == null || lines.isEmpty()) {
			throw new IllegalArgumentException("input is empty");
		}
		
		for (String line : lines) {
			String[] expParts = line.split(EXP_SEPARATOR);
			AssignmentEvaluator assignmentEvaluator = new AssignmentEvaluator(vars);
			assignmentEvaluator.eval(Arrays.stream(expParts).collect(Collectors.toList()));
		}
		
		return null;
	}
		
	
}
