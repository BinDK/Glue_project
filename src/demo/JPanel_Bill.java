package demo;
 
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
 
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
import entities.Bill;
import entities.BillDetail;
import model.BillModel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class JPanel_Bill extends JPanel {
	private JTable tableBill;
	private JButton jbuttonDeleteBill;
	private JPanel panel_1;
	private JButton btnReturnThisItem;
 
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
		
		btnReturnThisItem = new JButton("Return this item");
		btnReturnThisItem.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReturnThisItem_actionPerformed(e);
			}
		});
		btnReturnThisItem.setEnabled(false);
		panel.add(btnReturnThisItem);
 
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
 
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
 
		tableBill = new JTable();
		JPopupMenu mn = new JPopupMenu();
		JMenuItem mnDetail = new JMenuItem("Detail of this bill");
		mnDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mnDetail_actionPerformed(e);
			}
		});
		mn.add(mnDetail);
		tableBill.setComponentPopupMenu(mn);
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
 
	public void fillDETAILtoTable(List<BillDetail> bills) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Bill Detail ID");
		table.addColumn("Item ID");
		table.addColumn("Store Price");
		table.addColumn("Item Unit");
		table.addColumn("Item Quantity");
		table.addColumn("Bill Status");
		table.addColumn("Bill ID");
		for (BillDetail bill : bills) {
			table.addRow(new Object[] {
					bill.getBill_detail_id(), bill.getItem_id(), bill.getStore_price(),bill.getItem_unit(), 
					bill.getItem_quantity(), bill.getBill_status(),bill.getBill_id()
					});
		}
		tableBill.setModel(table);
	}
 
	public void mnDetail_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = tableBill.getSelectedRow();
		int bill_id =(int) tableBill.getValueAt(row, 0);
		BillModel billModel = new BillModel();
		fillDETAILtoTable(billModel.findAllBasedMainID(bill_id));
		btnReturnThisItem.setEnabled(true);
	}
	public void btnReturnThisItem_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = tableBill.getSelectedRow();
		int bill = (Integer) tableBill.getValueAt(row, 6);
		BillModel detail = new BillModel();
	if (row >= 0 ) {
		for(int i = row; i >= 0;) {
		int bill_detail_id;int item_id;int item_quantity; int bill_id; String temp1,temp2,temp3,temp4;
		temp1 = String.valueOf(tableBill.getValueAt(i, 0));
		bill_detail_id = Integer.parseInt(temp1);
		temp2 = String.valueOf(tableBill.getValueAt(i, 1));
		item_id = Integer.parseInt(temp2);
		temp3 = String.valueOf(tableBill.getValueAt(i, 4));
		item_quantity = Integer.parseInt(temp3);
		temp4 = String.valueOf(tableBill.getValueAt(i, 6));
		bill_id = Integer.parseInt(temp4);
//			System.out.println(bill_detail_id + " - " +item_id+ " - " + item_quantity+ " - " +bill_id);
		detail.updateReturnedItemQuantity(item_quantity, item_id);
		detail.updateStatusReturnedItem(bill_detail_id);
			JOptionPane.showMessageDialog(null,"Item đã được hoàn và cộng vào kho");
			fillDETAILtoTable(detail.findAllBasedMainID(bill));
			break;
		}
	} else JOptionPane.showMessageDialog(null, "Please select one of those item in the table in order to change it");
	}
	private void clearJPanel() {
		panel_1.removeAll();
		panel_1.revalidate();
		panel_1.repaint();
	}
}