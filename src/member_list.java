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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
@SuppressWarnings("serial")
public class member_list extends JFrame {

	private JPanel contentPane;private int mouseX,mouseY;
	private JTextField txt_value;
	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();DefaultTableModel model2 = new DefaultTableModel();
	Object[] column = { "ID", "NAME","SURNAME","PHONE","E-MAÝL","GENDER"};
	Object[] row = new Object[6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_list frame = new member_list();
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
			myRs = myStat.executeQuery("select * from members");	
			while (myRs.next()) {
				row[0] = myRs.getString("id");
				row[1] = myRs.getString("firstname");
				row[2] = myRs.getString("lastname");
				row[3] = myRs.getString("phone");
				row[4] = myRs.getString("email");
				row[5] = myRs.getString("gender");
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
	public member_list() {
	
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 867, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 867, 597);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 867, 65);
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
		lbl_exit.setBounds(834, 0, 23, 65);
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
		
	
		
		
		JLabel delete_member = new JLabel("MEMBER L\u0130ST");
		delete_member.setForeground(new Color(255, 255, 255));
		delete_member.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		delete_member.setBackground(Color.WHITE);
		delete_member.setBounds(336, 21, 156, 32);
		panel_1.add(delete_member);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(member_list.class.getResource("/images/user.png")));
		lblNewLabel.setBounds(10, 0, 70, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Value to Search");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(46, 76, 167, 36);
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
					sql_query="SELECT * FROM members WHERE firstname LIKE '%"+value+"%' OR lastname LIKE '%"+value+"%'";
						try {
							modelim.setRowCount(0);	
							conn conn=new conn();
							ResultSet myRs;
							Statement myStat;
							myStat = conn.connection.createStatement();
							myRs = myStat.executeQuery(sql_query);	
							while (myRs.next()) {
								row[0] = myRs.getString("id");
								row[1] = myRs.getString("firstname");
								row[2] = myRs.getString("lastname");
								row[3] = myRs.getString("phone");
								row[4] = myRs.getString("email");
								row[5] = myRs.getString("gender");
								modelim.addRow(row);
							}
						
							} catch (SQLException e1) {
							// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Error!");
						}
							
							table.setModel(modelim);
			     
			  }
			});
		txt_value.setForeground(Color.RED);
		txt_value.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_value.setColumns(10);
		txt_value.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_value.setBounds(202, 80, 258, 29);
		panel.add(txt_value);
		
		
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(640, 192, 65, 36);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(640, 292, 65, 36);
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Email");
		lblNewLabel_1_1_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_2.setBounds(640, 392, 65, 36);
		panel.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Gender");
		lblNewLabel_1_1_1_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_3.setBounds(640, 492, 74, 36);
		panel.add(lblNewLabel_1_1_1_3);
		
		JLabel lbl_name = new JLabel("");
		lbl_name.setForeground(new Color(255, 153, 0));
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_name.setBounds(640, 239, 217, 36);
		panel.add(lbl_name);
		
		JLabel lbl_phone = new JLabel("");
		lbl_phone.setForeground(new Color(255, 153, 0));
		lbl_phone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_phone.setBounds(640, 339, 217, 36);
		panel.add(lbl_phone);
		
		JLabel lbl_email = new JLabel("");
		lbl_email.setForeground(new Color(255, 153, 0));
		lbl_email.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_email.setBounds(640, 439, 217, 36);
		panel.add(lbl_email);
		
		JLabel lbl_gender = new JLabel("");
		lbl_gender.setForeground(new Color(255, 153, 0));
		lbl_gender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_gender.setBounds(640, 539, 105, 36);
		panel.add(lbl_gender);
		
		JLabel lbl_yazdýr = new JLabel("");
		lbl_yazdýr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					 table.print(JTable.PrintMode.NORMAL); 
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		lbl_yazdýr.setIcon(new ImageIcon(author_operations.class.getResource("/images/print.png")));
		lbl_yazdýr.setBounds(635, 132, 70, 54);
		panel.add(lbl_yazdýr);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 620, 454);
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbl_name.setText(modelim.getValueAt(table.getSelectedRow(),1).toString()+" "+modelim.getValueAt(table.getSelectedRow(),2).toString());
				lbl_phone.setText("(+90)"+(String) modelim.getValueAt(table.getSelectedRow(),3)); 
				lbl_email.setText((String) modelim.getValueAt(table.getSelectedRow(),4)); 
				lbl_gender.setText((String) modelim.getValueAt(table.getSelectedRow(),5));
			}
		});		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 17));
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
			
		JButton btn_refresh = new JButton("REFRESH");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_value.setText("");lbl_name.setText("");lbl_email.setText("");lbl_gender.setText("");lbl_phone.setText("");table.clearSelection();
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
		btn_refresh.setBounds(513, 78, 144, 38);
		panel.add(btn_refresh);
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("***Search by first name or last name.");
		lblNewLabel_1_1_1_5.setToolTipText("");
		lblNewLabel_1_1_1_5.setForeground(Color.RED);
		lblNewLabel_1_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_5.setBounds(189, 110, 288, 21);
		panel.add(lblNewLabel_1_1_1_5);
		fill_table();
		
	}
}
