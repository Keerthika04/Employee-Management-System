package tech_company;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import components.*;

public class HR extends JFrame {
    private JButton AddNewDepartment, AddNewDesignation, AddNewEmployee, AddNewHRA, Search;

    public HR() {
        setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelT = new JPanel();
        panelT.setBackground(Color.decode("#006666"));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 40, 30, 40));
        panel.setLayout(new GridLayout(6,1,10,10));
        panel.setBackground(Color.WHITE);

        AddNewDepartment = new JButton("Add New Department");
        AddNewDesignation = new JButton("Add New Designation");
        AddNewEmployee = new JButton("Add New Employee");
        AddNewHRA = new JButton("Add New HR Assistant");
        Search = new JButton("Search");
        
        AddNewDepartment.setBackground(Color.decode("#b1b0b0"));
        AddNewDesignation.setBackground(Color.decode("#b1b0b0"));
        AddNewEmployee.setBackground(Color.decode("#b1b0b0"));
        AddNewHRA.setBackground(Color.decode("#b1b0b0"));
        Search.setBackground(Color.decode("#b1b0b0"));
        AddNewDepartment.setFocusPainted(false);
        
        JLabel title = new JLabel("HR");
        title.setFont(new Font("Verdana",Font.BOLD,26));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        
        //Button Functions
        AddNewDepartment.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Add_New_Department();
            }
        });
        
        AddNewDesignation.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new Add_New_Designation();
        	}
        });
        
        AddNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Add_New_Employee();				
			}        	
        });
        
        AddNewHRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Add_New_HR_Assistant();				
			}        	
        });
        
        Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Search();		
			}        	
        });
        
        Buttons logout = new Logout_Back(); 
        
        panelT.add(title);
        panel.add(AddNewDepartment);
        panel.add(AddNewDesignation);
        panel.add(AddNewEmployee);
        panel.add(AddNewHRA);
        panel.add(Search);
        panel.add(logout.func(this));
        
        add(panelT, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
