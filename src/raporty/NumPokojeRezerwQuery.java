package raporty;

import java.sql.Date;

public class NumPokojeRezerwQuery extends Query {

	private Date dataOd;
	private Date dataDo;
	
	public NumPokojeRezerwQuery() {
		
		dataOd = new Date(0, 0, 0);
		dataDo = new Date(1000, 0, 0);
	}
	
	public NumPokojeRezerwQuery(java.util.Date dataOd, java.util.Date dataDo ) {
		
		this.dataOd = new Date(dataOd.getTime());
		this.dataDo = new Date(dataDo.getTime());
	}
	
	@Override
	public String toString() {
		return parts.get(0) + dataOd.toString() + parts.get(1) + dataDo.toString() + parts.get(2);
	}
	
	@Override
	protected void load() {
		parts.add("select count(*) as 'pokoje_zarezerwowane', rodzaj_pokoju from POKOJE, REZERWACJA_POKOJE left join REZERWACJA on REZERWACJA_POKOJE.nr_rezerwacji=REZERWACJA.nr_rezerwacji where REZERWACJA_POKOJE.nr_pokoju = POKOJE.nr_pokoju and REZERWACJA.data_rozpoczecia < '");
		parts.add("' and REZERWACJA.data_zakonczenia >= '");
		parts.add("' group by rodzaj_pokoju;");
	}
	
	public void setDataOd(java.util.Date dataOd) {
			this.dataOd.setTime(dataOd.getTime());
		}
	
	public void setDataDo(java.util.Date dataDo) {
		this.dataDo.setTime(dataDo.getTime());
	}
	
	public static void main(String[] args) {
		
		NumPokojeRezerwQuery query = new NumPokojeRezerwQuery();
		System.out.println(query);
	}
}