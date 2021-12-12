import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import com.toedter.calendar.JCalendar;
import java.awt.event.WindowFocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class add_book extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_genre_id = new JLabel("");

	private int mouseX,mouseY;
	private JTextField txt_ýsbn;
	private JTextField txt_name;
	private JTextField txt_author;
	JComboBox<select_genre_id> cb_genre = new JComboBox<select_genre_id>();
	private JTextField txt_publýsher;
	JSpinner spinner = new JSpinner();
	/** * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_book frame = new add_book();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void fill_combobox() {
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("select * from book_genres");	
			while (myRs.next()) {
			select_genre_id genre_id=new select_genre_id();	
			genre_id.genre_id=myRs.getInt("genre_id");
			genre_id.genre_name=myRs.getString("genre_name");
			cb_genre.addItem(genre_id);
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public add_book() {
		
		initGUI();
	}
	private void initGUI() {
	
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 899, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 899, 495);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 899, 65);
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
		lbl_exit.setBounds(866, 0, 23, 65);
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
		
	
		
		
		JLabel add_book = new JLabel("ADD BOOK");
		add_book.setForeground(new Color(255, 255, 255));
		add_book.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		add_book.setBackground(Color.WHITE);
		add_book.setBounds(406, 21, 126, 32);
		panel_1.add(add_book);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(add_book.class.getResource("/images/add_book.png")));
		lblNewLabel.setBounds(10, 0, 70, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("ISBN");
		lblNewLabel_1_1.setBounds(65, 101, 53, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_ýsbn = new JTextField();
		txt_ýsbn.setBounds(158, 105, 214, 29);
		txt_ýsbn.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ýsbn.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_ýsbn.setColumns(10);
		panel.add(txt_ýsbn);
		

		JLabel lblNewLabel_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(65, 160, 62, 36);
		panel.add(lblNewLabel_1_1_1);
		
		txt_name = new JTextField();
		txt_name.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_name.setColumns(10);
		txt_name.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_name.setBounds(158, 164, 246, 29);
		panel.add(txt_name);
		

		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Author");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(65, 221, 62, 36);
		panel.add(lblNewLabel_1_1_1_1);
		
		txt_author = new JTextField();
		txt_author.setEditable(false);
		txt_author.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_author.setColumns(10);
		txt_author.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_author.setBounds(158, 221, 214, 33);
		panel.add(txt_author);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Genre");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(65, 282, 62, 36);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1.setBounds(65, 344, 78, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Publ\u0131sher");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(65, 402, 85, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		txt_publýsher = new JTextField();
		txt_publýsher.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_publýsher.setColumns(10);
		txt_publýsher.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_publýsher.setBounds(158, 409, 246, 29);
		panel.add(txt_publýsher);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		scrollPane.setBounds(536, 296, 310, 125);
		panel.add(scrollPane);
		
		JTextArea jtextArea_About = new JTextArea();
		jtextArea_About.setFont(new Font("Tahoma", Font.BOLD, 15));
		jtextArea_About.setBorder(new LineBorder(new Color(51, 153, 204), 2, true));
		scrollPane.setViewportView(jtextArea_About);
		
		lbl_genre_id.setToolTipText("Genre ID");
		lbl_genre_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_genre_id.setForeground(Color.RED);
		lbl_genre_id.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_genre_id.setBackground(Color.WHITE);
		lbl_genre_id.setBounds(0, 282, 63, 36);
		panel.add(lbl_genre_id);
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Date of Registration");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(604, 85, 197, 36);
		panel.add(lblNewLabel_1_1_2);
		
		
		
		cb_genre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_genre_id.setText(String.valueOf(((select_genre_id)cb_genre.getSelectedItem()).genre_id));
			}
		});
		cb_genre.setBounds(158, 289, 176, 29);
		panel.add(cb_genre);
		fill_combobox();
		
		
		JSpinner spinner = new JSpinner();
		((SpinnerNumberModel) spinner.getModel()).setMinimum(0);		// SPÝNNER MÝNÝMUM 0 OLABÝLÝR*************
		spinner.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinner.setForeground(Color.BLACK);
		spinner.setBounds(158, 355, 78, 25);
		panel.add(spinner);
		
		JButton btn_select_author =new JButton("<html>&nbsp;SELECT<br/>AUTHOR</html>");
		btn_select_author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_author select_author=new select_author();
				select_author.setVisible(true);
				
			}
		});
		btn_select_author.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_select_author.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_select_author.setBackground(Color.white);
			}
		});
		btn_select_author.setForeground(new Color(0, 51, 204));
		btn_select_author.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_select_author.setFocusPainted(false);
		btn_select_author.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_select_author.setBackground(Color.WHITE);
		btn_select_author.setBounds(382, 204, 130, 53);
		panel.add(btn_select_author);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(536, 121, 310, 138);
		panel.add(calendar);
    	
		 
		
		
		JLabel lbl_authorid = new JLabel("");
		lbl_authorid.setToolTipText("Author ID");
		lbl_authorid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_authorid.setForeground(Color.RED);
		lbl_authorid.setBackground(Color.WHITE);
		lbl_authorid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_authorid.setBounds(0, 221, 63, 36);
		panel.add(lbl_authorid);
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_author.setText("");txt_ýsbn.setText(""); txt_name.setText(""); lbl_authorid.setText("");
				txt_publýsher.setText("");jtextArea_About.setText("");spinner.setValue(0);cb_genre.setSelectedIndex(0);
				calendar.setDate(new java.util.Date());select_author.firstname_author="";select_author.lastname_author="";select_author.id_author="";
			}
		});
		btn_clean.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_clean.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_clean.setBackground(Color.white);
			}
		});
		btn_clean.setForeground(new Color(0, 51, 204));
		btn_clean.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_clean.setFocusPainted(false);
		btn_clean.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_clean.setBackground(Color.WHITE);
		btn_clean.setBounds(713, 432, 130, 52);
		panel.add(btn_clean);
	
		
		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {	if (txt_author.getText().equals("")|| txt_ýsbn.getText().equals("")||txt_name.getText().equals("")||txt_publýsher.getText().equals("")||spinner.getValue().equals(0)||jtextArea_About.getText().equals("")) {
					JOptionPane.showMessageDialog(null, 
							  "Fill in the Blanks", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
					else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want to Add?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
						conn conn=new conn();
						PreparedStatement posted=conn.connection.prepareStatement("INSERT INTO books(isbn,name,author_id,genre_id,quantity,publisher,date_of_registration,description)VALUES (?,?,?,?,?,?,?,?)");
						posted.setString(1, txt_ýsbn.getText());
						posted.setString(2, txt_name.getText());
						posted.setString(3, lbl_authorid.getText());
						posted.setString(4, String.valueOf(((select_genre_id)cb_genre.getSelectedItem()).genre_id));
						posted.setString(5, spinner.getValue().toString());
						posted.setString(6, txt_publýsher.getText());
					    posted.setLong(7,calendar.getDate().getTime());
						posted.setString(8, jtextArea_About.getText());
						

						if (posted.executeUpdate()!=0) {
							JOptionPane.showMessageDialog(null, "Book insertion was successful", 
					            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
							txt_author.setText("");txt_ýsbn.setText(""); txt_name.setText(""); 
							txt_publýsher.setText("");jtextArea_About.setText("");spinner.setValue(0);cb_genre.setSelectedIndex(0);
							calendar.setDate(new java.util.Date());lbl_authorid.setText("");
							
							select_author.firstname_author="";select_author.lastname_author="";select_author.id_author="";

						} else {
							JOptionPane.showMessageDialog(null, "ERROR", 
						            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);	
						}	
					} 
					else {
						txt_author.setText("");txt_ýsbn.setText(""); txt_name.setText(""); 
						txt_publýsher.setText("");jtextArea_About.setText("");spinner.setValue(0);cb_genre.setSelectedIndex(0);lbl_authorid.setText("");
						calendar.setDate(new java.util.Date());select_author.firstname_author="";select_author.lastname_author="";select_author.id_author="";
					}
				}
			catch (SQLIntegrityConstraintViolationException e23) {
                
                JOptionPane.showMessageDialog(null,"Book already available!","Registration is failed!",JOptionPane.WARNING_MESSAGE);
            }
			catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}	
			}
		});
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_add.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_add.setBackground(Color.white);
			}
		});
		btn_add.setForeground(new Color(0, 51, 204));
		btn_add.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_add.setFocusPainted(false);
		btn_add.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_add.setBackground(Color.WHITE);
		btn_add.setBounds(536, 432, 130, 52);
		panel.add(btn_add);
		
		JLabel lbl_about = new JLabel("About the Book");
		lbl_about.setBounds(536, 270, 148, 16);
		panel.add(lbl_about);
		lbl_about.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			txt_author.setText(select_author.firstname_author+select_author.lastname_author);
			lbl_authorid.setText(String.valueOf(select_author.id_author));
			//select author formundan dönen deðerleri txtfield lara yazdýrdým.	
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}
}
