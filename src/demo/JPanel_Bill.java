package demo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import entities.Bill;
import model.BillModel;
import javax.swing.BoxLayout;

public class JPanel_Bill extends JPanel {
	private JTable tableBill;

	/**
	 * Create the panel.
	 */
	public JPanel_Bill() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton btnListTodayBill = new JButton("List today bill");
		panel.add(btnListTodayBill);
		
		JButton btnBillReturned = new JButton("Bill Returned");
		panel.add(btnBillReturned);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tableBill = new JTable();
		scrollPane.setViewportView(tableBill);
		
		loadData();
	}
	public void loadData() {

		BillModel modelBill = new BillModel();
		fillDatatoTable(modelBill.findAll());
	}
	public void fillDatatoTable(List<Bill> bills) {
		DefaultTableModel table = new DefaultTableModel();
SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
table.addColumn("Bill ID");
table.addColumn("Date");
table.addColumn("Status");
table.addColumn("Payment");
table.addColumn("Total Price");
table.addColumn("Customer ID");
table.addColumn("EMP ID");
		for (Bill bill: bills) {
			table.addRow(new Object[] { 
					bill.getBill_id(), format.format(bill.getDate_print()), bill.getBill_status(), bill.getPayment(), bill.getTotal_price(), bill.getCustomer_id(), bill.getEmp_id()
					});
		}
		tableBill.setModel(table);
	}

}
