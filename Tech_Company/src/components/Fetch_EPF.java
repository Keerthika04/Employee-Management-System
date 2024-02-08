package components;

import java.sql.*;
import javax.swing.*;

public class Fetch_EPF {
	
	//To Fetch EPF_No from HR assistant and employees tables to generate EPF No for next employee (it can also be HR assistant).
	
    public static void fetchEPF( JLabel name, JFrame frame) {
    	try (Connection connection = DriverManager.getConnection(DB_Connection.JDBC_URL, DB_Connection.DB_USER, DB_Connection.DB_PASSWORD)) {
            String sql = "SELECT EPF_No FROM hr_assistant ORDER BY ID DESC LIMIT 1";
            String sql2 = "SELECT EPF_No FROM employees ORDER BY ID DESC LIMIT 1";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            
            while (resultSet.next() && resultSet2.next()){
            	
            	//Converting the EPF_No from String to integer by removing the characters and adding 1 for next EPF_no which will be generated. 
                String Id = resultSet.getString("EPF_No").replaceAll("[^0-9]", "");
                String Id2 = resultSet2.getString("EPF_No").replaceAll("[^0-9]", "");
                int ID = Integer.valueOf(Id)+1;
                int ID2 = Integer.valueOf(Id2)+1;
                
                //To check which table has the biggest EPF_no
                if(ID > ID2) {               	
                	String ID1 = String.format("%03d", ID);
                	name.setText("TC"+ID1);
                }else {
                	String ID1 = String.format("%03d", ID2);
                    name.setText("TC"+ID1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
        }
    }
}
