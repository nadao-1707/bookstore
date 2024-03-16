import java.sql.Connection;
import java.sql.DriverManager;

//class to open, close and get the database connection 
public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    public Connection con;

    public void getConnection() {
        try {
            this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void closeConnection() {
        if (this.con != null) {
            try {
                this.con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    
}
