<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%-- <%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function reqWebServiceAjax(){
			var id = document.getElementById("id").value;
 			var data = '<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">'+
 								'<soap:Body>'+
 									'<ns2:getStudentById xmlns:ns2="http://webservice.dun.so/">'+
 										'<arg0>'+id+'</arg0>'+
 									'</ns2:getStudentById>'+
 								'</soap:Body>'+
 							'</soap:Envelope>';	
			var request = GetXmlHttpObject();
			request.onreadystatechange = function(){
			 	if(request.readyState == 4 && request.status == 200){
					var result = request.responseXML;
					var returnEle = result.getElementsByTagName("return")[0];
					var id= returnEle.getElementsByTagName("id")[0].textContent;
					var name= returnEle.getElementsByTagName("name")[0].textContent;
					var price= returnEle.getElementsByTagName("price")[0].textContent;
					
					alert(id+" "+name +" "+price);
				} 
			};
			request.open("POST","http://localhost:8080/WSServer/StudentWS");
			request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			request.send(data);
		}
		
		function GetXmlHttpObject(){
			var xmlHttp = null;
			try{
				//Firefox,Opera 8.0+, Safari,Chrome
				xmlHttp = new XMLHttpRequest();
			}catch(e){
				//Internet Explorer
				try{
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			return xmlHttp;
		}
	</script>
 
 	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.0.js"></script>
 	<script type="text/javascript">
 		$(function(){
 			$("#HttpUrlConnectionServlet").click(function(){
 			
 			 	var id = document.getElementById("id").value;
 				var url = "HttpUrlConnectionServlet";
 				$.post(url,"idss="+id,function(msg){
 					alert(msg);
 				},"text");
 			});
 		
 		
 			$("#reqWebServiceJQuery").click(function(){
 				var id = document.getElementById("id").value;
 				var data = '<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">'+
 								'<soap:Body>'+
 									'<ns2:getStudentById xmlns:ns2="http://webservice.dun.so/">'+
 										'<arg0>'+id+'</arg0>'+
 									'</ns2:getStudentById>'+
 								'</soap:Body>'+
 							'</soap:Envelope>';
				var url = "http://localhost:8080/WSServer/StudentWS";
				$.post(url,data,function(msg){
					var $Result = $(msg);
					var value = $Result.find("return").text();
					alert(value);
				},"xml");
 			});
 		})
 		
 	</script>
  </head>
  
  <body>
    <h1><center> TEST ！</center></h1> <br/><br/>
    <center>

    用户ID：<input id="id" name="id" value="1"/><br/><br/>
    <button onclick="reqWebServiceAjax()">Ajax请求WebService</button><br/><br/>
    <button id="reqWebServiceJQuery">JQuery请求WebService</button><br/><br/>
    <button id="HttpUrlConnectionServlet">HttpUrlConnectionServlet请求WebService</button><br/><br/>
    </center>
  </body>
</html>
