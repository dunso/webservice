package so.dun.webservice.client.test;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;

import so.dun.webservice.Student;
import so.dun.webservice.StudentDaoClient;
import so.dun.webservice.StudentDaoImplService;
import so.dun.webservice.client.interceptor.AddStudentInterceptor;

public class StudentWSClientTest {
	
	public static void main(String[] args) {
		StudentDaoImplService factory = new StudentDaoImplService();
		StudentDaoClient studentDaoClient = factory.getStudentDaoImplPort();
		
		Client client = ClientProxy.getClient(studentDaoClient);
		
		//日志拦截器
		List<Interceptor<? extends Message>> inInterceptors = client.getInInterceptors();
		inInterceptors.add(new LoggingInInterceptor());
		List<Interceptor<? extends Message>> outInterceptors = client.getOutInterceptors();
		outInterceptors.add(new LoggingOutInterceptor());
		//自定义拦截器
		outInterceptors.add(new AddStudentInterceptor("Tom", 100.0f));
		
		
		Student student = studentDaoClient.getStudentById(1);
		System.out.println(student);
		
	}
	
}
