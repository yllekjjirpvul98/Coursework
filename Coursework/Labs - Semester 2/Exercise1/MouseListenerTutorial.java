import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseListenerTutorial {
	public void init() {
		JFrame window = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		Container c1 = new Container();
	    JPanel j1 = new JPanel();
	    JPanel j2 = new JPanel();
	    JPanel j3 = new JPanel();
	    j1.setLayout(null);
	    j2.setLayout(null);
	    j3.setLayout(null);
	    j1.setBounds(0, 0, 300, 300);
	    j2.setBounds(300, 0, 200, 200);
	    j3.setBounds(500,30,50, 50);
	    j1.setBackground(Color.red);
	    j2.setBackground(Color.YELLOW);
	    j3.setBackground(Color.GREEN);
	    c1.add(j1);
	    c1.add(j2);
	    c1.add(j3);
		panel.add(c1, BorderLayout.CENTER);
	    
	    JLabel text = new JLabel();
	    Container c2 = new Container();
	    c2.setLayout(new FlowLayout());
	    c2.add(text);
	    panel.add(c2, BorderLayout.SOUTH);
	    j1.addMouseMotionListener(new PanelMouseListener(text, j1));
		j2.addMouseMotionListener(new PanelMouseListener(text, j2));
		j3.addMouseMotionListener(new PanelMouseListener(text, j3));
	    window.pack();
	    window.setContentPane(panel);
	    window.setVisible(true);
	    
	    
	}
	public static void main(String[] args)
	{
		MouseListenerTutorial ml = new MouseListenerTutorial();
		ml.init();
	}
}

class PanelMouseListener extends MouseAdapter{
	JLabel jl;
	JPanel panel;

	int dX;
	int dY;
	
	PanelMouseListener(JLabel jl, JPanel panel){
		this.jl = jl;
		this.panel = panel;
	}
	public void mousePressed(MouseEvent e) {
			dX = e.getLocationOnScreen().x - panel.getX(); //get corner coordinates of panel
			dY = e.getLocationOnScreen().y - panel.getY();
	}
	public void mouseDragged(MouseEvent e) {
			panel.setLocation(e.getLocationOnScreen().x - dX, e.getLocationOnScreen().y - dY);
			dX = e.getLocationOnScreen().x - panel.getX();
			dY = e.getLocationOnScreen().y - panel.getY();
	}
	public void mouseMoved(MouseEvent e) {
		Integer x = e.getX();
		Integer y = e.getY();
		jl.setText("x: " + x.toString() + ", y: " + y.toString());
	}
}