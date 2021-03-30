package demo;
 
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
 
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
 
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
import entities.Bill;
import model.BillModel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class JPanel_Bill extends JPanel {
	private JTable tableBill;
	private JButton jbuttonDeleteBill;
 
	/**
	 * Create the panel.
	 */
	public JPanel_Bill() {
		setLayout(new BorderLayout(0, 0));
 
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
 
		JButton jbuttonListTodayBill = new JButton("List today bill");
		jbuttonListTodayBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agr0) {
				jbuttonListTodayBill_actionPerformed(agr0);
			}
 
		});
		panel.add(jbuttonListTodayBill);
 
		JButton jbuttonBillReturned = new JButton("Bill Returned");
		panel.add(jbuttonBillReturned);
 
		jbuttonDeleteBill = new JButton("Delete Bill");
		jbuttonDeleteBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agr0) {
				jbuttonDeleteBill_actionPerformed(agr0);
			}
		});
		panel.add(jbuttonDeleteBill);
 
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
 
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
 
		tableBill = new JTable();
		scrollPane.setViewportView(tableBill);
 
		loadData();
	}
	public void jbuttonDeleteBill_actionPerformed(ActionEvent agr0) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				BillModel billModel = new BillModel();
				int selectedRow = tableBill.getSelectedRow();
				int id = Integer.parseInt(tableBill.getValueAt(selectedRow, 0).toString());
				if (billModel.delete(id)) {
					fillDatatoTable(billModel.findAll());
					jbuttonDeleteBill.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed");
		}
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
		for (Bill bill : bills) {
			table.addRow(new Object[] { bill.getBill_id(), format.format(bill.getDate_print()), bill.getBill_status(),
					bill.getPayment(), bill.getTotal_price(), bill.getCustomer_id(), bill.getEmp_id() });
		}
		tableBill.setModel(table);
	}
 
	public void jbuttonListTodayBill_actionPerformed(ActionEvent agr0) {
		DefaultTableModel table = (DefaultTableModel) tableBill.getModel();
		table.setRowCount(0);
		BillModel billModel = new BillModel();
		fillDatatoTable(billModel.searchCURRENTdate());
	}
 
 
 
}