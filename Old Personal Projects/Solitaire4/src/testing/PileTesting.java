package testing;

import java.awt.Color;

import javax.swing.JLayeredPane;

import piles.Pile;

public class PileTesting {
	/**Testing for the Pile class*/
	public static void main(String[] args) {
		javax.swing.JFrame window = new javax.swing.JFrame("test window");
		JLayeredPane layerPane = new JLayeredPane();
		javax.swing.JPanel panel = new javax.swing.JPanel();
		window.setSize(400,400);
		panel.setSize(400,400);
		panel.setLayout(null);
		
		Pile label1 = new Pile(layerPane,1);
		label1.setSize(60,80);
		label1.setLocation(40,40);
		label1.addToLayerPane();
		
		Pile label2 = new Pile(layerPane,1);
		label2.setSize(60,80);
		label2.setLocation(140,40);
		label2.addToLayerPane();
		
		panel.setBackground(Color.BLUE);
		layerPane.add(panel);
		
		window.setContentPane(layerPane);
		window.setVisible(true);
	}
}