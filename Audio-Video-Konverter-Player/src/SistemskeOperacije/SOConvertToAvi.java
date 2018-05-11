package SistemskeOperacije;

import java.io.File;

import javax.swing.JOptionPane;

import avgui.kontroler.GUIKontroler;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;

/**
 * Klasa koja predstavlja izdvojenu klasu sa sistemsku operaciju konvertovanja u
 * .Avi format!
 * 
 * @author NikolaKojic997
 */

public class SOConvertToAvi {

	/**
	 * 
	 * Metoda koja konvertuje fajl iz .mp4 formata u .avi format
	 * 
	 * @param open
	 *            predstavlja promenjivu tipa File koja oznacava Fajl koji ce se
	 *            kasnije ili reprodukovati ili koji ce se konvertovati u neki drugi
	 *            format!
	 * @param save
	 *            predstavlja promenjivu tipa String koja oznacava putanju do mesta
	 *            na koje se konvertovani fajl treba sacuvati
	 * @throws IllegalArgumentException
	 *             metoda baca IllegalArgumentException ukoliko je u metodu "encode"
	 *             kao parametar prosledjen nepodrzani format
	 * @throws InputFormatException
	 *             metoda baca InputFormatException ukoliko je u metodu "encode" kao
	 *             parametar prosledjen nepodrzani format
	 * @throws EncoderException
	 *             metoda baca EncoderException ukoliko metoda "encode" koja se
	 *             izvrsava nad objektom tipa Encoder nije u mogucnosti da se
	 *             izvrsi.
	 */

	public static void izvrsi(File open, String save)
			throws IllegalArgumentException, InputFormatException, EncoderException {

		File target = new File(save);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libfaac");
		audio.setBitRate(new Integer(128000));
		audio.setSamplingRate(new Integer(44100));
		audio.setChannels(new Integer(2));
		VideoAttributes video = new VideoAttributes();
		video.setBitRate(new Integer(128000));
		video.setFrameRate(new Integer(15));
		video.setCodec("mpeg4");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp4");
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();
		encoder.encode(open, target, attrs);
		GUIKontroler.animate(false);
		JOptionPane.showMessageDialog(null, "Fajl " + open.getName() + " uspesno konvertovan u .avi!", "Obavestenje",
				JOptionPane.INFORMATION_MESSAGE);

	}

}
