<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<portlet:defineObjects/>

<%
	String maxUrls = request.getAttribute("maxUrls").toString();
%>

<form action="<portlet:actionURL/>" method="post" >
	Número de Links: <input type="text" name="maxUrls" value="<%=maxUrls%>"/> <br/>
	<input type="submit" value="Salvar" />
</form>