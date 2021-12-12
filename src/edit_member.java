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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class edit_member extends JFrame {

	private JPanel contentPane; 
	private int mouseX,mouseY;
		private JTextField txt_firstname;
		private JTextField txt_lastname;
		private JTextField txt_email;
		private MaskFormatter fmt;
		private JTextField txt_id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit_member frame = new edit_member();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public edit_member() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 427, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 427, 661);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 427, 65);
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
		lbl_exit.setBounds(394, 0, 23, 65);
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
		
	
		
		
		JLabel add_member = new JLabel("ED\u0130T MEMBER");
		add_member.setForeground(new Color(255, 255, 255));
		add_member.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		add_member.setBackground(Color.WHITE);
		add_member.setBounds(157, 21, 154, 32);
		panel_1.add(add_member);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(edit_member.class.getResource("/images/innovation.png")));
		lblNewLabel.setBounds(10, 0, 70, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setBounds(66, 129, 97, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_firstname = new JTextField();
		txt_firstname.setForeground(Color.RED);
		txt_firstname.setBounds(66, 176, 289, 29);
		txt_firstname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_firstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_firstname.setColumns(10);
		panel.add(txt_firstname);
		

		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(66, 232, 97, 36);
		panel.add(lblNewLabel_1_1_1);
		
		txt_lastname = new JTextField();
		txt_lastname.setForeground(Color.RED);
		txt_lastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_lastname.setColumns(10);
		txt_lastname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_lastname.setBounds(66, 279, 289, 29);
		panel.add(txt_lastname);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(66, 337, 67, 36);
		panel.add(lblNewLabel_1_1_1_1);
		
			try {
				fmt = new MaskFormatter("###-###-####");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		JFormattedTextField formattedTextField = new JFormattedTextField(fmt);
		formattedTextField.setForeground(Color.RED);
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		formattedTextField.setColumns(12);
		formattedTextField.setBounds(66, 384, 136, 36);
		panel.add(formattedTextField);
				
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Email");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_2.setBounds(66, 441, 97, 36);
		panel.add(lblNewLabel_1_1_1_2);
		
		txt_email = new JTextField();
		txt_email.setForeground(Color.RED);
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_email.setBounds(66, 488, 289, 29);
		panel.add(txt_email);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Gender");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_2_1.setBounds(66, 549, 67, 36);
		panel.add(lblNewLabel_1_1_1_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.RED);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(177, 553, 99, 29);
		panel.add(comboBox);
		
		
		
		JButton btn_Search = new JButton("SEARCH");
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString;
				idString=txt_id.getText();
				if (txt_id.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");}
				 else  {			
				conn conn=new conn();
				Statement myStat;
				try {
					
					myStat = conn.connection.createStatement();
					ResultSet myRs = myStat.executeQuery("SELECT * FROM members WHERE id="+idString);
				if (myRs.next()) {
					btn_Search.setEnabled(false);txt_id.setEnabled(false);
					txt_firstname.setText(myRs.getString("firstname"));
					txt_lastname.setText(myRs.getString("lastname"));
					formattedTextField.setText(myRs.getString("phone"));
					txt_email.setText(myRs.getString("email"));
					comboBox.setSelectedItem(myRs.getString("gender"));
				}
				else {
					JOptionPane.showMessageDialog(null, 
							  "Invalid Member ID", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 }	
			}
		});
		btn_Search.setForeground(new Color(0, 51, 204));
		btn_Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Search.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_Search.setBackground(Color.white);
			}
		});
		btn_Search.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_Search.setFocusPainted(false);
		btn_Search.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_Search.setBackground(Color.WHITE);
		btn_Search.setBounds(284, 76, 111, 38);
		panel.add(btn_Search);

		JButton btn_edit = new JButton("EDIT");
		btn_edit.setBounds(52, 612, 111, 38);
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=txt_email.getText();
				final String EMAIL_PATTERN = 
					    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				
					
					try {if (txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")||formattedTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Fill in The Blanks");}
					else if (!email.matches(EMAIL_PATTERN)) { 
						JOptionPane.showMessageDialog(null, "Please enter a valid e-mail address.");
						} 
					 else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Do The Editing?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
						conn conn=new conn();
						
						PreparedStatement posted=conn.connection.prepareStatement("UPDATE members SET firstname=?,lastname=?,phone=?,email=?,gender=? WHERE id=?");
						posted.setString(1, txt_firstname.getText());
						posted.setString(2, txt_lastname.getText());
						posted.setString(3,formattedTextField.getText() );
						posted.setString(4, txt_email.getText());
						posted.setString(5, comboBox.getSelectedItem().toString());
						posted.setString(6, txt_id.getText());
						if (posted.executeUpdate()!=0) {
							JOptionPane.showMessageDialog(null, "Editing Performed Successfully.", 
					            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);					
							btn_Search.setEnabled(true);	btn_Search.setBackground(new Color(125,203,225,255));txt_id.setEnabled(true);

						} else {
								
							JOptionPane.showMessageDialog(null, 
									  "Member ID cannot be left blank", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
						txt_firstname.setText("");txt_lastname.setText("");txt_email.setText(""); formattedTextField.setText(""); txt_id.setText("");comboBox.setSelectedIndex(0);
										
					} 
					else {
						txt_firstname.setText("");txt_lastname.setText("");txt_email.setText(""); formattedTextField.setText("");txt_id.setText("");btn_Search.setEnabled(true);comboBox.setSelectedIndex(0);txt_id.setEnabled(true);
					}
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			
		});
		btn_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_edit.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_edit.setBackground(Color.white);
			}
		});
		btn_edit.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_edit.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_edit.setForeground(new Color(0, 51, 204));
		btn_edit.setBackground(Color.WHITE);
		panel.add(btn_edit);
		btn_edit.setFocusPainted(false);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Member ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(66, 76, 107, 36);
		panel.add(lblNewLabel_1_1_2);
		
		txt_id = new JTextField();
		txt_id.setForeground(Color.RED);
		txt_id.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_id.setColumns(10);
		txt_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				    if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
				        txt_id.setEditable(true);
				    } else {
				    	txt_id.setEditable(false);
				    	JOptionPane.showMessageDialog(null,"Enter only numeric digits(0-9)");
				    }
			}
		});
		txt_id.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_id.setBounds(183, 80, 81, 29);
		panel.add(txt_id);
		
	
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_firstname.setText("");txt_lastname.setText("");txt_email.setText(""); formattedTextField.setText(""); txt_id.setText("");btn_Search.setEnabled(true);comboBox.setSelectedIndex(0);txt_id.setEnabled(true);
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
		btn_clean.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_clean.setFocusPainted(false);
		btn_clean.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_clean.setBackground(Color.WHITE);
		btn_clean.setBounds(244, 612, 111, 38);
		panel.add(btn_clean);

		
	}
}
