package demo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import demo.JPanel_Selling.RenderCaTecbox;
import entities.Category;
import model.ItemModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanel_AddItemCate extends JPanel {
	private JTable tableItemCate;
	private JTextField textField;
	private JTextField itemNameField;
	private JComboBox cbCateDown;
	private JLabel lblPrice;
	private JComboBox cbCateUp;
	private JComboBox cbSupply;

	/**
	 * Create the panel.
	 */
	public JPanel_AddItemCate() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Category Name");
		panel.add(lblNewLabel);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnAddCategory = new JButton("Add Category");
		panel.add(btnAddCategory);

		cbCateUp = new JComboBox();
		panel.add(cbCateUp);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.SOUTH);

		JLabel lblItemName = new JLabel("iTem name");
		panel_1.add(lblItemName);

		itemNameField = new JTextField();
		panel_1.add(itemNameField);
		itemNameField.setColumns(6);

		JLabel lblToCategory = new JLabel("to Category");
		panel_1.add(lblToCategory);

		cbCateDown = new JComboBox();
		panel_1.add(cbCateDown);

		JLabel lblFrom = new JLabel("From");
		panel_1.add(lblFrom);

		cbSupply = new JComboBox();
		panel_1.add(cbSupply);

		JButton btnAddorder = new JButton("Add Order");
		btnAddorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddorder_actionPerformed(e);
			}
		});
		panel_1.add(btnAddorder);

		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setForeground(Color.GRAY);
		panel_1.add(lblTotalPrice);

		lblPrice = new JLabel("");
		panel_1.add(lblPrice);

		JButton btnCreateImportBill = new JButton("Create Import Bill");
		panel_1.add(btnCreateImportBill);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableItemCate = new JTable();
		scrollPane.setViewportView(tableItemCate);
		loadData();
	}

	public void loadData() {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("iTem Name");
		table.addColumn("Import Price");
		table.addColumn("Store Price");
		table.addColumn("Item quantity");
		table.addColumn("Item Unit");
		table.addColumn("Status");
		table.addColumn("Into Category");
		tableItemCate.setModel(table);
		ItemModel itemModel = new ItemModel();
		DefaultComboBoxModel<Category> Catecbox = new DefaultComboBoxModel<Category>();
		for (Category cate : itemModel.findAllCate()) {
			Catecbox.addElement(cate);
		}
		cbCateDown.setModel(Catecbox);
		cbCateDown.setRenderer(new RenderCaTecbox());
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

	public void btnAddorder_actionPerformed(ActionEvent e) {
		DefaultTableModel table = (DefaultTableModel) tableItemCate.getModel();
		Category cateName = (Category) cbCateDown.getSelectedItem();
		String cateConvert = cateName.getCate_name().toString();
		
		ItemModel modelItem = new ItemModel();
String itemName = itemNameField.getText().trim();
		table.addRow(new Object[] { itemName, 0, 0,
				100, "pcs", "Approved", modelItem.findIDCate(cateConvert)});
//		System.out.println(modelItem.findIDCate(cateConvert));
	}

}
