package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

import tech_company.HR;

//Back button which will take back to HR main page
public class HR_Back extends Buttons{

			@Override
			public Component func(JFrame Frame) {
				JButton back = new JButton("Back");
				back.setBackground(Color.decode("#b1b0b0"));
				
				back.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	Frame.dispose();
		                new HR();
		            }
				
			});
			return back;	          
}

}