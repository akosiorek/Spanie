package raporty;

import java.sql.Date;

public class KlienciQuery extends Query {

	
	private Date data;
	
	public static final String NAZWISKO = "nazwisko";
	public static final String NUMER_DOKUMENTU = "nr_dokumentu_klienta";
	public static final String NR_POBYTU = "nr_pobytu";
	public static final String OD = "od";
	public static final String DO = "do";
	public static final String DLUZNIK = "dluznik";
	public static final String DEFAULT = "HISTORIA_POBYTOW.data_rozpoczecia, HISTORIA_POBYTOW.data_zakonczenia, Klient.nazwisko";
	
	public KlienciQuery() {
		
		data = new Date(new java.util.Date().getTime());
		setSort("HISTORIA_POBYTOW.data_rozpoczecia, HISTORIA_POBYTOW.data_zakonczenia, Klient.nazwisko", true);
	}
	
	@Override
	public String toString() {
		return parts.get(0) + data.toString() + parts.get(1) + data.toString() + parts.get(2) + sortBy + ";";
	}

	@Override
	protected void load() {
		parts.add("select Klient.nazwisko, HISTORIA_POBYTOW.nr_dokumentu_klienta, HISTORIA_POBYTOW.nr_pobytu, HISTORIA_POBYTOW.data_rozpoczecia as 'od', HISTORIA_POBYTOW.data_zakonczenia as 'do', if(exists(select * from DLUZNICY where Klient.nr_dokumentu_klienta = DLUZNICY.nr_dokumentu_klienta),'tak','nie') as 'dluznik' from HISTORIA_POBYTOW left join Klient on HISTORIA_POBYTOW.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta left join DLUZNICY on Klient.nr_dokumentu_klienta=DLUZNICY.nr_dokumentu_klienta where HISTORIA_POBYTOW.data_rozpoczecia<='");
		parts.add("' and HISTORIA_POBYTOW.data_zakonczenia>='");
		parts.add("' ");
	}
	
	public void setDate(java.util.Date data) {
		this.data.setTime(data.getTime());
	}
	
	public static void main(String[] args) {

System.out.println(new KlienciQuery().toString());
	}
}

		
	