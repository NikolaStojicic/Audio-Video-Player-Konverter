package SistemskeOperacije;

import javax.media.Player;

public class SOStopAudio {

	public static void izvrsi(Player player, long pauseLocation, long songTotalLength) {
		if (player != null) {
			player.close();
			pauseLocation = 0;
			songTotalLength = 0;

		}

	}

}
