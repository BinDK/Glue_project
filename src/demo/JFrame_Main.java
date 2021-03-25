package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JFrame_Main extends JFrame {

	private JPanel contentPane;
	private JPanel JPanelCenter;
	private JButton btnSelling;

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
		
		JMenuItem mntmCheckInventory = new JMenuItem("Check Inventory");
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
		JPanelLEFT.setBackground(new Color(51,9,19));
		
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
		
		JButton btnTeacher = new JButton("Bill");
		btnTeacher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTeacher_actionPerformed(e);
			}
		});
		btnTeacher.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Load.png")));
		btnTeacher.setForeground(Color.WHITE);
		btnTeacher.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnTeacher.setFocusPainted(false);
		btnTeacher.setContentAreaFilled(false);
		btnTeacher.setBorderPainted(false);
		JPanelLEFT.add(btnTeacher);
		
		JButton btnFees = new JButton("Returned Bill");
		btnFees.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFees_actionPerformed(e);
			}
		});
		btnFees.setIcon(new ImageIcon(JFrame_Main.class.getResource("/resources/Modify.png")));
		btnFees.setForeground(Color.WHITE);
		btnFees.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		btnFees.setFocusPainted(false);
		btnFees.setContentAreaFilled(false);
		btnFees.setBorderPainted(false);
		JPanelLEFT.add(btnFees);
		
		JPanel JPanelBase = new JPanel();
		contentPane.add(JPanelBase, BorderLayout.SOUTH);
		JPanelBase.setBackground(new Color(51,9,19));
		JPanelBase.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel(	"My Layout R@");
		JPanelBase.add(lblNewLabel);
		
		JPanelCenter = new JPanel();
		contentPane.add(JPanelCenter, BorderLayout.CENTER);
		JPanelCenter.setLayout(new BoxLayout(JPanelCenter, BoxLayout.X_AXIS));
	}
	public void btnSelling_actionPerformed(ActionEvent e) {
	clearJPanel();
	JFrame_Selling selling = new JFrame_Selling();
	JPanelCenter.add(selling); selling.setVisible(true);
	}

	public void btnTeacher_actionPerformed(ActionEvent e) {
	clearJPanel();
//	JPanelTeacher teacher = new JPanelTeacher();
//	JPanelCenter.add(teacher);teacher.setVisible(true);
	}
	
	public void btnFees_actionPerformed(ActionEvent e) {
		clearJPanel();
//		JPanelFee fee = new JPanelFee();
//		JPanelCenter.add(fee);fee.setVisible(true);
	}
	
	private void clearJPanel() {
		JPanelCenter.removeAll();
		JPanelCenter.revalidate();
		JPanelCenter.repaint();
	}
}
