import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoilyPanel extends JPanel{
int panelWidth = 400;
int panelHeight=500;	
int sector = 12;
boolean reflect = true;
int currentPointsize = 10;
Color currentColor  = Color.RED;
Color selectedColor = currentColor;
int selectedPointsize = currentPointsize;
boolean showLines = true;
String currentTool = "pen";
double degree = (double)360/(double)(sector);
drawingPanel drawingPanel = new drawingPanel();
controlPanel controlPanel = new controlPanel();
DoilyFrame frame;
DoilyPanel dp = null;
galleryPanel galleryPanel = new galleryPanel();
LinkedList<BufferedImage> bflist = new LinkedList<BufferedImage>();
LinkedHashMap<Ellipse2D, Tuple<Color, Boolean>> removedRecently = new LinkedHashMap<Ellipse2D, Tuple<Color, Boolean>>(); 
LinkedHashMap<Ellipse2D, Tuple<Color, Boolean>> map = new LinkedHashMap<Ellipse2D, Tuple<Color, Boolean>>();
	
	/* Doily Panel is used to store both the display panel and control panel, and the entire panel
	 * will be placed in a DoilyFrame which extends JFrame
	 */

	DoilyPanel(){
		dp = this;
		frame = new DoilyFrame();
		this.setPreferredSize(new Dimension(panelWidth+400, panelHeight));
		/* Border Layout is being used as the layout for the panel, with drawingPanel placed at the centre and the control 
		 * panel being placed at the east
		 */
		this.setLayout(new BorderLayout());
		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.EAST);
		
		// The current panel displayed is the doilyPanel
		frame.setPanel(this);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	A 2-tuple class is being created to store multiple values - Color for the points and the boolean
//	value which indicate whether the point is being reflected
	class Tuple<Color,Boolean> {
		public final Color x;
		public final Boolean y;
		public Tuple(Color x, Boolean y) {
			this.x = x;
			this.y = y;
		}
	}
	
//	An inner class which represent the controlPanel
	class controlPanel extends JPanel{
		
		controlPanel(){
//		List of JComponent created for the control panel
		JLabel title = new JLabel("Control Panel");
		JButton clear = new JButton("Clear The Display");
		JLabel text = new JLabel("Colour:");
		JButton colour = new JButton("Click here for color chooser");
		JLabel text1 = new JLabel("Size: " + currentPointsize);
//		JSlider allows the size to range from 1 to 25, with initial value of 10
		JSlider size = new JSlider(1, 25, 10);
//		JSlider allows the sectors to range from 12 to 50, with initial value of 12
		JSlider sectors = new JSlider(12, 50, 12);
		JLabel text2 = new JLabel("Number of sectors: " + sector);
		JToggleButton show = new JToggleButton("Showing the sector lines", true);
		JToggleButton reflectPoint = new JToggleButton("Reflecting the drawn points", true);
		JButton undo = new JButton("Undo");
		JButton redo = new JButton("Redo");
		JRadioButton pen = new JRadioButton("Pen", true);
		JRadioButton eraser = new JRadioButton("Eraser");
		ButtonGroup tool = new ButtonGroup();
		tool.add(pen);
		tool.add(eraser);
		JButton save = new JButton("Save The Display");
		
//		Whenever the save button is pressed, the save method
//		will be run and the gallery panel will run the refresh
//		method so that the gallery is displaying the updated
//		buffered image list
		save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((DoilyPanel.drawingPanel) drawingPanel).save();
				galleryPanel.dp = dp;
				galleryPanel.frame = frame;
				galleryPanel.bflist = bflist;
				galleryPanel.refresh();
			}
			
		});
		
//		Change the tool currently used to pen if the radio button
//		pen is selected as each tool will behave differently
		pen.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					currentTool = "pen";
				}
			}
			
		});
		
//		Change the tool currently used to eraser
		eraser.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					currentTool = "eraser";
				}
				
			}
			
		});
		
//		Whenever a change is detected in the JSlider, 
//		the text to display the sector will be changed
//		accordingly and the degree for reflection will
//		change as well according to the number of sector.
//		Then the panel should repaint in order to display
//		the updated drawing after rotating at a different
//		angle
		sectors.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				sector = (int)slider.getValue();
				text2.setText("Number of sectors: " + sector);
				degree = (double)360/(double)(sector);
				drawingPanel.repaint();
			}
		});

//		Change text and boolean value that store whether
//		a point is being reflected according to the status
//		of the toggle button reflectPoint
		reflectPoint.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TOO Auto-generated method stub
				if (reflectPoint.isSelected()) {
					reflectPoint.setText("Reflecting the drawn points");
					reflect = true;
				}else{
					reflectPoint.setText("Not reflecting the drawn points");
					reflect = false;
				}
			}
		});

//		Change text and boolean value that store whether
//		the sector lines are being shown according to the status
//		of the toggle button show. Repaint afterwards so that
//		the display changes 
		show.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (show.isSelected()) {
					show.setText("Showing the sector lines");
					showLines = true;
					drawingPanel.repaint();
				}else{
					show.setText("Not showing sector lines");
					showLines = false;
					drawingPanel.repaint();
				}
			}
		});
		
//		Text and currentPointsize will change according to 
//		the value of the JSlider size
		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				currentPointsize = slider.getValue();
				text1.setText("Size: " + currentPointsize);
			}
		});
		
		
//		According to the input from user to the JColorChooser, the
//		currentColor will store the new input so that the new points
//		being drawn will be drawn in the new colour
		colour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentColor = JColorChooser.showDialog(null,"Choose color", currentColor);
			}
			
		});

//		Once the clear button is being pressed, the points being
//		stored in the hash map will be removed, and the display
//		will be repainted to display nothing
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.clear();
				removedRecently.clear();
				drawingPanel.repaint();
			}
		});
		
//		Once the undo button is pressed, the last 10 points being stored in 
//		the "map" hashmap will be stored to a hashmap called removedRecently. Then
//		these 10 points will be removed from the map so that they are no longer display in
//		the panel. Repaint is called in order to refresh the display.
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i =1; i < 10; i++) {
					if (map.size()-i > 0) {
					Ellipse2D key = (Ellipse2D) map.keySet().toArray()[map.size()-i];
					removedRecently.put(key,map.get(key));
					map.remove(key);
					}else if(map != null) {
						removedRecently.putAll(map);
						map.clear();
						break;
					}
				}drawingPanel.repaint();				
			}
			
		});
		
//		Once the redo button is pressed, the last 10 elements in the removedRecently hashmap
//		will be placed back to the linked hashmap "map" and removed from the removedRecently hashmap.
//		Repaint the panel so that the points added back to the hashmap will be shown on the display
		redo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				for (int i =1; i < 10; i++) {
					if (removedRecently.size()-i > 0) {
					  Ellipse2D key = (Ellipse2D) removedRecently.keySet().toArray()[removedRecently.size()-i];
					  map.put(key, removedRecently.get(key));
					  removedRecently.remove(key);
					}else if (removedRecently != null){
						map.putAll(removedRecently);
						removedRecently.clear();
						break;
					}
				}drawingPanel.repaint();
				
			}
			
		});

//		Grid layout is being used to display the control panel,
//		with 10 rows and 1 column
		this.setLayout(new GridLayout(10, 1));
		Container c = new Container();
		c.setLayout(new FlowLayout());
		c.add(title);
		
		JButton gallery = new JButton("Go to gallery");
		
//		Once the gallery button is pressed, the frame will display
//		the gallery panel instead of the doily panel
		gallery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				galleryPanel.dp = dp;
				galleryPanel.frame = frame;
				galleryPanel.bflist = bflist;
				galleryPanel.refresh();
				frame.setPanel(galleryPanel);				
			}	
		});
		
		c.add(gallery);
		this.add(c);
		
		Container c1 = new Container();
		c1.setLayout(new FlowLayout());
		c1.add(text);
		c1.add(colour);
		this.add(c1);
		
		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		c2.add(text1);
		c2.add(size);
		this.add(c2);
		
		Container c3 = new Container();
		c3.setLayout(new FlowLayout());
		c3.add(text2);
		c3.add(sectors);
		this.add(c3);
		
		this.add(show);
		this.add(reflectPoint);
		
		Container c4 = new Container();
		c4.setLayout(new FlowLayout());
		c4.add(pen);
		c4.add(eraser);
		this.add(c4);
		
		Container c5 = new Container();
		c5.setLayout(new FlowLayout());
		c5.add(undo);
		c5.add(redo);
		this.add(c5);
		
		this.add(save);
		this.add(clear);
		}
		
		
	}
	
	
	class drawingPanel extends JPanel{
		
		drawingPanel(){
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		
//		adding mouse motion listener and mouse listener to the drawing panel in order to detect mouse events
		this.addMouseMotionListener(new mouseListener());
		this.addMouseListener(new mouseListener());
		}
		
		
		public void paint(Graphics g) {
			
			super.paint(g);
//			Translate the origin point from (0,0) to the centre of the panel	
			((Graphics2D)g).translate(this.getWidth()/2,this.getHeight()/2);
			drawEllipse(g, map);
//			if show lines, then show the sector lines by rotating a sector line from origin to
//			(0,360) at a degree (360/number of sectors)
			if (showLines) {
			for (int i=0; i < sector; i++) {
				g.setColor(Color.WHITE);
				g.drawLine(0, 0, 0, -360);
				((Graphics2D)g).rotate(Math.toRadians(degree));
			}
			}
			

		}
		
//		The save method is being used to save the current display panel graphics as a bufferedimage
//		and add the new image to the imagelist called bflist
		public void save() {
			BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D cg = image.createGraphics();
			this.paintAll(cg);
			if (bflist.size() < 12) {
			bflist.add(image);
			}else {
				JOptionPane.showMessageDialog(frame, "The gallery is already full!");
			}
		}
		
//		The method createEllipse is used to create an ellipse 2d according to the current
//		pointsize and the x and y coordiantes of the point. The new ellipse will then be used
//		as a key that maps to a tuple which stores the current colour and the boolean value
//		of whether the point is being reflected and being stored in a linked hash map called
//		map. Noticed that linked hashmap is used instead of hashmap, so that the sequence of 
//		the key-value pair added matters.
		public void createEllipse(Point p, int pointsize, Color color, boolean reflect){
				int x = (int)p.getX()-this.getWidth()/2;
				int y = (int)p.getY()-this.getHeight()/2;
				Ellipse2D circle = new Ellipse2D.Double(x, y, pointsize, pointsize);
				map.put(circle, new Tuple(color, reflect));	
		}
		
//		The method createReflectedEllipse is being used to create a reflected
//		ellipse in respect to the original ellipse2D being passed to the 
//		method. An ellipse being reflected in the y-axis is being created.
		public Ellipse2D createReflectedEllipse(Ellipse2D e2d){	
			int x = (int) e2d.getX();
			int y = (int) e2d.getY();
			int pointsize = (int) e2d.getWidth();
			Ellipse2D circle = new Ellipse2D.Double(-(x+pointsize), y, pointsize, pointsize);
			return circle;
		}
		
//		The method drawEllipse is being used to loop through the linked hash map and perform
//		the method drawPoint on each ellipse2d being stored in the map set based on the color
//		and boolean value being stored in the tuple.
		private void drawEllipse(Graphics g, HashMap<Ellipse2D, Tuple<Color, Boolean>> map) {
			for (Ellipse2D circle : map.keySet()) {
				Tuple<Color, Boolean> tuple = map.get(circle);
				drawPoints(g,tuple.x , circle, tuple.y);
			}
		}
		
//		The method drawPoints is used to draw the ellipse2d using the rotate method so that
//		the ellipse is being drawn in each sector at the same position. The point will be reflected 
//		in each sector as well if the reflect boolean value is true
		private void drawPoints(Graphics g, Color selected, Ellipse2D circle, boolean reflect) {
				
			if (reflect) {
					
					Ellipse2D reflectedCircle = createReflectedEllipse(circle);
					for (int i=0; i < sector; i++) {
						drawCircle(g, selected, circle);
						drawCircle(g, selected, reflectedCircle);
						((Graphics2D)g).rotate(Math.toRadians(degree));
					}
					
				}else {
					for (int i=0; i < sector; i++) {
						drawCircle(g, selected, circle);
						((Graphics2D)g).rotate(Math.toRadians(degree));
				}
				}
				}
		
//		The method drawCircle sets the color of the graphics and draw
//		the ellipse2d being passed to the method.
		private void drawCircle(Graphics g, Color c, Ellipse2D circle) {
			g.setColor(c);
			((Graphics2D) g).fill(circle);
		}
			
		
//		mouseListener is used to capture mouse event
		class mouseListener extends MouseAdapter{
			
//			Whenever the mouse is being pressed or dragged, depending on what is the current tool, if
//			it is a pen, then get the current point and create an ellipse with currentPointsize,
//			currentColor and reflect boolean value. Else if it is an eraser, change the color to
//			black. The eraser is being implemented in this way so that the color black will be the same
//			as the background color and therefore can cover up the points being drawn, simulating
//			the process of a point being "erased"
			
			public void mousePressed(MouseEvent e) {
				if (currentTool == "pen") {
				Point p=e.getPoint();
				createEllipse(p, currentPointsize, currentColor, reflect);
				repaint();
			}else if (currentTool == "eraser") {
				Point p= e.getPoint();
				createEllipse(p, currentPointsize, Color.BLACK, reflect);
			}
			}
			
			public void mouseDragged(MouseEvent e) {
				if (currentTool == "pen") {
				Point p = e.getPoint();
				createEllipse(p, currentPointsize, currentColor, reflect);
				repaint();
				} else if (currentTool == "eraser") {
					Point p = e.getPoint();
					createEllipse(p, currentPointsize, Color.BLACK, reflect);
					repaint();
				}
				
			}

		}
	}
}




