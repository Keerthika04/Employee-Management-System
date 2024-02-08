package tech_company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import components.*;
import java.awt.*;

public class Add_New_HR_Assistant extends JFrame {
    private JLabel titleLabel, epfNoLabel, nameLabel, emailLabel, usernameLabel, passwordLabel;
    public static JTextField nameField, emailField, usernameField;
    public static JPasswordField passwordField;
	public static JLabel epfNoField;
    public static JFrame Frame;
    
    //Used to find the active page
	public static Component frame(JFrame frame) {
		Frame = frame;
		return Frame;
	}

    public Add_New_HR_Assistant() {
        setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelT = new JPanel();
        panelT.setBorder(new EmptyBorder(15, 40, 15, 40));
        panelT.setBackground(Color.decode("#006666"));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 40, 30, 40));
        panel.setLayout(new GridLayout(7,1,10,10));

        titleLabel = new JLabel("Add New HR Assistant");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);

        epfNoLabel = new JLabel("EPF No:");
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        epfNoField = new JLabel();
        nameField = new JTextField();
        emailField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        
        //Button functions
        Buttons back = new HR_Back();
        Buttons save = new Save();
        frame(this);

        panelT.add(titleLabel);
        panel.add(epfNoLabel);
        panel.add(epfNoField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(back.func(this));
        panel.add(save.func(this));
        
        //to generate EPF_No
        Fetch_EPF.fetchEPF(epfNoField,this);   
        frame(this);
        
        add(panelT, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
