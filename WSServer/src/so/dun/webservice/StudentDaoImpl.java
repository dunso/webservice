package so.dun.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import so.dun.webservice.po.Student;

@WebService
public class StudentDaoImpl implements StudentDao {

	@Override
	public boolean addStudent(Student student) {
		System.out.println("server addStudent() "+student);
		return true;
	}

	@Override
	public Student getStudentById(Integer id) {
		System.out.println("server getStudentById() "+id);
		return new Student(1, "Tom", "100");
	}

	@Override
	public List<Student> getStudentsByPrice(String price) {
		System.out.println("server getStudentsByPrice() "+price);
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Cat", price+1));
		students.add(new Student(2, "Lucy", "100"));
		return students;
	}

	@Override
	public Map<Integer, Student> getAllStudentsMap() {
		System.out.println("server getAllStudentsMap() ");
		Map<Integer, Student> map = new HashMap<Integer, Student>();
		map.put(1, new Student(1, "Lily", "10000") );
		map.put(2, new Student(2, "Jack", "100") );
		return map;
	}

}
