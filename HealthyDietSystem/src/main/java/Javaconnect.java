import java.sql.*;
import java.util.*;


public class Javaconnect {
        private final String url;
        private final int port;
        private final String dbName;
        private Connection connection;
        private static Javaconnect instance;

        private Javaconnect() throws SQLException {   // Pattern 2 (Singleton)
            this.dbName = "ORCL";
            this.port = 1521;
            this.url = "jdbc:oracle:thin:@localhost:" + Integer.toString(this.port) + ":" + this.dbName;
            Properties props = new Properties();
            props.setProperty("user","scott");
            props.setProperty("password","tiger");
            props.setProperty("ssl","false");

            this.connection = DriverManager.getConnection(url, props);
        }

        public Connection getConnection() {
            return this.connection;
        }

        public static Javaconnect getInstance() throws SQLException {
            if(instance == null){
                instance = new Javaconnect();
            }
            else if (instance.getConnection().isClosed()) {
                instance = new Javaconnect();
            }
            return instance;
        }
    }





