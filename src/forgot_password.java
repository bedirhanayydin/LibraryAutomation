import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
@SuppressWarnings("serial")
public class forgot_password extends JFrame {

	private JPanel contentPane;
	private JTextField txtSecurityQuestion;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtAnswer;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgot_password frame = new forgot_password();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public forgot_password() {
		
		initGUI();
	}
	private void initGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 450);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);		

		
		JPanel panel = new JPanel();
		panel.setToolTipText("Fill in the username box and click the search button, then fill in the answer box and click verify");
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 455, 450);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lbl_Exit = new JLabel("X");
		lbl_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
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
		lbl_Exit.setBounds(435, 0, 18, 32);
		panel.add(lbl_Exit);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(57, 90, 81, 14);
		panel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(57, 150, 50, 14);
		panel.add(lblName);
		
		JLabel lblYourSecurityQuestion = new JLabel("Your Security Question");
		lblYourSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYourSecurityQuestion.setBounds(129, 197, 181, 14);
		panel.add(lblYourSecurityQuestion);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAnswer.setBounds(57, 265, 62, 14);
		panel.add(lblAnswer);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(57, 309, 81, 14);
		panel.add(lblPassword);
		
		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSecurityQuestion.setDisabledTextColor(new Color(255, 0, 0));
		txtSecurityQuestion.setForeground(new Color(0, 0, 0));
		txtSecurityQuestion.setEnabled(false);
		txtSecurityQuestion.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtSecurityQuestion.setBounds(57, 222, 352, 20);
		panel.add(txtSecurityQuestion);
		txtSecurityQuestion.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setDisabledTextColor(new Color(255, 0, 0));
		txtName.setForeground(new Color(0, 0, 0));
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtName.setBounds(150, 149, 160, 20);
		panel.add(txtName);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUsername.setDisabledTextColor(new Color(255, 0, 0));
		txtUsername.setForeground(new Color(0, 0, 0));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtUsername.setBounds(150, 89, 160, 20);
		panel.add(txtUsername);
		
		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAnswer.setEnabled(false);
		txtAnswer.setDisabledTextColor(new Color(255, 0, 0));
		txtAnswer.setForeground(new Color(0, 0, 0));
		txtAnswer.setColumns(10);
		txtAnswer.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtAnswer.setBounds(150, 264, 160, 20);
		panel.add(txtAnswer);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPassword.setDisabledTextColor(new Color(255, 0, 0));
		txtPassword.setEnabled(false);
		txtPassword.setForeground(new Color(0, 0, 0));
		txtPassword.setColumns(10);
		txtPassword.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtPassword.setBounds(150, 308, 160, 20);
		panel.add(txtPassword);
		
		
		JButton btnBack = new JButton("Turn Back");		
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBack.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBack.setBackground(Color.white);
			}
		});
		btnBack.setForeground(new Color(0, 51, 204));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(174, 365, 89, 32);
		panel.add(btnBack);
		btnBack.setFocusPainted(false);//butonun üstüne basýnca çizmesini engelleme
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ae) {
				try {if (txtUsername.getText().equals("")&&txtSecurityQuestion.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");
				} else {
						conn conn=new conn();
					String query_username="select * from users where username=?";
					PreparedStatement statement=conn.connection.prepareStatement(query_username);
					statement.setString(1, txtUsername.getText());
					ResultSet rSet=statement.executeQuery();
					if (rSet.next()) {
						txtName.setText(rSet.getString("first_name"));
						txtSecurityQuestion.setText(rSet.getString("security_question"));	
						txtUsername.setForeground(Color.red);
						txtAnswer.enable();
						txtUsername.disable();
						
					}
					else {
						JOptionPane.showMessageDialog(null, 
								  "Invalid username", "ERROR", JOptionPane.ERROR_MESSAGE);txtUsername.setText("");
					}
					
				}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(Color.white);
			}
		});
		btnSearch.setForeground(new Color(0, 51, 204));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setFocusPainted(false);
		btnSearch.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(320, 81, 89, 32);
		panel.add(btnSearch);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
					try {
						if (txtUsername.getText().equals("")&&txtAnswer.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"Fill in The Blanks");
						} else {
						conn conn1=new conn();
						String query_answer="select * from users where answer=?";
					PreparedStatement statement_answer=conn1.connection.prepareStatement(query_answer);
						statement_answer.setString(1, txtAnswer.getText());
						ResultSet resultSet=statement_answer.executeQuery();
					if (resultSet.next()) {
						txtPassword.setText(resultSet.getString("password"));
						txtAnswer.disable();
						txtAnswer.setForeground(Color.red);
					}
					else {
						JOptionPane.showMessageDialog(null,"Invalid Answer");
					}
				}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnVerify.setForeground(new Color(0, 51, 204));
		btnVerify.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerify.setFocusPainted(false);
		btnVerify.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btnVerify.setBackground(Color.WHITE);
		btnVerify.setBounds(320, 256, 93, 32);
		panel.add(btnVerify);
		
		JLabel lblFillInThe = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;Fill in the username box and click the search<br>button, then fill in the answer box and click verify</html>", SwingConstants.CENTER);
		lblFillInThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblFillInThe.setForeground(Color.WHITE);
		lblFillInThe.setLabelFor(lblFillInThe);
		lblFillInThe.setToolTipText("");
		lblFillInThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFillInThe.setBounds(43, 11, 382, 59);
		panel.add(lblFillInThe);
		
	
		
		
		
	}
}
