package space_invaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener {
	private GamePanel panel = new GamePanel();
	
	public GameWindow() {
		super();
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		add(panel.getScorePanel(),BorderLayout.NORTH);
		pack();
		System.out.println(this.getWidth() + " " + this.getHeight());
	}
	
	public static void main(String[] args) {
		GameWindow window = new GameWindow();
		Timer timer = new Timer(100,window);
		window.setVisible(true);
		window.repaint();
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {repaint();}
}