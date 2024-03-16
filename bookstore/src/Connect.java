import java.sql.Connection;
import java.sql.DriverManager;

//class to open, close and get the database connection 
public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private Connection con;

    public Connect() {
        try {
            this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Connection getConnection() {
        return this.con;
    }

    public void closeConnection() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("Connection closed.");
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
    
}
