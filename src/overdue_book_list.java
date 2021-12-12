import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class overdue_book_list extends JFrame {

	private JPanel contentPane;public int mouseX,mouseY;
	private JTable table;private JTextField txt_value;
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID","BOOK NAME" ,"NAME","STATUS","ÝSSUE DATE","RETURN DATE"};
	Object[] row = new Object[6];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					overdue_book_list frame = new overdue_book_list();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unused")
	public void search() {
		
			String value;
			value=txt_value.getText().toString();
			conn conn=new conn();
			ResultSet myRs;
			Statement myStat;
			try {
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				Date currentDate = new Date();Date return_date=new Date();
				modelim.setRowCount(0);
				myStat = conn.connection.createStatement();
				myRs = myStat.executeQuery("SELECT * FROM issue_book INNER JOIN books on books.book_id=issue_book.issue_book_id INNER JOIN members on members.id=issue_book.member_id WHERE delivery_date IS NULL AND (firstname LIKE '%"+value+"%' OR lastname LIKE '%"+value+"%' OR name LIKE '%"+value+"%')");
				
				while (myRs.next()) {
						try {
							 return_date= new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
							        .parse(myRs.getString("return_date"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if (return_date.before(currentDate)) { //TESLÝM TARÝHÝ GEÇMÝÞSE
						row[0] = myRs.getString("issue_book_id");
						row[1]=myRs.getString("name");
						row[2] = myRs.getString("firstname")+" "+myRs.getString("lastname");
						row[3] = myRs.getString("status");
						row[4] = myRs.getString("issue_date");
						row[5] = myRs.getString("return_date");
						modelim.addRow(row);
					}	
				}
			}catch (SQLException e1) {
					// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error!");
				}
					
					table.setModel(modelim);
	  }
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unused")
	public overdue_book_list() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 630);
		contentPane = new JPanel();
		contentPane.setToolTipText("TESL\u0130M TAR\u0130H\u0130 GEC\u0130KM\u0130\u015E K\u0130TAP L\u0130STES\u0130");
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 770, 630);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				setLocation(x-mouseX,y-mouseY);
			}
		});
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}
		});
		panel_1.setBackground(new Color(143, 188, 143));
		panel_1.setBounds(0, 0, 770, 65);
		panel.add(panel_1);
		contentPane.setLayout(null);
		panel_1.setLayout(null);
		
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(737, 0, 23, 65);
		panel_1.add(lbl_exit);
		lbl_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,"The form is closing, are you sure","WARNING",JOptionPane.YES_NO_OPTION)==0) {
						dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_exit.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_exit.setForeground(new Color(51, 153, 204));
			}
		});
		lbl_exit.setForeground(new Color(51, 153, 204));
		lbl_exit.setFont(new Font("Tahoma", Font.BOLD, 33));
		lbl_exit.setBackground(Color.BLACK);
		
		JLabel overdue_book_list = new JLabel("OVERDUE BOOK L\u0130ST");
		overdue_book_list.setForeground(new Color(255, 255, 255));
		overdue_book_list.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		overdue_book_list.setBackground(Color.WHITE);
		overdue_book_list.setBounds(281, 21, 226, 32);
		panel_1.add(overdue_book_list);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(genre_operations.class.getResource("/images/operations.png")));
		lblNewLabel.setBounds(0, 0, 106, 65);
		panel_1.add(lblNewLabel);

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 121, 770, 509);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		panel.add(scrollPane);
		table = new JTable() {					//isCellEditable ekledim çünkü tablea 2kere týklayýnca üstünde düzenlemeyi engellemek için*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		modelim.setColumnIdentifiers(column);
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane.setViewportView(table);
		table.setSelectionBackground(new Color(249,105,14));
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		table.setShowGrid(true);
		table.setBackground(new Color(248,248,248));
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
		JLabel lbl_yazdýr = new JLabel("");
		lbl_yazdýr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					 table.print(JTable.PrintMode.NORMAL); 
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		lbl_yazdýr.setIcon(new ImageIcon(genre_operations.class.getResource("/images/print.png")));
		lbl_yazdýr.setBounds(643, 0, 70, 65);
		panel_1.add(lbl_yazdýr);
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("***Search by first name , last name or book name.");
		lblNewLabel_1_1_1_5.setToolTipText("");
		lblNewLabel_1_1_1_5.setForeground(Color.RED);
		lblNewLabel_1_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_5.setBounds(272, 99, 314, 21);
		panel.add(lblNewLabel_1_1_1_5);
		
		txt_value = new JTextField();
		txt_value.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				  search();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  search();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  search();
			  }
			});
		txt_value.setForeground(Color.RED);
		txt_value.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_value.setColumns(10);
		txt_value.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_value.setBounds(285, 69, 287, 29);
		panel.add(txt_value);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Value to Search");
		lblNewLabel_1_1_2.setForeground(Color.RED);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(129, 65, 167, 36);
		panel.add(lblNewLabel_1_1_2);
		
		
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();Date return_date=new Date();
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("SELECT * FROM issue_book INNER JOIN books on books.book_id=issue_book.issue_book_id INNER JOIN members on members.id=issue_book.member_id WHERE delivery_date IS NULL");
			while (myRs.next()) {
					try {
						 return_date= new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
						        .parse(myRs.getString("return_date"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (return_date.before(currentDate)) { //TESLÝM TARÝHÝ GEÇMÝÞSE
					row[0] = myRs.getString("issue_book_id");
					row[1]=myRs.getString("name");
					row[2] = myRs.getString("firstname")+" "+myRs.getString("lastname");
					row[3] = myRs.getString("status");
					row[4] = myRs.getString("issue_date");
					row[5] = myRs.getString("return_date");
					modelim.addRow(row);
				}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		table.setModel(modelim);

		
		
		
	
	}

}
