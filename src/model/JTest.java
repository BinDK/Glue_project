package model;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import demo.JPanel_Selling.RenderiTEMcbox;
import entities.Category;
import entities.iTem;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class JTest extends JFrame {

	private JPanel contentPane;
	private JComboBox cbCate;
	private JComboBox cbiTem;
	private JLabel lblHello;
	private JButton btnTextLength;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTest frame = new JTest();
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
	public JTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbCate = new JComboBox();
		cbCate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbCate_actionPerformed(e);	
			}
		});
		cbCate.setBounds(53, 42, 92, 35);
		contentPane.add(cbCate);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setToolTipText("popopo");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setBounds(43, 15, 70, 15);
		contentPane.add(lblCategory);
		
		cbiTem = new JComboBox();
		cbiTem.setBounds(221, 42, 110, 35);
		contentPane.add(cbiTem);
		
		lblHello = new JLabel("hello");
		lblHello.setForeground(Color.YELLOW);
		lblHello.setBounds(75, 150, 70, 15);
		contentPane.add(lblHello);
		
		btnTextLength = new JButton("text length");
		btnTextLength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTextLength_actionPerformed(e);
			}
		});
		btnTextLength.setBounds(237, 145, 117, 25);
		contentPane.add(btnTextLength);
		loadData();
	}
	public void loadData() {
		ItemModel itemModel = new ItemModel();
		DefaultComboBoxModel<Category> Catecbox = new DefaultComboBoxModel<Category>();
		for (Category cate : itemModel.findAllCate()) {
			Catecbox.addElement(cate);
		}
		cbCate.setModel(Catecbox);
		cbCate.setRenderer(new RenderCaTecbox());
	}
	public void cbCate_actionPerformed(ActionEvent e) {
		try {
			ItemModel itemModel = new ItemModel();
			DefaultComboBoxModel iTemcbox = (DefaultComboBoxModel) cbiTem.getModel();
					Category cateID = (Category) cbCate.getSelectedItem();

			for (iTem item : itemModel.findiTemOnCateID(cateID.getId())) {	
				
				iTemcbox.addElement(item);
			}
			cbiTem.setModel(iTemcbox);
			cbiTem.setRenderer(new RenderiTEMcbox());
		} catch (Exception e2) {
			// TODO: handle exception
			System.err.println(e2.getMessage());
		}
	}
	public class RenderiTEMcbox extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			// TODO Auto-generated method stub
			iTem item = (iTem) value;
			value = item.getItem_name();
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
		
	}
	public class RenderCaTecbox extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Category cate = (Category) value;
			value = cate.getCate_name();
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}		
	}
	public void btnTextLength_actionPerformed(ActionEvent e) {
		String  a = lblHello.getText();
		String b = a.substring(0,5-1);
		System.out.println(b);
	}
}
