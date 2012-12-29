<%@page import="net.fast.lbcs.data.entities.Pair"%>
<%@page import="org.simpleframework.xml.core.Persister"%>
<%@page import="org.simpleframework.xml.Serializer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
Serializer serializer = new Persister();
Pair p = new Pair("first", "second");

serializer.write(p, response.getWriter());

%>