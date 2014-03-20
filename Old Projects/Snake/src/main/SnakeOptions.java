package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SnakeOptions extends JFrame implements ActionListener {
	// Constants
	// ---------
	public static final String WINDOW_TITLE = "Snake Options";
	public static final int WINDOW_WIDTH = 400;
	public static final int WINDOW_HEIGHT = 200;

	public static final int DEFAULT_SNAKE_SIZE = 10;
	public static final int DEFAULT_SNAKE_SPEED = 60;
	public static final int DEFAULT_WINDOW_SIZE = 400;
	public static final int DEFAULT_BORDER_SIZE = 6;

	public static final int SMALLEST = 60;
	public static final int SMALL = 80;
	public static final int DEFAULT = 100;
	public static final int BIG = 120;
	public static final int BIGGEST = 140;

	public static final int[] SIZES = { SMALLEST, SMALL, DEFAULT, BIG, BIGGEST };
	public static final int[] SPEEDS = { BIGGEST, BIG, DEFAULT, SMALL, SMALLEST };
	public static final Color[] COLOURS = { Color.BLACK, Color.BLUE,
			Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE,
			Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };
	public static final String[] WINDOW_CHOICES = { "Smallest", "Small",
			"Default", "Big", "Biggest" };
	public static final String[] SNAKE_SIZE_CHOICES = { "Smallest", "Small",
			"Default", "Big", "Biggest" };
	public static final String[] SNAKE_SPEED_CHOICES = { "Slowest", "Slow",
			"Default", "Fast", "Fastest" };
	public static final String[] BORDER_SIZE_CHOICES = { "Thinnest", "Thin",
			"Default", "Thick", "Thickest" };
	public static final String[] COLOUR_CHOICES = { "Black", "Blue", "Cyan",
			"Gray", "Green", "Magenta", "Orange", "Pink", "Red", "White",
			"Yellow" };

	public static final Color DEFAULT_SNAKE_COLOUR = Color.GREEN;
	public static final Color DEFAULT_FOOD_COLOUR = Color.BLUE;
	public static final Color DEFAULT_WINDOW_COLOUR = Color.WHITE;
	public static final Color DEFAULT_BORDER_COLOUR = Color.BLACK;

	public static final int DEFAULT_SNAKE_COLOUR_INDEX = 4;
	public static final int DEFAULT_FOOD_COLOUR_INDEX = 1;
	public static final int DEFAULT_WINDOW_COLOUR_INDEX = 9;
	public static final int DEFAULT_BORDER_COLOUR_INDEX = 0;
	public static final int DEFAULT_SNAKE_SIZE_INDEX = 2;
	public static final int DEFAULT_SNAKE_SPEED_INDEX = 2;
	public static final int DEFAULT_WINDOW_SIZE_INDEX = 2;
	public static final int DEFAULT_BORDER_SIZE_INDEX = 2;

	// Data Members
	// ------------
	private int snakeSize = DEFAULT_SNAKE_SIZE;
	private int snakeSpeed = DEFAULT_SNAKE_SPEED;
	private int windowSize = DEFAULT_WINDOW_SIZE;
	private int borderSize = DEFAULT_BORDER_SIZE;
	private Color snakeColour = DEFAULT_SNAKE_COLOUR;
	private Color foodColour = DEFAULT_FOOD_COLOUR;
	private Color windowColour = DEFAULT_WINDOW_COLOUR;
	private Color borderColour = DEFAULT_BORDER_COLOUR;

	private int snakeSizeIndex = DEFAULT_SNAKE_SIZE_INDEX;
	private int snakeSpeedIndex = DEFAULT_SNAKE_SPEED_INDEX;
	private int windowSizeIndex = DEFAULT_WINDOW_SIZE_INDEX;
	private int borderSizeIndex = DEFAULT_BORDER_SIZE_INDEX;
	private int snakeColourIndex = DEFAULT_SNAKE_COLOUR_INDEX;
	private int foodColourIndex = DEFAULT_FOOD_COLOUR_INDEX;
	private int windowColourIndex = DEFAULT_WINDOW_COLOUR_INDEX;
	private int borderColourIndex = DEFAULT_BORDER_COLOUR_INDEX;

	private JComboBox windowSizeBox = new JComboBox(WINDOW_CHOICES);
	private JComboBox snakeSizeBox = new JComboBox(SNAKE_SIZE_CHOICES);
	private JComboBox snakeSpeedBox = new JComboBox(SNAKE_SPEED_CHOICES);
	private JComboBox borderSizeBox = new JComboBox(BORDER_SIZE_CHOICES);
	private JComboBox snakeColourBox = new JComboBox(COLOUR_CHOICES);
	private JComboBox foodColourBox = new JComboBox(COLOUR_CHOICES);
	private JComboBox windowColourBox = new JComboBox(COLOUR_CHOICES);
	private JComboBox borderColourBox = new JComboBox(COLOUR_CHOICES);

	private SnakeWindow snakeWindow;

	// Constructor
	// -----------
	public SnakeOptions(SnakeWindow window) {
		super();
		snakeWindow = window;
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fillContentPane();
	}

	// Methods
	// -------
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Ok") && twoColoursAreTheSame())
			JOptionPane
					.showMessageDialog(this,
							"Error: you may not have two game components with the same colour");
		else {
			if (command.equals("Ok")) {
				updateValuesAndComboBoxIndices();
				snakeWindow.updateWindow();
			} else {
				// Cancel
				adjustComboBoxIndices();
			}
			snakeWindow.setVisible(true);
			this.setVisible(false);
		}
	}

	public int getWindowSize() {
		return windowSize;
	}

	public int getSnakeSize() {
		return snakeSize;
	}

	public int getSnakeSpeed() {
		return snakeSpeed;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public Color getSnakeColour() {
		return snakeColour;
	}

	public Color getFoodColour() {
		return foodColour;
	}

	public Color getWindowColour() {
		return windowColour;
	}

	public Color getBorderColour() {
		return borderColour;
	}

	// Private Methods
	// ---------------
	private void fillContentPane() {
		JPanel comboBoxPanel = new JPanel();
		addComboBoxes(comboBoxPanel);
		titleBorders();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton okButton = new JButton("Ok");
		JButton cancelButton = new JButton("Cancel");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		getContentPane().add(comboBoxPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		adjustComboBoxIndices();
	}

	private void addComboBoxes(JPanel comboBoxPanel) {
		comboBoxPanel.setLayout(new GridLayout(2, 4));
		comboBoxPanel.add(windowSizeBox);
		comboBoxPanel.add(snakeSizeBox);
		comboBoxPanel.add(snakeSpeedBox);
		comboBoxPanel.add(borderSizeBox);
		comboBoxPanel.add(windowColourBox);
		comboBoxPanel.add(snakeColourBox);
		comboBoxPanel.add(foodColourBox);
		comboBoxPanel.add(borderColourBox);
	}

	private void adjustComboBoxIndices() {
		windowSizeBox.setSelectedIndex(windowSizeIndex);
		snakeSizeBox.setSelectedIndex(snakeSizeIndex);
		snakeSpeedBox.setSelectedIndex(snakeSpeedIndex);
		borderSizeBox.setSelectedIndex(borderSizeIndex);
		windowColourBox.setSelectedIndex(windowColourIndex);
		snakeColourBox.setSelectedIndex(snakeColourIndex);
		foodColourBox.setSelectedIndex(foodColourIndex);
		borderColourBox.setSelectedIndex(borderColourIndex);
	}

	private void updateValuesAndComboBoxIndices() {
		windowSizeIndex = windowSizeBox.getSelectedIndex();
		snakeSizeIndex = snakeSizeBox.getSelectedIndex();
		snakeSpeedIndex = snakeSpeedBox.getSelectedIndex();
		borderSizeIndex = borderSizeBox.getSelectedIndex();
		windowColourIndex = windowColourBox.getSelectedIndex();
		snakeColourIndex = snakeColourBox.getSelectedIndex();
		foodColourIndex = foodColourBox.getSelectedIndex();
		borderColourIndex = borderColourBox.getSelectedIndex();

		windowSize = (DEFAULT_WINDOW_SIZE * SIZES[windowSizeIndex]) / 100;
		snakeSize = (DEFAULT_SNAKE_SIZE * SIZES[snakeSizeIndex]) / 100;
		snakeSpeed = (DEFAULT_SNAKE_SPEED * SPEEDS[snakeSpeedIndex]) / 100;
		borderSize = (DEFAULT_BORDER_SIZE * SIZES[borderSizeIndex]) / 100;
		windowColour = COLOURS[windowColourIndex];
		snakeColour = COLOURS[snakeColourIndex];
		foodColour = COLOURS[foodColourIndex];
		borderColour = COLOURS[borderColourIndex];
	}

	private void titleBorders() {
		windowSizeBox
				.setBorder(BorderFactory.createTitledBorder("Window Size"));
		snakeSizeBox.setBorder(BorderFactory.createTitledBorder("Snake Size"));
		snakeSpeedBox
				.setBorder(BorderFactory.createTitledBorder("Snake Speed"));
		borderSizeBox
				.setBorder(BorderFactory.createTitledBorder("Border Size"));
		windowColourBox.setBorder(BorderFactory
				.createTitledBorder("Window Colour"));
		snakeColourBox.setBorder(BorderFactory
				.createTitledBorder("Snake Colour"));
		foodColourBox
				.setBorder(BorderFactory.createTitledBorder("Food Colour"));
		borderColourBox.setBorder(BorderFactory
				.createTitledBorder("Border Colour"));
	}

	private boolean twoColoursAreTheSame() {
		boolean sameColour = false;
		int[] array = { windowColourBox.getSelectedIndex(),
				snakeColourBox.getSelectedIndex(),
				foodColourBox.getSelectedIndex(),
				borderColourBox.getSelectedIndex()};
		int i, j;
		i = 0;
		while (i < array.length && !sameColour) {
			j = i + 1;
			while (j < array.length && !sameColour)
				sameColour = array[i] == array[j++];
			i = i + 1;
		}
		return sameColour;
	}
}