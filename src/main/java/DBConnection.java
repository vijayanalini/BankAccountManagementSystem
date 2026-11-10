import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "YOUR_MYSQL_PASSWORD";
    public static Connection getConnection()
    {
        try
        {
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            return con;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void main(String args[])
    {
        Connection con = DBConnection.getConnection();

        if (con != null)
        {
            try
            {
                con.close();
                System.out.println("Connection Closed");
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
