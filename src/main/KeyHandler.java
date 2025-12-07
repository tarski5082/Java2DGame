package main;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    private final List<Integer> directions = new ArrayList<>();
	
	public final int UP = 0;
    public final int DOWN = 1;
    public final int LEFT = 2;
    public final int RIGHT = 3;

	
	public int getDirection() {
		if(directions.isEmpty()) {
			return -1;
		}
		return directions.get(0);
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int dir=-1;
		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_UP: 
				dir=UP;
				break;
			case KeyEvent.VK_DOWN:
				dir=DOWN;
				break;
			case KeyEvent.VK_LEFT:
				dir=LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				dir=RIGHT;
				break;
		}
		if(dir!=-1 && !directions.contains(dir)) {
			directions.add(dir);
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		int dir=-1;
		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_UP: 
				dir=UP;
				break;
			case KeyEvent.VK_DOWN:
				dir=DOWN;
				break;
			case KeyEvent.VK_LEFT:
				dir=LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				dir=RIGHT;
				break;
		}
		directions.remove((Integer)dir);

	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
