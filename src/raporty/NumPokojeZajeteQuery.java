package raporty;

import java.sql.Date;

public class NumPokojeZajeteQuery extends Query {

	private Date dataOd;
	private Date dataDo;
	
	public NumPokojeZajeteQuery() {
		
		dataOd = new Date(new java.util.Date().getTime());
	}
	
	@Override
	public String toString() {
		return parts.get(0) + dataOd.toString() + parts.get(1) + dataOd.toString() + parts.get(2);
	}

	@Override
	protected void load() {
		parts.add("select count(*) as 'pokoje_zajęte', POKOJE.rodzaj_pokoju from POKOJE,POBYT_POKOJE left join POBYT on POBYT_POKOJE.nr_pobytu=POBYT.nr_pobytu where POBYT_POKOJE.nr_pokoju = POKOJE.nr_pokoju and POBYT.data_rozpoczecia < '");
		parts.add("' and POBYT.data_zakonczenia >= '");
		parts.add("' group by rodzaj_pokoju;");
	}

	public void setDataOd(java.util.Date dataOd) {
			this.dataOd.setTime(dataOd.getTime());
		}
	public static void main(String[] args) {
		
		NumPokojeZajeteQuery query = new NumPokojeZajeteQuery();
		System.out.println(query);
	}
}
