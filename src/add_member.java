import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class add_member extends JFrame {

	private JPanel contentPane; 
	private int mouseX,mouseY;
		private JTextField txt_firstname;
		private JTextField txt_lastname;
		private JTextField txt_email;
		private MaskFormatter fmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_member frame = new add_member();
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
	public add_member() {
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
		
	
		
		
		JLabel add_member = new JLabel("ADD MEMBER");
		add_member.setForeground(new Color(255, 255, 255));
		add_member.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		add_member.setBackground(Color.WHITE);
		add_member.setBounds(157, 21, 154, 32);
		panel_1.add(add_member);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(add_member.class.getResource("/images/add-group.png")));
		lblNewLabel.setBounds(10, 0, 70, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setBounds(65, 101, 97, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_firstname = new JTextField();
		txt_firstname.setBounds(65, 148, 289, 29);
		txt_firstname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_firstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_firstname.setColumns(10);
		panel.add(txt_firstname);
		

		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(65, 204, 97, 36);
		panel.add(lblNewLabel_1_1_1);
		
		txt_lastname = new JTextField();
		txt_lastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_lastname.setColumns(10);
		txt_lastname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_lastname.setBounds(65, 251, 289, 29);
		panel.add(txt_lastname);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(65, 309, 67, 36);
		panel.add(lblNewLabel_1_1_1_1);
		
			try {
				fmt = new MaskFormatter("###-###-####");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		JFormattedTextField formattedTextField = new JFormattedTextField(fmt);
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		formattedTextField.setColumns(12);
		formattedTextField.setBounds(65, 356, 136, 36);
		panel.add(formattedTextField);
				
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Email");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_2.setBounds(65, 413, 97, 36);
		panel.add(lblNewLabel_1_1_1_2);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_email.setBounds(65, 460, 289, 29);
		panel.add(txt_email);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Gender");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_2_1.setBounds(65, 521, 67, 36);
		panel.add(lblNewLabel_1_1_1_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(176, 525, 99, 29);
		panel.add(comboBox);
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_firstname.setText("");txt_lastname.setText("");txt_email.setText(""); formattedTextField.setText(""); 
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
		btn_clean.setBounds(243, 600, 111, 38);
		panel.add(btn_clean);
		
		

		JButton btn_add = new JButton("ADD");
		btn_add.setBounds(56, 600, 111, 38);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=txt_email.getText();
				final String EMAIL_PATTERN = 
					    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					try {if (txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")||formattedTextField.getText().equals("")||txt_email.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Fill in The Blanks");}
						if (!email.matches(EMAIL_PATTERN)) { 
						JOptionPane.showMessageDialog(null, 
								  "Please enter a valid e-mail address", "ERROR", JOptionPane.ERROR_MESSAGE);
						} 
					 else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Insertion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
						 conn conn=new conn();
						PreparedStatement posted=conn.connection.prepareStatement("INSERT INTO members(firstname,lastname,phone,email,gender)VALUES (?,?,?,?,?)");
						posted.setString(1, txt_firstname.getText());
						posted.setString(2, txt_lastname.getText());
						posted.setString(3,formattedTextField.getText() );
						posted.setString(4, txt_email.getText());
						posted.setString(5, comboBox.getSelectedItem().toString());
						if (posted.executeUpdate()!=0) {
							JOptionPane.showMessageDialog(null, "The insertion operation was successful.", 
					            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
							txt_firstname.setText("");txt_lastname.setText("");txt_email.setText(""); formattedTextField.setText("");

						} else {
							JOptionPane.showMessageDialog(null, "ERROR", 
						            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);	
						}	
					} 
					else {
						txt_firstname.setText("");txt_lastname.setText("");txt_email.setText(""); formattedTextField.setText("");
					}
						
					}
					catch (SQLIntegrityConstraintViolationException e23) {   //NUMARA VE MAÝLE GÖRE AYNI KAYIDI DENETLEME
	                    
	                    JOptionPane.showMessageDialog(null,"Member already available!","Registration is failed!",JOptionPane.WARNING_MESSAGE);
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
		btn_add.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_add.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_add.setForeground(new Color(0, 51, 204));
		btn_add.setBackground(Color.WHITE);
		panel.add(btn_add);
		btn_add.setFocusPainted(false);
		

	}
}
