package SistemskeOperacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SOResumeAudio {
	public static Player player = null;
	public static void izvrsi(long offset, String path, FileInputStream fis, BufferedInputStream bis, Player playerIN, long songTotalLength, long pauseLocation) {
		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);
			playerIN = new Player(bis);
			player = playerIN;
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
}
