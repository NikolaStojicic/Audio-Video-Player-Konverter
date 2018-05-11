package SistemskeOperacije;

import java.io.File;

import javax.swing.JOptionPane;

import avgui.kontroler.GUIKontroler;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

/**
 * Klasa koja predstavlja izdvojenu klasu sa sistemsku operaciju konvertovanja u .wav format!
 */

public class SOConvertToWav {
	
	/**
	 * 
	 * Metoda koja konvertuje fajl u .wav format
	 * 
	 * @param open predstavlja promenjivu tipa File koja oznacava Fajl koji ce se kasnije ili reprodukovati ili 
	 * koji ce se konvertovati u neki drugi format! 
	 * @param save predstavlja promenjivu tipa String koja oznacava putanju do mesta 
	 * na koje se konvertovani fajl treba sacuvati
	 * @throws IllegalArgumentException metoda baca IllegalArgumentException ukoliko je u metodu "encode" kao parametar prosledjen 
	 * nepodrzani format
	 * @throws InputFormatException metoda baca InputFormatException ukoliko je u metodu "encode" kao parametar prosledjen 
	 * nepodrzani format
	 * @throws EncoderException metoda baca EncoderException ukoliko metoda "encode" koja se izvrsava nad objektom
	 * tipa Encoder nije u mogucnosti da se izvrsi.
	 */

	public static void izvrsi(File open, String savePath)
			throws IllegalArgumentException, InputFormatException, EncoderException {
		
		System.out.println("TEST");
		File target = new File(savePath);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(128000));
		audio.setChannels(new Integer(2));
		audio.setSamplingRate(new Integer(44100));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("wav");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(open, target, attrs);
		GUIKontroler.animate(false);
		JOptionPane.showMessageDialog(null, "Fajl " + open.getName() + " uspesno konvertovan u .wav!", "Obavestenje",
				JOptionPane.INFORMATION_MESSAGE);

	}

}
