package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import tech_company.Login;

//Logout button which will take back to Login page
public class Logout_Back extends Buttons{

			@Override
			public Component func(JFrame Frame) {
				JButton back = new JButton("Logout");
				back.setBackground(Color.decode("#006666"));
				back.setForeground(Color.WHITE);
				
				back.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	Frame.dispose();
		                new Login();
		            }
		           
				
			});
			
			return back;	          
}

}