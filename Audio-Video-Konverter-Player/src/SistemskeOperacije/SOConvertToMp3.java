package SistemskeOperacije;

import java.io.File;

import javax.swing.JOptionPane;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

public class SOConvertToMp3 {
	
	public static void izvrsi(File open, String savePath) throws IllegalArgumentException, InputFormatException, EncoderException {
		
		
		File target = new File(savePath);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(128000));
		audio.setChannels(new Integer(2));
		audio.setSamplingRate(new Integer(44100));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(open, target, attrs);
		JOptionPane.showMessageDialog(null, "Fajl " + open.getName() + " uspesno konvertovan u .mp3!", "Obavestenje",
				JOptionPane.INFORMATION_MESSAGE);
	
}

}
