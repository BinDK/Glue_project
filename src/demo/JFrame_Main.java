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
	private JMenuItem mntmCheckInventory;

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
		setBounds(100, 100, 847, 454);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);

		mntmCheckInventory = new JMenuItem("Check Inventory");
		mnAction.add(mntmCheckInventory);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
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

		JButton btnInventory = new JButton("Inventory");
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
		JOptionPane.showMessageDialog(null, "Yo yo what sup!! " + user.getEmp_name(), "", JOptionPane.YES_OPTION);
		assignMenu(user);
	}

	public void assignMenu(User user) {
		if (user.getEmp_role().equalsIgnoreCase("sale")) {
//			mnWelcome.setVisible(false);
			mntmCheckInventory.setEnabled(false);
		}
	}

	public void btnSelling_actionPerformed(ActionEvent e) {
		clearJPanel();
		User user = (User) values.get("account");
//	int idUser = user.getId();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", user);
		JPanel_Selling selling = new JPanel_Selling(values);

		JPanelCenter.add(selling);
		selling.setVisible(true);
	}

	public void btnBIll_actionPerformed(ActionEvent e) {
		clearJPanel();
		JPanel_Bill bill = new JPanel_Bill();
		JPanelCenter.add(bill);
		bill.setVisible(true);
	}

	public void btnInventory_actionPerformed(ActionEvent e) {
	clearJPanel();
	JPanel_Inventory item = new JPanel_Inventory();
	JPanelCenter.add(item); item.setVisible(true);
	}

	private void clearJPanel() {
		JPanelCenter.removeAll();
		JPanelCenter.revalidate();
		JPanelCenter.repaint();
	}
}
