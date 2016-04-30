package so.dun.webservice.client.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.xml.utils.DOMHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@SuppressWarnings("deprecation")
public class AddStudentInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	private String name;
	private String price;
	
	public AddStudentInterceptor(String name,String price) {
		super(Phase.PRE_PROTOCOL);//在准备协议化的时候调用
		this.name = name;
		this.price = price;
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		List<Header> headers = msg.getHeaders();
		Document document = DOMHelper.createDocument();
		Element rootEle = document.createElement("dunso");
		Element nameEle = document.createElement("name");
		Element priceEle = document.createElement("price");
		nameEle.setTextContent(name);
		priceEle.setTextContent(price+"");
		rootEle.appendChild(nameEle);
		rootEle.appendChild(priceEle);
		headers.add(new Header(new QName("dunso"), rootEle));
		System.out.println("handleMessage run..." );
	}

}
