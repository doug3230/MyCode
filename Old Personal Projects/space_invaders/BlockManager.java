package space_invaders;

import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;

public class BlockManager extends GameSprite {

	//Constants
	//---------
	private static final int PIXEL_WIDTH = 10;
	private static final int PIXEL_HEIGHT = 10;
	
	//Data Members
	//------------
	private ShipManager shipManager;
	private AliensManager aliensManager;
	private Container parent;
	private ArrayList<RectSprite> pixels = new ArrayList<RectSprite>();
	private int y_start;
	private int y_end;
	private int num_of_blocks;
	
	//Constructor
	//-----------
	public BlockManager(ShipManager sm, AliensManager am, Container parent, int y_start, int y_end, int num_of_blocks) {
		shipManager = sm; aliensManager = am; this.parent = parent;
		this.y_start = y_start; this.y_end = y_end;
		this.num_of_blocks = num_of_blocks;
		initialiseBlocks();
	}
	
	//Methods
	//-------
	@Override
	public void paint(Graphics g) {
		for (RectSprite pixel : pixels)
			pixel.paint(g);
	}

	@Override
	public void update() {
		if (!isActive()) return;
		for (ShipBeamSprite beam : shipManager.getBeams())
			checkIfHitBy(beam);
		for (ShipBeamSprite beam : aliensManager.getBeams())
			checkIfHitBy(beam);
	}
	
	public void placeBlocks() {
		for (RectSprite sprite : pixels)
			sprite.restore();
	}
	
	public void destroyAllBlocks() {
		for (RectSprite sprite : pixels)
			sprite.suspend();
	}
 	
	//Private Methods
	//---------------
	private void initialiseBlocks() {
		int x_start = parent.getInsets().left;
		int x_end = parent.getWidth() - parent.getInsets().right;
		int x_gap = (x_end - x_start) / (2*num_of_blocks);
		
		int x = x_start;
		int i = 0;
		while (i < num_of_blocks) {
			for (int cur_x = x; cur_x < x + x_gap; cur_x += PIXEL_WIDTH)
				for (int cur_y = y_end - PIXEL_HEIGHT; cur_y >= y_start; cur_y -= PIXEL_HEIGHT)
					pixels.add(new RectSprite(cur_x,cur_y,PIXEL_WIDTH,PIXEL_HEIGHT));
			x += 2*x_gap;
			i++;
		}
		destroyAllBlocks();
	}
	private void checkIfHitBy(ShipBeamSprite beam) {
		boolean notHit = true;
		int i = 0;
		while (notHit && i < pixels.size()) {
			if (pixels.get(i).isActive() && beam.intersects(pixels.get(i))) {
				pixels.get(i).suspend();
				beam.suspend();
				notHit = false;
			} 
			i++;
		}
	}
	
}
