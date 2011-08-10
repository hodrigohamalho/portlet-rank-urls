<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>

<%
	List<String> errors = (List<String>) request.getAttribute("errors");
%>
	<div class="erros">
		<ul>
<%
	if (errors != null && !errors.isEmpty()){
		for(String erro : errors){
%>
			<li><%=erro %></li>			
<%		
		}
	}
%>
		</ul>
	</div>