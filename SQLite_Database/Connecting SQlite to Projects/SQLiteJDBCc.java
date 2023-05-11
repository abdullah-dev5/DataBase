import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteJDBC {
    public static void main(String[] args)
    {
        ConnectionDB.connect();
    }


}


class ConnectionDB {


    public static Connection connect() {

        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:myfirstdb..db");//connecting to database
            System.out.println("Connected ! ");
        } catch (ClassNotFoundException | SQLException e)//both exceptions can be thrown
        {
            System.err.println(e + "");
        }
        return con;
    }


}
