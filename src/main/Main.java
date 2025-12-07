package main;
import javax.swing.JFrame;
import java.util.Arrays;
public class Main {

	public static void main(String[]args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Treasure Hunting");
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		gamePanel.startGameThread();
		System.out.println(Arrays.toString(gamePanel.tileM.mapTileNum));
	}
}
