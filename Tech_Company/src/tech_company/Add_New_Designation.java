package tech_company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import components.*;
import java.awt.*;

public class Add_New_Designation extends JFrame{
    private JLabel titleLabel, nameLable, descriptionLabel;
    private static JTextField nameField, descriptionField;
	public static JFrame Frame;
	
    //Used to find the active page
	public static Component frame(JFrame frame) {
		Frame = frame;
		return Frame;
	}
	
    public static void getValues() {
    	Save.DesignationName = nameField.getText();
    	Save.DesignationDescription = descriptionField.getText();    	
    }

    public Add_New_Designation() {
        setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelT = new JPanel();
        panelT.setBorder(new EmptyBorder(15, 40, 15, 40));
        panelT.setBackground(Color.decode("#006666"));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 40, 30, 40));
        panel.setLayout(new GridLayout(4,1,10,10));

        titleLabel = new JLabel("New Designation");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);

        nameLable = new JLabel("Designation Name:");
        descriptionLabel = new JLabel("Designation Description:");

        nameField = new JTextField();
        descriptionField = new JTextField();

     	//Button functions
        Buttons back = new HR_Back();
        Buttons save = new Save();
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
