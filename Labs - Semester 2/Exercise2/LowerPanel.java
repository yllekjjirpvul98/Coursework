import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class LowerPanel extends JPanel{
		ClockFace cf = new ClockFace();
		Graphics g = cf.getGraphics();
		
		LowerPanel(){
		JLabel t1 = new JLabel("Hour: ");
		JLabel t2 = new JLabel("Minute: ");
		JLabel t3 = new JLabel("Seconds: ");
		SpinnerModel smh = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerModel smm = new SpinnerNumberModel(1, 1, 60, 1);
		SpinnerModel sms = new SpinnerNumberModel(1, 1, 60, 1);
		JSpinner hour = new JSpinner(smh);
		JSpinner min = new JSpinner(smm);
		JSpinner sec = new JSpinner(sms);
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
		c3.add(min);
		this.add(c1);
		this.add(c2);
		this.add(c3);
		hour.addChangeListener(new timeListener(hour, min, sec));
		min.addChangeListener(new timeListener(hour, min, sec));
		sec.addChangeListener(new timeListener(hour, min, sec));
		
		}
		public class timeListener implements ChangeListener{
			JSpinner t;
			JSpinner t1;
			JSpinner t2;
			timeListener(JSpinner t, JSpinner t2, JSpinner t3){
				this.t= t;
				this.t1 = t2;
				this.t2 = t3;
			}
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				cf.setTime(g, new Integer((int) t.getValue()), new Integer((int) t1.getValue()), new Integer((int) t2.getValue()));
		}
		}

}