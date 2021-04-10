package demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;

import demo.JPanel_Selling.RenderCaTecbox;
import demo.JPanel_Selling.RenderiTEMcbox;
import entities.Category;
import entities.iTem;
import model.ItemModel;
import model.ConnectDB;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;


import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JPanel_Item_Import extends JPanel{

	private JPanel contentPane;
	private JTextField qtyItemField;
	private JComboBox cbCate;
	private JComboBox cbiTem;
	private JTable tableItem;
	ItemModel modelItem = new ItemModel();
	private Map<String, Object> values = new HashMap<String, Object>();


	/**
	 * Create the panel.
	 */
	public JPanel_Item_Import() {
		setBounds(100, 100, 681, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel lblNewLabel = new JLabel("Item Category");
		panel.add(lblNewLabel);

		cbCate = new JComboBox();
		cbCate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbCate_actionPerformed(e);
			}
		});
		panel.add(cbCate);

		JLabel lblNewLabel_1 = new JLabel("Item Name");
		panel.add(lblNewLabel_1);

		cbiTem = new JComboBox();
		panel.add(cbiTem);

		JLabel lblNewLabel_3 = new JLabel("Item Quantity");
		panel.add(lblNewLabel_3);

		qtyItemField = new JTextField();
		panel.add(qtyItemField);
		qtyItemField.setColumns(10);
		qtyItemField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // ignore event
				}
			}

		});


		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tableItem = new JTable();
		scrollPane.setViewportView(tableItem);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(47, 79, 79));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImport_actionPerformed(e);
			}
		});
		panel_1.add(btnImport);

		JButton btnNewButton = new JButton("Items");
		panel_1.add(btnNewButton);
//		loadData();
	}
	public JPanel_Item_Import(Map<String, Object> values) {
		this();
		this.values = values;
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
		fillDatatoTable(modelItem.findAllLessthan10());
	}
	public void fillDatatoTable(List<iTem> items) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Item ID");
		table.addColumn("Item Name");
		table.addColumn("Store Price");
		table.addColumn("Import Price");
		table.addColumn("Quantity");
		table.addColumn("Unit");
		table.addColumn("Status");
		table.addColumn("Category ID");
		for (iTem item : items) {
			table.addRow(new Object[] {
					item.getItem_id(), item.getItem_name(), item.getItem_store_price(),item.getItem_import_price(), 
					item.getStore_quantity(), item.getItem_unit(), item.getStatus(), item.getCategory_id()
					});
		}
		tableItem.setModel(table);
	}
	public void cbCate_actionPerformed(ActionEvent e) {
		try {
			ItemModel itemModel = new ItemModel();
			DefaultComboBoxModel iTemcbox = (DefaultComboBoxModel) cbiTem.getModel();
			Category cateID = (Category) cbCate.getSelectedItem();
			cbiTem.removeAllItems();
			for (iTem item : itemModel.findiTemOnCateIDqtyOver10(cateID.getId())) {
				iTemcbox.addElement(item);
			}
			cbiTem.setModel(iTemcbox);
			cbiTem.setRenderer(new RenderiTEMcbox());

		} catch (Exception e2) {
			// TODO: handle exception
			System.err.println(e2.getMessage());
		}
	}

	public class RenderiTEMcbox extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
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

	public void btnImport_actionPerformed(ActionEvent e) {
		int qty = Integer.parseInt(qtyItemField.getText());
		
		iTem items = (iTem) cbiTem.getSelectedItem();
		System.out.println(cbiTem.getSelectedItem());
//		String name = (String) cbiTem.getSelectedItem();
		String name = items.getItem_name();
		ItemModel model = new ItemModel();
//		if (qty == 0 || name == "" ) {
//			JOptionPane.showMessageDialog(null, "Quantity must be more 0 and must choose item ");
//		} else {
//		
//		}
//		model.updateStock(qty, name); 
	}
		
}