import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class select_author extends JFrame {

	DefaultTableModel modelim = new DefaultTableModel();
	Object[] column = { "ID", "NAME","SURNAME","ABOUT"};
	Object[] row = new Object[4];
	private JPanel contentPane; private int mouseX,mouseY;
	 private JTable table;
	 private JButton btn_select;
	 public static int index;
	 public static String firstname_author="",lastname_author="",id_author="";
	 private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					select_author frame = new select_author();
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
	/**
	 * Create the frame.
	 */
	public select_author() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 479, 664);
		contentPane.add(panel);
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 479, 65);
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
		lbl_exit.setBounds(446, 0, 23, 65);
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
		
	
		
		
		JLabel select_author = new JLabel("SELECT AUTHOR");
		select_author.setForeground(new Color(255, 255, 255));
		select_author.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		select_author.setBackground(Color.WHITE);
		select_author.setBounds(178, 21, 180, 32);
		panel_1.add(select_author);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(select_author.class.getResource("/images/operations.png")));
		lblNewLabel.setBounds(0, 0, 106, 65);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 172, 455, 430);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 1, true));
		panel.add(scrollPane);
		table = new JTable() {					//isCellEditable ekledim çünkü tablea 2kere týklayýnca üstünde düzenlemeyi engellemek için*******************************
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index=table.getSelectedRow();
			}
		});
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
		
		
		
		btn_select = new JButton("SELECT AUTHOR");
		btn_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectionModel().isSelectionEmpty()) {
					firstname_author="";lastname_author="";
					JOptionPane.showMessageDialog(null, 
							  "Please select author.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					id_author=table.getValueAt(index, 0).toString();
					firstname_author=table.getValueAt(index, 1).toString();
					lastname_author=table.getValueAt(index, 2).toString();
				 JOptionPane.showMessageDialog(null,"Successfully selected.");
					dispose();
					
					
				}
				
			}
		});
		btn_select.setBounds(111, 613, 244, 40);
		btn_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_select.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_select.setBackground(Color.white);
			}
		});
		btn_select.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_select.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_select.setForeground(new Color(0, 51, 204));
		btn_select.setBackground(Color.WHITE);
		panel.add(btn_select);
		btn_select.setFocusPainted(false);
		
		JLabel lblNewLabel_1_1 = new JLabel("Value to Search");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 76, 167, 36);
		panel.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setBounds(166, 80, 258, 29);
		panel.add(textField);
		
		JButton btn_search = new JButton("SEARCH");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String value,sql_query;
				value=textField.getText().toString();
				sql_query="SELECT * FROM authors WHERE firstname LIKE '%"+value+"%' OR lastname LIKE '%"+value+"%'";
					try {
						if (textField.getText().equals("")) { 
							JOptionPane.showMessageDialog(null,"Fill in The Blanks");
							modelim.setRowCount(0); fill_table();
							}
						else {
						modelim.setRowCount(0);	
						conn conn=new conn();
						ResultSet myRs;
						Statement myStat;
						myStat = conn.connection.createStatement();
						myRs = myStat.executeQuery(sql_query);	
						while (myRs.next()) {
							row[0] = myRs.getString("author_id");
							row[1] = myRs.getString("firstname");
							row[2] = myRs.getString("lastname");
							row[3] = myRs.getString("about");
							modelim.addRow(row);
						}
					}
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,"Error!");
					}
						
						table.setModel(modelim);
				}
		});
		btn_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_search.setBackground(new Color(125,203,225,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_search.setBackground(Color.white);
			}
		});
		btn_search.setForeground(new Color(0, 51, 204));
		btn_search.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_search.setFocusPainted(false);
		btn_search.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_search.setBackground(Color.WHITE);
		btn_search.setBounds(78, 123, 144, 38);
		panel.add(btn_search);

		
		JButton btn_refresh = new JButton("REFRESH");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelim.setRowCount(0); fill_table();textField.setText("");
			}
		});
		btn_refresh.setForeground(new Color(0, 51, 204));
		btn_refresh.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_refresh.setFocusPainted(false);
		btn_refresh.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_refresh.setBackground(Color.WHITE);
		btn_refresh.setBounds(278, 123, 144, 38);
		panel.add(btn_refresh);
		
		
		fill_table();
		
	}
}
