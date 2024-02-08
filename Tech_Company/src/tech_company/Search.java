package tech_company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import components.*;
import components.DB_Connection;
import java.awt.*;
import java.sql.*;

public class Search extends JFrame {
    private JTextField searchField;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public Search() {
    	setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension panelSize = new Dimension(800, 400);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel panelT = new JPanel();
        panelT.setBorder(new EmptyBorder(15, 40, 15, 40));
        panelT.setBackground(Color.decode("#006666"));
        
        JLabel titleLabel = new JLabel("Search Employee Details");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new EmptyBorder(10, 40, 10, 40));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchEmployeeData(searchField.getText()));
        searchButton.setBackground(Color.decode("#b1b0b0"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        Buttons back = new HR_Back();
        Buttons back2 = new Logout_Back();

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make ID, Department, Designation,EPF_No, Name and Email columns non-editable
            	return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };
        
        dataTable = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Department");
        tableModel.addColumn("Designation");
        tableModel.addColumn("EPF_No");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        
        panelT.add(titleLabel);
        panel.add(panelT);
        
        panel.add(searchPanel);

        JScrollPane scrollPane = new JScrollPane(dataTable);    
        scrollPane.setPreferredSize(panelSize);

        panel.add(scrollPane);
        
        add(panel);
        
        
        if(Login.HR == true) {
        	add(back.func(this),BorderLayout.SOUTH);
        } else {
        	add(back2.func(this),BorderLayout.SOUTH);
        }
       
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        loadAllEmployeeData();
    }

    private void loadAllEmployeeData() {
        // Clear the table
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // Perform database connection and fetch all records
        try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
            String sql = "SELECT E.ID, D.Department_Name, DG.Designation_Name, E.EPF_No, E.Name, E.Email " +
                    "FROM employees E " +
                    "INNER JOIN departments D ON E.Department = D.ID " +
                    "INNER JOIN designation DG ON E.Designation = DG.ID ORDER BY E.ID";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] row = new Object[6];
                for (int i = 1; i <= 6; i++) {
                    row[i - 1] = " " + resultSet.getString(i);
                }
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    private void searchEmployeeData(String searchText) {
        // Clear the table
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // Perform database connection and search
        try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
            String sql = "SELECT E.ID, D.Department_Name, DG.Designation_Name, E.EPF_No, E.Name, E.Email " +
                    "FROM employees E " +
                    "INNER JOIN departments D ON E.Department = D.ID " +
                    "INNER JOIN designation DG ON E.Designation = DG.ID " +
                    "WHERE E.ID LIKE ? OR D.Department_Name LIKE ? OR DG.Designation_Name LIKE ? OR E.EPF_No LIKE ? OR E.Name LIKE ? OR E.Email LIKE ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= 6; i++) {
                preparedStatement.setString(i, "%" + searchText + "%");
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {    	
                Object[] row = new Object[6];
                for (int i = 1; i <= 6; i++) {
                    row[i - 1] = " " + resultSet.getString(i);
                }
                tableModel.addRow(row);
            }
            
            if(tableModel.getRowCount() == 0) {
            	JOptionPane.showMessageDialog(this," Results Not Found \n Please double check your input!");
            	searchField.setText("");
            	loadAllEmployeeData();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }
    
}
