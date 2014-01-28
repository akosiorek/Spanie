package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.dbDAO;

public class PobytManager {
	
	private DaneKlientaFrame daneKlientaFrame = new DaneKlientaFrame();
	private RezerwacjeKlientaFrame rezerwacjeKlientaFrame = new RezerwacjeKlientaFrame();
	private PokojeFrame pokojeFrame = new PokojeFrame();
	private PobytPanel pobytPanel = new PobytPanel();
	private RezerwacjaPanel rezerwacjaPanel = new RezerwacjaPanel();
	private UslugaDodatkowaFrame uslugaDodatkowaFrame = new UslugaDodatkowaFrame();
	
	
	private String imie= "";
	private String nazwisko= "";
	private String rodzajDokumentu= "";
	private String numerDokumentu= "";
	private String typPokoju= "";
	private String nrPokoju = "";
	private String nrPobytu = "";
	private String cenaPobytu = "0";

	// TODO zrobić dodawanie ceny
	private String cenaPokoju = "15"; 
	private Date dataOd= new Date();
	private Date dataDo= new Date();
	private String nrRezerwacji= "";
	private List<String> uslugiDodatkowe = new ArrayList<String>();
	private List<String> cenyUslugDodatkowych = new ArrayList<String>();

	private dbDAO db = new dbDAO();
	private MainWindow parent;
	
	public static final String POBYT_PANEL = "POBYT_PANEL";
	public static final String REZERWACJA_PANEL = "REZERWACJA_PANEL";
	
	private final static String najNumerPobytuQuery = "select nr_pobytu + 1 from POBYT where nr_pobytu >= ALL(select nr_pobytu from POBYT);";
	private final static String najNumerRezerwacjiQuery = "select nr_rezerwacji + 1 from REZERWACJA where nr_rezerwacji >= ALL(select nr_rezerwacji from REZERWACJA);";
	
	public void setParent(MainWindow parent) {
		
		this.parent = parent;
		parent.widokPanel.addPanel(pobytPanel, POBYT_PANEL);
		parent.widokPanel.addPanel(rezerwacjaPanel, REZERWACJA_PANEL);
	}
	
	public PobytManager() {
		
		daneKlientaFrame.setParent(this);
		rezerwacjeKlientaFrame.setParent(this);
		pokojeFrame.setParent(this);
		pobytPanel.setParent(this);	
		rezerwacjaPanel.setParent(this);	
		
		
		uslugaDodatkowaFrame.setParent(this);
		state = Stan.NOWA_REZERWACJA;
	}
	
	

	public List<String> getUslugiDodatkowe() {
		return uslugiDodatkowe;
	}



	public void setUslugiDodatkowe(List<String> uslugiDodatkowe) {
		this.uslugiDodatkowe = uslugiDodatkowe;
	}



	public List<String> getCenyUslugDodatkowych() {
		return cenyUslugDodatkowych;
	}



	public void setCenyUslugDodatkowych(List<String> cenyUslugDodatkowych) {
		this.cenyUslugDodatkowych = cenyUslugDodatkowych;
	}



	public String getNrRezerwacji() {
		return nrRezerwacji;
	}

	public void setNrRezerwacji(String nrRezerwacji) {
		this.nrRezerwacji = nrRezerwacji;
	}

	public String getTypPokoju() {
		return typPokoju;
	}

	public void setTypPokoju(String typPokoju) {
		this.typPokoju = typPokoju;
	}
	
	public String getCenaPobytu() {
		return cenaPobytu;
	}

	public void setCenaPobytu(String cenaPobytu) {
		this.cenaPobytu = cenaPobytu;
	}

	public enum Stan {
		
		NOWA_REZERWACJA,
		ISTNIEJACA_REZERWACJA,
		NOWY_POBYT,
		ISTNIEJACY_POBYT;
	}
	
	public enum Okno {
		
		DANE_KLIENTA,
		REZERWACJA_KLIENTA,
		POKOJE,
		REZERWACJA,
		POBYT,
		USLUGA_DODATKOWA;
	}
	
	Okno okno;
	
	private Stan state;

	public Stan getState() {
		return state;		
	}

	public void setState(Stan state) {
		this.state = state;
		daneKlientaFrame.setVisible(true);
		this.okno = Okno.DANE_KLIENTA;
	}
	
	public void show(Okno okno) {
		
		switch(this.okno) {
		
		case DANE_KLIENTA: daneKlientaFrame.setVisible(false); break;
		case REZERWACJA_KLIENTA: rezerwacjeKlientaFrame.setVisible(false); break;
		case POKOJE: pokojeFrame.setVisible(false); break;
		case REZERWACJA: break;
		case POBYT: break;
		case USLUGA_DODATKOWA: uslugaDodatkowaFrame.setVisible(false); break;
		}
		
		this.okno = okno;
		
		switch(this.okno) {
		
		case DANE_KLIENTA: daneKlientaFrame.setVisible(true); break;
		case REZERWACJA_KLIENTA: rezerwacjeKlientaFrame.setVisible(true); break;
		case POKOJE: pokojeFrame.setVisible(true); break;
		case REZERWACJA: rezerwacjaPanel.odswiez(); parent.widokPanel.wyswietl(REZERWACJA_PANEL);break;
		case POBYT: pobytPanel.odswiez(); parent.widokPanel.wyswietl(POBYT_PANEL); break;
		case USLUGA_DODATKOWA: uslugaDodatkowaFrame.setVisible(true); break;
		}
	}
	
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getRodzajDokumentu() {
		return rodzajDokumentu;
	}

	public void setRodzajDokumentu(String rodzajDokumentu) {
		this.rodzajDokumentu = rodzajDokumentu;
	}

	public String getNumerDokumentu() {
		return numerDokumentu;
	}

	public void setNumerDokumentu(String numerDokumentu) {
		this.numerDokumentu = numerDokumentu;
	}

	public Date getDataOd() {
		return dataOd;
	}

	public void setDataOd(Date dataOd) {
		this.dataOd = dataOd;
	}

	public Date getDataDo() {
		return dataDo;
	}

	public void setDataDo(Date dataDo) {
		this.dataDo = dataDo;
	}
	
	public String getNrPokoju() {
		return nrPokoju;
	}

	public void setNrPokoju(String nrPokoju) {
		this.nrPokoju = nrPokoju;
	}	
	
	public String getCenaPokoju() {
		return cenaPokoju;
	}

	public void setCenaPokoju(String cenaPokoju) {
		this.cenaPokoju = cenaPokoju;
	}
	
public void notifyDodanieUslugi(Date dataOd, Date dataDo) {
	
	db.establishConnection();		
	String nowaUslugaDodatkowaQuery = "insert into POBYT_USLUGA_DODATKOWA values ("
			+ nrPobytu + ", '"
			+ uslugiDodatkowe.get(uslugiDodatkowe.size() - 1) + "', '"
			+ Utils.dateToString(dataOd) + "', '"
			+ Utils.dateToString(dataDo) + "', "
			+ cenyUslugDodatkowych.get(cenyUslugDodatkowych.size() - 1) + ");";
	
	if(-1 == db.executeUpdate(nowaUslugaDodatkowaQuery)) {
		
		System.err.println("NowyUslugaDodatkowaQuery failed");
	}
	
	db.closeConnection();
	updateCenaPobytu();
	pobytPanel.notifyDodanieUslugi();
}

	private String genNumer(String query) {
		
		String str = null;
		db.establishConnection();
		ResultSet rs = db.executeQuery(najNumerPobytuQuery);
		try {
			rs.next();
			str = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		return str;
	}


	public void dodajPobyt() {
			
		db.establishConnection();
		nrPobytu = genNumer(najNumerPobytuQuery);
		
		String dataOd = Utils.dateToString(this.dataOd);
		String dataDo = Utils.dateToString(this.dataDo);
		
		String nowyPobytQuery = "insert into POBYT values ("
				+ nrPobytu + ", '"
				+ numerDokumentu + "', '"
				+ dataOd + "', '"
				+ dataDo + "', "
				+ "0);";
		
		String nowyPokojPobytQuery = "insert into POBYT_POKOJE values (" + nrPobytu + ", " + nrPokoju + ", " + cenaPokoju + ");"; 		
		String zajmijPokojQuery = "update POKOJE set stan = 'zajety' where nr_pokoju = " + nrPokoju + ";";		
		
		if(db.executeUpdate(nowyPobytQuery) == -1)
			System.err.println("NowyPobytQuery failed");
		
		if(db.executeUpdate(zajmijPokojQuery) == -1)			
			System.err.println("ZajmijPokojQuery failed");
		
		if(db.executeUpdate(nowyPokojPobytQuery) == -1)			
			System.err.println("NowyPokojPobytQuery failed");			

			db.closeConnection();
	
	}
	
	public void usunPobyt() {
		
		db.establishConnection();
		
		String usunPobytQuery = "delete from POBYT where nr_pobytu = " + nrPobytu + ";";
		String usunUslugeDodatkowaQuery = "delete from POBYT_USLUGA_DODATKOWA where nr_pobytu = " + nrPobytu + " and nazwa_uslugi = '";
		String usunPokojQuery = "delete from POBYT_POKOJE where nr_pobytu = " + nrPobytu + " and nr_pokoju = " + nrPokoju + ";";
		String zwolnijPokojQuery = "update POKOJE set stan = 'wolny' where nr_pokoju = " + nrPokoju + ";";
				
		for(final String nazwa : uslugiDodatkowe) {			
			if(db.executeUpdate(usunUslugeDodatkowaQuery + nazwa + "';") == -1) {
				System.err.println("usunUsluge failed at " + nazwa);
			}
		}
		if(-1 == db.executeUpdate(usunPokojQuery))
			System.err.println("UsunPokojQuery failed");
		
		if(-1 == db.executeUpdate(zwolnijPokojQuery))
			System.err.println("ZwolnijPokojQuery failed");
		
		if(-1 == db.executeUpdate(usunPobytQuery))
			System.err.println("UsunPobytQuery failed");		
	}
	
	public void zakonczPobyt() {
		
		String dodajDoHistoriiQuery = "INSERT INTO HISTORIA_POBYTOW (nr_pobytu, nr_dokumentu_klienta, "
				+ "data_rozpoczecia, data_zakonczenia, nr_pokoju, cena_pokoju) select p.nr_pobytu, "
				+ "nr_dokumentu_klienta, data_rozpoczecia, data_zakonczenia, nr_pokoju, " + cenaPobytu 
				+ "  FROM POBYT p join POBYT_POKOJE pok on p.nr_pobytu = pok.nr_pobytu WHERE p.nr_pobytu = "
				+ nrPobytu + ";";
		
		
		String usunPokojQuery = "delete from POBYT_POKOJE where nr_pobytu = " + nrPobytu + ";";
		String deleteUslugaDodatkowaQuery = "delete from POBYT_USLUGA_DODATKOWA where nr_pobytu = " + nrPobytu + ";";
		String deletePobytQuery = "delete from POBYT where nr_pobytu = " + nrPobytu + ";";
		
		db.establishConnection();
		
		if(-1 == db.executeUpdate(dodajDoHistoriiQuery))
			System.err.println("dodajDoHistoriiQuery failed");
		
		if(-1 == db.executeUpdate(usunPokojQuery))
			System.err.println("usunPokojQuery failed");
		
		if(-1 == db.executeUpdate(deleteUslugaDodatkowaQuery))
			System.err.println("deleteUslugaDodatkowaQuery failed");
		
		if(-1 == db.executeUpdate(deletePobytQuery))
			System.err.println("deletePobytQuery failed");
		
		db.closeConnection();
	}
	
	public void hidePanel() {
		
		parent.widokPanel.wyswietl(WidokPanel.EMPTY);
	}
	
	private void updateCenaPobytu() {
		
		
		String cenaPokojuQuery = "select sum(cena_pokoju*(datediff(data_zakonczenia, data_rozpoczecia) + 1)) from POBYT pob "
				+ "right join POBYT_POKOJE pok on pob.nr_pobytu=pok.nr_pobytu where pob.nr_pobytu = " + nrPobytu + ";";
		
		String cenaUslugQuery = "select sum(cena) from (select (cena*(datediff(data_zakonczenia, data_rozpoczecia) + 1 )) as "
				+ "cena from POBYT_USLUGA_DODATKOWA where nr_pobytu = "
				+ nrPobytu 
				+ " group by nazwa_uslugi,data_zakonczenia,data_rozpoczecia,cena) A;";
		
		db.establishConnection();		
		int cena1 = 0;
		int cena2 = 0;
		
		try {
			ResultSet rs = db.executeQuery(cenaPokojuQuery);
			rs.next();
			cena1 = rs.getInt(1);
			if(rs.wasNull()) cena1 = 0;
			
			
			rs = db.executeQuery(cenaUslugQuery);
			rs.next();
			cena2 = rs.getInt(1);
			if(rs.wasNull()) cena2 = 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		db.closeConnection();
		setCenaPobytu(Integer.toString(cena1 + cena2));
	}
	
private void updateCenaRezerwacji() {
		
		
		String cenaPokojuQuery = "select sum(cena_pokoju*(datediff(data_zakonczenia, data_rozpoczecia) + 1)) from REZERWACJA pob "
				+ "right join REZERWACJA_POKOJE pok on pob.nr_rezerwacji = pok.nr_rezerwacji where pob.nr_rezerwacji = " + nrRezerwacji + ";";
		
		db.establishConnection();		
		int cena = 0;
		
		try {
			ResultSet rs = db.executeQuery(cenaPokojuQuery);
			rs.next();
			cena = rs.getInt(1);
			if(rs.wasNull()) cena = 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		db.closeConnection();
		setCenaPobytu(Integer.toString(cena));
	}
	
	public boolean znajdzPobyt() {
		
		String nrPobytuQuery = "select nr_pobytu from POBYT where nr_dokumentu_klienta = '" + numerDokumentu + "';";
		
		String pokojQuery = "select nr_pokoju, cena_pokoju from POBYT_POKOJE where nr_pobytu = ";  
		
		db.establishConnection();
		try {
			ResultSet rs =  db.executeQuery(nrPobytuQuery);
			if(!rs.next()) return false;			
			nrPobytu = rs.getString(1);
			pokojQuery += nrPobytu + ";";
			
			
			rs =  db.executeQuery(pokojQuery);
			if(rs.next()) {
				nrPokoju = rs.getString(1);
				cenaPokoju = rs.getString(2);		
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		
		znajdzUslugiDodatkowePobytu();
//		znajdzNumerPokojuPobyt();
		updateCenaPobytu();
		return true;
	}
	
	public boolean znajdzRezerwacje() {
		
		String pokojQuery = "select nr_pokoju, cena_pokoju from REZERWACJA_POKOJE where nr_rezerwacji = " + nrRezerwacji + ";";  
		
		db.establishConnection();
		try {
			ResultSet rs =  db.executeQuery(pokojQuery);
			if(rs.next()) {
				nrPokoju = rs.getString(1);
				cenaPokoju = rs.getString(2);		
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		
		updateCenaRezerwacji();
		return true;
	}
	
	private void znajdzUslugiDodatkowePobytu() {
		
		String znajdzUslugiQuery = "select nazwa_uslugi, cena from POBYT_USLUGA_DODATKOWA where nr_pobytu = " + nrPobytu + ";";
		
		db.establishConnection();
		ResultSet rs = db.executeQuery(znajdzUslugiQuery);
		try {
			while(rs.next()) {
				
				uslugiDodatkowe.add(rs.getString(1));
				cenyUslugDodatkowych.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
	}
	
	public void anulujRezerwacje() {
		
		db.establishConnection();
		
		String usunPobytQuery = "delete from REZERWACJA where nr_rezerwacji = " + nrRezerwacji + ";";
		String usunPokojQuery = "delete from REZERWACJA_POKOJE where nr_rezerwacji = " + nrRezerwacji + " and nr_pokoju = " + nrPokoju + ";";
				
		if(-1 == db.executeUpdate(usunPokojQuery))
			System.err.println("UsunPokojQuery failed");	
		
		if(-1 == db.executeUpdate(usunPobytQuery))
			System.err.println("UsunRezerwacjeQuery failed");		
	}
	
	public void przeksztalcRezerwacjeWPobyt() {
		
		dodajPobyt();
		anulujRezerwacje();
	}
	
	public void dodajRezerwacje() {
		
		nrRezerwacji = genNumer(najNumerRezerwacjiQuery);		
		String nowaRezerwacjaQuery = "insert into REZERWACJA values ("
				+ nrRezerwacji + ", '"
				+ numerDokumentu + "', '"
				+ Utils.dateToString(this.dataOd) + "', '"
				+ Utils.dateToString(this.dataDo) + "', "
				+ "0);";
		
		String nowyPokojRezerwacjaQuery = "insert into REZERWACJA_POKOJE values (" + nrRezerwacji + ", " + nrPokoju + ", " + cenaPokoju + ");"; 		
		
		if(db.executeUpdate(nowaRezerwacjaQuery) == -1)
			System.err.println("NowaRezerwacjatQuery failed");
		
		if(db.executeUpdate(nowyPokojRezerwacjaQuery) == -1)			
			System.err.println("NowyPokojRezerwacjaQuery failed");			

			db.closeConnection();
	}
}
