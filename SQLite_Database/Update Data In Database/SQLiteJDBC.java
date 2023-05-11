import java.sql.*;



public class SQLiteJDBC {
      public static void main(String[] args)
    {
        //INSERT INTO DATABASE.
        // insert("John","keats","johnny.com","jack123.");

        //FETCH FROM DATABASE.
        //it reads All Data
        readAll();
        // Specfic Rows and Column
        //  readSpecificRows(); //we are reading 1st columns of Rows

        //UPDATE DATABASE.
       upDateData();

    }
        //INSERT DATA INTO DATABASE.
    public  static void insert(String name,String secondName,String email,String password)
    {
       Connection connect= ConnectionDB.connect();
        PreparedStatement ps = null;

       try  {
           //following  linking to db table to Insert info into it.
            String sql = "INSERT INTO students(name,SecondName,Emails,password) VALUES(?,?,?,?)";//Student is name of Table created in Db.And The Parameters are its Columns.
            ps = connect.prepareStatement(sql);

            ps.setString(1,name);
           ps.setString(2,secondName);//Added values
           ps.setString(3,email);
           ps.setString(4,password);
           ps.execute();
           System.out.println("Inserted Successfully");
            }
           catch (SQLException ex)
            {
         System.err.println(ex.getMessage());
            }



    }
    //READ DATA FROM TABLE DATABASE.

    //method # 1
    //read Whole table
    public static void readAll()
    {
        Connection connect = ConnectionDB.connect();
        PreparedStatement ps= null;
        ResultSet rs= null;

        try{
        String sql = "SELECT * FROM students";
        ps= connect.prepareStatement(sql);
        rs= ps.executeQuery();
        while (rs.next())
        {
            String firstName= rs.getString("name");
            String secondName= rs.getString("SecondName");
            String emails= rs.getString("Emails");
            String password= rs.getString("password");
            System.out.println("Al Students\n");
            System.out.println("First Name: " + firstName);
            System.out.println("Second Name: " + secondName);
            System.out.println("Emails: " + emails);
            System.out.println("Password: " + password+"\n\n");

        }


        }
        catch (SQLException ex)
        {

            System.err.println(ex.getMessage());
        }
        finally {
            try {
                    ps.close();;
                    rs.close();
                    connect.close();
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }

    }

    //method # 2
    // reads from Specifc Row of Table.
    public static void readSpecificRows()
    {

        Connection con = ConnectionDB.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql = "SELECT name FROM students WHERE Emails =? ";//it will select names whic is presented in Columns 1.
            // will fetch data of columns name or 1 on the basis of given emails.
            ps=con.prepareStatement(sql);
            ps.setString(1,"aladdin@wadiya.com");//Whatever The mail we provides it will print the Firstname of that Rows where that email is exists.
            rs= ps.executeQuery();

            String firstName = rs.getString(1);//Select the Coulmns No which you wanna print or fetch.Note : Before that Specify the columns name in sql statment  above.
            System.out.println(firstName);



        }

        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            try {
                //close the connections
                //the last one will be closed first.
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
    // Concept 4 Update.
    //we will update the the 1stname of specific Rows.

    public static void upDateData()
    {

        Connection conn = ConnectionDB.connect();
        PreparedStatement ps=null;
        try {
            String sql= "UPDATE students set name = ? WHERE Emails=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"Raplh");
            ps.setString(2,"alan.com");
            ps.execute();
            System.out.println("Updated Successfully");

        }
        catch (SQLException ex)
        {

            System.err.println(ex.getMessage());
        }
        finally {
            try {
                ps.close();
                conn.close();

            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
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
