package demo;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import entities.Bill;
import entities.BillDetail;
import entities.Category;
import entities.Customer;
import entities.User;
import entities.iTem;
import model.BillModel;
import model.CustomerModel;
import model.ItemModel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class JPanel_Selling extends JPanel {
	private JTextField customerNameField;
	private JTextField customerPhoneField;
	private JTable tableOrder;
	private JComboBox cbPayment;
	private JButton btnCreateBill;
	private JComboBox cbiTem;
	private JPanel buttonPannel;
	private JButton btnAddcustomer;
	private JPanel panel_2;
	private JButton btnAddOrder;
	private JTextField qtyField;
	private JLabel lblQuantity;
	private JLabel lblLable;
	private JButton btnPrice;
	private JTextField priceField;
	private JComboBox cuscbBox;
	private JLabel empIDText;
	private Map<String, Object> values = new HashMap<String, Object>();
	private JLabel lblEmployeeId;
	private JLabel lblCate;
	private JComboBox cbCate;
	private JLabel lblCurrent;

	/**
	 * Create the panel.
	 */
	public JPanel_Selling() {
		setLayout(new BorderLayout(0, 0));
		
		buttonPannel = new JPanel();
		buttonPannel.setBackground(new Color(139, 69, 19));
		add(buttonPannel, BorderLayout.EAST);
		buttonPannel.setLayout(new BoxLayout(buttonPannel, BoxLayout.Y_AXIS));
		
		JButton btnClearOrder = new JButton("Clear Order");
		btnClearOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClearOrder_actionPerformed(e);
			}
		});
		buttonPannel.add(btnClearOrder);
		
		btnAddcustomer = new JButton("Add Customer");
		btnAddcustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddcustomer_actionPerformed(e);
			}
		});
		buttonPannel.add(btnAddcustomer);
		
		btnPrice = new JButton("Total price");
		btnPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrice_actionPerformed(e);
			}
		});
		buttonPannel.add(btnPrice);
		
		cuscbBox = new JComboBox();
		cuscbBox.setMaximumSize(new Dimension(32767, 20));
		buttonPannel.add(cuscbBox);
		
		btnCreateBill = new JButton("Create bill");
		btnCreateBill.setEnabled(false);
		btnCreateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateBill_actionPerformed(e);
			}
		});
		buttonPannel.add(btnCreateBill);
		
		lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setForeground(Color.WHITE);
		buttonPannel.add(lblEmployeeId);
		
		empIDText = new JLabel("");
		empIDText.setForeground(Color.BLACK);
		buttonPannel.add(empIDText);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(23, 23));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblCustomer = new JLabel("Customer");
		panel_1.add(lblCustomer);
		
		customerNameField = new JTextField();
		panel_1.add(customerNameField);
		customerNameField.setColumns(10);
		
		JLabel lblTheirPhone = new JLabel("Their phone");
		panel_1.add(lblTheirPhone);
		
		customerPhoneField = new JTextField();
		panel_1.add(customerPhoneField);
		customerPhoneField.setColumns(10);
		customerPhoneField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			    	  e.consume();
			      } 
			      else if (customerPhoneField.getText().length() == 10) {
						e.consume();
					}
			}
		});
		
		JLabel lblPayment = new JLabel("Payment");
		panel_1.add(lblPayment);
		String paymentList[] = {"CASH", "VISA", "MASTERCARD"};
		cbPayment = new JComboBox(paymentList);
		panel_1.add(cbPayment);
		
		panel_2 = new JPanel();
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2, BorderLayout.SOUTH);
		
		lblCate = new JLabel("Category");
		panel_2.add(lblCate);
		
		cbCate = new JComboBox();
		cbCate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbCate_actionPerformed(e);
			}
		});
		cbCate.setMaximumSize(new Dimension(10, 3));
		panel_2.add(cbCate);
		
		JLabel lblItem = new JLabel("Item");
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
		
		lblCurrent = new JLabel("Current Qty: ");
		lblCurrent.setForeground(new Color(128, 128, 128));
		panel_2.add(lblCurrent);
		
		lblQuantity = new JLabel("Quantity to buy");
		panel_2.add(lblQuantity);
		
		qtyField = new JTextField();
		qtyField.setText("1");
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
		
		lblLable = new JLabel("Total price");
		panel_2.add(lblLable);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		panel_2.add(priceField);
		priceField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tableOrder = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		tableOrder.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableOrder);
		JPopupMenu popUp = new JPopupMenu();
		JMenuItem deleteRow = new JMenuItem("Delete this item");
		deleteRow.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteRow_actionPerformed(e);		
			}
		});
		popUp.add(deleteRow);
		tableOrder.setComponentPopupMenu(popUp);
	}
public JPanel_Selling(Map<String, Object> values) {
	this();
	this.values = values;
	loadData();
}
	public void loadData() {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Ma SP");
		table.addColumn("Ten San Pham");
		table.addColumn("Gia");
		table.addColumn("Unit");
		table.addColumn("So luong");
		table.addColumn("Tong tien");
		tableOrder.setModel(table);
		
//		ItemModel itemModel = new ItemModel();
//		DefaultComboBoxModel<iTem> iTemcbox = new DefaultComboBoxModel<iTem>();
//		for (iTem item : itemModel.findAll()) {
//			iTemcbox.addElement(item);
//		}
//		cbiTem.setModel(iTemcbox);
//		cbiTem.setRenderer(new RenderiTEMcbox());
//		
		ItemModel itemModel = new ItemModel();
		DefaultComboBoxModel<Category> Catecbox = new DefaultComboBoxModel<Category>();
		for (Category cate : itemModel.findAllCate()) {
			Catecbox.addElement(cate);
		}
		cbCate.setModel(Catecbox);
		cbCate.setRenderer(new RenderCaTecbox());
		// Combobox Customer
		DefaultComboBoxModel<Customer> CustomBox = new DefaultComboBoxModel<Customer>();
		CustomerModel customModel = new CustomerModel();
		for (Customer custom : customModel.findAll()) {
			CustomBox.addElement(custom);
		}
		cuscbBox.setModel(CustomBox);
		cuscbBox.setRenderer(new RenderCustomerBox());
		
		User user = (User) values.get("account");
		empIDText.setText(String.valueOf(user.getId()));		
}
	public class RenderCustomerBox extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Customer custom = (Customer) value;
			value = custom.getCustomer_name();
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
		
	}
	public class RenderiTEMcbox extends DefaultListCellRenderer{
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

	public void cbCate_actionPerformed(ActionEvent e) {
		try {
			ItemModel itemModel = new ItemModel();
			DefaultComboBoxModel iTemcbox = (DefaultComboBoxModel) cbiTem.getModel();
					Category cateID = (Category) cbCate.getSelectedItem();
					cbiTem.removeAllItems();
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
	public void cbiTem_actionPerformed(ActionEvent e) {
		try {
			iTem itemID = (iTem) cbiTem.getSelectedItem();
			ItemModel modelItem = new ItemModel();
			int qtyiTem = modelItem.itemQty(itemID.getItem_id());
			lblCurrent.setText("Currently Qty: " + qtyiTem);
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	public void btnAddOrder_actionPerformed(ActionEvent e) {
		DefaultTableModel table = (DefaultTableModel) tableOrder.getModel();
		iTem itemName = (iTem) cbiTem.getSelectedItem();
		if (qtyField.getText().isEmpty() || cbiTem.getSelectedItem() == null ) {
			JOptionPane.showMessageDialog(null, "Quantity is zero or not choose any item!");
		} 
		else {
			ItemModel itemModel = new ItemModel();
			int idd = itemName.getItem_id();
//			System.out.println(idd);
			String statuss = itemModel.itemStatus(idd);
			int qtyy = itemModel.itemQty(idd);
			 if (qtyy < Integer.parseInt(qtyField.getText())) {
					JOptionPane.showMessageDialog(null, "Not enough items in storage: " + itemModel.itemQty(idd));
				} 
				else if ( statuss.equalsIgnoreCase("Out of Stock") || statuss.equalsIgnoreCase("Unavailable")) {
					JOptionPane.showMessageDialog(null, "Not available, status: " + statuss);
				} 
			 else {
			int quantity = Integer.parseInt(qtyField.getText());
			table.addRow(new Object[] { itemName.getItem_id(), itemName.getItem_name(), itemName.getItem_store_price(),
					itemName.getItem_unit(), quantity,  Math.round((itemName.getItem_store_price() * quantity) * 100.0) / 100.0});
			qtyField.setText("1");
				}
				}
	}
	public void btnClearOrder_actionPerformed(ActionEvent e) {
		DefaultTableModel table = (DefaultTableModel) tableOrder.getModel();
		table.setRowCount(0);
	}
	public void deleteRow_actionPerformed(ActionEvent e) {		
		int[] tableRow = tableOrder.getSelectedRows();
			if (tableRow.length > 0 ) {
				DefaultTableModel table = (DefaultTableModel) tableOrder.getModel();
				for (int i = tableRow.length -1; i >= 0; i--) {
					table.removeRow(tableRow[i]);
				}
			}
	}
	public void btnPrice_actionPerformed(ActionEvent e) {
		int tableRow = tableOrder.getRowCount();
		DefaultTableModel table = (DefaultTableModel) tableOrder.getModel();
		double convert = 0;
		for (int i = tableRow -1 ; i>=0 ;i--) {
			double price = (double) table.getValueAt(i, 5);
			double roundedPrice = Math.round(price * 100.0) / 100.0;
			convert += price;
		}
		priceField.setText(Double.toString(convert));
		btnCreateBill.setEnabled(true);
	}
	public void btnAddcustomer_actionPerformed(ActionEvent e) {
		Customer cus = new Customer();
		ItemModel model = new ItemModel();
String cusName = customerNameField.getText();
cus.setCustomer_name(cusName);
int cusPhone = Integer.parseInt(customerPhoneField.getText().toString());
cus.setCustomer_phone(cusPhone);
	if (model.createCustomer(cus)) {
	JOptionPane.showConfirmDialog(null, "Just Add customer");
	this.removeAll();
	this.revalidate();
	this.repaint();
	}
}

	public void btnCreateBill_actionPerformed(ActionEvent e) {
try {
	Bill bill = new Bill();
	Customer customer = (Customer) cuscbBox.getSelectedItem();
	bill.setDate_print(new Date());
	bill.setBill_status("APPROVED");
	bill.setPayment(String.valueOf(cbPayment.getSelectedItem().toString().trim()));
	bill.setTotal_price(Double.parseDouble(priceField.getText().toString()));
	bill.setCustomer_id(customer.getCustomer_id());
	bill.setEmp_id(Integer.parseInt(empIDText.getText()));
	BillModel dbBill = new BillModel();
	int bill_id = dbBill.createBill(bill);
    SimpleDateFormat time = new SimpleDateFormat("ddMMyyyy-HHmmss");
    String fileName = time.format(new Date());
    String username = System.getProperty("user.name");
    String osname = System.getProperty("os.name");
    String folder = "";
    if (osname.equalsIgnoreCase("linux")) {
		File theDir = new File("/home/" + username +"/Documents/billPrinted");
		if (!theDir.exists())theDir.mkdirs();
		folder = String.valueOf(theDir);
	}
    else if(osname.equalsIgnoreCase("windows 10")) {
    	File theDir = new File("C:/Users/" + username +"/billPrinted");
    	if (!theDir.exists())theDir.mkdirs();            	
    	folder = String.valueOf(theDir);
    }
	FileOutputStream streamTo = new FileOutputStream(folder+"/Bill-"+fileName+".txt", true);
    String save = "						Purchase Bill" + "\n\r";
    save += "ID Bill: " + bill_id + " - Date: " + time.format(new Date()) + "\n\r";
    save += "Payment: " + cbPayment.getSelectedItem().toString().trim() + "Customer: " + customer.getCustomer_id() + "\n\r";
    save += "Employee ID: " + Integer.parseInt(empIDText.getText());

		//Bill detail
	int row = tableOrder.getRowCount();
	BillDetail detail = new BillDetail();
	ItemModel model = new ItemModel();
//	System.out.println(model.itemQty());
	for(int i = row-1; i>=0 ;i--) {
		int id;double price;String unit; int quantity; String temp; String temp1; String temp2;
		temp = String.valueOf(tableOrder.getValueAt(i, 0));
		id = Integer.parseInt(temp);
		temp1 = String.valueOf(tableOrder.getValueAt(i, 2));
		price = Double.parseDouble(temp1.toString());
		unit = String.valueOf(tableOrder.getValueAt(i, 3));
		temp2 = String.valueOf(tableOrder.getValueAt(i, 4));
		quantity = Integer.parseInt(temp2);
	
		detail.setItem_id(id);
		detail.setStore_price(price);
		detail.setItem_unit(unit);
		detail.setItem_quantity(quantity);
			save += "\n\r" + "Item: " + id + " - Quantity: " + quantity + "Price: " + price;
					dbBill.createBillDetail(detail, bill_id);
					dbBill.removeItemQuantity(quantity, id);				
					if (model.itemQty(id) == 0) {
						model.updateSaleStatus(id);
					}
//			System.out.println(id + " - " +price + " - " + unit + " - " +quantity);

	this.removeAll();
	this.revalidate();
	this.repaint();
}
    save += "\n\r" +"Total: " + Double.parseDouble(priceField.getText().toString());

    streamTo.write(save.getBytes());
    streamTo.flush();
    streamTo.close();
	JOptionPane.showConfirmDialog(null, "Payment success");
} catch (Exception e2) {
	// TODO: handle exception
	System.out.println(e2.getMessage());
}
	}
}
