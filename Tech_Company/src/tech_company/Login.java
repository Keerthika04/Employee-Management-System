package tech_company;

import javax.swing.*;
import javax.swing.border.*;
import components.DB_Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public static boolean HR;

    public Login() {
        setTitle("The Tech Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelT = new JPanel();
        panelT.setBorder(new EmptyBorder(15, 40, 15, 40));
        panelT.setBackground(Color.decode("#006666"));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 40, 30, 40));
        panel.setLayout(new GridLayout(4,1,10,10));
        
        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.decode("#b1b0b0"));
        
        panelT.add(titleLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panelT, BorderLayout.NORTH);       
        add(panel, BorderLayout.SOUTH);        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Login Method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if(username.equals("HR@Tc") && password.equals("HR@tc123")) {
            	dispose();
            	HR = true;
                new HR();
            } else {
            try (Connection conn = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
                String sql = "SELECT * FROM hr_assistant WHERE Username = ? AND Password = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                	dispose();
                	HR = false;
                    new Search();
                } else {
                    JOptionPane.showMessageDialog(this, "Login Failed! Please check your username and password.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            }
        }
        }
    }

    public static void main(String[] args) {
        Login enter = new Login();
        
//					or
//        SwingUtilities.invokeLater(() -> {
//            new Login();
//        });
    }
}
