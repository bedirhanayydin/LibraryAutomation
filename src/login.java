import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class login {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	static String userString;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);		
					window.frame.setFocusable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 *  the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 804, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 785, 444);
		frame.setLocationRelativeTo(null);		

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204));
		panel.setBounds(0, 0, 284, 444);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/images/LOG\u0130N.png")));
		lblNewLabel_1.setBounds(20, 46, 254, 359);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(282, 0, 503, 444);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUsername.getText().equals("username")) {
					txtUsername.setText("");
			}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername.getText().equals("")) {
					txtUsername.setText("username");
			}
		}});

			
		txtUsername.setToolTipText("USERNAME");
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		txtUsername.setText("username");
		txtUsername.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtUsername.setBounds(130, 145, 252, 38);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn conn=new conn();
					String query="select * from users where username=? and password=?";
					PreparedStatement statement=conn.connection.prepareStatement(query);
					statement.setString(1, txtUsername.getText().toString());
					statement.setString(2, String.valueOf(pwdPassword.getPassword()));
					ResultSet rs= statement.executeQuery();
					if (rs.next()) {
						
						JOptionPane.showMessageDialog(null,"Welcome "+ rs.getString("first_name") +" "+ rs.getString("last_name"));
						//diðer forma girilen kullanýcýnýn name i yolladým	
					    userString=rs.getString("first_name");
					    home home=new home();
						if (rs.getString("user_type").equals("user")) {	//EÐER LOGÝNDE USER TÝPÝNDE BÝRÝ GÝRÝÞ YAPMIÞSA SADECE MEMBERLÝST VE BOOKLÝST GÖREBÝLECEK ADMÝN HERÞEYÝ GÖREBÝLÝR!!!
							home.getBtnDeleteMember().setVisible(false);
							home.getBtnAddBook().setVisible(false);
							home.getBtnAddMember().setVisible(false);
							home.getBtnAuthorOperations().setVisible(false);
							home.getBtnBookGenresOperations().setVisible(false);
							home.getBtnDeleteBook().setVisible(false);
							home.getBtnEditBook().setVisible(false);
							home.getBtnEditMember().setVisible(false);
							home.getBtnýssuebook().setVisible(false);
							home.getBtnReturnbook().setVisible(false);
							home.getBtnUsersOperations().setVisible(false);
							home.getLbl_welcome_1_1_1().setVisible(false);
							home.getLbl_welcome_1_2().setVisible(false);
							home.getLbl_welcome_1_2_1().setVisible(false);
							home.getLbl_welcome_1().setBounds(10, 254, 74, 32);
							home.getLbl_welcome_1().setForeground(new Color(139, 0, 0));
							home.getBtnBookList().setBounds(52, 294, 145, 23);
							home.getBtnBookList().setFont(new Font("Tahoma", Font.BOLD, 18));
							home.getBtnBookList().setForeground(new Color(255, 192, 203));
							home.getLbl_welcome_1_1().setBounds(10, 385, 111, 32);
							home.getLbl_welcome_1_1().setForeground(new Color(139, 0, 0));
							home.getBtnMemberList().setBounds(52, 425, 145, 23);
							home.getBtnMemberList().setFont(new Font("Tahoma", Font.BOLD, 18));
							home.getBtnMemberList().setForeground(new Color(255, 192, 203));
							home.getLbl_welcome().setForeground(Color.cyan);


						}
						home.setVisible(true);
						frame.dispose();
						
					} else {
						
						JOptionPane.showMessageDialog(null, 
								  "Invalid username or password", "ERROR", JOptionPane.ERROR_MESSAGE);	
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_login.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_login.setBackground(Color.white);
			}
		});
		
		btn_login.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_login.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_login.setForeground(new Color(0, 51, 204));
		btn_login.setBackground(Color.WHITE);
		btn_login.setBounds(130, 314, 111, 38);
		panel_1.add(btn_login);
		btn_login.setFocusPainted(false); //butonun üstüne basýnca çizmesini engelleme

		
		JButton btn_forgot_password = new JButton("Forgot Password?");
		btn_forgot_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgot_password fp=new forgot_password();
				fp.setVisible(true);
			}
		});
		btn_forgot_password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_forgot_password.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_forgot_password.setBackground(Color.white);
			}
		});
		btn_forgot_password.setForeground(new Color(0, 51, 204));
		btn_forgot_password.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_forgot_password.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_forgot_password.setBackground(Color.WHITE);
		btn_forgot_password.setBounds(271, 314, 161, 38);
		panel_1.add(btn_forgot_password);
		btn_forgot_password.setFocusPainted(false);//butonun üstüne basýnca çizmesini engelleme
		
		JLabel lbl_Exit = new JLabel("X");
		lbl_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,"The program is closing, are you sure","WARNING",JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
					}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_Exit.setForeground(Color.red);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_Exit.setForeground(new Color(51, 153, 204));
			}
		});
		lbl_Exit.setForeground(new Color(51, 153, 204));
		lbl_Exit.setBackground(Color.BLACK);
		lbl_Exit.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_Exit.setBounds(478, 0, 18, 32);
		panel_1.add(lbl_Exit);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (pwdPassword.getText().equals("password")) {
					pwdPassword.setText("");
			}
			}
			@SuppressWarnings("deprecation")
			// belli derleyici uyarýlarýný devre dýþý býrakýr
			public void focusLost(FocusEvent e) {
				if (pwdPassword.getText().equals("")) {
					pwdPassword.setText("password");
			}
		}});

		pwdPassword.setForeground(Color.GRAY);
		pwdPassword.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		pwdPassword.setText("password");
		pwdPassword.setToolTipText("PASSWORD");
		pwdPassword.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pwdPassword.setBounds(130, 215, 252, 32);
		panel_1.add(pwdPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(new Color(51, 153, 204));
		lblNewLabel_2.setBackground(new Color(51, 153, 204));
		lblNewLabel_2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 40));
		lblNewLabel_2.setBounds(209, 41, 119, 38);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(login.class.getResource("/images/padlock.png")));
		lblNewLabel_3.setBounds(392, 209, 23, 38);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setIcon(new ImageIcon(login.class.getResource("/images/name.png")));
		lblNewLabel_3_1.setBounds(392, 145, 23, 38);
		panel_1.add(lblNewLabel_3_1);
		
		JCheckBox show_password = new JCheckBox("Show Password");
		show_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (show_password.isSelected()) 
					pwdPassword.setEchoChar((char)0);
				else 
					pwdPassword.setEchoChar('*');
			}
		});
		show_password.setForeground(new Color(0, 51, 204));
		show_password.setBackground(Color.WHITE);
		show_password.setFont(new Font("Tahoma", Font.BOLD, 13));
		show_password.setBounds(130, 257, 127, 23);
		panel_1.add(show_password);
		show_password.setFocusPainted(false); //butonun üstüne basýnca çizmesini engelleme


	}
}
