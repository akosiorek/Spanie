package raporty;

public class RodzajUslugiQuery extends Query {

	@Override
	public String toString() {
		return parts.get(0);
	}

	@Override
	protected void load() {
		parts.add("select nazwa_uslugi from USLUGA_DODATKOWA;");

	}

}
