<%@page import="net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.ServiceItemGroup"%>
<%@page import="net.fast.lbcs.data.entities.admin.Administrator"%>
<%@page import="net.fast.lbcs.data.entities.Pair"%>
<%@page import="org.simpleframework.xml.core.Persister"%>
<%@page import="org.simpleframework.xml.Serializer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
Serializer serializer = new Persister();
//Administrator p = new Administrator("f", "z");
ServiceItemGroupID gid = new ServiceItemGroupID("The id");
ServiceItemGroup p = new ServiceItemGroup(gid, "Name 1", "Description 1");

serializer.write(p, response.getWriter());

%>