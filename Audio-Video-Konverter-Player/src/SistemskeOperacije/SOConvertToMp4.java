package SistemskeOperacije;

import java.io.File;

import javax.swing.JOptionPane;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;

public class SOConvertToMp4 {

	public void izvrsi(File open, String save) throws IllegalArgumentException, InputFormatException, EncoderException {
		File target = new File(save);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libfaac");
		audio.setBitRate(new Integer(128000));
		audio.setSamplingRate(new Integer(44100));
		audio.setChannels(new Integer(2));
		VideoAttributes video = new VideoAttributes();
		video.setBitRate(new Integer(16000));
		video.setFrameRate(new Integer(25));
		video.setCodec("mpeg4");
		// video.setCodec(VideoAttributes.DIRECT_STREAM_COPY);
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp4");
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();
		encoder.encode(open, target, attrs);

		JOptionPane.showMessageDialog(null, "Fajl " + open.getName() + " uspesno konvertovan u .mp4!", "Obavestenje",
				JOptionPane.INFORMATION_MESSAGE);

	}

}
