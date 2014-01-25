package raporty;

import java.sql.Date;

public class ListaPokoiTypQuery extends Query {
	
	private java.sql.Date data;
	
	private boolean szczegol;
	
	public static final String NR_POKOJU = "nr_pokoju";
	public static final String STAN_POKOJU = "stan_pokoju";
	public static final String TYP_POKOJU = "rodzaj_pokoju";
	public static final String DATA_ROZP = "od";
	public static final String DATA_ZAK = "do";
	
	private static final String pokojeData = ", POBYT.data_rozpoczecia as 'od', POBYT.data_zakonczenia as 'do' ";
	private static final String rezerwacjeData = ", REZERWACJA.data_rozpoczecia as 'od', REZERWACJA.data_zakonczenia as 'do' ";
	private static final String wolneData = ", 'wolne' as 'od', 'wolne' as 'do'";
	
	public ListaPokoiTypQuery() {
		
		data = new Date(new java.util.Date().getTime());
		szczegol = false;
		setSort(NR_POKOJU);
	}
	
	@Override
	public String toString() {
		
		if(szczegol) {
			return parts.get(0) + pokojeData + parts.get(1) + data.toString() + parts.get(2) + data.toString()
					+ parts.get(3) + rezerwacjeData + parts.get(4) + data.toString() + parts.get(5) + data.toString() 
					+ parts.get(6) + wolneData + parts.get(7) + data.toString() + parts.get(8) + data.toString()
					+ parts.get(9) + data.toString() + parts.get(10) + data.toString() + parts.get(11)
					+ sortBy + ";"; 
		} else {
			return parts.get(0) + parts.get(1) + data.toString() + parts.get(2) + data.toString()
					+ parts.get(3) + parts.get(4) + data.toString() + parts.get(5) + data.toString() 
					+ parts.get(6) + parts.get(7) + data.toString() + parts.get(8) + data.toString()
					+ parts.get(9) + data.toString() + parts.get(10) + data.toString() + parts.get(11)
					+ sortBy + ";"; 
		}
	}

	@Override
	protected void load() {
		parts.add("select POBYT_POKOJE.nr_pokoju, 'zajety' as stan_pokoju, POKOJE.rodzaj_pokoju ");
		parts.add("from POKOJE,POBYT_POKOJE left join POBYT on POBYT_POKOJE.nr_pobytu=POBYT.nr_pobytu where POBYT_POKOJE.nr_pokoju = POKOJE.nr_pokoju and POBYT.data_rozpoczecia < '");
		parts.add("' and POBYT.data_zakonczenia >= '");
		parts.add("' UNION select REZERWACJA_POKOJE.nr_pokoju, 'rezerwacja' as stan_pokoju, POKOJE.rodzaj_pokoju ");				
		parts.add("from POKOJE, REZERWACJA_POKOJE left join REZERWACJA on REZERWACJA_POKOJE.nr_rezerwacji=REZERWACJA.nr_rezerwacji where REZERWACJA_POKOJE.nr_pokoju = POKOJE.nr_pokoju and REZERWACJA.data_rozpoczecia < '");
		parts.add("' and REZERWACJA.data_zakonczenia >= '");
		
		
		parts.add("' UNION select POKOJE.nr_pokoju, 'wolny' as stan_pokoju, POKOJE.rodzaj_pokoju ");				
		parts.add("from POKOJE where POKOJE.nr_pokoju not in(select POKOJE.nr_pokoju from POKOJE, REZERWACJA_POKOJE left join REZERWACJA on REZERWACJA_POKOJE.nr_rezerwacji=REZERWACJA.nr_rezerwacji where REZERWACJA_POKOJE.nr_pokoju = POKOJE.nr_pokoju and REZERWACJA.data_rozpoczecia < '");

		parts.add("' and REZERWACJA.data_zakonczenia >= '");
		parts.add("') and POKOJE.nr_pokoju not in(select POBYT_POKOJE.nr_pokoju from POKOJE,POBYT_POKOJE left join POBYT on POBYT_POKOJE.nr_pobytu=POBYT.nr_pobytu where POBYT_POKOJE.nr_pokoju = POKOJE.nr_pokoju and POBYT.data_rozpoczecia < '");
		parts.add("' and POBYT.data_zakonczenia >= '");
		parts.add("')");
	}
	
	public void setData(java.util.Date data) {
		this.data = new Date(data.getTime());		
	}
	
	public void setSzczegol(boolean szczegol) {
		this.szczegol = szczegol;
	}
	
	public boolean getSzczegol() {
		return this.szczegol;
	}
	
	public static void main(String[] args) {
		
		ListaPokoiTypQuery query = new ListaPokoiTypQuery();
		query.setSort(query.STAN_POKOJU, false);
		System.out.println(query);
	}

}
