package crud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;


public class RunSQLScript {

    public void run() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String mysqlUrl = "jdbc:mysql://localhost:3306/web5";
            Connection con = DriverManager.getConnection(mysqlUrl, "root", "Opti111222");
            System.out.println("Connection established......");
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            Reader reader = null;
            try {
                reader = new BufferedReader(new FileReader("src/full.sql"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Running the script
            sr.runScript(reader);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
