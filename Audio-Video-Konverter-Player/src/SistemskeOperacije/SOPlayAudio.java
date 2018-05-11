package SistemskeOperacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SOPlayAudio {

	public static Player player = null;
	public static void izvrsi(FileInputStream fis, java.io.BufferedInputStream bis, String fileLocation, long songTotalLength, String path,  Player playerIN) {
		try {
			fis = new FileInputStream(path);
			bis = new java.io.BufferedInputStream(fis);
			playerIN = new Player(bis);
			songTotalLength = fis.available(); // imamo punu duzinu pesme
			player = playerIN;
			fileLocation = path + "";
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
