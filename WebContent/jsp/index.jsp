<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.google.gdata.data.analytics.DataEntry" %> 
<%@page import="java.util.List" %> 

<html>
<head>
	<link rel="stylesheet" href="<%=request.getContextPath() + "/css/urls_mais_acessadas.css"%>" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath() + "/js/index.js"%>"></script>
</head>
	<body>
	<%
		String error = (String) request.getAttribute("error");
		List<DataEntry> entries = (List<DataEntry>) request.getAttribute("entries");
	%>
	
		<% if (error != null){ %>
			<div class="error">
				<%= request.getAttribute("error") %>
			</div>
		<% } else if (entries != null){%>
			<div class="urls_mais_acessadas">
				<% if (!entries.isEmpty()){%>
					<table>
						<tbody>
							<%
							    for (DataEntry entry : entries) {
							%>
								<tr>
									<td class="link">
										<% String url = "http://"+request.getServerName()+entry.stringValueOf("ga:pagePath"); %>
										<a href="<%=url %>" target="_blank"><%= entry.stringValueOf("ga:pageTitle") %></a>
							   		<td>
							   	</td>
							<%   } %>
						</tbody>
					</table>
				<%}else{%>
					Nenhum registro encontrado.
				<%} %>
		</div>
		<% } %>
	</body>
</html>