package net.fast.lbcs.web.listing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Listing {
	private List<String> columns = new ArrayList<String>();
	private String footerNote = "";
	private boolean createButton = true;
	private boolean editButton = true;
	private boolean deleteButton = true;
	private int currentPage = 0;
	private boolean canGoNext = true;
	private boolean createSelectionColumn = true;
	private List<Object[]> rows = new ArrayList<Object[]>();
	private Set<String> focusColumns = new HashSet<String>();
	private String title = "";
	private String createClickURL = "";

	public void addRow(Object... row) {
		rows.add(row);
	}
	
	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getFooterNote() {
		return footerNote;
	}

	public void setFooterNote(String footerNote) {
		this.footerNote = footerNote;
	}

	public boolean isCreateButton() {
		return createButton;
	}

	public void setCreateButton(boolean createButton) {
		this.createButton = createButton;
	}

	public boolean isEditButton() {
		return editButton;
	}

	public void setEditButton(boolean editButton) {
		this.editButton = editButton;
	}

	public boolean isDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(boolean deleteButton) {
		this.deleteButton = deleteButton;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isCanGoNext() {
		return canGoNext;
	}

	public void setCanGoNext(boolean canGoNext) {
		this.canGoNext = canGoNext;
	}

	public boolean isCreateSelectionColumn() {
		return createSelectionColumn;
	}

	public void setCreateSelectionColumn(boolean createSelectionColumn) {
		this.createSelectionColumn = createSelectionColumn;
	}

	public List<Object[]> getRows() {
		return rows;
	}

	public void setRows(List<Object[]> rows) {
		this.rows = rows;
	}

	public Set<String> getFocusColumns() {
		return focusColumns;
	}

	public void setFocusColumns(Set<String> focusColumns) {
		this.focusColumns = focusColumns;
	}
	
	public boolean isFocusColumn(String col) {
		return getFocusColumns().contains(col);
	}
	
	public boolean isFocusColumn(int col) {
		return getFocusColumns().contains(getColumns().get(col));
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateClickURL() {
		return createClickURL;
	}

	public void setCreateClickURL(String createClickURL) {
		this.createClickURL = createClickURL;
	}

	public static String popupValue(String caption, String url, String height, String width) {
		return "<a href='#' onclick='loadPopup(" + 
				 "\"" + url + "\", " + 
				 "\"" + height + "\", " + 
				 "\"" + width + "\" " + 
					")'>" +  caption + "</a>";		
	}

	public static String anchorValue(String caption, String url ) {
		return "<a href='" + url + "'>" +  caption + "</a>";		
	}

	public static String selectTag(List<String> options, String selected, String valueChange ) {
		String res = "<select onchange='" + valueChange + "'>";		
		for(String option : options){
			if(option.equals(selected))				
				res+="<option selected='selected'>"+option+"</option>";
			else
			res+="<option>"+option+"</option>";
		}
		res+="</select>";
		return res;
	}
	
	public static String onClickCreateButton(String url, String height, String width) {
		if("".equals(url))
			return "";
		return "onclick='loadPopup(" + 
				 "\"" + url + "\", " + 
				 "\"" + height + "\", " + 
				 "\"" + width + "\" " + 
					")'";
	}
}
