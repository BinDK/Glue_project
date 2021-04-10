package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.User;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JFrame_Main extends JFrame {

	private JPanel contentPane;
	private JPanel JPanelCenter;
	private JButton btnSelling;

	private Map<String, Object> values = new HashMap<String, Object>();
	private JMenuItem mntmLogut;
	private JButton btnInventory;
	private JButton btnAddUser;
	private JButton btAdditemcate;
	private JButton btnInveReStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_Main frame = new JFrame_Main();
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
	public JFrame_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 530);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);

		mntmLogut = new JMenuItem("Logut");
		mntmLogut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmLogut_actionPerformed(e);
			}
			
		});
		mnAction.add(mntmLogut);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel JPanelLEFT = new JPanel();
//		JPanelLEFT.setBackground(new Color(51, 51, 51));
		contentPane.add(JPanelLEFT, BorderLayout.WEST);
		JPanelLEFT.setLayout(new BoxLayout(JPanelLEFT, BoxLayout.Y_AXIS));
		JPanelLEFT.setBackground(new Color(51, 9, 19));

		btnSelling = new JButton("Selling");
		btnSelling.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnSelling.setForeground(Color.WHITE);
		btnSelling.setFocusPainted(false);
		btnSelling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSelling_actionPerformed(e);
			}
		});
		
		JButton btnProfile = new JButton("My Profile");
		btnProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProfile_actionPerformed(e);
			}
		});
		btnProfile.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Profile.png")));
		btnProfile.setForeground(Color.WHITE);
		btnProfile.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnProfile.setFocusPainted(false);
		btnProfile.setContentAreaFilled(false);
		btnProfile.setBorderPainted(false);
		JPanelLEFT.add(btnProfile);
		btnSelling.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelling.setContentAreaFilled(false);
		btnSelling.setBorderPainted(false);
		btnSelling.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Info.png")));
		JPanelLEFT.add(btnSelling);

		JButton btnBIll = new JButton("Bill");
		btnBIll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBIll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBIll_actionPerformed(e);
			}
		});
		btnBIll.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Load.png")));
		btnBIll.setForeground(Color.WHITE);
		btnBIll.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnBIll.setFocusPainted(false);
		btnBIll.setContentAreaFilled(false);
		btnBIll.setBorderPainted(false);
		JPanelLEFT.add(btnBIll);

		btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInventory_actionPerformed(e);
			}
		});
		btnInventory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventory.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Loading.png")));
		btnInventory.setForeground(Color.WHITE);
		btnInventory.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnInventory.setFocusPainted(false);
		btnInventory.setContentAreaFilled(false);
		btnInventory.setBorderPainted(false);
		JPanelLEFT.add(btnInventory);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddUser_actionPerformed(e);
			}
		});
		
		btnInveReStock = new JButton("ReStock");
		btnInveReStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInveReStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInveReStock_actionPerformed(e);
			}
		});
		btnInveReStock.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Save.png")));
		btnInveReStock.setForeground(Color.WHITE);
		btnInveReStock.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnInveReStock.setFocusPainted(false);
		btnInveReStock.setContentAreaFilled(false);
		btnInveReStock.setBorderPainted(false);
		JPanelLEFT.add(btnInveReStock);
		btnAddUser.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Profile.png")));
		btnAddUser.setForeground(Color.WHITE);
		btnAddUser.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnAddUser.setFocusPainted(false);
		btnAddUser.setContentAreaFilled(false);
		btnAddUser.setBorderPainted(false);
		JPanelLEFT.add(btnAddUser);
		
		btAdditemcate = new JButton("Add Item / CateGory");
		btAdditemcate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAdditemcate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAdditemcate_actionPerformed(e);
			}
		});
		btAdditemcate.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Save.png")));
		btAdditemcate.setForeground(Color.WHITE);
		btAdditemcate.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btAdditemcate.setFocusPainted(false);
		btAdditemcate.setContentAreaFilled(false);
		btAdditemcate.setBorderPainted(false);
		JPanelLEFT.add(btAdditemcate);

		JPanel JPanelBase = new JPanel();
		contentPane.add(JPanelBase, BorderLayout.SOUTH);
		JPanelBase.setBackground(new Color(51, 9, 19));
		JPanelBase.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel = new JLabel("My Layout R@");
		JPanelBase.add(lblNewLabel);

		JPanelCenter = new JPanel();
		contentPane.add(JPanelCenter, BorderLayout.CENTER);
		JPanelCenter.setLayout(new BoxLayout(JPanelCenter, BoxLayout.X_AXIS));
	}

	public JFrame_Main(Map<String, Object> values) {
		this();
		this.values = values;
		loadData();
	}

	public void loadData() {
		User user = (User) values.get("account");
		JOptionPane.showConfirmDialog(null, "Yo yo what sup!! " + user.getEmp_name(), "", JOptionPane.OK_OPTION);
		assignMenu(user);
	}

	public void assignMenu(User user) {
		if (user.getEmp_role().equalsIgnoreCase("sale")) {
//			mnWelcome.setVisible(false);
			btAdditemcate.setVisible(false);
			btnAddUser.setVisible(false);
			btnInveReStock.setVisible(false);
		} 
		else if (user.getEmp_role().equalsIgnoreCase("IM")) {
//			mnWelcome.setVisible(false);
			btnSelling.setVisible(false);
			btAdditemcate.setVisible(false);
			btnAddUser.setVisible(false);
			
		}
		else if (user.getEmp_role().equalsIgnoreCase("SM")) {
//			mnWelcome.setVisible(false);
			btnSelling.setVisible(false);
			btAdditemcate.setVisible(false);
			btnAddUser.setVisible(false);
			btnInveReStock.setVisible(false);
		} 
		else if (user.getEmp_role().equalsIgnoreCase("ADMIN")) {
//			mnWelcome.setVisible(false);
			btnSelling.setVisible(false);
			btnInveReStock.setVisible(false);
		} 
	}
	public void btnProfile_actionPerformed(ActionEvent e) {
		clearJPanel();
		User user = (User) values.get("account");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", user);
		JPanel_Profile profile = new JPanel_Profile(values);
		JPanelCenter.add(profile);
		profile.setVisible(true);
		
	}
	public void btnSelling_actionPerformed(ActionEvent e) {
		clearJPanel();
		User user = (User) values.get("account");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", user);
		
		JPanel_Selling selling = new JPanel_Selling(values);
		JPanelCenter.add(selling);
		selling.setVisible(true);
	}

	public void btnBIll_actionPerformed(ActionEvent e) {
		clearJPanel();
		User user = (User) values.get("account");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", user);
		
		JPanel_Bill bill = new JPanel_Bill();
		JPanelCenter.add(bill);
		bill.setVisible(true);
	}

	public void btnInventory_actionPerformed(ActionEvent e) {
	clearJPanel();
	User user = (User) values.get("account");
	Map<String, Object> values = new HashMap<String, Object>();
	values.put("account", user);
	JPanel_Inventory item = new JPanel_Inventory();
	JPanelCenter.add(item); item.setVisible(true);
	}
	public void btnAddUser_actionPerformed(ActionEvent e) {
		clearJPanel();
//		User user = (User) values.get("account");
//		Map<String, Object> values = new HashMap<String, Object>();
//		values.put("account", user);
		JPanel_Admin_User adminU = new JPanel_Admin_User();
		JPanelCenter.add(adminU); adminU.setVisible(true);
	}
	public void btAdditemcate_actionPerformed(ActionEvent e) {
		clearJPanel();
//		User user = (User) values.get("account");
//		Map<String, Object> values = new HashMap<String, Object>();
//		values.put("account", user);		
		JPanel_AddItemCate addItemCate = new JPanel_AddItemCate();
		JPanelCenter.add(addItemCate);addItemCate.setVisible(true);
	}
	public void btnInveReStock_actionPerformed(ActionEvent e) {
		clearJPanel();
		User user = (User) values.get("account");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", user);
		JPanel_Restock restock = new JPanel_Restock(values);
		JPanelCenter.add(restock);restock.setVisible(true);
	}
	public void mntmLogut_actionPerformed(ActionEvent e) {
	JFrame_Login logut = new JFrame_Login();
	this.setVisible(false);logut.setVisible(true);
	}
	private void clearJPanel() {
		JPanelCenter.removeAll();	
		JPanelCenter.revalidate();
		JPanelCenter.repaint();
	}
}
