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
	
	public void removeString(String str) {
		if (str == null) {
			return;
		}
		
		m_strings = m_strings.stream()
						.filter(s -> !s.equals(str))
						.collect(Collectors.toList());
	}
	
	public boolean containsNumber(long number) {
		if (m_numbers == null) {
			return false;
		}
		
		return m_numbers.contains(number);
	}
	
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
