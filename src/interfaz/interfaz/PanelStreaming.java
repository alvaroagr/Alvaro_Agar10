package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PanelStreaming extends JPanel implements ActionListener{
	
	public final static String STREAMING = "Streaming";
	
	private JButton streaming;

	private InterfazPrincipalLogin p;
	public PanelStreaming(InterfazPrincipalLogin principal) {
		// TODO Auto-generated constructor stub
		p = principal;
		this.setBorder(new TitledBorder(new EtchedBorder(),"Streaming"));
		this.setBackground(Color.lightGray);
		setSize(450,321);
		
		streaming = new JButton ("STREAMING");
        streaming.setSize(new Dimension(200,100));
        streaming.setAlignmentX(CENTER_ALIGNMENT);
        streaming.setAlignmentY(CENTER_ALIGNMENT);
	    streaming.addActionListener(this);
	    streaming.setActionCommand(STREAMING);
		
	    add(streaming);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(STREAMING)) {
			p.iniciarStreaming();
		}
	}

}
