package gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PobytManager {
	
	private DaneKlientaFrame daneKlientaFrame = new DaneKlientaFrame();
	private RezerwacjeKlientaFrame rezerwacjeKlientaFrame = new RezerwacjeKlientaFrame();
	private PokojeFrame pokojeFrame = new PokojeFrame();
	private PobytPanel pobytPanel = new PobytPanel();
	private UslugaDodatkowaFrame uslugaDodatkowaFrame = new UslugaDodatkowaFrame();
	
	
	private String imie= "";
	private String nazwisko= "";
	private String rodzajDokumentu= "";
	private String numerDokumentu= "";
	private String typPokoju= "";
	private String nrPokoju = "";
	public String getNrPokoju() {
		return nrPokoju;
	}

	public void setNrPokoju(String nrPokoju) {
		this.nrPokoju = nrPokoju;
	}

	private String cenaPokoju = "";
	
	public String getCenaPokoju() {
		return cenaPokoju;
	}

	public void setCenaPokoju(String cenaPokoju) {
		this.cenaPokoju = cenaPokoju;
	}

	private Date dataOd= new Date();
	private Date dataDo= new Date();
	private String nrRezerwacji= "";
	private List<String> uslugiDodatkowe = new ArrayList<String>();
	private List<String> cenyUslugDodatkowych = new ArrayList<String>();
	
	private MainWindow parent;
	
	public static final String POBYT_PANEL = "POBYT_PANEL";
	
	public void setParent(MainWindow parent) {
		
		this.parent = parent;
		parent.widokPanel.addPanel(pobytPanel, POBYT_PANEL);
	}
	
	public PobytManager() {
		
		daneKlientaFrame.setParent(this);
		rezerwacjeKlientaFrame.setParent(this);
		pokojeFrame.setParent(this);
		pobytPanel.setParent(this);	
		
		
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
		case REZERWACJA: parent.widokPanel.wyswietl(POBYT_PANEL);break;
		case POBYT: parent.widokPanel.wyswietl(POBYT_PANEL); break;
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
	
public void notifyDodanieUslugi() {
		
	pobytPanel.notifyDodanieUslugi();	
	
	
	}
}
