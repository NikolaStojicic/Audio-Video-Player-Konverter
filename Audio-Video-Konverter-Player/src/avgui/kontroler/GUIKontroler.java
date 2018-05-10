package avgui.kontroler;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import avgui.AVGUIMainWindow;

public class GUIKontroler {

	public static AVGUIMainWindow mainWindow;
	public static LinkedList<JLabel> formats = new LinkedList<JLabel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new AVGUIMainWindow();
					mainWindow.setVisible(true);
					for (JLabel format : formats)
						setRadioButtonListener(format, formats);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean togglePlay(JLabel component) {
		String selectedImage = GUIKontroler.guiGetName(component);
		selectedImage = selectedImage.split("_")[1];
		if (selectedImage.equals("1")) {
			GUIKontroler.setGuiComponentImage(component, "playButton_2_down.png");
			return true;
		} else {
			GUIKontroler.setGuiComponentImage(component, "playButton_1_down.png");
			return false;
		}
	}

	public static void setRadioButtonListener(JLabel component, LinkedList<JLabel> formats) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GUIKontroler.radioButtons(component, formats);
			}
		});
	}

	public static void setGuiComponentListener(JLabel component) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				guiComponentPressed(component);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				guiComponentPressed(component);
			}
		});
	}

	public static void setGuiComponentImage(JLabel component, String image) {
		setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
	}

	public static void setGuiComponentImage(JLabel component, String image, int x, int y) {
		Icon icon = new ImageIcon(new javax.swing.ImageIcon(AVGUIMainWindow.class.getResource("/res/gui/" + image))
				.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
		component.setIcon(icon);
		component.setText(image);
	}

	public static void guiComponentInitializer(JLabel component, String image) {
		setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
	}

	public static void guiComponentInitializer(JLabel component, String image, int x, int y) {
		component.setFont(new java.awt.Font("Tahoma", 1, 0));
		setGuiComponentImage(component, image, x, y);
		component.setSize(x, y);
	}

	public static void guiButtonComponentInitializer(JLabel component, String image) {
		guiComponentInitializer(component, image, component.getWidth(), component.getHeight());
		setGuiComponentListener(component);
	}

	public static void guiButtonComponentInitializer(JLabel component, String image, int x, int y) {
		guiComponentInitializer(component, image, x, y);
		setGuiComponentListener(component);
	}

	public static String radioButtons(JLabel component, LinkedList<JLabel> formats) {
		for (Iterator iterator = formats.iterator(); iterator.hasNext();) {
			JLabel jLabel = (JLabel) iterator.next();
			if (jLabel.getText().endsWith("down.png"))
				GUIKontroler.setGuiComponentImage(jLabel, jLabel.getText().split("down.png")[0] + "up.png");
		}
		if (component.getText().endsWith("up.png"))
			GUIKontroler.setGuiComponentImage(component, component.getText().split("up.png")[0] + "down.png");
		System.out.println(component.getText().split("_")[0]);
		return component.getText().split("_")[0];
	}

	public static String guiGetName(JLabel component) {
		return component.getText().split("_up.")[0];
	}

	public static void guiComponentPressed(JLabel component) {
		String nameOfComponent = guiGetName(component);
		if (!nameOfComponent.endsWith("png")) {
			String image = nameOfComponent + "_down.png";
			setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
		} else {
			nameOfComponent = component.getText().split("_down.")[0];
			String image = nameOfComponent + "_up.png";
			setGuiComponentImage(component, image, component.getWidth(), component.getHeight());
		}

	}
}