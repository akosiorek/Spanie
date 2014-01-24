package raporty;

import java.sql.Date;

public class UslugiQuery extends Query {

	private Date dataOd;
	private Date dataDo;
	private String typ;
	
	
	public static final String NAZWA = "NAZWA";
	public static final String KLIENT = "KLIENT";
	
	public static final String  NAZWA_USLUGI = "nazwa_uslugi";
	public static final String  NR_POBYTU = "nr_pobytu";
	public static final String  NAZWISKO = "nazwisko";
	public static final String  ILOSC_USLUGI = "ilość_usługi";
	
	public UslugiQuery() {
		
		dataOd = new Date(0, 0, 0);
		dataDo = new Date(1000, 0, 0);
		typ = KLIENT;
		setSort(NAZWA_USLUGI, true);
	}
	
	public UslugiQuery(java.util.Date dataOd, java.util.Date dataDo ) {
		
		this.dataOd = new Date(dataOd.getTime());
		this.dataDo = new Date(dataDo.getTime());
	}
	
	@Override
	public String toString() {
		if(typ.equals(KLIENT))
			return parts.get(0) + dataOd.toString() + parts.get(1) + dataDo.toString()
				+ parts.get(2) + sortBy + ";";
		else
			return parts.get(3) + dataOd.toString() + parts.get(4) + dataDo.toString()
				+ parts.get(5) + sortBy + ";";
	}
	
	@Override
	protected void load() {
		
		parts.add("select POBYT_USLUGA_DODATKOWA.nazwa_uslugi, POBYT.nr_pobytu, Klient.nazwisko, count(*) as 'ilość_usługi' from POBYT_USLUGA_DODATKOWA left join POBYT on POBYT_USLUGA_DODATKOWA.nr_pobytu=POBYT.nr_pobytu join Klient on POBYT.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta where Klient.nazwisko='Koziolek' and POBYT_USLUGA_DODATKOWA.data_rozpoczecia<='");
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_zakonczenia>='");
		parts.add("' group by POBYT_USLUGA_DODATKOWA.nazwa_uslugi,POBYT.nr_pobytu");
		
		parts.add("select POBYT_USLUGA_DODATKOWA.nazwa_uslugi, POBYT.nr_pobytu, Klient.nazwisko, count(*) as 'ilość_usługi' from POBYT_USLUGA_DODATKOWA left join POBYT on POBYT_USLUGA_DODATKOWA.nr_pobytu=POBYT.nr_pobytu join Klient on POBYT.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta where POBYT_USLUGA_DODATKOWA.nazwa_uslugi='obiad' and POBYT_USLUGA_DODATKOWA.data_rozpoczecia<='");
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_zakonczenia>='");
		parts.add("' group by POBYT_USLUGA_DODATKOWA.nazwa_uslugi,POBYT.nr_pobytu");
	}
	
	public void setOd(java.util.Date dataOd) {
			this.dataOd.setTime(dataOd.getTime());
		}
	
	public void setDo(java.util.Date dataDo) {
		this.dataDo.setTime(dataDo.getTime());
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

