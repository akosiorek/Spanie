package raporty;

import java.sql.Date;

public class UslugiQuery extends Query {

	private Date dataOd;
	private String typ;
	private String usluga;
	
	
	public static final String USLUGA = "NAZWA";
	public static final String KLIENT = "KLIENT";
	
	public static final String  NAZWA_USLUGI = "nazwa_uslugi";
	public static final String  NR_POBYTU = "nr_pobytu";
	public static final String  NAZWISKO = "nazwisko";
	public static final String  ILOSC_USLUGI = "ilosc_uslugi";
	
	public UslugiQuery() {
		
		dataOd = new Date(new java.util.Date().getTime());
		typ = KLIENT;
		setSort(NAZWA_USLUGI, true);
		usluga = new RodzajeUslug().getUslugi().get(0);
	}
	
	@Override
	public String toString() {
		if(typ.equals(KLIENT))
			return parts.get(0) + dataOd.toString() + parts.get(1) + dataOd.toString()
				+ parts.get(2) + sortBy + ";";
		else
			return parts.get(3) + usluga + parts.get(4) + dataOd.toString() + parts.get(5) + dataOd.toString()
				+ parts.get(6) + sortBy + ";";
	}
	
	public void setUsluga(String usluga) {
		this.usluga = usluga;
	}

	@Override
	protected void load() {
		
		parts.add("select POBYT_USLUGA_DODATKOWA.nazwa_uslugi, POBYT.nr_pobytu, Klient.nazwisko, count(*) as 'ilosc_uslugi' from POBYT_USLUGA_DODATKOWA left join POBYT on POBYT_USLUGA_DODATKOWA.nr_pobytu=POBYT.nr_pobytu join Klient on POBYT.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta where Klient.nazwisko='Koziolek' and POBYT_USLUGA_DODATKOWA.data_rozpoczecia<='");
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_zakonczenia>='");
		parts.add("' group by POBYT_USLUGA_DODATKOWA.nazwa_uslugi,POBYT.nr_pobytu");
		
		parts.add("select POBYT_USLUGA_DODATKOWA.nazwa_uslugi, POBYT.nr_pobytu, Klient.nazwisko, count(*) as 'ilosc_uslugi' from POBYT_USLUGA_DODATKOWA left join POBYT on POBYT_USLUGA_DODATKOWA.nr_pobytu=POBYT.nr_pobytu join Klient on POBYT.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta where POBYT_USLUGA_DODATKOWA.nazwa_uslugi='");
		
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_rozpoczecia<='");
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_zakonczenia>='");
		parts.add("' group by POBYT_USLUGA_DODATKOWA.nazwa_uslugi,POBYT.nr_pobytu");
	}
	
	public void setOd(java.util.Date dataOd) {
			this.dataOd.setTime(dataOd.getTime());
		}	
	
	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public static void main(String[] args) {
		
		UslugiQuery query = new UslugiQuery();
		System.out.println(query);
	}
}

