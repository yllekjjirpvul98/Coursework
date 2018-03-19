//Question 2
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUI {
	String font;
	String style;

 GUI(){
	 JFrame window = new JFrame("Font Chooser");
	 JCheckBox cb1 = new JCheckBox("Bold");
	 JCheckBox cb2 = new JCheckBox("Italic");
	 JTextField textbox = new JTextField("                         ");
	 JButton ok = new JButton("OK");
	 String[] font = {"Times", "Helvetica", "Courier"};
	 JComboBox jcb = new JComboBox(font); //checklist
	 JPanel panel = new JPanel();
	 Font fonts = textbox.getFont();
	 window.setContentPane(panel);
	 panel.setLayout(new FlowLayout());
	 Container c1 = new Container();
	 c1.setLayout(new GridLayout(2,1));
	 c1.add(cb1);
	 c1.add(cb2);
	 panel.add(c1);
	 panel.add(jcb);
	 panel.add(textbox);
	 panel.add(ok);
	 
	 ItalicCheckboxListener icl = new ItalicCheckboxListener(textbox, this, cb1, fonts);
	 BoldCheckboxListener bcl =  new BoldCheckboxListener(textbox, this, cb2, fonts);

	 
	 ok.addActionListener((ActionListener) new OKListener(window, this));
	 cb1.addItemListener((ItemListener)bcl);
	 cb2.addItemListener(icl);
	 jcb.addItemListener(new ComboBoxListener(textbox, jcb, this, cb1, cb2));
	 window.setVisible(true);
	 window.pack();	 
 }
 public static void main(String[] args){
	 GUI myGUI = new GUI();
 }
}

class OKListener implements ActionListener {
	JFrame window;
	GUI gui;
	
	OKListener(JFrame window, GUI gui){
		this.window = window;
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.dispose();
		String texts = gui.font + gui.style;
		System.out.println(texts);	
	}
}

class BoldCheckboxListener implements ItemListener {
	public JTextField text;
	public GUI gui;
	public JCheckBox cb1;
	public Font font;
	BoldCheckboxListener(JTextField textbox, GUI gui, JCheckBox cb1, Font fonts){
		this.text = textbox;
		this.gui = gui;
		this.cb1 = cb1;
		this.font = fonts;
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			gui.font = cb1.isSelected()? "Bold Italic " : "Bold ";
			int font = cb1.isSelected()? Font.BOLD+Font.ITALIC : Font.BOLD;
			String texts = gui.font + gui.style;
			this.font = new Font(gui.style, font, 12);
			text.setText(texts);
			text.setFont(this.font);
		}if (e.getStateChange() == ItemEvent.DESELECTED) {
		    gui.font = cb1.isSelected() ?"Italic " : "";
			String texts = gui.font + gui.style;
			int font = cb1.isSelected()? Font.ITALIC : 0;
			this.font = new Font(gui.style, font, 12);
			text.setText(texts);
			text.setFont(this.font);
		}
	}
}

class ItalicCheckboxListener implements ItemListener {
	public JTextField text;
	public GUI gui;
	JCheckBox cb2;
	Font font;
	
	ItalicCheckboxListener(JTextField textbox, GUI gui, JCheckBox cb2, Font fonts){
		this.text = textbox;
		this.gui = gui;
		this.cb2 = cb2;
		this.font = fonts;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			gui.font = cb2.isSelected()? "Bold Italic " : "Italic ";
			int font = cb2.isSelected()? Font.BOLD+Font.ITALIC : Font.ITALIC;
			String texts = gui.font + gui.style;
			this.font = new Font(gui.style, font, 12);
			text.setText(texts);
			text.setFont(this.font);
		}if (e.getStateChange() == ItemEvent.DESELECTED) {
			gui.font = cb2.isSelected() ? "Bold ":"";
			int font = cb2.isSelected()? Font.BOLD : 0;
			String texts = gui.font + gui.style;
			this.font = new Font(gui.style, font, 12);
			text.setText(texts);
			text.setFont(this.font);
		}
	}
}
class ComboBoxListener implements ItemListener{
	public JTextField text;
	public JComboBox jcb;
	public GUI gui;
	public JCheckBox cb1;
	public JCheckBox cb2;
	
	ComboBoxListener(JTextField textbox, JComboBox jcb, GUI gui, JCheckBox cb1, JCheckBox cb2){
		this.text = textbox;
		this.jcb = jcb;
		this.gui = gui;
		this.cb1 = cb1;
		this.cb2 = cb2;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			gui.style = jcb.getSelectedItem().toString();
			String texts = gui.font + gui.style;
			text.setText(texts);
			int font = 0;
			if (cb1.isSelected() && cb2.isSelected()) {
				font = Font.BOLD + Font.ITALIC;
			}else if (cb1.isSelected()) font = Font.BOLD;
			else if (cb2.isSelected()) font = Font.ITALIC;
			else font = 0;
			text.setFont(new Font(gui.style, font, 12));
			
    }
	}
}