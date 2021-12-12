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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class delete_member extends JFrame {

	private JPanel contentPane;private int mouseX,mouseY;
private JTextField txt_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delete_member frame = new delete_member();
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
	public delete_member() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 427, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 427, 281);
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
		
	
		
		
		JLabel delete_member = new JLabel("DELETE MEMBER");
		delete_member.setForeground(new Color(255, 255, 255));
		delete_member.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		delete_member.setBackground(Color.WHITE);
		delete_member.setBounds(145, 21, 187, 32);
		panel_1.add(delete_member);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(delete_member.class.getResource("/images/remove-user.png")));
		lblNewLabel.setBounds(10, 0, 70, 65);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Member ID");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(47, 119, 167, 36);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);
		
		
		
		JButton btn_clean = new JButton("CLEAN");
		btn_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 txt_id.setText("");
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
		btn_clean.setBounds(237, 209, 111, 38);
		panel.add(btn_clean);
		
		

		JButton btn_delete = new JButton("DELETE");
		btn_delete.setBounds(53, 209, 111, 38);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=txt_id.getText();
				try {if (txt_id.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill in The Blanks");
				} 
				else if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want To Perform The Deletion?","WARNING",JOptionPane.YES_NO_OPTION)==0 ) {
						conn conn=new conn();
					PreparedStatement posted=conn.connection.prepareStatement("DELETE FROM members WHERE id="+id);
					if (posted.executeUpdate()!=0) {
						JOptionPane.showMessageDialog(null, "The Deletion Was Successful", 
				            "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					} 
					else {
						JOptionPane.showMessageDialog(null, 
								  "Invalid Member ID", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					txt_id.setText("");
						}
				else {//optýon mesaj da no cevabý verýrsede sildirme iþlemi
					txt_id.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}});
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
		btn_delete.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		btn_delete.setBorder(new LineBorder(new Color(51, 153, 204), 1, true));
		btn_delete.setForeground(new Color(0, 51, 204));
		btn_delete.setBackground(Color.WHITE);
		panel.add(btn_delete);
		btn_delete.setFocusPainted(false);
		
		txt_id = new JTextField();
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
		txt_id.setForeground(Color.RED);
		txt_id.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_id.setColumns(10);
		txt_id.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_id.setBounds(224, 123, 124, 29);
		panel.add(txt_id);
		

	}
}
