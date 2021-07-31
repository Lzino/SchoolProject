import javax.swing.*;

public class test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("User Defined Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//AdCard ad = new AdCard();
		//EventPanel ev = new EventPanel();
		testPanel t = new testPanel();
		
		frame.getContentPane().add(t);
		frame.pack();
		frame.setVisible(true);
	}
}