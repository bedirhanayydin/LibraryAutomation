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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

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
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class author_operations extends JFrame {
	private JTable table;
	private JTextField txt_ýd;
	private JTextField txt_firstname; 
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID", "NAME","SURNAME","ABOUT"};
	Object[] row = new Object[4];
	private JPanel contentPane; private int mouseX,mouseY;
 private JTextField txt_lastname; 
 private JTextArea textArea_About = new JTextArea();

	/**
	 * Launch the application.
	 * @return 
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					author_operations frame = new author_operations();
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
	public void fill_table() {
		conn conn=new conn();
		ResultSet myRs;
		Statement myStat;
		try {
			myStat = conn.connection.createStatement();
			myRs = myStat.executeQuery("select * from authors");	
			while (myRs.next()) {
				row[0] = myRs.getString("author_id");
				row[1] = myRs.getString("firstname");
				row[2] = myRs.getString("lastname");
				row[3] = myRs.getString("about");
				modelim.addRow(row);
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(modelim);
	}
	
	public void delete() {
		String id,sql_query;
		id=txt_ýd.getText();
		sql_query="DELETE FROM authors WHERE author_id="+id;
		try {if (txt_ýd.getText().equals("") || txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")) {
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
			txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText("");textArea_About.setText("");
			fill_table();
			
				}
		else {
			txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText(""); textArea_About.setText("");
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void insert() {
		String name=txt_firstname.getText();
		String lastname=txt_lastname.getText();
		String about=textArea_About.getText();
		try {if (textArea_About.getText().equals("") || txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
		} else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Insertion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
			conn conn=new conn();
			PreparedStatement posted=conn.connection.prepareStatement("INSERT INTO authors(firstname,lastname,about) "
					+ "VALUES ('"+name+"','"+lastname+"','"+about+"')");
			posted.executeUpdate();
			JOptionPane.showMessageDialog(null, "The insertion operation was successful.", 
		            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.setRowCount(0);
			txt_firstname.setText("");txt_ýd.setText("");txt_lastname.setText("");textArea_About.setText("");
			fill_table();				
		} 
		else {
			txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText("");textArea_About.setText("");
		}
		}
		catch (SQLIntegrityConstraintViolationException e23) {
            JOptionPane.showMessageDialog(null,"Author already available!","Registration is failed!",JOptionPane.WARNING_MESSAGE);
        }catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void edit() {
		String idString=txt_ýd.getText();
		String name=txt_firstname.getText();
		String lastname=txt_lastname.getText();
		String about=textArea_About.getText();
		String sql_query="UPDATE authors SET author_id="+idString+","+
				  "firstname='"+name+
				  "',lastname='"+lastname+
				  "',about='"+about+"'WHERE author_id="+idString;
		try {if (txt_ýd.getText().equals("") || txt_firstname.getText().equals("")|| txt_lastname.getText().equals("")||textArea_About.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
		} 
		else if(JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Do The Editing?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
			conn conn=new conn();
			PreparedStatement posted = conn.connection.prepareStatement(sql_query);
		    posted.executeUpdate();
		    JOptionPane.showMessageDialog(null, "Editing Performed Successfully.", 
		            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.setRowCount(0);
			txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText("");textArea_About.setText("");
			fill_table();
		}
		else {
			txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText("");textArea_About.setText("");
		}
	}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public author_operations() {
		initGUI();
	}
	private void initGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 756, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 756, 536);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 756, 65);
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
		lbl_exit.setBounds(723, 0, 23, 65);
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
		
	
		
		
		JLabel author_operations = new JLabel("AUTHOR OPERATIONS");
		author_operations.setForeground(new Color(255, 255, 255));
		author_operations.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		author_operations.setBackground(Color.WHITE);
		author_operations.setBounds(253, 21, 242, 32);
		panel_1.add(author_operations);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(author_operations.class.getResource("/images/operations.png")));
		lblNewLabel.setBounds(0, 0, 106, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(102, 94, 28, 36);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 270, 265, 107);
		panel.add(scrollPane_1);
		textArea_About.setFont(new Font("Tahoma", Font.BOLD, 16));
		textArea_About.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		scrollPane_1.setViewportView(textArea_About);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setBounds(33, 141, 97, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		txt_ýd = new JTextField();
		txt_ýd.setBounds(140, 98, 111, 29);
		txt_ýd.setEnabled(false);
		txt_ýd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ýd.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(txt_ýd);
		txt_ýd.setColumns(10);
		
		txt_firstname = new JTextField();
		txt_firstname.setBounds(140, 145, 157, 29);
		txt_firstname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_firstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_firstname.setColumns(10);
		panel.add(txt_firstname);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 76, 421, 449);
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
				txt_ýd.setText(modelim.getValueAt(table.getSelectedRow(),0).toString());
				txt_firstname.setText((String) modelim.getValueAt(table.getSelectedRow(),1)); 
				txt_lastname.setText((String) modelim.getValueAt(table.getSelectedRow(),2)); 
				textArea_About.setText((String) modelim.getValueAt(table.getSelectedRow(),3)); 
			}
		});		
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
		fill_table();
		
		txt_lastname = new JTextField();
		txt_lastname.setBounds(140, 188, 157, 29);
		txt_lastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_lastname.setColumns(10);
		txt_lastname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(txt_lastname);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1.setBounds(33, 184, 97, 36);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("About the Author");
		lblNewLabel_1_1_1_1.setBounds(33, 228, 164, 36);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1_1_1);
		
		
		
		
		JButton btn_edit = new JButton("EDIT");
		btn_edit.setBounds(186, 396, 111, 38);
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
		btn_delete.setBounds(33, 455, 111, 38);
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
		btn_add.setBounds(33, 396, 111, 38);
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
					 table.print(JTable.PrintMode.NORMAL); 
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		lbl_yazdýr.setIcon(new ImageIcon(author_operations.class.getResource("/images/print.png")));
		lbl_yazdýr.setBounds(250, 76, 70, 54);
		panel.add(lbl_yazdýr);
		
		
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_ýd.setText("");txt_firstname.setText("");txt_lastname.setText("");textArea_About.setText("");table.clearSelection();
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
		btn_clean.setBounds(186, 455, 111, 38);
		panel.add(btn_clean);
		
		
		
	
	}
}
