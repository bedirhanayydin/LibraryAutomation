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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
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

import com.mysql.cj.jdbc.CallableStatement;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class users_operations extends JFrame {

	private JTable table;
	private JTextField txt_ýd;
	@SuppressWarnings("rawtypes")
	JComboBox cb_type = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox();

	private JTextField txt_firstname; 
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID", "NAME","SURNAME","USERNAME","PASSWORD","EMAÝL","SECUÝRTY QUESTÝON","ANSWER","TYPE","REGÝSTRATÝON"};
	Object[] row = new Object[10];
	private JPanel contentPane; private int mouseX,mouseY;
 private JTextField txt_lastname; 
 private JTextField txt_username;
 private JTextField txt_password;
 private JTextField txt_email;
 private JTextField txt_answer;
 private JTextField txt_value;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					users_operations frame = new users_operations();
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
	public void value_search() {
				  String value,sql_query;
					value=txt_value.getText().toString();
					sql_query="SELECT * FROM users WHERE first_name LIKE '%"+value+"%' OR last_name LIKE '%"+value+"%' OR username LIKE '%"+value+"%'";
						try {
							modelim.setRowCount(0);	
							conn conn=new conn();
							ResultSet myRs;
							Statement myStat;
							myStat = conn.connection.createStatement();
							myRs = myStat.executeQuery(sql_query);	
							while (myRs.next()) {
								row[0] = myRs.getString("user_id");
								row[1] = myRs.getString("first_name");
								row[2] = myRs.getString("last_name");
								row[3] = myRs.getString("username");
								row[4] = myRs.getString("password");
								row[5] = myRs.getString("email");
								row[6] = myRs.getString("security_question");
								row[7] = myRs.getString("answer");
								row[8] = myRs.getString("user_type");
								row[9] = myRs.getString("date_of_registration");
								modelim.addRow(row);
							}
						
							} catch (SQLException e1) {
							// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Error!");
						}
							
							table.setModel(modelim);
	}	
	
	public void fill_table() {
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("select * from users");	
			while (myRs.next()) {
				row[0] = myRs.getString("user_id");
				row[1] = myRs.getString("first_name");
				row[2] = myRs.getString("last_name");
				row[3] = myRs.getString("username");
				row[4] = myRs.getString("password");
				row[5] = myRs.getString("email");
				row[6] = myRs.getString("security_question");
				row[7] = myRs.getString("answer");
				row[8] = myRs.getString("user_type");
				row[9] = myRs.getString("date_of_registration");
				modelim.addRow(row);
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(modelim);
	}
	
	public void delete() {
		String id=txt_ýd.getText().toString();
		try {if (txt_ýd.getText().equals("") || txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")|| txt_answer.getText().equals("")|| txt_email.getText().equals("")|| txt_password.getText().equals("")|| txt_username.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
		} 
		else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Deletion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
				conn conn=new conn();
			CallableStatement cs=(CallableStatement) conn.connection.prepareCall("{call procedure_user_delete(?)}");
			cs.setString(1, id);
			cs.executeUpdate();
			JOptionPane.showMessageDialog(null, "The Deletion Was Successful", 
		            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.setRowCount(0);
			clean();
			fill_table();
				}
		else {
			clean(); 
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void insert() {
		String email=txt_email.getText();
		final String EMAIL_PATTERN = 
			    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		try {if ( txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")|| txt_answer.getText().equals("")|| txt_email.getText().equals("")|| txt_password.getText().equals("")|| txt_username.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
			
		}
		if (!email.matches(EMAIL_PATTERN)) { 
			JOptionPane.showMessageDialog(null, 
					  "Please enter a valid e-mail address", "ERROR", JOptionPane.ERROR_MESSAGE);
			} 
		else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Insertion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
			conn conn=new conn();
			CallableStatement callableStatement=(CallableStatement) conn.connection.prepareCall("{call procedure_user_add(?,?,?,?,?,?,?,?,?)}");
			callableStatement.setString(1, txt_firstname.getText());
			callableStatement.setString(2, txt_lastname.getText());
			callableStatement.setString(3, txt_username.getText());
			callableStatement.setString(4, txt_password.getText());
			callableStatement.setString(5, txt_email.getText());
			callableStatement.setString(6, comboBox.getSelectedItem().toString());
			callableStatement.setString(7, txt_answer.getText());
			callableStatement.setString(8, cb_type.getSelectedItem().toString());
			Date currentDate = new Date();
			callableStatement.setString(9,currentDate.toString() );
			if (callableStatement.executeUpdate()!=0) {
				JOptionPane.showMessageDialog(null, "The insertion operation was successful.", 
		            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				clean();
				fill_table();	
			} else {
				JOptionPane.showMessageDialog(null, "ERROR", 
			            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);	
			}			
		} 
		else {
			clean();
		}
		}
		catch (SQLIntegrityConstraintViolationException e23) {
            JOptionPane.showMessageDialog(null,"Username already available!","Registration is failed!",JOptionPane.WARNING_MESSAGE);
        }
		
		
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void edit() {
		String email=txt_email.getText();
		final String EMAIL_PATTERN = 
			    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		try {if ( txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")|| txt_answer.getText().equals("")|| txt_email.getText().equals("")|| txt_password.getText().equals("")|| txt_username.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
		}
			else if (!email.matches(EMAIL_PATTERN)) { 
				JOptionPane.showMessageDialog(null, "Please enter a valid e-mail address.");
				} 
			 else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Do The Editing?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
				conn conn=new conn();
				CallableStatement callableStatement=(CallableStatement) conn.connection.prepareCall("{call procedure_user_edit(?,?,?,?,?,?,?,?,?)}");
				callableStatement.setString(1, txt_firstname.getText());
				callableStatement.setString(2, txt_lastname.getText());
				callableStatement.setString(3, txt_username.getText());
				callableStatement.setString(4, txt_password.getText());
				callableStatement.setString(5, txt_email.getText());
				callableStatement.setString(6, comboBox.getSelectedItem().toString());
				callableStatement.setString(7, txt_answer.getText());
				callableStatement.setString(8, cb_type.getSelectedItem().toString());
				callableStatement.setString(9, txt_ýd.getText());
				if (callableStatement.executeUpdate()!=0) {
					JOptionPane.showMessageDialog(null, "Editing Performed Successfully.", 
			            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);					
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					model.setRowCount(0);
					clean();
					fill_table();	
				} else {
						
					JOptionPane.showMessageDialog(null, 
							  "User ID cannot be left blank", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				clean();
								
			} 
			else {
				clean();
			}
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	public void clean() {
		txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText("");table.clearSelection();txt_answer.setText("");txt_email.setText("");txt_password.setText("");
		txt_username.setText("");comboBox.setSelectedIndex(0);cb_type.setSelectedIndex(0);txt_value.setText("");
	}
	public users_operations() {
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1072, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1072, 669);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1072, 65);
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
		panel_1.setBackground(new Color(204, 204, 204));
		panel.add(panel_1);
		contentPane.setLayout(null);
		panel_1.setLayout(null);
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(1039, 0, 23, 65);
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
		
	
		
		
		JLabel users_operations = new JLabel("USERS OPERATIONS");
		users_operations.setForeground(new Color(0, 0, 0));
		users_operations.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		users_operations.setBackground(new Color(255, 255, 255));
		users_operations.setBounds(453, 21, 217, 32);
		panel_1.add(users_operations);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(users_operations.class.getResource("/images/users_operations.png")));
		lblNewLabel.setBounds(10, 0, 106, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(102, 86, 28, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setBounds(33, 133, 97, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_ýd = new JTextField();
		txt_ýd.setForeground(Color.RED);
		txt_ýd.setBounds(140, 90, 111, 29);
		txt_ýd.setEnabled(false);
		txt_ýd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ýd.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(txt_ýd);
		txt_ýd.setColumns(10);
		
		txt_firstname = new JTextField();
		txt_firstname.setBounds(140, 137, 206, 29);
		txt_firstname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_firstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_firstname.setColumns(10);
		panel.add(txt_firstname);
		
		
		
	
		
		txt_lastname = new JTextField();
		txt_lastname.setBounds(140, 180, 206, 29);
		txt_lastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_lastname.setColumns(10);
		txt_lastname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(txt_lastname);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1.setBounds(33, 176, 97, 36);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1_1);
		
		
		
		
		JButton btn_edit = new JButton("EDIT");
		btn_edit.setBounds(186, 561, 111, 38);
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
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
		
		btn_edit.setForeground(new Color(0, 51, 204));
		btn_edit.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_edit.setFocusPainted(false);
		btn_edit.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_edit.setBackground(Color.WHITE);
		panel.add(btn_edit);
		
		JButton btn_delete = new JButton("DELETE");
		btn_delete.setBounds(33, 620, 111, 38);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btn_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_delete.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_delete.setBackground(Color.white);
			}
		});
		
		btn_delete.setForeground(new Color(0, 51, 204));
		btn_delete.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_delete.setFocusPainted(false);
		btn_delete.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_delete.setBackground(Color.WHITE);
		panel.add(btn_delete);
		
		
		
		JButton btn_add = new JButton("ADD");
		btn_add.setBounds(33, 561, 111, 38);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert(); 
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
		
		JLabel lbl_yazdýr = new JLabel("");
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
		lbl_yazdýr.setBounds(276, 76, 70, 54);
		panel.add(lbl_yazdýr);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Username");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(33, 220, 97, 36);
		panel.add(lblNewLabel_1_1_1_1);
		
		txt_username = new JTextField();
		txt_username.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_username.setColumns(10);
		txt_username.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_username.setBounds(140, 224, 206, 29);
		panel.add(txt_username);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(33, 267, 97, 36);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		txt_password = new JTextField();
		txt_password.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_password.setColumns(10);
		txt_password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_password.setBounds(140, 271, 206, 29);
		panel.add(txt_password);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Email");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_2.setBounds(33, 314, 59, 36);
		panel.add(lblNewLabel_1_1_1_1_2);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_email.setBounds(140, 318, 206, 29);
		panel.add(txt_email);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Security Question");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1.setBounds(33, 361, 167, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 400, 313, 54);
		panel.add(scrollPane_1);
		
		scrollPane_1.setViewportView(comboBox);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"What Is Your Mother's Job?", "What Is Your Father's Job?","What primary school did you attend?","What are the last five digits of your driver's license number?","What is your mother's maiden name?","What is the name of your favorite childhood friend?","What was your childhood nickname?"}));
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Answer");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_2.setBounds(33, 452, 97, 36);
		panel.add(lblNewLabel_1_1_1_1_1_2);
		
		txt_answer = new JTextField();
		txt_answer.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_answer.setColumns(10);
		txt_answer.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_answer.setBounds(140, 456, 206, 29);
		panel.add(txt_answer);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("User Type");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(33, 506, 97, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		cb_type.setFont(new Font("Tahoma", Font.BOLD, 18));
		cb_type.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		cb_type.setBounds(140, 513, 97, 29);
		panel.add(cb_type);
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
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
		btn_clean.setBounds(186, 620, 111, 38);
		panel.add(btn_clean);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(356, 123, 706, 535);
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
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("***Search by first name , last name or username.");
		lblNewLabel_1_1_1_5.setToolTipText("");
		lblNewLabel_1_1_1_5.setForeground(Color.RED);
		lblNewLabel_1_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_5.setBounds(540, 101, 314, 21);
		panel.add(lblNewLabel_1_1_1_5);
		
		txt_value = new JTextField();
		txt_value.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  value_search();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  value_search();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    value_search();
			  }
			});
		txt_value.setForeground(Color.RED);
		txt_value.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_value.setColumns(10);
		txt_value.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_value.setBounds(553, 71, 287, 29);
		panel.add(txt_value);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Value to Search");
		lblNewLabel_1_1_2.setForeground(Color.RED);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(397, 67, 167, 36);
		panel.add(lblNewLabel_1_1_2);
		
		JButton btn_refresh = new JButton("REFRESH");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();txt_value.setText("");
				modelim.setRowCount(0); fill_table();clean();
			}
		});
		btn_refresh.setForeground(new Color(0, 51, 204));
		btn_refresh.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_refresh.setFocusPainted(false);
		btn_refresh.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_refresh.setBackground(Color.WHITE);
		btn_refresh.setBounds(864, 69, 167, 38);
		panel.add(btn_refresh);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_ýd.setText(modelim.getValueAt(table.getSelectedRow(),0).toString());
				txt_firstname.setText((String) modelim.getValueAt(table.getSelectedRow(),1)); 
				txt_lastname.setText((String) modelim.getValueAt(table.getSelectedRow(),2)); 
				txt_username.setText((String) modelim.getValueAt(table.getSelectedRow(),3)); 
				txt_password.setText((String) modelim.getValueAt(table.getSelectedRow(),4)); 
				txt_email.setText((String) modelim.getValueAt(table.getSelectedRow(),5)); 
				comboBox.setSelectedItem((String) modelim.getValueAt(table.getSelectedRow(),6)); 
				txt_answer.setText((String) modelim.getValueAt(table.getSelectedRow(),7)); 
				cb_type.setSelectedItem((String) modelim.getValueAt(table.getSelectedRow(),8)); 
			}
		});		
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
		fill_table();
		
	
	}
}
