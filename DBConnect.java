package hellodb;

import java.sql.*;
import java.util.Calendar;

public class DBConnect {    
    private Connection con;
    private Statement st;
    private ResultSet rs;    
    
    public DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://sql9.freesqldatabase.com:3306/sql9198431", "sql9198431", "MjlAdXwINI");
            st = con.createStatement();
        }
        catch(Exception ex){
            System.out.println("Error:" + ex);
        }
    }
    
    public void getData(){
        try{
            
           // String query2 = "INSERT INTO `manager` (`id`, `firstName`, `lastName`, `dob`, `nationality`, `joinedOn`, `team`) VALUES (NULL, 'Alice', 'Bob', '2015-10-01', 'American', '2007-07-07', 'Arsenal')";
            String query = "select * from manager where firstName = 'Alice'";
            rs = st.executeQuery(query);
            //rs = st.executeQuery(query1);
            
            System.out.println("The results from our Database:");
            
            while(rs.next())
            {
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                String dob = rs.getString("dob");
                String nationality = rs.getString("nationality");
                
                System.out.println("First Name: " + fname + " Last Name: " + lname + " Date of Birth: " + dob + " Nationality: " + nationality);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    
    public void setData(){
        String query = "INSERT INTO `manager` (`id`, `firstName`, `lastName`, `dob`, `nationality`, `joinedOn`, `team`) "
                + "VALUES (NULL, 'Alice', 'Bob', '2015-10-01', 'American', '2007-07-07', 'Arsenal')";
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        try{
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = con.prepareStatement(query);
     /* preparedStmt.setString (1, "Barney");
      preparedStmt.setString (2, "Rubble");
      preparedStmt.setDate   (3, startDate);
      preparedStmt.setBoolean(4, false);
      preparedStmt.setInt    (5, 5000);*/

      // execute the preparedstatement
      preparedStmt.execute();
      
     // con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateData(){
        
        
        String query = "update manager set firstName = 'Morinho' where id = 2";        
        try{
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
      
            // con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    
     public void deleteData(){
        String query = "delete from manager where id = 2";
        try{
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.execute();
      
     // con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    
    
}
