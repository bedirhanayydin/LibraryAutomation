import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class genre_operations extends JFrame {

	private JPanel contentPane;public int mouseX,mouseY;
	private JTextField txt_ýd;
	private JTextField txt_name;
	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID", "NAME"};
	Object[] row = new Object[2];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					genre_operations frame = new genre_operations();
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
			myRs = myStat.executeQuery("select * from book_genres");	
			while (myRs.next()) {
				row[0] = myRs.getString("genre_id");
				row[1] = myRs.getString("genre_name");
				modelim.addRow(row);
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(modelim);
	}
	public void insert() {
		 String name=txt_name.getText();
		try {if (txt_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
		} else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Insertion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
			conn conn=new conn();
			PreparedStatement posted=conn.connection.prepareStatement("INSERT INTO book_genres(genre_name) "
					+ "VALUES ('"+name+"')");
			posted.executeUpdate();
			JOptionPane.showMessageDialog(null, "The insertion operation was successful.", 
		            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.setRowCount(0);
			txt_name.setText("");txt_ýd.setText("");
			fill_table();				
		} 
		else {
			txt_name.setText("");txt_ýd.setText("");		
			}
		}
		catch (SQLIntegrityConstraintViolationException e23) {
            
            JOptionPane.showMessageDialog(null,"Book genre already available!","Registration is failed!",JOptionPane.WARNING_MESSAGE);
        }catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void delete() {

		String id,sql_query;
		id=txt_ýd.getText();
		sql_query="DELETE FROM book_genres WHERE genre_id="+id;
		try {if (txt_ýd.getText().equals("") || txt_name.getText().equals("")) {
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
			txt_ýd.setText("");txt_name.setText("");
			fill_table();
				}
		else {
			txt_name.setText("");txt_ýd.setText("");		
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void edit() 
	{
		String sql_query,id,name;
		id=txt_ýd.getText();
		name=txt_name.getText();
		sql_query="UPDATE book_genres SET genre_id="+id+","+
				  "genre_name='"+name+"'WHERE genre_id="+id;
		try {if (txt_ýd.getText().equals("") || txt_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Fill in The Blanks");
		} 
		else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Do The Editing?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
			conn conn=new conn();
			PreparedStatement posted = conn.connection.prepareStatement(sql_query);
		    posted.executeUpdate();
		    JOptionPane.showMessageDialog(null, "Editing Performed Successfully.", 
		            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.setRowCount(0);
			txt_ýd.setText("");txt_name.setText("");
			fill_table();
		}
		else {
			txt_name.setText("");txt_ýd.setText("");		
			}
	}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public genre_operations() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 593, 330);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
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
		panel_1.setBackground(new Color(51, 102, 153));
		panel_1.setBounds(0, 0, 593, 65);
		panel.add(panel_1);
		contentPane.setLayout(null);
		panel_1.setLayout(null);
		
		JLabel lbl_exit = new JLabel("X");
		lbl_exit.setBounds(560, 0, 23, 65);
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
		
		JLabel genre_operatýons = new JLabel("BOOK GENRE OPERATIONS");
		genre_operatýons.setForeground(new Color(255, 255, 255));
		genre_operatýons.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		genre_operatýons.setBackground(Color.WHITE);
		genre_operatýons.setBounds(165, 21, 279, 32);
		panel_1.add(genre_operatýons);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(genre_operations.class.getResource("/images/operations.png")));
		lblNewLabel.setBounds(0, 0, 106, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(65, 123, 28, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(40, 170, 53, 36);
		panel.add(lblNewLabel_1_1);
		
		txt_ýd = new JTextField();
		txt_ýd.setEnabled(false);
		txt_ýd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ýd.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_ýd.setBounds(103, 123, 131, 29);
		panel.add(txt_ýd);
		txt_ýd.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_name.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_name.setColumns(10);
		txt_name.setBounds(103, 170, 180, 29);
		panel.add(txt_name);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		scrollPane.setBounds(325, 76, 258, 243);
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
		table.getTableHeader().setBackground(Color.red);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setOpaque(false);
		
		fill_table();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_ýd.setText(modelim.getValueAt(table.getSelectedRow(),0).toString());
				txt_name.setText((String) modelim.getValueAt(table.getSelectedRow(),1)); 
			}
		});		
		
		
		
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
		lbl_yazdýr.setIcon(new ImageIcon(genre_operations.class.getResource("/images/print.png")));
		lbl_yazdýr.setBounds(250, 76, 70, 53);
		panel.add(lbl_yazdýr);
		
		JButton btn_add = new JButton("ADD");
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
		btn_add.setBounds(50, 217, 111, 38);
		panel.add(btn_add);
		btn_add.setFocusPainted(false); //butonun üstüne basýnca çizmesini engelleme
		
		JButton btn_edit = new JButton("EDIT");
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
		btn_edit.setBounds(199, 217, 111, 38);
		panel.add(btn_edit);
		
		JButton btn_delete = new JButton("DELETE");
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
		btn_delete.setBounds(50, 266, 111, 38);
		panel.add(btn_delete);
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_ýd.setText("");txt_name.setText("");table.clearSelection();
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
		btn_clean.setBounds(199, 266, 111, 38);
		panel.add(btn_clean);
	}
}
