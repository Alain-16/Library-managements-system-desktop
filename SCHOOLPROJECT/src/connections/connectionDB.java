package connections;


import java.sql.*;
import java.time.LocalDate;


/**
 * <b>The connectionDB class</b>
 * This is a very important class it holds all connections and transactions done to the database main components of this
 * class are DATABASE_URL:the link to the database(phpmyadmin),DATABASE_USERNAME:the user of the database like the admin
 * this is not a new thing to mysql database users ,DATABASE_PASSWORD:the user password of the database.
 * there are also functions like the insertRecord():which deals with the INSERT INTO table_name VALUE ??? transaction.
 * <b>com.mysql.cj.jdbc.Driver</b> is database driver for connecting java applications to the mysql database,
 * <b>NOTE:</b>
 * Every database has its database driver for connecting to it according to the language your using ,
 * for mysql uses <b>"com.mysql.cj.jdbc.Driver"</b> to connect to java programs.
 * for more:
 *
 * @see <a href="https://www.mysqltutorial.org"></a>
 */

public class connectionDB {


    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Library";
    private static final String DATABASE_USERNAME = "ALAIN";
    private static final String DATABASE_PASSWORD = "0102Mugi";

    public static Connection Connectiondb2() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);

        }
    }


    public void insertRecord(String bookid, String bookname, String bookclass, String Department, String period, Date date, String status, String student_id, String student_name, String student_class, String special) {
        String INSERT_QUERY = "INSERT INTO issued_alevel_books (Book_ID,Book_name,Book_class,Department,period,issued_date,status,Student_ID,Student_name,Student_class,special) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, bookclass);
            preparedStatement.setString(4, Department);
            preparedStatement.setString(5, period);
            preparedStatement.setDate(6, date);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, student_id);
            preparedStatement.setString(9, student_name);
            preparedStatement.setString(10, student_class);
            preparedStatement.setString(11, special);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void update_alevel(String bookid, String bookname, String bookclass, String Department, Date date, String status, String student_id, String student_name, String student_class, String special) {
        String INSERT_QUERY = "UPDATE issued_alevel_books SET Book_name = ?,Book_class = ?,Department = ?,issued_date = ?,status = ?,Student_ID = ?,Student_name =?,Student_class = ?,special = ? WHERE Book_ID = ?";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, bookclass);
            preparedStatement.setString(3,Department);
            preparedStatement.setDate(4, date);
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, student_id);
            preparedStatement.setString(7, student_name);
            preparedStatement.setString(8, student_class);
            preparedStatement.setString(9, special);
            preparedStatement.setString(10,bookid);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void insert_into_Olevel(String bookid, String bookname, String bookclass, String period, Date date, String status, String student_id, String std_name, String student_class, String special) {
        String query = "INSERT INTO issued_olevel_books (Book_ID,Book_name,Book_class,period,issued_date,status,Student_ID,Student_name,student_class,special) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, bookclass);
            preparedStatement.setString(4, period);
            preparedStatement.setDate(5, date);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, student_id);
            preparedStatement.setString(8, std_name);
            preparedStatement.setString(9, student_class);
            preparedStatement.setString(10, special);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void update_Olevel(String bookid, String bookname, String bookclass, Date date, String status, String student_id, String std_name, String student_class, String special) {
        String query = "UPDATE issued_olevel_books SET Book_name = ?,Book_class = ?,status = ?,issued_date = ?,Student_ID = ?,Student_name = ?,student_class = ?,special = ? WHERE Book_ID = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, bookclass);
            preparedStatement.setString(3, status);
            preparedStatement.setDate(4, date);
            preparedStatement.setString(5, student_id);
            preparedStatement.setString(6, std_name);
            preparedStatement.setString(7, student_class);
            preparedStatement.setString(8, special);
            preparedStatement.setString(9, bookid);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void return_into_Olevel(String bookid, String bookname, String bookclass, String date, Date date2, String status, String student_id, String Student_Name, String student_class, String special) {
        String query = "INSERT INTO returned_olevel_books (Book_ID,Book_name,Book_class,issued_date,return_date,status,Student_ID,Student_name,Student_class,special) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, bookclass);
            preparedStatement.setString(4, date);
            preparedStatement.setDate(5, date2);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, student_id);
            preparedStatement.setString(8, Student_Name);
            preparedStatement.setString(9, student_class);
            preparedStatement.setString(10, special);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void return_into_Alevel(String bookid, String bookname, String bookclass, String department, String date, Date date2, String status, String student_id, String student_Name, String student_class, String special) {
        String query = "INSERT INTO returned_alevel_books (Book_ID,Book_name,Book_class,Department,issued_date,return_date,status,Student_ID,Student_name,Student_class,special) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, bookclass);
            preparedStatement.setString(4, department);
            preparedStatement.setString(5, date);
            preparedStatement.setDate(6, date2);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, student_id);
            preparedStatement.setString(9, student_Name);
            preparedStatement.setString(10, student_class);
            preparedStatement.setString(11, special);


            System.out.println(preparedStatement);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }


    public void Sigin(String username, String password,String email) {
        String query = "INSERT INTO users (username,password,Email) VALUES (?,?,?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3,email);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

    }

    public void insert_into_profile(String name, String username, String password, String email, String phone) {
        String INSERT_QUERY = "INSERT INTO profile (Name,username,userpassword,email,phone) VALUES (?,?,?,?,?)";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information;
            e.printStackTrace();
        }
    }

    public void create_into_alevel(String bookid, String bookname, String depart, String bookclass, String status) {
        String query = "INSERT INTO alevel_books (Book_ID,Book_name,Department,Book_class,status) VALUES (?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, depart);
            preparedStatement.setString(4, bookclass);
            preparedStatement.setString(5, status);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void create_into_olevel(String bookid, String bookname, String bookclass, String status) {
        String query = "INSERT INTO olevel_books (Book_ID,Book_name,Book_class,status) VALUES (?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, bookclass);
            preparedStatement.setString(4, status);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void create_into_other(String bookid, String bookname, String depart, String bookclass, String status) {
        String query = "INSERT INTO other_books (Book_id,Book_name,Department,type,status) VALUES (?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, depart);
            preparedStatement.setString(4, bookclass);
            preparedStatement.setString(5, status);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void UpdateRecords(String bookid, String bookname, String Depart, String bookclass, String status) {
        String INSERT_QUERY = "UPDATE  alevel_books SET Book_name= ?,Department= ?,Book_class= ?,status= ? WHERE Book_ID= ?";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, Depart);
            preparedStatement.setString(3, bookclass);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, bookid);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }

    public void Update_Records_class(String bookid, String bookname, String Depart, String bookclass, String status) {
        String INSERT_QUERY = "UPDATE  alevel_books SET Book_ID= ?,Book_name= ?,Department= ?,status= ? WHERE Book_class= ?";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, Depart);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, bookclass);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }

    public void Update_Records_Olevel(String bookid, String bookname, String bookclass, String status) {
        String INSERT_QUERY = "UPDATE  olevel_books SET Book_name= ?,Book_class= ?,status= ? WHERE Book_ID= ?";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, bookclass);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, bookid);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }

    public void Update_class_Olevel(String bookid, String bookname, String bookclass, String status) {
        String INSERT_QUERY = "UPDATE  olevel_books SET Book_ID= ?, Book_name= ?,status= ? WHERE Book_class= ?";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, bookclass);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }



    public void insertRecord_others(String bookid, String bookname, String Department, String type, String period, Date date, String status, String student_id, String student_name, String student_class) {
        String INSERT_QUERY = "INSERT INTO issued_other_books (Book_ID,Book_name,Department,Type,period,issue_date,status,Student_ID,Student_name,Student_class) VALUES (?,?,?,?,?,?,?,?,?,?)";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, Department);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, period);
            preparedStatement.setDate(6, date);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, student_id);
            preparedStatement.setString(9, student_name);
            preparedStatement.setString(10, student_class);



            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void return_others(String bookid, String bookname, String Department, String type, String period, Date date, Date date2, String status, String student_id, String student_name, String student_class, String special) {
        String INSERT_QUERY = "INSERT INTO issued_other_books (Book_ID,Book_name,Department,Type,issued_date,return_date,status,Student_ID,Student_name,Student_class,special) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, Department);
            preparedStatement.setString(4, type);
            preparedStatement.setDate(5, date);
            preparedStatement.setDate(6, date2);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, student_id);
            preparedStatement.setString(9, student_name);
            preparedStatement.setString(10, student_class);
            preparedStatement.setString(11, special);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }


    public void Update_others(String bookid, String bookname, String Depart, String type, String status) {
        String INSERT_QUERY = "UPDATE  other_books SET Book_name= ?,Department= ?,type= ?,status= ? WHERE Book_id= ?";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, Depart);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, bookid);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }

    public void registerStudent(String bookid, String bookname, String Department, String stuclass, String level, String gender) {
        String INSERT_QUERY = "INSERT INTO students (Student_ID,Student_name,Combination,Student_class,level,Gender) VALUES (?,?,?,?,?,?)";

        // load and register JDBC driver for MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, Department);
            preparedStatement.setString(4, stuclass);
            preparedStatement.setString(5, level);
            preparedStatement.setString(6, gender);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void return_into_others(String bookid, String bookname, String depart, String type, String date1, Date date2, String status, String student_id, String Student_Name, String student_class) {
        String query = "INSERT INTO returned_other_books (Book_id,Book_name,Department,Type,issued_date,return_data,status,Student_ID,Student_name,Student_class) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookid);
            preparedStatement.setString(2, bookname);
            preparedStatement.setString(3, depart);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, date1);
            preparedStatement.setDate(6, date2);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, student_id);
            preparedStatement.setString(9, Student_Name);
            preparedStatement.setString(10, student_class);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}



