package tech_company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add_New_Employee extends JFrame {
    private JLabel titleLabel, departmentLabel, designationLabel, epfLabel, nameLabel, emailLabel;
    public static JLabel epfNoField;
    public static JComboBox<String> departmentComboBox;
    public static JComboBox<String> designationComboBox;
    public static JTextField nameField, emailField;
    public static JFrame Frame;
    
    //Used to find the active page
	public static Component frame(JFrame frame) {
		Frame = frame;
		return Frame;
	}

    public Add_New_Employee() {
    	setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelT = new JPanel();
        panelT.setBorder(new EmptyBorder(15, 40, 15, 40));
        panelT.setBackground(Color.decode("#006666"));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 40, 30, 40));
        panel.setLayout(new GridLayout(7,1,10,10));

        titleLabel = new JLabel("Employee Registration Form");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);

        departmentLabel = new JLabel("Department:");
        designationLabel = new JLabel("Designation:");
        epfLabel = new JLabel("EPF No:");
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");

        departmentComboBox = new JComboBox<>();
        designationComboBox = new JComboBox<>();

        epfNoField = new JLabel();
        nameField = new JTextField();
        emailField = new JTextField();

        //Button functions
        Buttons back = new HR_Back();
        Buttons save = new Save();
        frame(this);

        panelT.add(titleLabel);
        panel.add(epfLabel);
        panel.add(epfNoField);
        panel.add(departmentLabel);
        panel.add(departmentComboBox);
        panel.add(designationLabel);
        panel.add(designationComboBox);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(back.func(this));
        panel.add(save.func(this));

        //To add the Department_Name and designation_Name to JComboBox by retrieving it from database
        fetchAndPopulateDepartments();
        fetchAndPopulateDesignation();
        
        //to generate EPF_No
        Fetch_EPF.fetchEPF(epfNoField,this);  
        
        add(panelT, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    //To add the Department_Name and designation_Name to JComboBox by retrieving it from database
    private void fetchAndPopulateDepartments() {
        try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
            String sql = "SELECT Department_Name FROM departments";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String department = resultSet.getString("Department_Name");
                departmentComboBox.addItem(department);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }
    
    private void fetchAndPopulateDesignation() {
        try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
            String sql = "SELECT designation_Name FROM designation";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String designation = resultSet.getString("designation_Name");
                designationComboBox.addItem(designation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

}
