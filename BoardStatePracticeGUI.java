import javax.swing.JFrame;

public class BoardStatePracticeGUI {
	
	public enum cardTypes {Creature, Instant, Sorcery, Enchantment, Artifact, Planeswalker};

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Board State Practice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoardStatePanel panel = new BoardStatePanel();
		frame.getContentPane().add(panel);	
		
		frame.pack();
		frame.setVisible(true);

	}

}
