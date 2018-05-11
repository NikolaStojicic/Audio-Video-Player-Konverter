package avgui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SistemskeOperacije.SOConvertToAvi;
import SistemskeOperacije.SOConvertToMp3;
import SistemskeOperacije.SOConvertToMp4;
import SistemskeOperacije.SOConvertToWav;
import avgui.kontroler.GUIKontroler;
import avgui.util.MoveMouseListener;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextPane;

public class AVGUIMainWindow extends JFrame {

	private JPanel contentPane;
	
	private File open;
	private String savePath = "";
	
	

	/**
	 * Create the frame.
	 */
	public AVGUIMainWindow() {
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 190);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		MoveMouseListener mml = new MoveMouseListener(contentPane);
		contentPane.addMouseListener(mml);
		contentPane.addMouseMotionListener(mml);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel label1 = new JLabel("PLAY");
		label1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				boolean isPlay = GUIKontroler.togglePlay(label1);
				if (isPlay) {
					// Play dugme
				} else {
					// Pause dugme
				}
			}
		});
		label1.setBounds(53, 45, 120, 120);
		GUIKontroler.guiButtonComponentInitializer(label1, "playButton_1_up.png");
		contentPane.add(label1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 24));
		textPane.setBounds(205, 47, 560, 115);
		textPane.setOpaque(false);
		contentPane.add(textPane);
		
		JLabel lblConvert = new JLabel("convert");
		lblConvert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if (GUIKontroler.getSelctedButton().equals("mp3")) {
				
					if (GUIKontroler.getFormat(open.getPath())== "mp3") {
						GUIKontroler.prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
						return;
					}
					
					try {
						GUIKontroler.saveDialog();
						SOConvertToMp3.izvrsi(open, savePath+".mp3");
					} catch (IllegalArgumentException | EncoderException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
				if (GUIKontroler.getSelctedButton().equals("wav")) {
					if (GUIKontroler.getFormat(open.getPath())== "waw") {
						GUIKontroler.prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
						return;
					}
					try {
						GUIKontroler.saveDialog();
						SOConvertToWav.izvrsi(open, savePath+".wav");
					} catch (IllegalArgumentException | EncoderException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
				if (GUIKontroler.getSelctedButton().equals("avi")) {
					
					if (GUIKontroler.getFormat(open.getPath()) == "mp3" || GUIKontroler.getFormat(open.getPath()) == "waw") {
						GUIKontroler.prikaziPoruku("nije moguce konvertovati iz audio u video formate!");
						return;
					}
					if (GUIKontroler.getFormat(open.getPath())== "avi") {
						GUIKontroler.prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
						return;
					}
					
					try {
						GUIKontroler.saveDialog();
						SOConvertToAvi.izvrsi(open, savePath+".avi");
					} catch (IllegalArgumentException | EncoderException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					
					
				}
				if (GUIKontroler.getSelctedButton().equals("mp4")) {
					if (GUIKontroler.getFormat(open.getPath()) == "mp3" || GUIKontroler.getFormat(open.getPath()) == "waw") {
						GUIKontroler.prikaziPoruku("nije moguce konvertovati iz audio u video formate!");
						return;
					}
					if (GUIKontroler.getFormat(open.getPath())== "mp4") {
						GUIKontroler.prikaziPoruku("odabrali ste format koji je isti formatu koji ste ucitali!");
						return;
					}
					
					
					try {
						GUIKontroler.saveDialog();
						SOConvertToMp4.izvrsi(open, savePath+".mp4");
					} catch (IllegalArgumentException | EncoderException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				}
		});
		lblConvert.setBounds(796, 130, 135, 40);
		contentPane.add(lblConvert);
		lblConvert.setPreferredSize(new Dimension(133, 40));
		GUIKontroler.guiButtonComponentInitializer(lblConvert, "convert_up.png");
		JLabel lblOpen = new JLabel("open");
		lblOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				GUIKontroler.showOpenDialog(textPane);
				
				
			}
		});
		lblOpen.setBounds(796, 40, 135, 40);
		contentPane.add(lblOpen);
		lblOpen.setPreferredSize(new Dimension(133, 40));
		GUIKontroler.guiButtonComponentInitializer(lblOpen, "open_up.png");

		JLabel labelMp4 = new JLabel("mp4");
		labelMp4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		labelMp4.setPreferredSize(new Dimension(133, 40));
		labelMp4.setBounds(795, 90, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(labelMp4, "mp4_up.png");
		contentPane.add(labelMp4);

		JLabel labelmp3 = new JLabel("mp3");
		labelmp3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		labelmp3.setPreferredSize(new Dimension(133, 40));
		labelmp3.setBounds(830, 90, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(labelmp3, "mp3_up.png");
		contentPane.add(labelmp3);

		JLabel labelwav = new JLabel("wav");
		labelwav.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		labelwav.setPreferredSize(new Dimension(133, 40));
		labelwav.setBounds(865, 90, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(labelwav, "wav_up.png");
		contentPane.add(labelwav);

		JLabel lblAvi = new JLabel("avi");
		lblAvi.setPreferredSize(new Dimension(133, 40));
		lblAvi.setBounds(900, 90, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblAvi, "avi_up.png");
		contentPane.add(lblAvi);

		JLabel lblAbout = new JLabel("about");
		lblAbout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAbout.setBounds(85, 5, 70, 25);
		GUIKontroler.guiButtonComponentInitializer(lblAbout, "about_up.png");
		contentPane.add(lblAbout);

		JLabel lblClose = new JLabel("close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		lblClose.setBounds(861, 5, 70, 25);
		GUIKontroler.guiButtonComponentInitializer(lblClose, "close_up.png");
		contentPane.add(lblClose);

		JLabel lblFile = new JLabel("file");
		lblFile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblFile.setBounds(9, 5, 70, 25);
		GUIKontroler.guiButtonComponentInitializer(lblFile, "file_up.png");
		contentPane.add(lblFile);

		JLabel lblForward = new JLabel("forward");
		lblForward.setBounds(16, 92, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblForward, "fastforward_up.png");
		contentPane.add(lblForward);

		JLabel lblStop = new JLabel("stop");
		lblStop.setBounds(17, 50, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblStop, "stop_up.png");
		contentPane.add(lblStop);

		JLabel lblBackward = new JLabel("backward");
		lblBackward.setBounds(16, 133, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblBackward, "rewind_up.png");
		contentPane.add(lblBackward);

		JLabel lblBg = new JLabel("BG");
		lblBg.setIcon(new ImageIcon(AVGUIMainWindow.class.getResource("/res/gui/ux.png")));
		lblBg.setBounds(0, 0, 957, 190);
		GUIKontroler.guiComponentInitializer(lblBg, "ux.png", 940, 190);
		lblBg.addMouseListener(mml);
		lblBg.addMouseMotionListener(mml);
		
		

		JLabel lblPlaceholder = new JLabel("PLACEHOLDER");
		lblPlaceholder.setBounds(200, 42, 570, 125);
		GUIKontroler.guiComponentInitializer(lblPlaceholder, "info_section.png");
		contentPane.add(lblPlaceholder);
		contentPane.add(lblBg);
		GUIKontroler.formats.add(labelMp4);
		GUIKontroler.formats.add(labelmp3);
		GUIKontroler.formats.add(labelwav);
		GUIKontroler.formats.add(lblAvi);

	}

	public void setOpen(File open) {
		this.open = open;
	}
	
	public File getOpen() {
		return this.open;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}



	
}
