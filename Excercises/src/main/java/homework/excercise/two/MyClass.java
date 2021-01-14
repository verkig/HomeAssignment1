package homework.excercise.two;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MyClass{
	private LocalDateTime m_time;
	private String m_name;
	private List<Long> m_numbers;
	private List<String> m_strings;
	
	public MyClass(LocalDateTime time, String name, List<Long> numbers, List<String> strings) {

		m_time = time;
		m_name = name;
		m_numbers = numbers;
		m_strings = strings;
	}
	
	/**
	 * equals should check null, use getClass instead of instance of
	 * also need to implement hashCode along with equals on same attributes
	 * so that the objects can be used in maps or sets
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyClass other = (MyClass) obj;
		if (m_name == null) {
			if (other.m_name != null)
				return false;
		} else if (!m_name.equals(other.m_name))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_name == null) ? 0 : m_name.hashCode());
		return result;
	}
	

	/**
	 * toString that appends alot of strings should use StringBuffer in order to 
	 * avoid creation on many String objects in the process
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (m_name != null) {
			sb.append(m_name);
		}
		
		if (m_numbers != null) {
			m_numbers.forEach(number -> sb.append(" ").append(number));
		}
		
		return sb.toString();
	}
	
	/**
	 * can't remove during iteration over a List, need to use stream or iterator
	 * @param str
	 */
	public void removeString(String str) {
		if (str == null) {
			return;
		}
		
		m_strings = m_strings.stream()
						.filter(s -> !s.equals(str))
						.collect(Collectors.toList());
	}
	
	/**
	 * cleaner however need to consider if HashSet better be used here
	 * as it will improve the performance
	 * @param number
	 * @return
	 */
	public boolean containsNumber(long number) {
		if (m_numbers == null) {
			return false;
		}
		
		return m_numbers.contains(number);
	}
	
	/**
	 * java 8 offers a simpler API which is also immutable and thus thread-safe
	 * @return
	 */
	public boolean isHistoric() {
		if (m_time == null) {
			return false;
		}
		
		return m_time.isBefore(LocalDateTime.now());
	}
	
	List<String> getStrings(){
		return m_strings;
	}
}
