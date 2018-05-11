package avgui.player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SistemskeOperacije.SOPauseAudio;
import SistemskeOperacije.SOPlayAudio;
import SistemskeOperacije.SOResumeAudio;
import SistemskeOperacije.SOStopAudio;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlayer implements PlayerInterface {

	public Player player;
	FileInputStream fis;
	BufferedInputStream bis;

	public long pauseLocation;
	public long songTotalLength;
	public int rewindLocation;
	public String fileLocation;

	@Override
	public void playA(String path) {
		SOPlayAudio.izvrsi(fis, bis, fileLocation, songTotalLength, path,  player);

	}

	// +1000000
	// time in miliseconds
	@Override
	public void rewind(long offset) {
		pause();
		resumeP(offset);

	}

	@Override
	public long pause() {

		return SOPauseAudio.izvrsi( (javax.media.Player) player, pauseLocation, fis);
	}

	@Override
	public void stop() {
		SOStopAudio.izvrsi((javax.media.Player) player, pauseLocation, songTotalLength);
	}

	private void resumeP(long offset) {
		try {
			fis = new FileInputStream(fileLocation);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);

			fis.skip(songTotalLength - pauseLocation + offset);
		} catch (FileNotFoundException e1) {

		} catch (JavaLayerException e1) {

		} catch (IOException e) {

		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {

				}

			}

		}.start();

	}

	@Override
	public void resume(long offset, String path) {
		SOResumeAudio.izvrsi(offset, path, fis, bis,  player, songTotalLength, pauseLocation);

	}

	@Override
	public void playV(JFrame f, String path, JPanel p) {
		// TODO Auto-generated method stub

	}

}
