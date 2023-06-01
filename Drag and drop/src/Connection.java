import java.sql.DriverManager;
import java.sql.Statement;

public class Connection {

    java.sql.Connection c = null;
    Statement s;
    Connection(){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres25505");
            s = c.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
