package raporty;

import java.sql.Date;

public class NumPokojeRezerwQuery extends Query {

	private Date dataOd;
	
	public NumPokojeRezerwQuery() {
		
		dataOd = new Date(new java.util.Date().getTime());
	}
	
	@Override
	public String toString() {
		return parts.get(0) + dataOd.toString() + parts.get(1) + dataOd.toString() + parts.get(2);
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
	
	public static void main(String[] args) {
		
		NumPokojeRezerwQuery query = new NumPokojeRezerwQuery();
		System.out.println(query);
	}
}