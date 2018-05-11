package avgui.kontroler;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import SistemskeOperacije.SOConvertToAvi;
import SistemskeOperacije.SOConvertToMp3;
import SistemskeOperacije.SOConvertToMp4;
import SistemskeOperacije.SOConvertToWav;
import avgui.AVGUIMainWindow;
import it.sauronsoftware.jave.EncoderException;

public class GUIKontroler {

	public static AVGUIMainWindow mainWindow;
	public static LinkedList<JLabel> formats = new LinkedList<JLabel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					mainWindow = new AVGUIMainWindow();
					mainWindow.setVisible(true);
					for (JLabel format : formats)
						setRadioButtonListener(format, formats);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 
	 * metoda koja proverava kog je formata uneti fajl.
	 * 
	 * @param path predstavlja String koji oznacava putanju do unetog fajla
	 * @return String sa imenom formata!
	 */
	
	public static String getFormat(String path) {
		
		if (path.endsWith(".mp4")) return "mp4";
		if (path.endsWith(".avi")) return "avi";
		if (path.endsWith(".mp3")) return "mp3";
		if (path.endsWith(".wav")) return "wav";
		
		
		return null;		
	}
	
	/**
	 * Metoda koja kreira i prikazuje prozor sa odredjenom informacionom porukom
	 * @param poruka predstavlja String koji predjstavlja poruku koja ce se prikazati u prozoru
	 */
	
	public static void prikaziPoruku(String poruka) {
		JOptionPane.showMessageDialog(null,  poruka, "Obavestenje",
				JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Metoda koja kreira i prikazuje prozor sa odredjenom porukom koji upozorava na gresku
	 * @param poruka predstavlja String koji predjstavlja poruku koja ce se prikazati u prozoru
	 */
	
	public static void prikaziPorukuGreska(String poruka) {
		JOptionPane.showMessageDialog(null,  poruka, "Greska",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Metoda koja kreira i prikazuje Save prozor 
	 * putem kojeg se odredjuje SavePath (adresa na koju ce se konvertovani fajl sacuvati)
	 */
	
	public static void saveDialog() {
		
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		
			File file = fc.getSelectedFile();
			mainWindow.setSavePath(file.getPath());
			System.out.println(mainWindow.getSavePath());
		}
			
					
	}
	
	/**
	 * Metoda koja kreira i prikazuje Open prozor 
	 * putem kojeg se odredjuje parametar Open (atribut glavnog prozora, predstavlja fajl koji ce se kasnije reprodukovati
	 * ili konvertovati )
	 * @param textPane predstavlja textPane iz glavnog prozora, u koji se ispisuje ime fajla koji je ucitan
	 */
	
	public static void showOpenDialog(JTextPane textPane) {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (getFormat(fc.getSelectedFile().getName()) == null) {
					JOptionPane.showMessageDialog(null,
							"Nepodrzani format fajla koji ste izabrali",
							"Greska",
							JOptionPane.ERROR_MESSAGE);
							return;
				}
				File file = fc.getSelectedFile();
				mainWindow.setOpen(file);
				textPane.setText(fc.getSelectedFile().getName());
				
			}	
		} catch (Exception e1) {
			
		}
	}

	public static boolean togglePlay(JLabel component) {
		String selectedImage = GUIKontroler.guiGetName(component);
		selectedImage = selectedImage.split("_")[1];
		if (selectedImage.equals("1")) {
			GUIKontroler.setGuiComponentImage(component, "playButton_2_down.png");
			return true;
		} else {
			GUIKontroler.setGuiComponentImage(component, "playButton_1_down.png");
			return false;
		}
	}

	public static void setRadioButtonListener(JLabel component, LinkedList<JLabel> formats) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GUIKontroler.radioButtons(component, formats);
			}
		});
	}

	public static void setGuiComponentListener(JLabel component) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				guiComponentPressed(component);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				guiComponentPressed(component);
			}
		});
	}

	public static void setGuiComponentImage(JLabel component, String image) {
		setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
	}

	public static void setGuiComponentImage(JLabel component, String image, int x, int y) {
		Icon icon = new ImageIcon(new javax.swing.ImageIcon(AVGUIMainWindow.class.getResource("/res/gui/" + image))
				.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
		component.setIcon(icon);
		component.setText(image);
	}

	public static void guiComponentInitializer(JLabel component, String image) {
		setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
	}

	public static void guiComponentInitializer(JLabel component, String image, int x, int y) {
		component.setFont(new java.awt.Font("Tahoma", 1, 0));
		setGuiComponentImage(component, image, x, y);
		component.setSize(x, y);
	}

	public static void guiButtonComponentInitializer(JLabel component, String image) {
		guiComponentInitializer(component, image, component.getWidth(), component.getHeight());
		setGuiComponentListener(component);
	}

	public static void guiButtonComponentInitializer(JLabel component, String image, int x, int y) {
		guiComponentInitializer(component, image, x, y);
		setGuiComponentListener(component);
	}

	public static String radioButtons(JLabel component, LinkedList<JLabel> formats) {
		for (Iterator iterator = formats.iterator(); iterator.hasNext();) {
			JLabel jLabel = (JLabel) iterator.next();
			if (jLabel.getText().endsWith("down.png"))
				GUIKontroler.setGuiComponentImage(jLabel, jLabel.getText().split("down.png")[0] + "up.png");
		}
		if (component.getText().endsWith("up.png"))
			GUIKontroler.setGuiComponentImage(component, component.getText().split("up.png")[0] + "down.png");
		System.out.println(component.getText().split("_")[0]);
		return component.getText().split("_")[0];
	}
	

	public static String guiGetName(JLabel component) {
		return component.getText().split("_up.")[0];
	}

	public static void guiComponentPressed(JLabel component) {
		String nameOfComponent = guiGetName(component);
		if (!nameOfComponent.endsWith("png")) {
			String image = nameOfComponent + "_down.png";
			setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
		} else {
			nameOfComponent = component.getText().split("_down.")[0];
			String image = nameOfComponent + "_up.png";
			setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
		}

	}
	
	/**
	 * metoda koja predstavlja izvucenu funkcionalnost za dugne "Convert"!
	 */
	
	public static void Convert() {
		
		
		if (mainWindow.getOpen() == null) {
			prikaziPorukuGreska("Niste odabrali fajl koji zelite da konvertujete!");
			return;
		}

		if (getSelctedButton() == null) {
			prikaziPorukuGreska("Niste odabrali format u koji zelite da konvertujete!");
			return;
		}

		if (getSelctedButton().equals("mp3")) {

			if (getFormat(mainWindow.getOpen().getPath()) == "mp3") {
				prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
				return;
			}

			try {
				saveDialog();
				SOConvertToMp3.izvrsi(mainWindow.getOpen(), mainWindow.getSavePath() + ".mp3");
			} catch (IllegalArgumentException | EncoderException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

		if (getSelctedButton().equals("wav")) {
			if (getFormat(mainWindow.getOpen().getPath()) == "waw") {
				prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
				return;
			}
			try {
				saveDialog();
				SOConvertToWav.izvrsi(mainWindow.getOpen(), mainWindow.getSavePath() + ".wav");
			} catch (IllegalArgumentException | EncoderException e1) {

				e1.printStackTrace();
			}

		}

		if (getSelctedButton().equals("avi")) {

			if (getFormat(mainWindow.getOpen().getPath()) == "mp3"
					|| getFormat(mainWindow.getOpen().getPath()) == "waw") {
				prikaziPorukuGreska("nije moguce konvertovati iz audio u video formate!");
				return;
			}
			if (getFormat(mainWindow.getOpen().getPath()) == "avi") {
				prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
				return;
			}

			try {
				saveDialog();
				SOConvertToAvi.izvrsi(mainWindow.getOpen(), mainWindow.getSavePath() + ".avi");
			} catch (IllegalArgumentException | EncoderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (getSelctedButton().equals("mp4")) {
			if (getFormat(mainWindow.getOpen().getPath()) == "mp3"
					|| getFormat(mainWindow.getOpen().getPath()) == "waw") {
				prikaziPoruku("nije moguce konvertovati iz audio u video formate!");
				return;
			}
			if (getFormat(mainWindow.getOpen().getPath()) == "mp4") {
				prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
				return;
			}

			try {
				saveDialog();
				SOConvertToMp4.izvrsi(mainWindow.getOpen(), mainWindow.getSavePath() + ".mp4");
			} catch (IllegalArgumentException | EncoderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	/**
	 * Metoda koja pokazuje koje Radio dugme je selektovano
	 * @return String sa imenom selektovanog dugmeta
	 */
	
	public static String getSelctedButton() {
		for (JLabel jLabel : formats) {
			if (jLabel.getText().endsWith("down.png")) {
				return jLabel.getText().split("_")[0];
			}
		}
		return null;
	}
	
	
}
