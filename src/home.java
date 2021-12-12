import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class home extends JFrame {

	private JPanel contentPane;
	public int mouseX,mouseY;
	private static int counter_author=0,counter_member=0,counter_book=0;
	static String userLogString=login.userString;      //LOGÝN FORMUNDA ALDIGIMIZ KULLANICI NAME Ý ALDIK
	public static JButton btnAddBook;
	public static JButton btnDeleteBook;
	public static JButton btnEditBook;
	public static JLabel lbl_welcome_1_2;
	public static JLabel lbl_welcome_1_2_1;
	public static JButton btnEditMember;
	public static JButton btnAuthorOperations;
	public static JButton btnReturnbook;
	public static JButton btnAddMember;
	public static JLabel lbl_welcome_1_1_1;
	public static JButton btnDeleteMember;
	public static JButton btnUsersOperations;
	public static JButton btnýssuebook;
	public static JButton btnBookGenresOperations;
	public static JLabel lbl_welcome_1;
	public static JLabel lbl_welcome_1_1;
	public static JButton btnBookList;
	public static JButton btnMemberList;
	public static JLabel lbl_welcome;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home home = new home();
					home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**  
	 * Create the frame.
	 */
	public home() {
		initGUI();
	}
	@SuppressWarnings("static-access")
	public void mail(String baslikString,String icerikString) {
		Mail mail=new Mail();
		try {
			mail.MailBaslik=baslikString;
			mail.MailIcerik=icerikString;
			mail.sendMail("bedirhhanaydin@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	public void control() {
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();Date return_date=new Date();
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("SELECT * FROM issue_book INNER JOIN books on books.book_id=issue_book.issue_book_id  WHERE delivery_date IS NULL");
			while (myRs.next()) {
					try {
						 return_date= new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
						        .parse(myRs.getString("return_date"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (return_date.before(currentDate)) { //TESLÝM TARÝHÝ GEÇMÝÞSE
					mail("UYARI-AYDIN LÝBRARY",myRs.getString("name")+" kitabý için belirlenmiþ son teslim tarihini geçmiþ bulunmaktasýnýz.Lütfen kitabý kütüphanemize getiriniz. Aksi takdirde cezai iþlem baþlatýlacaktýr.");
				}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setContentPane(contentPane); 

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1170, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 250, 700);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 153, 204));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 250, 150);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(0, 0, 128));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(home.class.getResource("/images/logo.png")));
		lblNewLabel.setBounds(0, 0, 250, 150);
		panel_2.add(lblNewLabel);
		
		JPanel header_panel = new JPanel();
		header_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				setLocation(x-mouseX,y-mouseY);
			}
		});
		header_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}
		});
		header_panel.setBounds(0, 0, 1171, 29);
		contentPane.add(header_panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 145, 250, 10);
		panel_1.add(panel_3);
		
		lbl_welcome = new JLabel("");
		lbl_welcome.setText("Welcome"+" "+userLogString);
		lbl_welcome.setForeground(Color.WHITE);
		lbl_welcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lbl_welcome.setBackground(Color.WHITE);
		lbl_welcome.setBounds(10, 153, 219, 32);
		panel_1.add(lbl_welcome);
		
		lbl_welcome_1 = new JLabel("Books");
		lbl_welcome_1.setForeground(Color.BLACK);
		lbl_welcome_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lbl_welcome_1.setBackground(Color.WHITE);
		lbl_welcome_1.setBounds(10, 178, 74, 32);
		panel_1.add(lbl_welcome_1);
		
		btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_book add_book=new add_book();
				add_book.setVisible(true);
			}
		});
		btnAddBook.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddBook.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnAddBook.setBorderPainted(false);

			}
		});
		btnAddBook.setFocusPainted(false);
		btnAddBook.setBorderPainted(false);
		btnAddBook.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddBook.setContentAreaFilled(false);
		btnAddBook.setBounds(52, 287, 145, 23);
		panel_1.add(btnAddBook);
		
		btnEditBook = new JButton("Edit Book");
		btnEditBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit_book edit_book=new edit_book();
				edit_book.setVisible(true);
			}
		});
		btnEditBook.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnEditBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditBook.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnEditBook.setBorderPainted(false);

			}
		});
		btnEditBook.setBorderPainted(false);
		btnEditBook.setFocusPainted(false);
		btnEditBook.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditBook.setContentAreaFilled(false);
		btnEditBook.setBounds(52, 234, 145, 23);
		panel_1.add(btnEditBook);
		
		btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete_book delete_book=new delete_book();
				delete_book.setVisible(true);
			}
		});
		btnDeleteBook.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnDeleteBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteBook.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnDeleteBook.setBorderPainted(false);
			}
		});
		btnDeleteBook.setFocusPainted(false);
		btnDeleteBook.setBorderPainted(false);
		btnDeleteBook.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeleteBook.setContentAreaFilled(false);
		btnDeleteBook.setBounds(52, 261, 145, 23);
		panel_1.add(btnDeleteBook);
		
		btnBookList = new JButton("Book List");
		btnBookList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_list book_list=new book_list();
				book_list.setVisible(true);
			}
		});
		btnBookList.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnBookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBookList.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnBookList.setBorderPainted(false);

			}
		});
		btnBookList.setFocusPainted(false);
		btnBookList.setBorderPainted(false);
		btnBookList.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBookList.setContentAreaFilled(false);
		btnBookList.setBounds(52, 208, 145, 23);
		panel_1.add(btnBookList);
		
		lbl_welcome_1_1 = new JLabel("Members");
		lbl_welcome_1_1.setForeground(Color.BLACK);
		lbl_welcome_1_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lbl_welcome_1_1.setBackground(Color.WHITE);
		lbl_welcome_1_1.setBounds(10, 307, 111, 32);
		panel_1.add(lbl_welcome_1_1);
		
		btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_member add_member=new add_member();
				add_member.setVisible(true);
			}
		});
		btnAddMember.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnAddMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddMember.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnAddMember.setBorderPainted(false);

			}
		});
		btnAddMember.setFocusPainted(false);
		btnAddMember.setBorderPainted(false);
		btnAddMember.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddMember.setContentAreaFilled(false);
		btnAddMember.setBounds(52, 418, 145, 23);
		panel_1.add(btnAddMember);
		
		btnEditMember = new JButton("Edit Member");
		btnEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit_member edit_member=new edit_member();
				edit_member.setVisible(true);
			}
		});
		btnEditMember.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnEditMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditMember.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnEditMember.setBorderPainted(false);

			}
		});
		btnEditMember.setFocusPainted(false);
		btnEditMember.setBorderPainted(false);
		btnEditMember.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditMember.setContentAreaFilled(false);
		btnEditMember.setBounds(52, 365, 145, 23);
		panel_1.add(btnEditMember);
		
		btnDeleteMember = new JButton("Delete Member");
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete_member delete_member=new delete_member();
				delete_member.setVisible(true);
			}
		});
		btnDeleteMember.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnDeleteMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteMember.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnDeleteMember.setBorderPainted(false);

			}
		});
		btnDeleteMember.setFocusPainted(false);
		btnDeleteMember.setBorderPainted(false);
		btnDeleteMember.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeleteMember.setContentAreaFilled(false);
		btnDeleteMember.setBounds(52, 392, 150, 23);
		panel_1.add(btnDeleteMember);
		
		btnMemberList = new JButton("Member List");
		btnMemberList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_list member_list=new member_list();
				member_list.setVisible(true); 
			}
		});
		btnMemberList.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnMemberList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMemberList.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnMemberList.setBorderPainted(false);

			}
		});
		btnMemberList.setFocusPainted(false);
		btnMemberList.setBorderPainted(false);
		btnMemberList.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMemberList.setContentAreaFilled(false);
		btnMemberList.setBounds(52, 338, 145, 23);
		panel_1.add(btnMemberList);
		
		btnBookGenresOperations = new JButton("Genre Operations");
		btnBookGenresOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genre_operations genre_operations=new genre_operations();
				genre_operations.setVisible(true);
			}
		});
		btnBookGenresOperations.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBookGenresOperations.setFocusPainted(false);
		btnBookGenresOperations.setContentAreaFilled(false);
		btnBookGenresOperations.setBorderPainted(false);
		btnBookGenresOperations.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnBookGenresOperations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBookGenresOperations.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnBookGenresOperations.setBorderPainted(false);

			}
		});
		btnBookGenresOperations.setBounds(52, 468, 138, 23);
		panel_1.add(btnBookGenresOperations);
		
		lbl_welcome_1_1_1 = new JLabel("Book Genres");
		lbl_welcome_1_1_1.setForeground(Color.BLACK);
		lbl_welcome_1_1_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lbl_welcome_1_1_1.setBackground(Color.WHITE);
		lbl_welcome_1_1_1.setBounds(10, 439, 138, 32);
		panel_1.add(lbl_welcome_1_1_1);
		
		lbl_welcome_1_2 = new JLabel("Authors");
		lbl_welcome_1_2.setForeground(Color.BLACK);
		lbl_welcome_1_2.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lbl_welcome_1_2.setBackground(Color.WHITE);
		lbl_welcome_1_2.setBounds(10, 489, 93, 32);
		panel_1.add(lbl_welcome_1_2);
		
		btnAuthorOperations = new JButton("Author Operations ");
		btnAuthorOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				author_operations aOperations=new author_operations();
				aOperations.setVisible(true);
			}
		});
		btnAuthorOperations.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAuthorOperations.setFocusPainted(false);
		btnAuthorOperations.setContentAreaFilled(false);
		btnAuthorOperations.setBorderPainted(false);
		btnAuthorOperations.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		btnAuthorOperations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAuthorOperations.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnAuthorOperations.setBorderPainted(false);

			}
		});
		btnAuthorOperations.setBounds(52, 518, 145, 23);
		panel_1.add(btnAuthorOperations);
		
		lbl_welcome_1_2_1 = new JLabel("Users");
		lbl_welcome_1_2_1.setForeground(Color.BLACK);
		lbl_welcome_1_2_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lbl_welcome_1_2_1.setBackground(Color.WHITE);
		lbl_welcome_1_2_1.setBounds(10, 539, 93, 32);
		panel_1.add(lbl_welcome_1_2_1);
		
		btnUsersOperations = new JButton("Users Operations ");
		btnUsersOperations.setForeground(Color.BLACK);
		btnUsersOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users_operations users_operations=new users_operations();
				users_operations.setVisible(true);
			}
		});
		btnUsersOperations.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUsersOperations.setFocusPainted(false);
		btnUsersOperations.setContentAreaFilled(false);
		btnUsersOperations.setBorderPainted(false);
		btnUsersOperations.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		btnUsersOperations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUsersOperations.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnUsersOperations.setBorderPainted(false);

			}
		});
		btnUsersOperations.setBounds(52, 568, 145, 23);
		panel_1.add(btnUsersOperations);
		
		btnýssuebook = new JButton("Issue Book");
		btnýssuebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ýssue_book ýssueBook=new ýssue_book();
				ýssueBook.setVisible(true);
			}
		});
		btnýssuebook.setForeground(Color.CYAN);
		btnýssuebook.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnýssuebook.setFocusPainted(false);
		btnýssuebook.setContentAreaFilled(false);
		btnýssuebook.setBorderPainted(false);
		btnýssuebook.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		btnýssuebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnýssuebook.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnýssuebook.setBorderPainted(false);

			}
		});
		btnýssuebook.setBounds(10, 616, 111, 23);
		panel_1.add(btnýssuebook);
		
		btnReturnbook = new JButton("Return Book");
		btnReturnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				return_book return_book=new return_book();
				return_book.setVisible(true);
			}
		});
		btnReturnbook.setForeground(Color.CYAN);
		btnReturnbook.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnReturnbook.setFocusPainted(false);
		btnReturnbook.setContentAreaFilled(false);
		btnReturnbook.setBorderPainted(false);
		btnReturnbook.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		btnReturnbook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReturnbook.setBorderPainted(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				btnReturnbook.setBorderPainted(false);

			}
		});
		btnReturnbook.setBounds(91, 646, 123, 23);
		panel_1.add(btnReturnbook);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(283, 36, 270, 225);
		panel.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(252,151,89));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 102, 51));
		panel_5.setBounds(0, 0, 270, 64);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Books");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 21, 133, 32);
		panel_5.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(0, 56, 270, 10);
		panel_4.add(panel_6);
		
		JLabel lbl_book = new JLabel("0");
		lbl_book.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_book.setForeground(Color.WHITE);
		lbl_book.setFont(new Font("SansSerif", Font.BOLD, 50));
		lbl_book.setBackground(Color.WHITE);
		lbl_book.setBounds(48, 107, 180, 64);
		panel_4.add(lbl_book);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBounds(579, 36, 270, 225);
		panel.add(panel_4_1);
		panel_4_1.setLayout(null);
		panel_4_1.setBackground(new Color(226,116,164));
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(new Color(255, 0, 153));
		panel_5_1.setBounds(0, 0, 270, 64);
		panel_4_1.add(panel_5_1);
		panel_5_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Members");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(10, 21, 133, 32);
		panel_5_1.add(lblNewLabel_1_1);
		
		JPanel panel_6_2_1 = new JPanel();
		panel_6_2_1.setBackground(Color.WHITE);
		panel_6_2_1.setBounds(0, 56, 270, 10);
		panel_4_1.add(panel_6_2_1);
		
		JLabel lbl_member = new JLabel("0");
		lbl_member.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_member.setForeground(Color.WHITE);
		lbl_member.setFont(new Font("SansSerif", Font.BOLD, 50));
		lbl_member.setBackground(Color.WHITE);
		lbl_member.setBounds(51, 106, 180, 64);
		panel_4_1.add(lbl_member);
		
				
				JPanel panel_4_2 = new JPanel();
		panel_4_2.setBounds(873, 36, 270, 225);
		panel.add(panel_4_2);
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(new Color(233,222,109));
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBackground(new Color(255, 215, 0));
		panel_5_2.setBounds(0, 0, 270, 64);
		panel_4_2.add(panel_5_2);
		panel_5_2.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Authors");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		lblNewLabel_1_2.setBackground(Color.WHITE);
		lblNewLabel_1_2.setBounds(10, 21, 133, 32);
		panel_5_2.add(lblNewLabel_1_2);
		
	
		
		JPanel panel_6_2 = new JPanel();
		panel_6_2.setBackground(Color.WHITE);
		panel_6_2.setBounds(0, 56, 270, 10);
		panel_4_2.add(panel_6_2);
		
		JLabel lbl_author = new JLabel("0");
		lbl_author.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_author.setForeground(Color.WHITE);
		lbl_author.setFont(new Font("SansSerif", Font.BOLD, 50));
		lbl_author.setBackground(Color.WHITE);
		lbl_author.setBounds(49, 108, 180, 64);
		panel_4_2.add(lbl_author);
		
	
		
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(1146, 0, 24, 31);
		panel.add(lbl_exit);
		lbl_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,"The program is closing, are you sure","WARNING",JOptionPane.YES_NO_OPTION)==0) {
						System.exit(0);
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
		lbl_exit.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_exit.setBackground(Color.BLACK);
		
		JPanel panel_4_3 = new JPanel();
		panel_4_3.setLayout(null);
		panel_4_3.setBackground(new Color(153, 102, 255));
		panel_4_3.setBounds(283, 272, 866, 417);
		panel.add(panel_4_3);
		
		JPanel panel_5_3 = new JPanel();
		panel_5_3.setLayout(null);
		panel_5_3.setBackground(new Color(153, 0, 255));
		panel_5_3.setBounds(0, 0, 860, 64);
		panel_4_3.add(panel_5_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Recently Added Books");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		lblNewLabel_1_3.setBackground(Color.WHITE);
		lblNewLabel_1_3.setBounds(10, 21, 299, 32);
		panel_5_3.add(lblNewLabel_1_3);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBackground(Color.WHITE);
		panel_6_1.setBounds(0, 56, 860, 10);
		panel_4_3.add(panel_6_1);
		
		JPanel panel_4_4 = new JPanel();
		panel_4_4.setLayout(null);
		panel_4_4.setBackground(Color.WHITE);
		panel_4_4.setBounds(10, 75, 270, 331);
		panel_4_3.add(panel_4_4);
		
		JLabel lbl_bookname = new JLabel("");
		lbl_bookname.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_bookname.setFont(new Font("Sitka Small", Font.BOLD, 14));
		lbl_bookname.setBounds(49, 35, 190, 42);
		panel_4_4.add(lbl_bookname);
		
		JLabel lbl_authorname = new JLabel("");
		lbl_authorname.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_authorname.setFont(new Font("Sitka Small", Font.ITALIC, 14));
		lbl_authorname.setBounds(49, 80, 190, 40);
		panel_4_4.add(lbl_authorname);
		
		JLabel lbl_puslisher = new JLabel("");
		lbl_puslisher.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puslisher.setFont(new Font("Sitka Small", Font.BOLD, 14));
		lbl_puslisher.setBounds(49, 289, 190, 31);
		panel_4_4.add(lbl_puslisher);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(home.class.getResource("/images/book_mockup.jpg")));
		lblNewLabel_2.setBounds(0, 0, 270, 331);
		panel_4_4.add(lblNewLabel_2);
		
		JPanel panel_4_4_1 = new JPanel();
		panel_4_4_1.setLayout(null);
		panel_4_4_1.setBackground(Color.WHITE);
		panel_4_4_1.setBounds(298, 75, 270, 331);
		panel_4_3.add(panel_4_4_1);
		
		JLabel lbl_bookname_1 = new JLabel("");
		lbl_bookname_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_bookname_1.setFont(new Font("Sitka Small", Font.BOLD, 14));
		lbl_bookname_1.setBounds(49, 35, 190, 42);
		panel_4_4_1.add(lbl_bookname_1);
		
		JLabel lbl_authorname_1 = new JLabel("");
		lbl_authorname_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_authorname_1.setFont(new Font("Sitka Small", Font.ITALIC, 14));
		lbl_authorname_1.setBounds(49, 80, 190, 40);
		panel_4_4_1.add(lbl_authorname_1);
		
		JLabel lbl_puslisher_1 = new JLabel("");
		lbl_puslisher_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puslisher_1.setFont(new Font("Sitka Small", Font.BOLD, 14));
		lbl_puslisher_1.setBounds(49, 289, 190, 31);
		panel_4_4_1.add(lbl_puslisher_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(home.class.getResource("/images/book_mockup.jpg")));
		lblNewLabel_3.setBounds(0, 0, 270, 331);
		panel_4_4_1.add(lblNewLabel_3);
		
		JPanel panel_4_4_2 = new JPanel();
		panel_4_4_2.setLayout(null);
		panel_4_4_2.setBackground(Color.WHITE);
		panel_4_4_2.setBounds(586, 75, 270, 331);
		panel_4_3.add(panel_4_4_2);
		
		JLabel lbl_bookname_2 = new JLabel("");
		lbl_bookname_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_bookname_2.setFont(new Font("Sitka Small", Font.BOLD, 14));
		lbl_bookname_2.setBounds(49, 35, 190, 42);
		panel_4_4_2.add(lbl_bookname_2);
		
		JLabel lbl_authorname_2 = new JLabel("");
		lbl_authorname_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_authorname_2.setFont(new Font("Sitka Small", Font.ITALIC, 14));
		lbl_authorname_2.setBounds(49, 80, 190, 40);
		panel_4_4_2.add(lbl_authorname_2);
		
		JLabel lbl_puslisher_2 = new JLabel("");
		lbl_puslisher_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puslisher_2.setFont(new Font("Sitka Small", Font.BOLD, 14));
		lbl_puslisher_2.setBounds(49, 289, 190, 31);
		panel_4_4_2.add(lbl_puslisher_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setIcon(new ImageIcon(home.class.getResource("/images/book_mockup.jpg")));
		lblNewLabel_2_2.setBounds(0, 0, 270, 331);
		panel_4_4_2.add(lblNewLabel_2_2);

		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				conn conn=new conn(); 
				Statement myStat,myStatmember,myStatBook,myStat_lastaddedbook = null;
				try {
					myStat = conn.connection.createStatement();
					ResultSet myRs = myStat.executeQuery("SELECT COUNT(author_id) FROM authors");
					while (myRs.next()) {
						counter_author=Integer.valueOf(myRs.getString("COUNT(author_id)"));
					}
				lbl_author.setText(String.valueOf(counter_author));
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet myRsmember;
				try {
					myStatmember = conn.connection.createStatement();
					myRsmember = myStatmember.executeQuery("SELECT COUNT(id) FROM members");
					while (myRsmember.next()) {
					counter_member=Integer.valueOf(myRsmember.getString("COUNT(id)"));
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				lbl_member.setText(String.valueOf(counter_member));
				
				ResultSet myRsbook;
				try {
					myStatBook = conn.connection.createStatement();
					myRsbook = myStatBook.executeQuery("SELECT COUNT(book_id) FROM books");
					while (myRsbook.next()) {
					counter_book=Integer.valueOf(myRsbook.getString("COUNT(book_id)"));
				}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				lbl_book.setText(String.valueOf(counter_book));

				
				ResultSet lastadded_bookResultSet;
				try {
					myStat_lastaddedbook =conn.connection.createStatement();
					lastadded_bookResultSet = myStat_lastaddedbook.executeQuery("SELECT * FROM books ORDER BY books.book_id DESC LIMIT 3");
					Book [] books= new Book[3];
					int index=0;
					while (lastadded_bookResultSet.next()) {
						Book b=new Book();
						b.name=lastadded_bookResultSet.getString("name");
						b.isbn=lastadded_bookResultSet.getString("isbn");
						b.author_id=lastadded_bookResultSet.getInt("author_id");
						b.publisher=lastadded_bookResultSet.getString("publisher");
						
						books[index++]=b;
					}
					lbl_bookname.setText(books[2].name);
					//lbl_isbn.setText(books[2].isbn);
					Author a1=books[2].getAuthor();
					lbl_authorname.setText(a1.firstname+" "+a1.lastname);
					lbl_puslisher.setText(books[2].publisher);

					lbl_bookname_1.setText(books[1].name);
					//lbl_isbn_1.setText("ISBN = "+books[1].isbn);
					Author a2=books[1].getAuthor();
					lbl_authorname_1.setText(a2.firstname+" "+a2.lastname);
					lbl_puslisher_1.setText(books[1].publisher);
					
					lbl_bookname_2.setText(books[0].name);
					//lbl_isbn_2.setText("ISBN = "+books[0].isbn);
					Author a3=books[0].getAuthor();
					lbl_authorname_2.setText(a3.firstname+" "+a3.lastname);
					lbl_puslisher_2.setText(books[0].publisher);
				}				
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void windowLostFocus(WindowEvent e) {
			}
			
		});
		control();

	}
	public JButton getBtnAddBook() {
		return btnAddBook;
	}
	public JButton getBtnDeleteBook() {
		return btnDeleteBook;
	}
	public JButton getBtnEditBook() {
		return btnEditBook;
	}
	public JLabel getLbl_welcome_1_2() {
		return lbl_welcome_1_2;
	}
	public JLabel getLbl_welcome_1_2_1() {
		return lbl_welcome_1_2_1;
	}
	public JButton getBtnEditMember() {
		return btnEditMember;
	}
	public JButton getBtnAuthorOperations() {
		return btnAuthorOperations;
	}
	public JButton getBtnReturnbook() {
		return btnReturnbook;
	}
	public JButton getBtnAddMember() {
		return btnAddMember;
	}
	public JLabel getLbl_welcome_1_1_1() {
		return lbl_welcome_1_1_1;
	}
	public JButton getBtnDeleteMember() {
		return btnDeleteMember;
	}
	public JButton getBtnUsersOperations() {
		return btnUsersOperations;
	}
	public JButton getBtnýssuebook() {
		return btnýssuebook;
	}
	public JButton getBtnBookGenresOperations() {
		return btnBookGenresOperations;
	}
	public JLabel getLbl_welcome_1() {
		return lbl_welcome_1;
	}
	public JLabel getLbl_welcome_1_1() {
		return lbl_welcome_1_1;
	}
	public JButton getBtnBookList() {
		return btnBookList;
	}
	public JButton getBtnMemberList() {
		return btnMemberList;
	}
	public JLabel getLbl_welcome() {
		return lbl_welcome;
	}
}
