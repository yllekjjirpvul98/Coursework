import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class galleryPanel extends JPanel{
	
	int panelWidth;
	int panelHeight;
	
	Rectangle2D box;
	boolean selected = false;
	JLabel currentSelected;
	LinkedList<JLabel> jlabels = new LinkedList<JLabel>();
	DoilyPanel dp;
	DoilyFrame frame;
	LinkedList<BufferedImage> bflist;
	
	galleryPanel(){
		this.setPreferredSize(new Dimension (800,500));
		Container c1 = new Container();
		//The gallery is represented in a grid layout with 3 rows and 4 columns
		c1.setLayout(new GridLayout(3, 4));
		c1.setPreferredSize(new Dimension(800, 450));
		panelWidth = 800;
		panelHeight = 450;
		
//		Add 12 jlabels to the jlabel list
		for (int i=0; i < 12; i++) {
			JLabel j = new JLabel();
			jlabels.add(j);
		}
		
//		Add imageListener to all 12 jlabels using a for loop and add each jlabel to the container
		for (JLabel j : jlabels) {
			j.addMouseListener(new imageListener(j));
			c1.add(j);
		}
		
//		Set currentSelected to the first jLabel
		currentSelected = jlabels.getFirst();
		
		//The lower part of the panel (buttons) is represented using flow layout 
		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		JButton delete = new JButton("Delete");
		c2.add(delete);
		JButton Return = new JButton("Return");
		c2.add(Return);
		
//		If the delete button is pressed, it should run the deleteImage method
		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				deleteImage();
			}
		});
		
//		If return button is pressed, the frame should jump back to the doily panel
		Return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setPanel(dp);
			}
			
		});
		
//		The overall layout will be border layout, with the gallery container at the centre and
//		the lower part(buttons) at the southern part
		this.setLayout(new BorderLayout());
		this.add(c1, BorderLayout.CENTER);
		this.add(c2, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
//	The refresh method is used to reset the icon of the jlabels once there are changes in the buffered image list, so that
//	the old icon will be replaced and the position of the icons will change accordingly 
	public void refresh() {
		if (bflist.size() != 0) {
		for (int i=0; i < bflist.size(); i++) {
			Icon image = new ImageIcon(bflist.get(i));
			Image img = ((ImageIcon) image).getImage();
			Image newimg = img.getScaledInstance(panelWidth/4, panelHeight/3, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(newimg);
			jlabels.get(i).setIcon(image);
		}
		}
	}

//	The imageListener is used to detect whenever the user press on the jlabel which means that
//	the jlabel is being selected, and a Rectangular 2D box will be created to surround the
//	jlabel
	class imageListener extends MouseAdapter{
		JLabel img;
		imageListener(JLabel img){
			this.img = img;
		}
		
		public void mouseClicked(MouseEvent e) {
				selected = true;
				currentSelected = img;
				box = new Rectangle2D.Double(img.getX(), img.getY(), panelWidth/4, panelHeight/3);
				repaint();
		}
	}
	
//	The deleteImage method is being used to remove the selected image from the buffered image
//	list and set the last jlabel in the list to null, so that when the bufferedlist is refreshed,
//	the previous icon will not remain. If the user try to remove image from a jlabel without icon,
//	it will display a warning message
	private void deleteImage() {
		JLabel current = currentSelected;
		
		try {
		
		for (int i=0; i < jlabels.size(); i++) {
			if (current == jlabels.get(i)) {
				bflist.remove(i);
				jlabels.get(bflist.size()).setIcon(null);;
				break;
			}
		}refresh();
		
		}catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(frame, "There is no image at this position!");
		}
		
	}
	
//	The paint method will be responsible to paint out the rectangular 2D box surrounding a jlabel, if there is no
//	jlabel box being selected, the default setting is that the first jLabel will be "selected", else the position of
//	the box will vary according to the jlabel selected
	public void paint(Graphics g) {
		super.paint(g);
		if (selected)
		drawBox(g, box);
		else {
			box = new Rectangle2D.Double(jlabels.getFirst().getX(), jlabels.getFirst().getY(), panelWidth/4, panelHeight/3);
			drawBox(g, box);
		}
	}
	
//	The method drawBox is used to draw a yellow rectangle with stroke size of 5
//	in respect to the position, width and height of a rectangular 2d box passed into the method
	private void drawBox(Graphics g, Rectangle2D box) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(), (int)box.getHeight());
	}
	
}
	
