import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {	
	Connection connection;
	Statement statement;
	public conn() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root","password");
			statement=connection.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
