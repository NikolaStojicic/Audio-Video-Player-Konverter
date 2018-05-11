package avgui.player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.OffsetDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlayer implements PlayerInterface {

	public Player player;
	FileInputStream fis;
	BufferedInputStream bis;

	/**
	 * Promenljiva koji predstavlja vrednost na kojoj je pesma pauzirana.
	 */
	public long pauseLocation;
	/**
	 * Promenljiva koja predstavlja celu duzinu pesme.
	 */
	public long songTotalLength;
	/**
	 * Promenljiva koja predstavlja vrednost od koje ce se nastaviti pesma, tako da
	 * bude premotana.
	 */
	public int rewindLocation;
	/**
	 * Promenljiva koja predstavlja putanju odabrane pesme.
	 */
	public String fileLocation;

	/**
	 * Metoda koja treba da pusti odabranu pesmu.
	 * 
	 * @param path
	 *            predstavlja String koji predstavlja putanju pesme koja ce se
	 *            pustiti
	 */
	@Override
	public void playA(String path) {
		try {
			fis = new FileInputStream(path);
			bis = new java.io.BufferedInputStream(fis);
			player = new Player(bis);
			songTotalLength = fis.available(); // imamo punu duzinu pesme

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

	// +1000000
	// time in miliseconds
	/**
	 * Metoda koja treba da premota unapred ili unazad pesmu koja je pustena za
	 * odredjeno vreme.
	 * 
	 * @param offset
	 *            predstavlja podatak tipa long koji predstavlja odredjeni broj
	 *            milisekundi za koje ce data pesma biti premotana unapred (ukoliko
	 *            offset ima pozitivni znak) ili unazad (ukoliko je offset
	 *            negativan)
	 */
	@Override
	public void rewind(long offset) {
		pause();
		resumeP(offset);

	}

	/**
	 * Metoda koja treba da pauzira pesmu koja se tom trenutku slusa.
	 * 
	 * @return pauseLocation koji predstavlja podatak tipa long koji predstavlja
	 *         vreme na kome je zaustavljena pesma
	 */
	@Override
	public long pause() {

		if (player != null) {

			try {

				pauseLocation = fis.available();
				player.close();

			} catch (IOException e) {

			}

		}
		return pauseLocation;
	}

	/**
	 * Metoda koja treba da stopira pesmu koja se slusala u datom trenutku.
	 * 
	 */
	@Override
	public void stop() {
		if (player != null) {
			player.close();
			pauseLocation = 0;
			songTotalLength = 0;

		}
	}

	/**
	 * Pomocna metoda koja omogucava premotavanje pesme.
	 * 
	 * @param offset
	 *            predstavlja podatak tipa long koji predstvalja za koliko ce se
	 *            data pesma premotati unapred ili unazad (pozitivan offset za
	 *            premotavanje unapred i negativan za premotavanje unazad)
	 */

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

	/**
	 * Metoda koja treba da nastavi pesmusa mesta gde je pauzirana.
	 * 
	 * @param offset
	 *            predstavlja podatak tipa long koji nam omogucava da premotamo
	 *            pesmu
	 * 
	 * @param path
	 *            predstavlja String koji predstavlja putanju odabrane pesme koju
	 *            zelimo da pustimo
	 */
	@Override
	public void resume(long offset, String path) {
		try {
			fis = new FileInputStream(path);
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
	public void playV(JFrame f, String path, JPanel p) {
		// TODO Auto-generated method stub

	}

}
