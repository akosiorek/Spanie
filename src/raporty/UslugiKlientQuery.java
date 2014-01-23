package raporty;

import java.sql.Date;

public class UslugiKlientQuery extends Query {

	private Date dataOd;
	private Date dataDo;
	
	public UslugiKlientQuery() {
		
		dataOd = new Date(0, 0, 0);
		dataDo = new Date(1000, 0, 0);
	}
	
	public UslugiKlientQuery(java.util.Date dataOd, java.util.Date dataDo ) {
		
		this.dataOd = new Date(dataOd.getTime());
		this.dataDo = new Date(dataDo.getTime());
	}
	
	@Override
	public String toString() {
		return parts.get(0) + dataOd.toString() + parts.get(1) + dataDo.toString()
				+ parts.get(2);
	}
	
	@Override
	protected void load() {
		parts.add("select POBYT_USLUGA_DODATKOWA.nazwa_uslugi, POBYT.nr_pobytu, Klient.nazwisko, count(*) as 'ilość_usługi' from POBYT_USLUGA_DODATKOWA left join POBYT on POBYT_USLUGA_DODATKOWA.nr_pobytu=POBYT.nr_pobytu join Klient on POBYT.nr_dokumentu_klienta=Klient.nr_dokumentu_klienta where Klient.nazwisko='Koziolek' and POBYT_USLUGA_DODATKOWA.data_rozpoczecia<='");
		parts.add("' and POBYT_USLUGA_DODATKOWA.data_zakonczenia>='");
		parts.add("' group by POBYT_USLUGA_DODATKOWA.nazwa_uslugi,POBYT.nr_pobytu;");
	}
	
	public void setDataOd(java.util.Date dataOd) {
			this.dataOd.setTime(dataOd.getTime());
		}
	
	public void setDataDo(java.util.Date dataDo) {
		this.dataDo.setTime(dataDo.getTime());
	}
	
	public static void main(String[] args) {
		
		UslugiKlientQuery query = new UslugiKlientQuery();
		System.out.println(query);
	}
}

