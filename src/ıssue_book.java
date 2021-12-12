import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ýssue_book extends JFrame {
	private JLabel lbl_genre_id = new JLabel("");

	private int mouseX,mouseY;
	private JTextField txt_book_ýd;
	private JTextField txt_memberýd;
	private JPanel contentPane;
	private JDateChooser return_date;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ýssue_book frame = new ýssue_book();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*public int count(int _book_id) {
		int total=0;
		conn conn=new conn();
		PreparedStatement ps;
		ResultSet myRs;
		try {
			ps = conn.connection.prepareStatement("SELECT  COUNT(*) AS total FROM issue_book WHERE issue_book_id=? and status=issued");	
			ps.setInt(1, _book_id);
			myRs=ps.executeQuery();	
			if (myRs.next()) {
				total=myRs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(total);
		return total;
	}*/
	/**
	 * Create the frame.
	 */
	public ýssue_book() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 524, 679);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 524, 65);
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
		panel_1.setBackground(new Color(0, 100, 0));
		panel.add(panel_1);
		contentPane.setLayout(null);
		panel_1.setLayout(null);
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(493, 0, 23, 65);
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
		
	
		
		
		JLabel add_book = new JLabel("ISSUE BOOK");
		add_book.setForeground(new Color(255, 255, 255));
		add_book.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		add_book.setBackground(Color.WHITE);
		add_book.setBounds(185, 21, 126, 32);
		panel_1.add(add_book);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ýssue_book.class.getResource("/images/operations.png")));
		lblNewLabel.setBounds(10, 0, 106, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1.setBounds(65, 101, 89, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_book_ýd = new JTextField();
		txt_book_ýd.setForeground(new Color(0, 0, 128));
		txt_book_ýd.setBounds(181, 105, 165, 29);
		txt_book_ýd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_book_ýd.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_book_ýd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				    if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
				    	txt_book_ýd.setEditable(true);
		
				    } else {
				    	txt_book_ýd.setEditable(false);
				    	JOptionPane.showMessageDialog(null,"Enter only numeric digits(0-9)");
				    }
			}
		});
		txt_book_ýd.setColumns(10);
		panel.add(txt_book_ýd);
		

		JLabel lblNewLabel_1_1_1 = new JLabel("Member ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(65, 193, 119, 36);
		panel.add(lblNewLabel_1_1_1);
		
		txt_memberýd = new JTextField();
		txt_memberýd.setForeground(new Color(0, 0, 128));
		txt_memberýd.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_memberýd.setColumns(10);
		txt_memberýd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_memberýd.setBounds(181, 197, 165, 29);
		txt_memberýd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				    if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
				    	txt_memberýd.setEditable(true);
		
				    } else {
				    	txt_memberýd.setEditable(false);
				    	JOptionPane.showMessageDialog(null,"Enter only numeric digits(0-9)");
				    }
			}
		});
		panel.add(txt_memberýd);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Issue Date:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(65, 320, 119, 36);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Return Date:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1.setBounds(65, 395, 119, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lbl_book_name = new JLabel("Book Name:");
		lbl_book_name.setForeground(Color.RED);
		lbl_book_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_book_name.setBounds(122, 146, 392, 36);
		panel.add(lbl_book_name);
		
		JLabel lbl_member_name = new JLabel("Member Name:");
		lbl_member_name.setForeground(Color.RED);
		lbl_member_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_member_name.setBounds(122, 240, 392, 36);
		panel.add(lbl_member_name);
		
		JDateChooser issue_date = new JDateChooser();
		GregorianCalendar cal = (GregorianCalendar)GregorianCalendar.getInstance();
		issue_date.setSelectableDateRange(new Date(),cal.getTime());
		issue_date.setBounds(194, 330, 211, 20);
		panel.add(issue_date);
		
		return_date = new JDateChooser();
		/*GregorianCalendar cale = (GregorianCalendar)GregorianCalendar.getInstance();
		// set the max date
		cale.set(2021, 5, 30);
		// MinDate is the current Date
		// MaxDate you can set in the GregorianCalendar object
		return_date.setSelectableDateRange(new Date(), cale.getTime());*/
		return_date.getJCalendar().setMinSelectableDate(new Date());
		return_date.setBounds(194, 406, 211, 20);
		panel.add(return_date);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		scrollPane.setBounds(194, 478, 211, 84);
		panel.add(scrollPane);
		
		JTextArea jtextArea_About = new JTextArea();
		jtextArea_About.setFont(new Font("Tahoma", Font.BOLD, 11));
		jtextArea_About.setBorder(new LineBorder(new Color(51, 153, 204), 2, true));
		scrollPane.setViewportView(jtextArea_About);
		
		lbl_genre_id.setToolTipText("Genre ID");
		lbl_genre_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_genre_id.setForeground(Color.RED);
		lbl_genre_id.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_genre_id.setBackground(Color.WHITE);
		lbl_genre_id.setBounds(0, 282, 63, 36);
		panel.add(lbl_genre_id);
		
		
		JButton btn_search_member = new JButton("SEARCH MEMBER\r\n\r\n");
		btn_search_member.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_memberýd.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");}
				 else  {			
				conn conn=new conn();
				Statement myStat;
				try {
					myStat = conn.connection.createStatement();
					ResultSet myRs = myStat.executeQuery("SELECT * FROM members WHERE id="+txt_memberýd.getText());
				if (myRs.next()) {
					lbl_member_name.setText(myRs.getString("firstname")+" "+myRs.getString("lastname")+" "+myRs.getString("phone"));
				}
				else {
					JOptionPane.showMessageDialog(null, 
							  "Invalid Member ID", "ERROR", JOptionPane.ERROR_MESSAGE);
					txt_memberýd.setText("");lbl_member_name.setText("Member Name:");
				}
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 }
			}
		});
		btn_search_member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_search_member.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_search_member.setBackground(Color.white);
			}
		});
		btn_search_member.setForeground(new Color(0, 51, 204));
		btn_search_member.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_search_member.setFocusPainted(false);
		btn_search_member.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_search_member.setBackground(Color.WHITE);
		btn_search_member.setBounds(356, 194, 158, 36);
		panel.add(btn_search_member);
		
		
		
		JButton btn_search_book =new JButton("SEARCH BOOK\r\n");
		btn_search_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_book_ýd.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");}
				 else  {			
				conn conn=new conn();
				Statement myStat;
				try {
					myStat = conn.connection.createStatement();
					ResultSet myRs = myStat.executeQuery("SELECT * FROM books WHERE book_id="+txt_book_ýd.getText());
				if (myRs.next()) {
					lbl_book_name.setText(myRs.getString("name"));
				}
				else {
					JOptionPane.showMessageDialog(null, 
							  "Invalid Books ID", "ERROR", JOptionPane.ERROR_MESSAGE);
					txt_book_ýd.setText("");lbl_book_name.setText("Book Name:");
				}
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 }
			}
			
			
		});
		btn_search_book.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_search_book.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_search_book.setBackground(Color.white);
			}
		});
		btn_search_book.setForeground(new Color(0, 51, 204));
		btn_search_book.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_search_book.setFocusPainted(false);
		btn_search_book.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_search_book.setBackground(Color.WHITE);
		btn_search_book.setBounds(356, 105, 158, 36);
		panel.add(btn_search_book);
    	
		 
		
		
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
				txt_book_ýd.setText("");txt_memberýd.setText("");issue_date.setCalendar(null);return_date.setCalendar(null);jtextArea_About.setText("");
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
		btn_clean.setBounds(305, 601, 130, 52);
		panel.add(btn_clean);
	
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		JButton btn_complete = new JButton("COMPLETE");
		btn_complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {	if (txt_book_ýd.getText().equals("")||txt_memberýd.getText().equals("")||issue_date.getDate() == null||return_date.getDate() == null) {
					JOptionPane.showMessageDialog(null, 
							  "Fill in the Blanks", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
					else if (JOptionPane.showConfirmDialog(null,"Are you sure you want to perform the operation?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
						conn conn=new conn();
						PreparedStatement posted=conn.connection.prepareStatement("INSERT INTO issue_book(issue_book_id,member_id,status,issue_date,return_date,note)VALUES (?,?,?,?,?,?)");
						posted.setInt(1, Integer.valueOf(txt_book_ýd.getText()));
						posted.setInt(2, Integer.valueOf(txt_memberýd.getText()));
						posted.setString(3, "issued");
						posted.setString(4, dateFormat.format(issue_date.getDate()));
						posted.setString(5, dateFormat.format(return_date.getDate()));
						posted.setString(6, jtextArea_About.getText());
						
						if (posted.executeUpdate()!=0) {
							JOptionPane.showMessageDialog(null, "Book delivery has been completed.", 
					            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
							
							txt_book_ýd.setText("");txt_memberýd.setText("");issue_date.setDate(new java.util.Date());return_date.setCalendar(null);jtextArea_About.setText("");txt_memberýd.setText("");lbl_member_name.setText("Member Name:");
							lbl_book_name.setText("Book Name:");
							select_author.firstname_author="";select_author.lastname_author="";select_author.id_author="";

						} else {
							JOptionPane.showMessageDialog(null, "ERROR", 
						            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);	
							lbl_book_name.setText("Book Name:");txt_book_ýd.setText("");txt_memberýd.setText("");issue_date.setDate(new java.util.Date());return_date.setCalendar(null);jtextArea_About.setText("");txt_memberýd.setText("");lbl_member_name.setText("Member Name:");
						}	
					} 
					else {
						lbl_book_name.setText("Book Name:");txt_book_ýd.setText("");txt_memberýd.setText("");issue_date.setDate(new java.util.Date());return_date.setCalendar(null);	jtextArea_About.setText("");	txt_memberýd.setText("");lbl_member_name.setText("Member Name:");
						}
				}
			
			catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}	
			}
		});
		btn_complete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_complete.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_complete.setBackground(Color.white);
			}
		});
		btn_complete.setForeground(new Color(0, 51, 204));
		btn_complete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_complete.setFocusPainted(false);
		btn_complete.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_complete.setBackground(Color.WHITE);
		btn_complete.setBounds(104, 601, 130, 52);
		panel.add(btn_complete);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Note:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(127, 501, 57, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		

		
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			
			
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}
}
