package raporty;

import java.util.ArrayList;
import java.util.List;

public abstract class Query {

	protected List<String> parts;
	protected String sortBy;
	private String sort;
	private boolean sortOrder;
	protected static final String ASC = "asc";
	protected static final String DESC = "desc";
	
	
	public Query() {
		
		setSort(null);
		parts = new ArrayList<String>();		
		load();		
	}
	
	public abstract String toString();
	protected abstract void load();
	
public void setSort(String sortBy) {
		
		this.sort = sortBy;
		this.sortOrder = true;
		this.sortBy = sortBy == null ? "" : " order by " + sortBy;
	}
	
	public void setSort(String sortBy, boolean order) {
		
		this.sort = sortBy;
		this.sortOrder = order;
		this.sortBy = " order by " + sortBy + " " + (sortOrder ? ASC : DESC);
	}
	
	public boolean getSortOrder() {
		return sortOrder;
	}
	
	public String getSort() {
		return sort;
	}
	
	public boolean isSortedBy(String str) {
		return str.equals(sort);
	}
	
}
