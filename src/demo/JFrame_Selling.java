package demo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import entities.iTem;
import model.ItemModel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class JFrame_Selling extends JPanel {
	private JTextField customerName;
	private JTextField customerPhone;
	private JTable tableOrder;
	private JComboBox cbPayment;
	private JButton btnCreateBill;
	private JComboBox cbiTem;
	private JDateChooser JdateChooser;
	private JPanel buttonPannel;
	private JButton btnAddcustomer;
	private JPanel panel_2;
	private JButton btnAddOrder;
	private JTextField qtyField;
	private JLabel lblQuantity;

	/**
	 * Create the panel.
	 */
	public JFrame_Selling() {
		setLayout(new BorderLayout(0, 0));
		
		buttonPannel = new JPanel();
		buttonPannel.setBackground(new Color(139, 69, 19));
		add(buttonPannel, BorderLayout.EAST);
		buttonPannel.setLayout(new BoxLayout(buttonPannel, BoxLayout.Y_AXIS));
		
		btnCreateBill = new JButton("Create bill");
		buttonPannel.add(btnCreateBill);
		
		JButton btnClearOrder = new JButton("Clear Order");
		buttonPannel.add(btnClearOrder);
		
		btnAddcustomer = new JButton("Add Customer");
		buttonPannel.add(btnAddcustomer);
		
		JButton btnCreateBill_3 = new JButton("Create bill");
		buttonPannel.add(btnCreateBill_3);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(23, 23));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblCustomer = new JLabel("Customer");
		panel_1.add(lblCustomer);
		
		customerName = new JTextField();
		panel_1.add(customerName);
		customerName.setColumns(10);
		
		JLabel lblTheirPhone = new JLabel("Their phone");
		panel_1.add(lblTheirPhone);
		
		customerPhone = new JTextField();
		panel_1.add(customerPhone);
		customerPhone.setColumns(10);
		
		JLabel lblPayment = new JLabel("Payment");
		panel_1.add(lblPayment);
		String paymentList[] = {"CASH", "VISA", "MASTERCARD"};
		cbPayment = new JComboBox(paymentList);
		panel_1.add(cbPayment);
		
		panel_2 = new JPanel();
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblItem = new JLabel("iTem");
		panel_2.add(lblItem);
		
		cbiTem = new JComboBox();
		cbiTem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbiTem_actionPerformed(e);
			}
		});
		cbiTem.setMaximumSize(new Dimension(10, 3));
		panel_2.add(cbiTem);
		
		btnAddOrder = new JButton("Add order");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddOrder_actionPerformed(e);
			}
		});
		
		lblQuantity = new JLabel("Quantity");
		panel_2.add(lblQuantity);
		
		qtyField = new JTextField();
		qtyField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			         e.consume();  // ignore event
			      }
			}
		
		});
		panel_2.add(qtyField);
		qtyField.setColumns(10);
		panel_2.add(btnAddOrder);
		
		JLabel lblDate = new JLabel("Date");
		panel_2.add(lblDate);
		
		JdateChooser = new JDateChooser();
		panel_2.add(JdateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tableOrder = new JTable();
		scrollPane.setViewportView(tableOrder);
		
		
		loadData();
	}

	public void loadData() {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Ma SP");
		table.addColumn("Ten San Pham");
		table.addColumn("Gia");
		table.addColumn("So luong");
		table.addColumn("Tong tien");
		tableOrder.setModel(table);
		
		ItemModel itemModel = new ItemModel();
		DefaultComboBoxModel<iTem> iTemcbox = new DefaultComboBoxModel<iTem>();
		for (iTem item : itemModel.findAll()) {
			iTemcbox.addElement(item);
		}
		cbiTem.setModel(iTemcbox);
		cbiTem.setRenderer(new RenderiTEMcbox());
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
	

	public void cbiTem_actionPerformed(ActionEvent e) {
//	int id = cbiTem.getSelectedIndex() + 1;
//	ItemModel model = new ItemModel();
//	DefaultTableModel table = new DefaultTableModel();
	
	}
	public void btnAddOrder_actionPerformed(ActionEvent e) {

	DefaultTableModel table = (DefaultTableModel) tableOrder.getModel();
	iTem itemName = (iTem) cbiTem.getSelectedItem();
//	int id = itemName.getItem_id();
//	String name = itemName.getItem_name();
//double storeprice = itemName.getItem_store_price();
if (qtyField.getText().isEmpty()) {
	JOptionPane.showMessageDialog(null, "Quên số lượng");
} else {
	int quantity = Integer.parseInt(qtyField.getText());
	table.addRow(new Object[] {
			itemName.getItem_id(),itemName.getItem_name(),itemName.getItem_store_price(), quantity, itemName.getItem_store_price() * quantity
	});	
}	
}

//	public void filliTemtoTable(){
//		DefaultComboBoxModel<>
//	}

}
