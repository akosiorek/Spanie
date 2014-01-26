package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainWindow {

	 JFrame frmHotelSpanie;
	 ZadniaPanel zadaniaPanel;
	 
	 
	 JPanel zakladkiPanel;
	 JPanel logowaniePanel;
	 JButton btnStronaDomowa;
	 JButton btnRaporty;
	 JButton btnProfil;
	 JLabel lblLogin;
	 JLabel lblHaslo;
	 JTextField txtLogin;
	 JPasswordField pwdHaslo;
	 JButton btnZaloguj;
	 WidokPanel widokPanel;	
	 
	 
	 
	 boolean zalogowany;
	 boolean admin;
	 
	 private final String adminErrorMsg = "Brak uprawnień administratora.";
	 private final String niezalogowanyErrorMsg = "Brak dostępu.\n Proszę się zalogować.";
	
	private static void setNimbusLookAndFeel() {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}	
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
			
		setNimbusLookAndFeel();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmHotelSpanie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	private void zrobHotelSpanie() {
		
		frmHotelSpanie = new JFrame();
		frmHotelSpanie.setResizable(false);
		frmHotelSpanie.setTitle("Hotel Spanie");
		frmHotelSpanie.setBounds(100, 100, 1066, 882);
		frmHotelSpanie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		zrobHotelSpanie();
		frmHotelSpanie.getContentPane().setLayout(null);
		
		zadaniaPanel = new ZadniaPanel(this);
		zadaniaPanel.setBounds(10, 180, 250, 349);
		zadaniaPanel.setBorder(BorderFactory.createTitledBorder("Zadania"));
		
		
		frmHotelSpanie.getContentPane().add(zadaniaPanel);
		
		zakladkiPanel = new JPanel();
		zakladkiPanel.setBounds(41, 13, 370, 136);
		zakladkiPanel.setBorder(BorderFactory.createTitledBorder("Zakładki"));
		frmHotelSpanie.getContentPane().add(zakladkiPanel);
		
		btnStronaDomowa = new JButton("Strona domowa");
		btnStronaDomowa.setBounds(21, 27, 152, 90);
		btnStronaDomowa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				zadaniaPanel.wyswietl(zadaniaPanel.ZWYKLE);
			}
		});
		zakladkiPanel.setLayout(null);
		btnStronaDomowa.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		zakladkiPanel.add(btnStronaDomowa);
		
		logowaniePanel = new JPanel();
		logowaniePanel.setBounds(668, 13, 380, 152);
		logowaniePanel.setBorder(BorderFactory.createTitledBorder("Panel logowania"));
		frmHotelSpanie.getContentPane().add(logowaniePanel);
		logowaniePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("59px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("27px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnProfil = new JButton("Profil");
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sprawdzLogowanie()) {
					
					
				}
					
			}
		});
		btnProfil.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		logowaniePanel.add(btnProfil, "1, 2, 7, 6, fill, fill");
		
		lblLogin = new JLabel("Login:");
		logowaniePanel.add(lblLogin, "11, 2, right, default");
		
		txtLogin = new JTextField();
		txtLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLogin.selectAll();
				
			}
		});
		txtLogin.setText("Login");
		logowaniePanel.add(txtLogin, "13, 2, fill, default");
		txtLogin.setColumns(10);
		
		lblHaslo = new JLabel("Hasło");
		logowaniePanel.add(lblHaslo, "11, 4, right, default");
		
		pwdHaslo = new JPasswordField();
		pwdHaslo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pwdHaslo.selectAll();
			}
		});
		pwdHaslo.setText("Hasło");
		logowaniePanel.add(pwdHaslo, "13, 4, fill, default");
		
		widokPanel = new WidokPanel(this);
		widokPanel.setBounds(266, 180, 782, 572);
		
		btnZaloguj = new JButton("Zaloguj");
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnZaloguj.getText() == "Zaloguj") {
					btnZaloguj.setText("Wyloguj");
					zalogowany = true;
					admin = sprawdzCzyAdmin();
					
				} else {
					btnZaloguj.setText("Zaloguj");
					zalogowany = false;
					admin = false;
					zadaniaPanel.wyswietl(zadaniaPanel.ZWYKLE);
					widokPanel.wyswietl(widokPanel.EMPTY);
					
				}
			}
		});
	
		logowaniePanel.add(btnZaloguj, "13, 5, 1, 4, fill, top");
		
		btnRaporty = new JButton("Raporty");
		btnRaporty.setBounds(185, 27, 152, 90);
		btnRaporty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sprawdzLogowanie()) {
					if(!admin) {
						JOptionPane.showMessageDialog(new JFrame(), adminErrorMsg, "Brak uprawnień!",
						        JOptionPane.ERROR_MESSAGE);
					} else {
						zadaniaPanel.wyswietl(zadaniaPanel.RAPORT);	
					}
				}
			}
		});
		btnRaporty.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		zakladkiPanel.add(btnRaporty);
		frmHotelSpanie.getContentPane().add(widokPanel);	
	}
	
	private boolean sprawdzCzyAdmin() {
		
		return true;
	}
	
	boolean sprawdzLogowanie() {
		if(!zalogowany) {
			JOptionPane.showMessageDialog(new JFrame(), niezalogowanyErrorMsg, "Brak uprawnień!",
			        JOptionPane.ERROR_MESSAGE);		
			return false;
		}
			return true;
	}
}
