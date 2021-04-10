package demo;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.BillDetail;
import model.BillModel;
import model.ItemModel;

public class JIFrame_SoldItem extends JInternalFrame {
	private JTable tableSold;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrame_SoldItem frame = new JIFrame_SoldItem();
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
	public JIFrame_SoldItem() {
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableSold = new JTable();
		scrollPane.setViewportView(tableSold);
loadData();
	}
public void loadData() {
	BillModel model = new BillModel();	
		findItemSoldinDAY(model.findItemSoldinDAY());
	}
public void findItemSoldinDAY(List<BillDetail> bills) {
	ItemModel modelItem = new ItemModel();
	DefaultTableModel table = new DefaultTableModel();
	table.addColumn("Item ID");
	table.addColumn("Item Quantity");
	for (BillDetail bill : bills) {
		int id = bill.getBill_id();
		String name = modelItem.itemName(id);
		table.addRow(new Object[] { name.toString(),bill.getItem_quantity() });
	}
	table_1.setModel(table);
}

}
