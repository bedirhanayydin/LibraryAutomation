import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class return_book extends JFrame {
	private JTable table;
	JTextArea textArea = new JTextArea();
	private JTextField txt_ýd;
	@SuppressWarnings("rawtypes")
	JComboBox cb_type = new JComboBox();

	private JTextField txt_membername; 
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID","BOOK NAME" ,"NAME","STATUS","ÝSSUE DATE","RETURN DATE","DELÝVERY DATE","NOTE"};
	Object[] row = new Object[8];
	private int mouseX,mouseY;
	private JTextField txt_value;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					return_book frame = new return_book();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	  public void search() {
		  String value,sql_query;
			value=txt_value.getText().toString();
			sql_query="SELECT members.firstname, members.lastname, issue_book.member_id, members.id, issue_book.`status`, issue_book.issue_date, issue_book.return_date, issue_book.note,issue_book.delivery_date,  issue_book.issue_book_id,  books.book_id,  books.`name` FROM issue_book INNER JOIN members ON  issue_book.member_id = members.id INNER JOIN books ON issue_book.issue_book_id = books.book_id WHERE firstname LIKE '%"+value+"%' OR lastname LIKE '%"+value+"%' OR name LIKE '%"+value+"%'";	
				try {
					modelim.setRowCount(0);	
					conn conn=new conn();
					ResultSet myRs;
					Statement myStat;
					myStat = conn.connection.createStatement();
					myRs = myStat.executeQuery(sql_query);	
					while (myRs.next()) {
						row[0] = myRs.getString("issue_book_id");
						row[1]=myRs.getString("name");
						row[2] = myRs.getString("firstname")+" "+myRs.getString("lastname");
						row[3] = myRs.getString("status");
						row[4] = myRs.getString("issue_date");
						row[5] = myRs.getString("return_date");
						row[6] = myRs.getString("delivery_date");
						row[7] = myRs.getString("note");
						modelim.addRow(row);
					}
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error!");
				}
					
					table.setModel(modelim);
	  }
	
	public void clean() {
		txt_ýd.setText("");txt_membername.setText("");txt_value.setText(""); textArea.setText("");cb_type.setSelectedIndex(0);
	}
	public void fill_table() {
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("SELECT members.firstname, members.lastname, issue_book.member_id, members.id, issue_book.`status`, issue_book.issue_date, issue_book.return_date, issue_book.note,  issue_book.issue_book_id,  books.book_id,  books.`name`,issue_book.delivery_date FROM issue_book INNER JOIN members ON  issue_book.member_id = members.id  INNER JOIN books ON issue_book.issue_book_id = books.book_id");	
			while (myRs.next()) {
				row[0] = myRs.getString("issue_book_id");
				row[1]=myRs.getString("name");
				row[2] = myRs.getString("firstname")+" "+myRs.getString("lastname");
				row[3] = myRs.getString("status");
				row[4] = myRs.getString("issue_date");
				row[5] = myRs.getString("return_date");
				row[6] = myRs.getString("delivery_date");
				row[7] = myRs.getString("note");
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public return_book() {
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
		panel_1.setBackground(new Color(0, 100, 0));
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(105, 356, 241, 115);
		panel.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		JLabel return_book = new JLabel("RETURN BOOK");
		return_book.setForeground(new Color(0, 0, 0));
		return_book.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		return_book.setBackground(new Color(255, 255, 255));
		return_book.setBounds(472, 21, 168, 32);
		panel_1.add(return_book);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(return_book.class.getResource("/images/operations.png")));
		lblNewLabel.setBounds(10, 0, 106, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0130ssue Book ID");
		lblNewLabel_1.setBounds(10, 118, 134, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Member Name");
		lblNewLabel_1_1.setBounds(10, 170, 134, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_ýd = new JTextField();
		txt_ýd.setEditable(false);
		txt_ýd.setForeground(Color.RED);
		txt_ýd.setBounds(150, 122, 101, 29);
		txt_ýd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ýd.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(txt_ýd);
		txt_ýd.setColumns(10);
		
		txt_membername = new JTextField();
		txt_membername.setEditable(false);
		txt_membername.setBounds(150, 174, 196, 29);
		txt_membername.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_membername.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_membername.setColumns(10);
		panel.add(txt_membername);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		
		JButton btn_update = new JButton("UPDATE");
		btn_update.setBounds(47, 561, 111, 38);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {if (txt_ýd.getText().equals("")|| txt_membername.getText().equals("")||textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");}
				 else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Do The Editing?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
					conn conn=new conn();
					PreparedStatement posted=conn.connection.prepareStatement("UPDATE issue_book SET status=?,delivery_date=?,note=? WHERE issue_book_id=?");
					posted.setString(1,String.valueOf((cb_type.getSelectedItem())));
					posted.setString(2,dateFormat.format(currentDate));
					posted.setString(3, textArea.getText().toString());
					posted.setString(4, txt_ýd.getText().toString());
					if (posted.executeUpdate()!=0) {
						JOptionPane.showMessageDialog(null, "Editing Performed Successfully.", 
				            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						model.setRowCount(0);
						clean();
						fill_table();
					} else {
							
						JOptionPane.showMessageDialog(null, 
								  "WARNING", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					clean(); textArea.setText("");
									
				} 
				else {
					clean();	textArea.setText("");	
					}
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		
	});
		btn_update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_update.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_update.setBackground(Color.white);
			}
		});
		btn_update.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_update.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_update.setForeground(new Color(0, 51, 204));
		btn_update.setBackground(Color.WHITE);
		panel.add(btn_update);
		btn_update.setFocusPainted(false);
		
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
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Note");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_2.setBounds(47, 343, 48, 36);
		panel.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Status");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(47, 256, 97, 36);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		cb_type.setFont(new Font("Tahoma", Font.BOLD, 18));
		cb_type.setModel(new DefaultComboBoxModel(new String[] {"issued", "return"}));
		cb_type.setBounds(154, 260, 111, 29);
		panel.add(cb_type);
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();textArea.setText("");
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
		btn_clean.setBounds(123, 618, 111, 38);
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
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("***Search by first name , last name or book name.");
		lblNewLabel_1_1_1_5.setToolTipText("");
		lblNewLabel_1_1_1_5.setForeground(Color.RED);
		lblNewLabel_1_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_5.setBounds(540, 101, 314, 21);
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
				table.clearSelection();textArea.setText("");
				modelim.setRowCount(0); fill_table(); clean();
			}
		});
		btn_refresh.setForeground(new Color(0, 51, 204));
		btn_refresh.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_refresh.setFocusPainted(false);
		btn_refresh.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_refresh.setBackground(Color.WHITE);
		btn_refresh.setBounds(864, 69, 167, 38);
		panel.add(btn_refresh);
		
		scrollPane_1.setViewportView(textArea);
		
		JButton btn_deletee = new JButton("DELETE"); 
		btn_deletee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql_query;
				sql_query="DELETE FROM issue_book WHERE issue_book_id="+txt_ýd.getText();
				try {if (txt_ýd.getText().equals("")|| txt_membername.getText().equals("")||textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");
				} 
				else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Deletion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
						conn conn=new conn();
					PreparedStatement posted=conn.connection.prepareStatement(sql_query);
					posted.executeUpdate();
					JOptionPane.showMessageDialog(null, "The Deletion Was Successful", 
				            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					model.setRowCount(0);
					clean();textArea.setText("");
					fill_table();
						}
				else {
					clean();	textArea.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_deletee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_deletee.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_deletee.setBackground(Color.white);
			}
		});
		btn_deletee.setForeground(new Color(0, 51, 204));
		btn_deletee.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_deletee.setFocusPainted(false);
		btn_deletee.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_deletee.setBackground(Color.WHITE);
		btn_deletee.setBounds(196, 561, 111, 38);
		panel.add(btn_deletee);
		
		JButton btn_tarihi_geçmiþ = new JButton("OVERDUE BOOK LIST");
		btn_tarihi_geçmiþ.setToolTipText("TESL\u0130M TAR\u0130H\u0130 GEC\u0130KM\u0130\u015E K\u0130TAP L\u0130STES\u0130");
		btn_tarihi_geçmiþ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				overdue_book_list overdue_book_list=new overdue_book_list();
				overdue_book_list.setVisible(true);
			}
		});
		btn_tarihi_geçmiþ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_tarihi_geçmiþ.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_tarihi_geçmiþ.setBackground(Color.white);
			}
		});
		btn_tarihi_geçmiþ.setForeground(new Color(0, 51, 204));
		btn_tarihi_geçmiþ.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_tarihi_geçmiþ.setFocusPainted(false);
		btn_tarihi_geçmiþ.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_tarihi_geçmiþ.setBackground(Color.WHITE);
		btn_tarihi_geçmiþ.setBounds(10, 500, 336, 38);
		panel.add(btn_tarihi_geçmiþ);
		
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_ýd.setText(modelim.getValueAt(table.getSelectedRow(),0).toString());
				txt_membername.setText((String) modelim.getValueAt(table.getSelectedRow(),2));  
				cb_type.setSelectedItem((String) modelim.getValueAt(table.getSelectedRow(),3)); 
				textArea.setText((String) modelim.getValueAt(table.getSelectedRow(),7)); 
			}
		});		
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
		
		fill_table();
	}
}
