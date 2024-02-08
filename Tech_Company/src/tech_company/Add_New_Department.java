package tech_company;

import javax.swing.*;
import javax.swing.border.*;
import components.*;
import java.awt.*;

public class Add_New_Department extends JFrame {
    private JLabel titleLabel, nameLable, descriptionLabel;
    private static JTextField nameField, descriptionField;
    public static JFrame Frame;
   
    //Used to find the active page
    public static Component frame(JFrame frame) {
    	Frame = frame;
		return Frame;
	}
    
    public static void getValues() {
    	Save.departmentName = nameField.getText();
    	Save.departmentDescription = descriptionField.getText();    	
    }

    public Add_New_Department() {
        setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelT = new JPanel();
        panelT.setBorder(new EmptyBorder(15, 40, 15, 40));
        panelT.setBackground(Color.decode("#006666"));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 40, 30, 40));
        panel.setLayout(new GridLayout(4,1,10,10));
        panel.setBackground(Color.WHITE);

        titleLabel = new JLabel("New Department");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);

        nameLable = new JLabel("Department Name:");
        descriptionLabel = new JLabel("Department Description:");

        nameField = new JTextField();
        descriptionField = new JTextField();
        
        //Button functions
        Buttons save = new Save(); 
        Buttons back = new HR_Back();
        
        frame(this);
        
        panelT.add(titleLabel);
        panel.add(nameLable);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(back.func(this));
        panel.add(save.func(this));
        
        add(panelT, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    //To clear the previous data which was saved successfully.
    public static void clearFields() {
    		nameField.setText("");
	    	descriptionField.setText("");
        }

}
