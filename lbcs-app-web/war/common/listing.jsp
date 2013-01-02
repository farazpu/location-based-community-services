<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.web.listing.Listing"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Listing lst = (Listing) request.getAttribute("listing");
	if (lst == null) {
%>
No Record
<%
	} else {
%>
<div class="listing-table">
	<table>
		<thead>
			<tr>
				<%if(lst.isCreateSelectionColumn()) {%>
				<th>&nbsp;</th>
				<%
				}
					List<String> cols = lst.getColumns();
					for(String col : cols) {
				%>
				<th <%=lst.isFocusColumn(col)? "class='focus'" : "" %>><%=col %></th>
				<%
					}
			}
				%>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td colspan="100%">
					<%
					if(lst.isCreateButton()) {
					%>
					<input class="button" type="button" onclick="window.location = 'new_service.jsp'" value="Create" /> 
					<%
					}
					if(lst.isEditButton()) {
					%>
					<input class="button" type="button" value="Edit" /> 
					<%
					}
					if(lst.isDeleteButton()) {
					%>
					<input class="button" type="button" value="Delete" /> 
					<%
					}
					%>
					<span class="message"> 
						<%=lst.getFooterNote() %>
					</span> 
					<span class="page-buttons"> 
						<%						
						if(lst.getCurrentPage() != 0) {
						%> 
						<a href="welcome.jsp?pageNum=<%=lst.getCurrentPage() - 1%>">
							<img alt="Previous Page" src="../images/prev_page.png">
						</a> 
						<%
						}
						
						if(lst.isCanGoNext()) {
						%> 
						<a href="welcome.jsp?pageNum=<%= lst.getCurrentPage() + 1%>">
							<img alt="Next Page" src="../images/next_page.png">
						</a> 
						<%
						}
						%>
					</span>
				</td>
			</tr>
		</tfoot>
		<tbody>
			<%
			List<Object[]> rows = lst.getRows();
			for(Object[] row : rows) {
				out.print("<tr>");
				if(lst.isCreateSelectionColumn()) {
					out.print("<td>");
					out.print("<input type='checkbox' />");
					out.print("</td>");
				}
				int count = 0;
				for(Object value : row) {
					
					out.print("<td " + (lst.isFocusColumn(count)? "class='focus'" : "") + ">");
					out.print(value);
					out.print("</td>");
					count++;
				}
				out.print("</tr>");
			}
			%>
			<%
			if(rows.size()==0) {
				if(lst.getCurrentPage()!=0) {
					%>			
					 <tr>
					 	<td colspan="100%">
							No record on this page.
					 	</td>
					</tr>
					<%
				}
			}
			%>
		</tbody>
	</table>
</div>