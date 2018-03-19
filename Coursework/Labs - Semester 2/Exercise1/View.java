//Quesiton one
import javax.swing.*;
import java.awt.*;
public class View {
	View(){
	JFrame window = new JFrame("Simple Submit Cancel Form");
	JPanel panel = new JPanel();
	Container c = new Container();
	window.setContentPane(panel);
	panel.setLayout(new GridLayout(2, 1));
	JButton submit = new JButton("Submit");
	JButton cancel = new JButton("Cancel");
	JTextField text = new JTextField("           ");
	panel.add(text);
	c.add(submit);
	c.add(cancel);
	c.setLayout(new FlowLayout());
	panel.add(c);
	
	window.setVisible(true);
	window.pack();
	}
	public static void main(String[] args){
		View myView = new View();
	}
	
}
