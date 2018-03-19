import javax.swing.JFrame;
import javax.swing.JPanel;

public class DoilyFrame extends JFrame{
	
	JPanel contentPane = (JPanel) this.getContentPane();
//	The setPanel method is being used to set which panel to be displayed in the DoilyFrame depending on 
//	which panel is being passed into the method
	public void setPanel(JPanel jp) {
		contentPane.removeAll();
		contentPane.add(jp);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	
}