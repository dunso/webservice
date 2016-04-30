package so.dun.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import so.dun.webservice.po.Student;

@WebService
public interface StudentDao {
	@WebMethod
	public boolean addStudent(Student student);
	@WebMethod
	public Student getStudentById(Integer id);
	@WebMethod
	public List<Student> getStudentsByPrice(float price);
	@WebMethod
	public Map<Integer, Student> getAllStudentsMap();
	
}
