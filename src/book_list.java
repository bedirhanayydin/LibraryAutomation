import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class book_list extends JFrame {


	private JPanel contentPane;		
	SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzzz");
	private int mouseX,mouseY;
	private JTextField txt_value;
	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID", "ISBN","NAME","AUTHOR","GENRE","QUANTITY","PUBLISHER","DATE OF REGISTRATION","DESCRIPTION"};
	Object[] row = new Object[9];

	private JTextArea lbl_description = new JTextArea();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					book_list frame = new book_list();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void fill_table() {
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("SELECT * FROM books INNER JOIN book_genres ON books.genre_id=book_genres.genre_id INNER JOIN authors ON authors.author_id=books.author_id");	
			while (myRs.next()) {
				row[0] = myRs.getString("book_id");
				row[1] = myRs.getString("isbn");
				row[2] = myRs.getString("name");
				row[3] = myRs.getString("firstname")+myRs.getString("lastname");
				row[4] = myRs.getString("genre_name");
				row[5] = myRs.getString("quantity");
				row[6] = myRs.getString("publisher");
				row[7] = sdf.format(new java.util.Date(myRs.getLong("date_of_registration")));
				row[8] = myRs.getString("description");
				modelim.addRow(row);
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(modelim);
	}
	/**
	 * Create the frame.
	 */
	public book_list() {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1359, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1359, 661);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1359, 65);
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
		panel.setLayout(null);
		panel_1.setBackground(new Color(51, 102, 153));
		panel.add(panel_1);
		contentPane.setLayout(null);
		panel_1.setLayout(null);
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(1326, 0, 23, 65);
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
		
	
		
		
		JLabel book_list = new JLabel("BOOK L\u0130ST");
		book_list.setForeground(new Color(255, 255, 255));
		book_list.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		book_list.setBackground(Color.WHITE);
		book_list.setBounds(579, 21, 118, 32);
		panel_1.add(book_list);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(book_list.class.getResource("/images/books_list.png")));
		lblNewLabel.setBounds(10, 0, 70, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Value to Search");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(10, 76, 167, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		
		
		
		
		
		
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

			  public void search() {
				  String value,sql_query;
					value=txt_value.getText().toString();
					sql_query="SELECT * FROM books INNER JOIN book_genres ON books.genre_id=book_genres.genre_id INNER JOIN authors ON authors.author_id=books.author_id WHERE isbn LIKE '%"+value+"%' OR name LIKE '%"+value+"%'OR publisher LIKE '%"+value+"%'";
						try {
							modelim.setRowCount(0);	
							conn conn=new conn();
							ResultSet myRs;
							Statement myStat;
							myStat = conn.connection.createStatement();
							myRs = myStat.executeQuery(sql_query);	
							while (myRs.next()) {
								row[0] = myRs.getString("book_id");
								row[1] = myRs.getString("isbn");
								row[2] = myRs.getString("name");
								row[3] = myRs.getString("firstname")+myRs.getString("lastname");
								row[4] = myRs.getString("genre_name");
								row[5] = myRs.getString("quantity");
								row[6] = myRs.getString("publisher");
								row[7] = sdf.format(new java.util.Date(myRs.getLong("date_of_registration")));  
								row[8] = myRs.getString("description");
								modelim.addRow(row);
							}
							} catch (SQLException e1) {
							// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Error!");
						}
							
							table.setModel(modelim);
			  }
			});
		txt_value.setToolTipText("search by book name ,\u0131sbn or publ\u0131sher");
		
		txt_value.setForeground(Color.RED);
		txt_value.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_value.setColumns(10);
		txt_value.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_value.setBounds(163, 80, 319, 29);
		panel.add(txt_value);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ISBN");
		lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(844, 131, 70, 36);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(844, 231, 70, 36);
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Author");
		lblNewLabel_1_1_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_2.setBounds(844, 331, 70, 36);
		panel.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Genre");
		lblNewLabel_1_1_1_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_3.setBounds(844, 431, 70, 36);
		panel.add(lblNewLabel_1_1_1_3);
		
		JLabel lbl_isbn = new JLabel("");
		lbl_isbn.setForeground(new Color(255, 153, 0));
		lbl_isbn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_isbn.setBounds(844, 178, 195, 36);
		panel.add(lbl_isbn);
		
		JLabel lbl_name = new JLabel("");
		lbl_name.setForeground(new Color(255, 153, 0));
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_name.setBounds(844, 278, 195, 36);
		panel.add(lbl_name);
		
		JLabel lbl_authorid = new JLabel("");
		lbl_authorid.setForeground(new Color(255, 153, 0));
		lbl_authorid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_authorid.setBounds(844, 378, 195, 36);
		panel.add(lbl_authorid);
		
		JLabel lbl_genreid = new JLabel("");
		lbl_genreid.setForeground(new Color(255, 153, 0));
		lbl_genreid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_genreid.setBounds(844, 478, 195, 36);
		panel.add(lbl_genreid);
		
		JLabel lbl_quantity = new JLabel("");
		lbl_quantity.setForeground(new Color(255, 153, 0));
		lbl_quantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_quantity.setBounds(844, 572, 177, 36);
		panel.add(lbl_quantity);
		
		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_3_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_3_1.setBounds(844, 525, 70, 36);
		panel.add(lblNewLabel_1_1_1_3_1);
		
		JLabel lblNewLabel_1_1_1_4 = new JLabel("Publ\u0131sher");
		lblNewLabel_1_1_1_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_4.setBounds(1049, 131, 92, 36);
		panel.add(lblNewLabel_1_1_1_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		lbl_description.setBorder(new LineBorder(new Color(51, 153, 204), 2, true));
		lbl_description.setEditable(false);
		lbl_description.setForeground(new Color(255, 153, 0));
		lbl_description.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane_1.setBounds(1049, 378, 288, 136);
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(lbl_description);
		
		
		JLabel lbl_publýsher = new JLabel("");
		lbl_publýsher.setForeground(new Color(255, 153, 0));
		lbl_publýsher.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_publýsher.setBounds(1049, 178, 288, 36);
		panel.add(lbl_publýsher);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Date of Registration");
		lblNewLabel_1_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_1_1.setBounds(1049, 231, 167, 36);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lbl_registration = new JLabel("");
		lbl_registration.setForeground(new Color(255, 153, 0));
		lbl_registration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_registration.setBounds(1049, 278, 300, 36);
		panel.add(lbl_registration);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Description");
		lblNewLabel_1_1_1_2_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_2_1.setBounds(1049, 331, 92, 36);
		panel.add(lblNewLabel_1_1_1_2_1);
		
		JButton btn_refresh = new JButton("REFRESH");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_value.setText("");lbl_isbn.setText("");lbl_authorid.setText("");lbl_genreid.setText("");lbl_name.setText("");table.clearSelection();lbl_name.setText("");
				lbl_publýsher.setText("");lbl_registration.setText("");lbl_description.setText("");lbl_quantity.setText("");
				modelim.setRowCount(0); fill_table();
			}
		});
		btn_refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_refresh.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_refresh.setBackground(Color.white);
			}
		});
		btn_refresh.setForeground(new Color(0, 51, 204));
		btn_refresh.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_refresh.setFocusPainted(false);
		btn_refresh.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_refresh.setBackground(Color.WHITE);
		btn_refresh.setBounds(565, 78, 144, 38);
		panel.add(btn_refresh);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 824, 518);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		panel.add(scrollPane);
		table = new JTable() {					//isCellEditable ekledim çünkü tablea 2kere týklayýnca üstünde düzenlemeyi engellemek için*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    modelim.setColumnIdentifiers(column);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		table.setSelectionBackground(new Color(249,105,14));
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		table.setShowGrid(true);
		table.setBackground(new Color(248,248,248));
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("***Search by book name , ISBN or publisher");
		lblNewLabel_1_1_1_5.setToolTipText("");
		lblNewLabel_1_1_1_5.setForeground(Color.RED);
		lblNewLabel_1_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_5.setBounds(163, 113, 288, 21);
		panel.add(lblNewLabel_1_1_1_5);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbl_isbn.setText(modelim.getValueAt(table.getSelectedRow(),1).toString());
				lbl_name.setText((String) modelim.getValueAt(table.getSelectedRow(),2)); 
				lbl_authorid.setText((String) modelim.getValueAt(table.getSelectedRow(),3)); 
				lbl_genreid.setText((String) modelim.getValueAt(table.getSelectedRow(),4));
				lbl_quantity.setText((String) modelim.getValueAt(table.getSelectedRow(),5));
				lbl_publýsher.setText((String) modelim.getValueAt(table.getSelectedRow(),6));
				lbl_registration.setText((String) modelim.getValueAt(table.getSelectedRow(),7));
				lbl_description.setText((String) modelim.getValueAt(table.getSelectedRow(),8));
			}
		});		
		table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 13));
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
		JLabel lbl_yazdýr = new JLabel("");
		lbl_yazdýr.setBounds(1289, 61, 70, 54);
		panel.add(lbl_yazdýr);
		lbl_yazdýr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					 table.print(JTable.PrintMode.FIT_WIDTH); 
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		lbl_yazdýr.setIcon(new ImageIcon(author_operations.class.getResource("/images/print.png")));
		
		
		
		fill_table();
	}
}
