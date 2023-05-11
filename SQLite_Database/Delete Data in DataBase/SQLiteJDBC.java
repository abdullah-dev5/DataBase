import java.sql.*;


public class SQLiteJDBC {
    public static void main(String[] args) {

        //DELETE FROM DATABASE.
        delete();

        //FETCH FROM DATABASE.
        // readAll();

    }

    //READ DATA FROM TABLE DATABASE.

    public static void readAll() {
        Connection connect = ConnectionDB.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM students";
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("name");
                String secondName = rs.getString("SecondName");
                String emails = rs.getString("Emails");
                String password = rs.getString("password");
                System.out.println("Al Students\n");
                System.out.println("First Name: " + firstName);
                System.out.println("Second Name: " + secondName);
                System.out.println("Emails: " + emails);
                System.out.println("Password: " + password + "\n\n");

            }


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                ;
                rs.close();
                connect.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }


    private static void delete() {
        Connection conn = ConnectionDB.connect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM students WHERE Emails =? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "johnny.com");

            ps.execute();
            System.out.println("Deleted Successfully");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();

            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }
        }

    }
}

//Connector class
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
