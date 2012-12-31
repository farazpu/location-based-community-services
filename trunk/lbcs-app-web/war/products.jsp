<%@page import="java.util.ArrayList"%>
<%@page import="net.fast.lbcs.data.entities.user.Location"%>
<%@page import="net.fast.lbcs.data.InMemoryDebugFacade"%>
<%@page import="net.fast.lbcs.data.entities.user.ProductResultSet"%>
<%@page import="net.fast.lbcs.data.entities.user.Product"%>
<%@page import="java.util.List"%>
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
ProductResultSet prs = new ProductResultSet();
List<Product> products= new ArrayList<Product>(InMemoryDebugFacade.getProducts().subList(0, 5));

prs.setProducts(products);
prs.setLocation(new Location(32,73));
//ServiceItemGroupID gid = new ServiceItemGroupID("The id");
//ServiceItemGroup p = new ServiceItemGroup(gid, "Name 1", "Description 1");
boolean value=true;
serializer.write(value, response.getWriter());
serializer.write(prs, response.getWriter());

%>