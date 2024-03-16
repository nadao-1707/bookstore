import java.sql.*;

public class Server {

    //dummy code for testing purposes
    static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore";
    static final String USER = "root";
    static final String PASS = "12345678";
 
    public static void main(String[] args) {
       try{
          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
          Statement stmt = conn.createStatement();
          stmt.executeUpdate("INSERT INTO users SET username='lilia ', name='nada' , password='123456' ");
       } catch (SQLException e) {
          e.printStackTrace();
       } 
    }
 }
