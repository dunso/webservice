package so.dun.webservice.server.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

public class CheckStudentInterceptor extends AbstractPhaseInterceptor<SoapMessage>{
	
	public CheckStudentInterceptor() {
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Header header = message.getHeader(new QName("dunso"));
		if(header != null){
			Element dunso = (Element) header.getObject();
			String name = dunso.getElementsByTagName("name").item(0).getTextContent();
			String price = dunso.getElementsByTagName("price").item(0).getTextContent();
			if("Tom".equals(name) && "100".equals(price)){
				System.out.println("Server 通过拦截器");
				return;
			}
			//不通过
			System.out.println("Server没有通过拦截器");
			throw new Fault(new RuntimeException("name或price不正确"));
		}
	}

}
