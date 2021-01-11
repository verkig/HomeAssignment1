package homework.excercise.one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import homework.excercise.one.evaluators.MultiAssignmentEvaluator;
/**
 * TextCalculator evaluates assignment expressions
 */
public class TextCalculator {
	
	/**
	 * parse - gets a list of string expressions and evaluates them 
	 * are returns the the values of the evaluated variables
	 * @param lines - assignment expressions
	 * @return - variables values after evaluating the expressions
	 */
	public Map<String,Integer> parse(List<String> lines) {
		
		Map<String,Integer> vars = new HashMap<>();
		
		MultiAssignmentEvaluator multiAssignmentEvaluator = new MultiAssignmentEvaluator(vars);
		multiAssignmentEvaluator.eval(lines);
		
		return vars;
	}
}
