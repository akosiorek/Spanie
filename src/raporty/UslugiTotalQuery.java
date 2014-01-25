package raporty;

import java.sql.Date;

public class UslugiTotalQuery extends Query {
	
	public static final String NAZWA_USLUGI = "nazwa_uslugi";
	public static final String ILOSC_USLUGI = "ilosc_uslugi_zamowionej_ogolem";
	
	Date dataOd;
	
	public UslugiTotalQuery() {
		
		dataOd = new Date(new java.util.Date().getTime());
		setSort(NAZWA_USLUGI, true);
	}

	@Override
	public String toString() {
		return parts.get(0) + dataOd.toString() + parts.get(1) + dataOd.toString() + parts.get(2) + sortBy + ";";
	}

	@Override
	protected void load() {
		parts.add("select count(*) as 'ilosc_uslugi_zamowionej_ogolem', POBYT_USLUGA_DODATKOWA.nazwa_uslugi from POBYT_USLUGA_DODATKOWA left join POBYT on POBYT_USLUGA_DODATKOWA.nr_pobytu=POBYT.nr_pobytu join Klient on POBYT.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta where POBYT_USLUGA_DODATKOWA.data_rozpoczecia<='");
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_zakonczenia>='");
		parts.add("' group by POBYT_USLUGA_DODATKOWA.nazwa_uslugi");

	}
	
	public void setOd(java.util.Date dataOd) {
		this.dataOd.setTime(dataOd.getTime());
	}

	public static void main(String[] args) {
		
		System.out.println(new UslugiTotalQuery().toString());
	}

}