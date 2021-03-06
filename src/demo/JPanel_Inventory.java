package demo;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import demo.JPanel_AddItemCate.RenderCaTecbox;
import entities.BillDetail;
import entities.Category;
import entities.User;
import entities.iTem;
import model.ItemModel;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;

public class JPanel_Inventory extends JPanel {
	private JTable tableItem;
	private JComboBox comboBox;
	private Map<String, Object> values = new HashMap<String, Object>();
	private JMenuItem mnDontSell;
	private JPopupMenu mn;
	/**
	 * Create the panel.
	 */
	public JPanel_Inventory() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableItem = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		tableItem.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableItem);
		JPopupMenu mn = new JPopupMenu();
		JMenuItem mnDontSell = new JMenuItem("Not sell this item");
		mnDontSell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mnDontSell_actionPerformed(e);
			}
		});
		mn.add(mnDontSell);
		tableItem.setComponentPopupMenu(mn);
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(new Color(0, 100, 0));
		add(panel_1, BorderLayout.SOUTH);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_actionPerformed(e);
			}
		});
		panel_1.add(comboBox);
//		loadData();
	}
public JPanel_Inventory(Map<String, Object> values) {
	this();
	this.values = values;
	loadData();
}
	public void loadData() {
		ItemModel itemModel = new ItemModel();
		fillDatatoTable(itemModel.findAll());

		DefaultComboBoxModel<Category> Catecbox = new DefaultComboBoxModel<Category>();
		for (Category cate : itemModel.findAllCate()) {
			Catecbox.addElement(cate);
		}
		comboBox.setModel(Catecbox);
		comboBox.setRenderer(new RenderCaTecbox());
		User user = (User) values.get("account");
		assignMenu(user);
	}
	public void assignMenu(User user) {

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
	public void comboBox_actionPerformed(ActionEvent e) {
		ItemModel itemModel = new ItemModel();
		Category itemID = (Category) comboBox.getSelectedItem();
		fillDatatoTable(itemModel.findiTemOnCateID(itemID.getId()));
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
	public void mnDontSell_actionPerformed(ActionEvent e) {
		int row = tableItem.getSelectedRow();
		int bill_id = (int) tableItem.getValueAt(row, 0);
		ItemModel model = new ItemModel();
		System.out.println(bill_id);
		if (model.notSellStatus(bill_id)) {
			JOptionPane.showMessageDialog(null, "Item set to Unavailable ", "",JOptionPane.PLAIN_MESSAGE);
		}
	}

}
