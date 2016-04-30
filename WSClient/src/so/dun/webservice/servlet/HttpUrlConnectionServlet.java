package so.dun.webservice.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 使用HttpUrlConnectionServlet发送webservice请求
 *
 */
public class HttpUrlConnectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("#########################");
		String id =request.getParameter("idss");
		String data = "<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>"+
				"<soap:Body>"+
					"<ns2:getStudentById xmlns:ns2='http://webservice.dun.so/'>"+
						"<arg0>"+id+"</arg0>"+
					"</ns2:getStudentById>"+
				"</soap:Body>"+
			"</soap:Envelope>";
		URL url = new URL("http://localhost:8080/WSServer/StudentWS");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		
		OutputStream os = connection.getOutputStream();
		os.write(data.getBytes("utf-8"));
		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			InputStream is = connection.getInputStream();
			System.out.println(is.available());
			response.setContentType("text/text;charset=utf-8");
			ServletOutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = is.read(buffer)) > 0){
				outputStream.write(buffer, 0, len);
			}
			outputStream.flush();
		}
	}
}
