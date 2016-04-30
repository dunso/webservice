package so.dun.webservice.server.start;

import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws22.EndpointImpl;
import org.apache.cxf.message.Message;

import so.dun.webservice.StudentDaoImpl;
import so.dun.webservice.server.interceptor.CheckStudentInterceptor;



public class StudentServer {

	public static void main(String[] args) {
		String address = "http://localhost:9095/WebService/StudentWS";
		Endpoint publish = Endpoint.publish(address, new StudentDaoImpl());
		EndpointImpl endpointImpl = (EndpointImpl) publish;
		List<Interceptor<? extends Message>> inInterceptors = endpointImpl.getInInterceptors();
		//服务器端日志入拦截器
		inInterceptors.add(new LoggingInInterceptor());
		//服务器端日志出拦截器
		List<Interceptor<? extends Message>> outInterceptors = endpointImpl.getOutInterceptors();
		outInterceptors.add(new LoggingOutInterceptor());
		
		//自定义拦截器
		inInterceptors.add(new CheckStudentInterceptor(""));
		
		System.out.println("StudentWS 发布成功");
	}
}
