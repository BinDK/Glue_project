package demo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.BillDetail;
import entities.iTem;
import model.ItemModel;

import javax.swing.JButton;
import java.awt.Color;

public class JPanel_Inventory extends JPanel {
	private JTable tableItem;
	private JButton btnListShortageItem;

	/**
	 * Create the panel.
	 */
	public JPanel_Inventory() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(9, 51, 41));
		add(panel, BorderLayout.EAST);

		btnListShortageItem = new JButton("List Shortage iTem");
		panel.add(btnListShortageItem);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableItem = new JTable();
		scrollPane.setViewportView(tableItem);
		loadData();
	}

	public void loadData() {
		ItemModel itemModel = new ItemModel();
		fillDatatoTable(itemModel.findAll());
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
}
