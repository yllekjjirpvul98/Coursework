//Tutorial 2 - Question 1
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Button {
	public int counter = 0;
	public void init() {
		JFrame window = new JFrame();
		JButton increment = new JButton("Increment");
		JButton reset = new JButton("Reset");
		JTextField text = new JTextField("       ");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(text);
		Container contain = new Container();
		contain.setLayout(new FlowLayout());
		contain.add(increment);
		contain.add(reset);
		panel.add(contain);
		window.setContentPane(panel);
		window.setVisible(true);
		window.pack();
		IncrementListener il = new IncrementListener(text, counter);
		increment.addActionListener(il);
		reset.addActionListener(new ResetListener(text, counter, il));
	}
	public static void main(String[] args) {
		Button b = new Button();
		b.init();
	}
}

class IncrementListener implements ActionListener{
	public JTextField text;
	public int counter = 0;
	public JButton b;
	public IncrementListener(JTextField text, int counter){
		this.text = text;
		this.counter = counter;
		this.b = b;
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		counter += 1;
		text.setText(new Integer(counter).toString());
	}
}

class ResetListener implements ActionListener{
	public JTextField text;
	public int counter;
	public IncrementListener il;
	public ResetListener(JTextField text, int counter, IncrementListener il){
		this.text = text;
		this.il = il;
	}
	public void actionPerformed(ActionEvent e) {
		counter = 0;
		il.counter = 0;
		text.setText(new Integer(counter).toString());
	}
}

