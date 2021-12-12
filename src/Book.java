import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Book {
	public int book_id;
	public String isbn;
	public String name;
	public int author_id;
	public int genre_id;
	public String quantity;
	public String publisher;
	public long date_of_registration;
	public String description;
	
	public Author getAuthor() {
			ResultSet rs;
			Author a=null;
			conn conn=new conn(); 
			Statement myStat;
			try {
				myStat =conn.connection.createStatement();
				rs = myStat.executeQuery("SELECT * FROM authors WHERE author_id="+author_id);
				while (rs.next()) {
					a=new Author();
					a.id=author_id;
					a.firstname=rs.getString("firstname");
					a.lastname=rs.getString("lastname");
					a.about=rs.getString("about");
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;
	}
}
