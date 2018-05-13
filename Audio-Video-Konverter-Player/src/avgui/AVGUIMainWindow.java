package avgui;

//import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;
import java.awt.MenuItem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SistemskeOperacije.SOConvertToAvi;
import SistemskeOperacije.SOConvertToMp3;
import SistemskeOperacije.SOConvertToMp4;
import SistemskeOperacije.SOConvertToWav;
import avgui.anim.Animation;
import avgui.kontroler.GUIKontroler;
import avgui.player.AudioPlayer;
import avgui.player.VideoPlayer;
import avgui.util.MoveMouseListener;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;

import java.awt.Panel;
import java.awt.PopupMenu;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class AVGUIMainWindow extends JFrame {

	private JPanel contentPane;

	private File open = null;
	private String savePath = "";
	public JLabel label1;
	private AudioPlayer ap;
	private VideoPlayer vp;
	private static long pauseTime;
	public static long pauseTime1;
	boolean first;

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

		first = false;

		ap = new AudioPlayer();
		vp = new VideoPlayer();

		label1 = new JLabel("PLAY");
		label1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (GUIKontroler.enabled && getOpen() != null) {
					boolean isPlay = GUIKontroler.togglePlay(label1);

					String path = open.getPath();
					if (isPlay) {
						if (path.endsWith(".mp3") || path.endsWith(".wav")) {
							System.out.println(pauseTime1);
							if (pauseTime1 == 0)
								ap.playA(path);
							else
								ap.resume(0, path);
						} else {
							GUIKontroler.prikaziPorukuGreska("Uneti falj moze biti mp3 ili wav.");
							return;
						}

					} else {
						if (path.endsWith(".mp3") || path.endsWith(".wav")) {
							pauseTime1 = ap.pause();
						}
					}
				}
			}
		});

		label1.setBounds(53, 52, 120, 120);
		GUIKontroler.guiButtonComponentInitializer(label1, "playButton_1_up.png");
		contentPane.add(label1);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 24));
		textPane.setBounds(205, 54, 560, 115);
		textPane.setOpaque(false);
		contentPane.add(textPane);

		JLabel lblConvert = new JLabel("convert");
		lblConvert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				if (GUIKontroler.enabled) {
					stop();
					GUIKontroler.Convert();
				}

			}
		});
		lblConvert.setBounds(796, 137, 135, 40);
		contentPane.add(lblConvert);
		lblConvert.setPreferredSize(new Dimension(133, 40));
		GUIKontroler.guiButtonComponentInitializer(lblConvert, "convert_up.png");
		JLabel lblOpen = new JLabel("open");
		lblOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (GUIKontroler.enabled) {
					GUIKontroler.showOpenDialog(textPane);
					stop();
				}
			}
		});
		lblOpen.setBounds(796, 47, 135, 40);
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
		labelMp4.setBounds(795, 97, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(labelMp4, "mp4_up.png");
		contentPane.add(labelMp4);

		JLabel labelmp3 = new JLabel("mp3");
		labelmp3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		labelmp3.setPreferredSize(new Dimension(133, 40));
		labelmp3.setBounds(830, 97, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(labelmp3, "mp3_up.png");
		contentPane.add(labelmp3);

		JLabel labelwav = new JLabel("wav");
		labelwav.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		labelwav.setPreferredSize(new Dimension(133, 40));
		labelwav.setBounds(865, 97, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(labelwav, "wav_up.png");
		contentPane.add(labelwav);

		JLabel lblAvi = new JLabel("avi");
		lblAvi.setPreferredSize(new Dimension(133, 40));
		lblAvi.setBounds(900, 97, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblAvi, "avi_up.png");
		contentPane.add(lblAvi);

		JLabel lblAbout = new JLabel("about");

		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

				GUIKontroler.prikaziPorukuAbout();

			}
		});

		lblAbout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAbout.setBounds(85, 12, 70, 25);
		GUIKontroler.guiButtonComponentInitializer(lblAbout, "about_up.png");
		contentPane.add(lblAbout);

		JLabel lblClose = new JLabel("close");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (GUIKontroler.enabled)
					System.exit(0);
			}
		});
		lblClose.setBounds(861, 12, 70, 25);
		GUIKontroler.guiButtonComponentInitializer(lblClose, "close_up.png");
		contentPane.add(lblClose);

		JLabel lblFile = new JLabel("file");
		lblFile.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				GUIKontroler.setGuiComponentImage(lblFile, "file_down.png");
			}
		});
		lblFile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblFile.setBounds(9, 12, 70, 25);
		GUIKontroler.guiButtonComponentInitializer(lblFile, "file_up.png");
		contentPane.add(lblFile);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(lblFile, popupMenu);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (GUIKontroler.enabled) {
					GUIKontroler.showOpenDialog(textPane);
					stop();
				}
			}
		});
		mntmOpen.setIcon(
				new ImageIcon(AVGUIMainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		popupMenu.add(mntmOpen);

		JMenuItem mntmConvert = new JMenuItem("Convert");
		mntmConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GUIKontroler.enabled)
					GUIKontroler.Convert();
			}
		});
		mntmConvert.setIcon(
				new ImageIcon(AVGUIMainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		popupMenu.add(mntmConvert);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GUIKontroler.enabled)
					System.exit(0);
			}
		});
		mntmExit.setIcon(
				new ImageIcon(AVGUIMainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		popupMenu.add(mntmExit);

		JLabel lblForward = new JLabel("forward");
		lblForward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				if (GUIKontroler.enabled && getOpen() != null)
					ap.rewind(100000);

			}
		});
		lblForward.setBounds(16, 99, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblForward, "fastforward_up.png");
		contentPane.add(lblForward);

		JLabel lblStop = new JLabel("stop");
		lblStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (GUIKontroler.enabled) {
					stop();
				}
			}
		});
		lblStop.setBounds(17, 57, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblStop, "stop_up.png");
		contentPane.add(lblStop);

		JLabel lblBackward = new JLabel("backward");
		lblBackward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (GUIKontroler.enabled && getOpen() != null)
					ap.rewind(-100000);
			}
		});
		lblBackward.setBounds(16, 140, 32, 32);
		GUIKontroler.guiButtonComponentInitializer(lblBackward, "rewind_up.png");
		contentPane.add(lblBackward);

		JLabel lblBg = new JLabel("BG");
		lblBg.setIcon(new ImageIcon(AVGUIMainWindow.class.getResource("/res/gui/ux.png")));
		lblBg.setBounds(0, 0, 957, 190);
		GUIKontroler.guiComponentInitializer(lblBg, "ux.png", 940, 190);
		lblBg.addMouseListener(mml);
		lblBg.addMouseMotionListener(mml);

		JLabel lblPlaceholder = new JLabel("PLACEHOLDER");
		lblPlaceholder.setBounds(200, 49, 570, 125);
		GUIKontroler.guiComponentInitializer(lblPlaceholder, "info_section.png");
		contentPane.add(lblPlaceholder);
		contentPane.add(lblBg);
		GUIKontroler.formats.add(labelMp4);
		GUIKontroler.formats.add(labelmp3);
		GUIKontroler.formats.add(labelwav);
		GUIKontroler.formats.add(lblAvi);
		GUIKontroler.contentPane = contentPane;
	}

	public void stop() {
		ap.stop();
		GUIKontroler.setGuiComponentImage(label1, "playButton_1_up.png");
		pauseTime1 = 0;
	}

	public void setOpen(File open) {
		this.open = open;
	}

	public File getOpen() {
		pauseTime = 0;
		return this.open;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				if (GUIKontroler.enabled)
					if (SwingUtilities.isLeftMouseButton(e)) {
						showMenu(e);
					}

			}

			public void mousePressed(MouseEvent e) {
				if (GUIKontroler.enabled)
					if (SwingUtilities.isLeftMouseButton(e)) {
						showMenu(e);
					}

			}

			private void showMenu(MouseEvent e) {

				if (GUIKontroler.enabled)
					popup.show(e.getComponent(), component.getX() - 10, component.getY() + 20);

			}
		});
	}
}
