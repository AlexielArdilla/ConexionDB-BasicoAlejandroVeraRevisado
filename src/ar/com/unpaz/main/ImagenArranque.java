package ar.com.unpaz.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImagenArranque extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int MILLIS = 2_000;

	public ImagenArranque() {
		add(new JLabel(new ImageIcon("logo arranque.png")));
		add(new JLabel(new ImageIcon(ImagenArranque.class.getResource("/images/logo arranque UNPAZ.png"))));
		setUndecorated(true); 
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		run();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(MILLIS);
			setVisible(false);
			dispose();
		} catch (InterruptedException e) {
		}
	}
}
