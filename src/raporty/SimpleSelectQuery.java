package raporty;

public class SimpleSelectQuery extends Query {

	private String tabela;
	private String kolumna;
	
	public SimpleSelectQuery() {
		
		tabela = null;
		kolumna = null;
	}
	
	public SimpleSelectQuery(String tabela, String kolumna) {
		super();
		this.tabela = tabela;
		this.kolumna = kolumna;
	}

	@Override
	public String toString() {
		return parts.get(0) + kolumna + parts.get(1) + tabela + parts.get(2);
	}

	@Override
	protected void load() {

		parts.add("select distinct ");
		parts.add(" from ");
		parts.add(";");
	}

	public String getTabla() {
		return tabela;
	}

	public void setTabla(String tabla) {
		this.tabela = tabla;
	}

	public String getKolumna() {
		return kolumna;
	}

	public void setKolumna(String kolumna) {
		this.kolumna = kolumna;
	}
}
