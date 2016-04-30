package so.dun.webservice.test;

import java.util.List;

import org.junit.Test;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWebService;
import cn.com.webxml.WeatherWebServiceSoap;

public class WeatherTest {

	@Test
	public void testWeather() {
		WeatherWebService service = new WeatherWebService();
		WeatherWebServiceSoap weatherWebServiceSoap = service.getWeatherWebServiceSoap();
		ArrayOfString weather = weatherWebServiceSoap.getWeatherbyCityName("宜昌");
		List<String> list = weather.getString();
		System.out.println(list);
	}

}
