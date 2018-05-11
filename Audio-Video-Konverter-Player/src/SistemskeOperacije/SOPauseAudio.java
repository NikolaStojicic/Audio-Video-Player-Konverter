package SistemskeOperacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.media.Player;

public class SOPauseAudio {
	
	public static long izvrsi(Player player, long pauseLocation, FileInputStream fis) {
		
		
		if (player != null) {

			try {

				pauseLocation = fis.available();
				player.close();

			} catch (IOException e) {

			}

		}
		return pauseLocation;
	}

}
