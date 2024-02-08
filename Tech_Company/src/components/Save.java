package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import tech_company.*;


public class Save extends Buttons {

	//Used getter method to take private variables and store in the below variables.
    public static String departmentName;
    public static String departmentDescription;
    public static String DesignationName;
    public static String DesignationDescription;
	
	@Override
	public Component func(JFrame Frame) {
		
		JButton save = new JButton("Save");
		save.setBackground(Color.decode("#b1b0b0"));
		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	
					//If Add_New_Department page was active it will execute the below save function.
			        if(Frame == Add_New_Department.Frame) {
			        		Add_New_Department.getValues();
			        	
			        	//If any field is left empty it will show a message to fill. If all fields are filled them the else will work (will save the taken data to database).
			            if(departmentName.equals("") || departmentDescription.equals("")) {
			            	JOptionPane.showMessageDialog( Frame, "Please fill all the fields");
			            }else {
			            try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
			                String sql = "INSERT INTO departments (Department_Name, Department_Description) VALUES (?, ?)";
			                PreparedStatement preparedStatement = connection.prepareStatement(sql);
			                preparedStatement.setString(1, departmentName);
			                preparedStatement.setString(2, departmentDescription);
			                int rowsAffected = preparedStatement.executeUpdate();

			                if (rowsAffected > 0) {
			                    JOptionPane.showMessageDialog( Frame, "Department Registration Successful!");
			                    Add_New_Department.clearFields();
			                } else {
			                    JOptionPane.showMessageDialog(Frame, "Department Registration Failed!");
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(Frame,  ex.getMessage());
			            }
			        }
			        
			        //If Add_New_Designation page was active it will execute the below save function.
			        }else if(Frame == Add_New_Designation.Frame) {
			        	Add_New_Designation.getValues();
			        	
			        	//If any field is left empty it will show a message to fill. If all fields are filled them the else will work (will save the taken data to database).
			        	if(DesignationName.equals("") || DesignationDescription.equals("")) {
			        	   JOptionPane.showMessageDialog(Frame, "Please fill all the fields");
			        	}else {
			        	   try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
			        	     String sql = "INSERT INTO designation (Designation_Name, Designation_Description) VALUES (?, ?)";
			        	     PreparedStatement preparedStatement = connection.prepareStatement(sql);
			        	     preparedStatement.setString(1, DesignationName);
			        	     preparedStatement.setString(2, DesignationDescription);
			        	     int rowsAffected = preparedStatement.executeUpdate();

			        	  if (rowsAffected > 0) {
			        	      JOptionPane.showMessageDialog(Frame, "Designation Registration Successful!");
			        	      Add_New_Designation.clearFields();
			        	  } else {
			        	      JOptionPane.showMessageDialog(Frame, "Designation Registration Failed!");
			        	   }
			        	  } catch (SQLException ex) {
			        	                ex.printStackTrace();
			        	                JOptionPane.showMessageDialog(Frame,  ex.getMessage());
			        	            }
			        	        }
			        //If Add_New_Employee page was active it will execute the below save function.
			        }else if(Frame ==  Add_New_Employee.Frame) {			        	
			        	String departmentName = Add_New_Employee.departmentComboBox.getSelectedItem().toString();
			            String designationName = Add_New_Employee.designationComboBox.getSelectedItem().toString();
			            String epfNo = Add_New_Employee.epfNoField.getText();
			            String name = Add_New_Employee.nameField.getText();
			            String email = Add_New_Employee.emailField.getText();
			            
			          //If any field is left empty it will show a message to fill and email validation is applied. If all fields are filled them the else will work (will save the taken data to database).
			            if(epfNo.equals("") || name.equals("") || email.equals("")) {
        	            	JOptionPane.showMessageDialog(Frame, "Please fill all the fields");
        	            }else if(!(email.matches("^(.+)@(\\S+)$")) || email.contains(" ")){
        	            	JOptionPane.showMessageDialog(Frame, "Please write a proper email address");
        	            }else {
			            try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
			               
			                int departmentId = getDepartmentId(connection, departmentName);
			                int designationId = getDesignationId(connection, designationName);

			                String sql = "INSERT INTO employees (Department, Designation, EPF_No, Name, Email) VALUES (?, ?, ?, ?, ?)";
			                PreparedStatement preparedStatement = connection.prepareStatement(sql);
			                preparedStatement.setInt(1, departmentId);
			                preparedStatement.setInt(2, designationId);
			                preparedStatement.setString(3, epfNo);
			                preparedStatement.setString(4, name);
			                preparedStatement.setString(5, email);
			                int rowsAffected = preparedStatement.executeUpdate();

			                if (rowsAffected > 0) {
			                    JOptionPane.showMessageDialog(Frame, "Registration Successful!");
			                    clearFields();
			                } else {
			                    JOptionPane.showMessageDialog(Frame, "Registration Failed!");
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(Frame,  ex.getMessage());
			            }
			          }
			         //If Add_New_HR_Assistant page was active it will execute the below save function.
			        }else {
			        	String epfNo = Add_New_HR_Assistant.epfNoField.getText();
			            String name = Add_New_HR_Assistant.nameField.getText();
			            String email = Add_New_HR_Assistant.emailField.getText();
			            String username = Add_New_HR_Assistant.usernameField.getText();
			            String password = new String(Add_New_HR_Assistant.passwordField.getPassword());
			            
			            //If any field is left empty it will show a message to fill. If all fields are filled them the else will work (will save the taken data to database).
			            if(epfNo.equals("") || name.equals("") || email.equals("") || username.equals("") || password.equals("")) {
			            	JOptionPane.showMessageDialog(Frame, "Please fill all the fields");
			            }else if(!(email.matches("^(.+)@(\\S+)")) || email.contains(" ")){
        	            	JOptionPane.showMessageDialog(Frame, "Please write a proper email address");
        	            }else if (username.contains(" ")) {
        	            	JOptionPane.showMessageDialog(Frame, "Username cannot have any spaces!");
        	            }else if (!(password.length() >= 6) || password.contains(" ")) {
			            	JOptionPane.showMessageDialog(Frame, "Password must be at least 6 characters long and password cannot have any spaces!");
	                    } else {
			            try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
			                String sql = "INSERT INTO hr_assistant (EPF_No, Name, Address, Username, Password) VALUES (?, ?, ?, ?, ?)";
			                PreparedStatement preparedStatement = connection.prepareStatement(sql);
			                preparedStatement.setString(1, epfNo);
			                preparedStatement.setString(2, name);
			                preparedStatement.setString(3, email);
			                preparedStatement.setString(4, username);
			                preparedStatement.setString(5, password);
			                int rowsAffected = preparedStatement.executeUpdate();

			                if (rowsAffected > 0) {
			                    JOptionPane.showMessageDialog(Frame, "Registration Successful!");
			                    clearFields();
			                } else {
			                    JOptionPane.showMessageDialog(Frame, "Registration Failed!");
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(Frame,  ex.getMessage());
			            }
			        }
			        }
				}
			
			//To get the department ID which is chosen from JCombo box
			private int getDepartmentId(Connection connection, String departmentName) throws SQLException {
                String sql = "SELECT ID FROM departments WHERE Department_Name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, departmentName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("ID");
                }
                return 0; 
            }
			
			//To get the designation ID which is chosen from JCombo box
            private int getDesignationId(Connection connection, String designationName) throws SQLException {
                String sql = "SELECT ID FROM designation WHERE designation_Name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, designationName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("ID");
                }
                return 0; 
            }
			
            //To clear the previous data which was saved successfully.
		    private void clearFields() {
		    	if(Frame ==  Add_New_Employee.Frame){
		        	Add_New_Employee.departmentComboBox.setSelectedIndex(0);
		        	Add_New_Employee.designationComboBox.setSelectedIndex(0);
		        	Fetch_EPF.fetchEPF(Add_New_Employee.epfNoField,Add_New_Employee.Frame);
		        	Add_New_Employee.nameField.setText("");
		        	Add_New_Employee.emailField.setText("");
		        }else {
		        	Fetch_EPF.fetchEPF(Add_New_HR_Assistant.epfNoField,Add_New_HR_Assistant.Frame);
		        	Add_New_HR_Assistant.nameField.setText("");
		        	Add_New_HR_Assistant.emailField.setText("");
		        	Add_New_HR_Assistant.usernameField.setText("");
		        	Add_New_HR_Assistant.passwordField.setText("");
		        }
		    }
			
		});

		return save;
		
	}


}
