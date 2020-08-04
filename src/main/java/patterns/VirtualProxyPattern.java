package patterns;

import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

public class VirtualProxyPattern {

	class ImageProxy {
		private ImageIcon imageIcon;
		private URL imageUrl;
		private Thread reatrievalThread;
		private boolean reatrieving;

		public ImageProxy(URL imageUrl) {
			this.imageUrl = imageUrl;
		}

		public void paint(final Component c, Graphics g, int x, int y) {
			if (imageIcon != null) {
				imageIcon.paintIcon(c, g, x, y);
			} else {
				g.drawString("Loading CD cover, please wait...", x + 300, y + 190);
				if (!reatrieving) {
					reatrieving = true;
					reatrievalThread = new Thread(() -> {
						try {
							imageIcon = new ImageIcon(imageUrl, "CD cover");
							c.repaint();
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
				reatrievalThread.start();
			}
		}
	}
}
