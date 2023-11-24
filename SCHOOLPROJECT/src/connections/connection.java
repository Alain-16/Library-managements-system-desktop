package connections;

import java.sql.*;


/**
 * <b>The connection class</b>
 * This is a very important class it holds all connections and transactions done to the database main components of this
 * class are DATABASE_URL:the link to the database(phpmyadmin),DATABASE_USERNAME:the user of the database like the admin
 * this is not a new thing to mysql database users ,DATABASE_PASSWORD:the user password of the database.
 * <b>com.mysql.cj.jdbc.Driver</b> is database driver for connecting java applications to the mysql database,
 * <b>NOTE:</b>
 * Every database has its database driver for connecting to it according to the language your using ,
 * for mysql uses <b>"com.mysql.cj.jdbc.Driver"</b> to connect to java programs.
 * <p>
 * for more:
 *
 * @see <a href="https://www.mysqltutorial.org"></a>
 */

public class connection {
    private static Connection con;


    private static final String url = "jdbc:mysql://localhost:3306/Library";
    private static final String username1 = "ALAIN";
    private static final String password1 = "0102Mugi";


    public final static Connection connectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, username1, password1);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
    }

    public void initdatabase() {
        while (con == null) {
            con = connectionDB();
        }
    }
}


