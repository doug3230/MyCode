package space_invaders;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.Timer;

public class GameApplet extends JApplet implements ActionListener {
	private GamePanel panel = new GamePanel();
	
	public GameApplet() {
		super();
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		add(panel.getScorePanel(),BorderLayout.NORTH);
		Timer timer = new Timer(100,this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
