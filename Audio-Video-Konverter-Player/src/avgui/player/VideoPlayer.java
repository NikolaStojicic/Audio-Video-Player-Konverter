package avgui.player;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class VideoPlayer implements PlayerInterface{

	// private Window f;
		private EmbeddedMediaPlayer emp;
		private MediaPlayerFactory mpf;
		private float pauseTime;
		
		@Override
		public void playV(JFrame f, String path, JPanel p) {
			Canvas c = new Canvas();
			c.setBackground(Color.black);

			p.add(c);
			f.add(p);
			
		//	try {
				NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");

				Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
				// inicijalizacija Media Player
			

			 mpf = new MediaPlayerFactory();
			// control all the interactions with user
			 emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
			emp.setVideoSurface(mpf.newVideoSurface(c));
		
			emp.setEnableKeyInputHandling(false);

			String file = path;
			// prepare file to read
			emp.prepareMedia(file);
			// read the file
			emp.play();

		}

		@Override
		public void rewind(long offset) {
			emp.skip(-10000);
		//	mediaPlayerComponent.getMediaPlayer().skip(-10000);
		}

		@Override
		public long pause() {
			emp.pause();
			pauseTime = emp.getPosition();
		return (long)pauseTime;
		}

		@Override
		public void stop() {
			emp.stop();

		}

		@Override
		public void resume(long off,String path) {
			emp.skipPosition(pauseTime);

		}

		@Override
		public void playA(String path) {
			String file =path;
			//prepare file to read 
			emp.prepareMedia(file);
			//read the file
			emp.play();

		}	
		
		
		
		
		
	
	
}
