import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class DrawingPanel extends JPanel{
	ArrayList<RectangularShape> shape = new ArrayList<RectangularShape>();
	RectangularShape shapeClicked = null;
	boolean first = true;
	
	DrawingPanel(){
		this.setPreferredSize(new Dimension(400, 400));
		this.addMouseListener(new circleListener());
		Ellipse2D oval2 = new Ellipse2D.Double(0, 0, 50, 50);
		Ellipse2D oval3 = new Ellipse2D.Double(350, 350, 50, 50);
		Rectangle2D rec = new Rectangle2D.Double(100, 100, 200, 200);
		Rectangle2D rec2 = new Rectangle2D.Double(350, 0,50, 50);
		shape.add(rec);
		shape.add(rec2);
		shape.add(oval2);
		shape.add(oval3);
	}
	

	
	public boolean clickOnAShape(double x, double y) {
		boolean clicked = false;
		for (RectangularShape shapes : shape) {
			if (shapes.contains(x, y)) {
				clicked = true;
				shapeClicked = shapes;
				break;
			}
		}return clicked;
	}
	
	public void firstDraw(Graphics g) {

		for (RectangularShape shapes : shape) {
			int red = (int)(Math.random()*255);
			int green = (int)(Math.random()*255);
			int blue = (int)(Math.random()*255);
			g.setColor(new Color(red, blue, green));
			((Graphics2D) g).fill(shapes);
		}
	}

	class circleListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if ((e.getButton() == 1) && clickOnAShape(e.getX(), e.getY())) {
				repaint();
			}
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {	
		if (first) {
		firstDraw(g);
		first = !first;
		}else {
		updateG(g);
		}
	}
	
	public void updateG(Graphics g) {
		int red = (int)(Math.random()*255);
		int green = (int)(Math.random()*255);
		int blue = (int)(Math.random()*255);
		g.setColor(new Color(red, blue, green));
		((Graphics2D)g).fill(shapeClicked);
	}
	
	public static void main(String[] args) {
		DrawingPanel dp = new DrawingPanel();
		JFrame window = new JFrame();
		window.setContentPane(dp);
		window.setVisible(true);
		window.pack();
		
	}
	
}
