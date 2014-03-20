package space_invaders;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GamePanel extends JPanel {
	
	//Constants
	//---------
	private static final int PREF_WIDTH = 500;
	private static final int PREF_HEIGHT = 500;
	private static final Font TITLE_FONT = new Font("Arial",Font.BOLD,30);
	private static final Font START_FONT = new Font("Courier",Font.PLAIN,16);
	
	//Data Members
	//------------
	private CrossFighter ship = new CrossFighter(45,45,this);
	private AlienSprite[][] aliens = new AlienSprite[5][11];
	private MotherShipSprite mothership = new MotherShipSprite(30,20,this);
	private ScorePanel scorePanel = new ScorePanel();
	private ShipManager shipManager;
	private MotherShipManager motherManager;
	private AliensManager aliensManager;
	private BlockManager blockManager;
	private ShipKeyboardListener shipListener = new ShipKeyboardListener(ship);
	private KeyboardListener keyboardListener = new KeyboardListener();
	private boolean isTitleScreen = true;
	private boolean isPaused = false;
	
	//Constructor
	//-----------
	public GamePanel() {
		super();
		setSize(getPreferredSize());
		setBackground(Color.GRAY);
		setFocusable(true);
		requestFocusInWindow();
		initShipManager();
		initMotherShipManager();
		initAliensManager();
		initBlockManager();
		setDoubleBuffered(true);
		addKeyListener(keyboardListener);
		addKeyListener(shipListener);
	}
	
	//Methods
	//-------
	@Override
	public Dimension getPreferredSize() {return new Dimension(PREF_WIDTH,PREF_HEIGHT);}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isTitleScreen) {
			g.setColor(Color.BLUE);
			g.setFont(TITLE_FONT);
			int x = getWidth() / 2 - g.getFontMetrics(TITLE_FONT).stringWidth("Space Invaders")/2;
			int y = getHeight() / 4;
			g.drawString("Space Invaders", x, y);
			
			x = getWidth() / 2 - g.getFontMetrics(START_FONT).stringWidth("Press 'n' to start a new game") / 2;
			y += 50;
			g.setColor(Color.YELLOW);
			g.setFont(START_FONT);
			g.drawString("Press 'n' to start a new game", x, y);
		}
		
		if (aliensManager != null) {
			aliensManager.update();
			aliensManager.paint(g);
		}
		if (blockManager != null) {
			blockManager.update();
			blockManager.paint(g);
		}
		if (shipManager != null) {
			shipManager.update();
			shipManager.paint(g);
		}
		if (motherManager != null) {
			motherManager.update();
			motherManager.paint(g);
		}
		scorePanel.updateLabels();
	}
	
	public ShipSprite getShip() {return ship;}
	public void newGame() {
		isTitleScreen = false;
		shipManager.removeShipBeams();
		aliensManager.removeAlienBeams();
		motherManager.removeShip();
		shipManager.placeShip();
		aliensManager.placeAliens();
		blockManager.placeBlocks();
		scorePanel.initializeValues();
		shipManager.restore();
		aliensManager.restore();
		blockManager.restore();
		unPauseManagers();
	}
	
	public void pauseOrUnpause() {
		if (isPaused)
			unPauseManagers();
		else
			pauseManagers();
		isPaused = !isPaused;
	}
	
	public ScorePanel getScorePanel() {return scorePanel;}
	
	//Private Methods
	//---------------
	private void initShipManager() {
		shipManager = new ShipManager(ship,this,scorePanel);
	}
	
	private void initMotherShipManager() {
		motherManager = new MotherShipManager(mothership,this,scorePanel,shipManager);
	}
	
	private void initAliensManager() {
		int hor_gap = 10;
		int ver_gap = 10;
		int starting_x = 50; 
		int starting_y = 50;
		int alien_width = 290/11;
		int alien_height = 15;
		
		aliensManager = new AliensManager(aliens,this,starting_x,starting_y,
				hor_gap,ver_gap,alien_width,alien_height,scorePanel,motherManager);
		aliensManager.setShipManager(shipManager);
		shipManager.setAliensManager(aliensManager);
	}
	
	private void initBlockManager() {
		int y_end = this.getWidth() - this.getInsets().bottom - ship.getHeight() - 10;
		int y_start = y_end - 50;
		int num_of_blocks = 4;
		blockManager = new BlockManager(shipManager,aliensManager,this,y_start,y_end,num_of_blocks);
	}
	
	private void pauseManagers() {
		shipManager.setActive(false);
		aliensManager.setActive(false);
		blockManager.setActive(false);
		motherManager.setActive(false);
		scorePanel.gameIsPaused();
	}
	
	private void unPauseManagers() {
		shipManager.setActive(true);
		aliensManager.setActive(true);
		blockManager.setActive(true);
		motherManager.setActive(true);
		scorePanel.gameIsUnpaused();
	}
	
	private class KeyboardListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			if (keycode == KeyEvent.VK_N)
				newGame();
			else if (keycode == KeyEvent.VK_P)
				pauseOrUnpause();
		}
	}
}