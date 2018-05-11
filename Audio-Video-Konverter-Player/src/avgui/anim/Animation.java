package avgui.anim;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import avgui.kontroler.GUIKontroler;

public class Animation {
	public static JLabel label;

	public synchronized static void animThreada(JLabel label, int i) {
		GUIKontroler.setGuiComponentImage(label, "anim_" + i + ".png");
		label.revalidate();
	}

	static Thread thread = new Thread() {
		public void run() {
			int i = 1;
			while (true) {
	
				animThreada(label, i);
				i++;
				if (i == 5)
					i = 1;

			}
		};
	};

	public static void start() {
		thread.start();
	}

	public static void stop() {
		thread.stop();
	}
}
