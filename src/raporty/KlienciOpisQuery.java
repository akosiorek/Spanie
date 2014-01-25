package raporty;

import java.sql.Date;

public class KlienciOpisQuery extends Query {

	
	private Date data;
	
	public static final String NAZWISKO = "nazwisko";
	public static final String OPIS = "opis";
	
	public KlienciOpisQuery() {
		
		data = new Date(new java.util.Date().getTime());
		setSort(NAZWISKO, true);
	}
	
	@Override
	public String toString() {
		return parts.get(0) + data.toString() + parts.get(1) + data.toString() + parts.get(2) + sortBy + ";";
	}

	@Override
	protected void load() {
		parts.add("select distinct Klient.nazwisko,  DLUZNICY.opis from HISTORIA_POBYTOW left join Klient on HISTORIA_POBYTOW.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta join DLUZNICY on Klient.nr_dokumentu_klienta=DLUZNICY.nr_dokumentu_klienta where HISTORIA_POBYTOW.data_rozpoczecia<='");
		parts.add("' and HISTORIA_POBYTOW.data_zakonczenia>='");
		parts.add("' ");
	}
	
	public void setDate(java.util.Date data) {
		this.data.setTime(data.getTime());
	}
	
	public static void main(String[] args) {

System.out.println(new KlienciOpisQuery().toString());
	}
}
	