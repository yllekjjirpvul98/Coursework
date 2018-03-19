import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ClockFace extends JPanel{

	int vhour = 12;
	int vmin = 0;
	int vsec = 0;
	
	ClockFace(){
		this.setPreferredSize(new Dimension(400, 400));
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		JFrame window = new JFrame();
		main.add(this, BorderLayout.CENTER);
		main.add(new LowerPanel(), BorderLayout.SOUTH);
		window.setContentPane(main);
		window.setVisible(true);
		window.pack();
	}

	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawOval(50, 50, 300, 300);
		g.translate(200,200);
		setTime(g, vhour, vmin, vsec);
		for (int i=0; i<13; i++) {
			g.setColor(Color.BLACK);
			((Graphics2D) g).setStroke(new BasicStroke(1));
			if (i != 0 && i < 7) {
				g.drawString(new Integer(i+6).toString(),0 , 90);
	            }else if (i != 0) g.drawString(new Integer(i-6).toString(),0 , 90);
            if (i%3 == 0)g.drawLine(0, 150, 0, 100);
            else g.drawLine(0, 150, 0, 130);
            ((Graphics2D) g).rotate(Math.toRadians(30));
		}	
		
		
		
	}
	
	public void setTime(Graphics g,int h, int m, int s) {
		double ihour = new Double(h)/12*360 + 180;
		double imin =  new Double(m)/60*360 + 180;
		double isec = new Double(s)/60*360 + 180;

		Graphics2D g2d = (Graphics2D)g;
		Line2D hour = new Line2D.Double(0, 0, 0, 30);
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(ihour), hour.getX1(), hour.getY1());
		g2d.setStroke(new BasicStroke(10));
		g2d.draw(at.createTransformedShape(hour));
		Line2D min = new Line2D.Double(0, 0, 0, 60);
		AffineTransform at2 = AffineTransform.getRotateInstance(Math.toRadians(imin), min.getX1(), min.getY1());
		g2d.setStroke(new BasicStroke(5));
		g2d.draw(at2.createTransformedShape(min));
		Line2D sec = new Line2D.Double(0,0,0,90);
		AffineTransform at3 = AffineTransform.getRotateInstance(Math.toRadians(isec), sec.getX1(), sec.getY1());
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(at3.createTransformedShape(sec));
	
	}
	public class LowerPanel extends JPanel{
	
		LowerPanel(){
		JLabel t1 = new JLabel("Hour: ");
		JLabel t2 = new JLabel("Minute: ");
		JLabel t3 = new JLabel("Seconds: ");
		JSlider hour = new JSlider(1, 12, 1);
		JSlider min = new JSlider(0, 60, 1);
		JSlider sec = new JSlider(0, 60, 1);
		this.setLayout(new GridLayout(3,1));
		Container c1 = new Container();
		c1.setLayout(new FlowLayout());
		c1.add(t1);
		c1.add(hour);
		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		c2.add(t2);
		c2.add(min);
		Container c3 = new Container();
		c3.setLayout(new FlowLayout());
		c3.add(t3);
		c3.add(sec);
		this.add(c1);
		this.add(c2);
		this.add(c3);
		hour.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				vhour = slider.getValue();
				ClockFace.this.repaint();
			}
		});
		min.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				vmin = slider.getValue();
				ClockFace.this.repaint();
			}
		});
		sec.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				vsec = slider.getValue();
				ClockFace.this.repaint();
				
			}
		});
		
		}
	}

		
		public static void main(String[] args) {
			ClockFace cf = new ClockFace();
		}
}



