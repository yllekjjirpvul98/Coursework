import java.awt.*;
import javax.swing.*;

public class Die extends JPanel{
	int rollValue = 0;
	Die(){
		rollValue = 0;
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRoundRect(50, 50, 400,400,50,50);
		g.setColor(Color.BLACK);
		switch(rollValue) {
		case 1: drawOneCentralDot(g);
		        break;
		        
		case 2: drawCorner(g);		        
		        break;
		        
		case 3: drawCorner(g);
				drawOneCentralDot(g);
				break;
				
		case 4: drawUpperDots(g);
				drawLowerDots(g);
				break;
		
		case 5: drawLowerDots(g);
				drawUpperDots(g);
				drawOneCentralDot(g);
				break;
				
		case 6: drawUpperDots(g);
				drawLowerDots(g);
				g.fillOval(100,200,100,100);
				g.fillOval(300, 200 ,100, 100);
				break;	
		}

	}
	
	public void drawCorner(Graphics g) {
		 g.fillOval(300,100, 100, 100);
	     g.fillOval(100,300, 100, 100);
	}
	
	public void drawUpperDots(Graphics g) {
		g.fillOval(100, 100, 100, 100);
		g.fillOval(300,100, 100, 100);
	}
	
	public void drawLowerDots(Graphics g) {
		g.fillOval(100,300, 100, 100);
		g.fillOval(300,300, 100, 100);
	}
	
	public void drawOneCentralDot(Graphics g) {
		g.fillOval(200, 200, 100, 100);
	}
	
	public void setRollValue(int i) {
		rollValue = i;
		repaint();
	}
	
	public void updateVal(int i) {
		setRollValue(i);
	}
	
	public static void main(String[] args) {
		Die die = new Die();
		die.updateVal(3);
		JFrame window = new JFrame();
		window.setContentPane(die);
		window.setVisible(true);
		window.pack();
	}
}
