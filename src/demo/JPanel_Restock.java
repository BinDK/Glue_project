package demo;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import entities.Category;
import entities.Import;
import entities.ImportDetail;
import entities.Supplier;
import entities.User;
import entities.iTem;
import model.BillModel;
import model.ItemModel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import javax.swing.BoxLayout;

public class JPanel_Restock extends JPanel {
	private JTable tableItem;
	private JComboBox cbCate;
	private JComboBox cbiTem;
	private JLabel lblQuantity;
	private JButton btnImport;
	ItemModel modelItem = new ItemModel();
	private Map<String, Object> values = new HashMap<String, Object>();
	private JButton btnAddItem;
	private JButton btnTotalPrice;
	private JLabel labelTotal;
	private JLabel lblPrice;

	/**
	 * Create the panel.
	 */
	public JPanel_Restock() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 51));
		add(panel, BorderLayout.EAST);

		btnImport = new JButton("Import");
		btnImport.setEnabled(false);
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImport_actionPerformed(e);
			}
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnTotalPrice = new JButton("Total Price");
		btnTotalPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTotalPrice_actionPerformed(e);
			}
		});
		panel.add(btnTotalPrice);
		panel.add(btnImport);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Item Category");
		panel_1.add(lblNewLabel);

		cbCate = new JComboBox();
		panel_1.add(cbCate);
		cbCate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbCate_actionPerformed(e);
			}
		});
		JLabel lblNewLabel_1 = new JLabel("Item Name");
		panel_1.add(lblNewLabel_1);

		cbiTem = new JComboBox();
		cbiTem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbiTem_actionPerformed(e);
			}
		});
		panel_1.add(cbiTem);

		lblQuantity = new JLabel("Quantity");
		panel_1.add(lblQuantity);

		btnAddItem = new JButton("Add iTem to fill more QTY");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddItem_actionPerformed(e);
			}
		});
		panel_1.add(btnAddItem);
		
		labelTotal = new JLabel("Total Price: ");
		panel_1.add(labelTotal);
		
		lblPrice = new JLabel("");
		panel_1.add(lblPrice);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableItem = new JTable();
		tableItem.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableItem);
		JPopupMenu mn = new JPopupMenu();
		JMenuItem deleteRow = new JMenuItem("Delect Row");
		deleteRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteRow_actionPerformed(e);
			}
		});
		mn.add(deleteRow);
		tableItem.setComponentPopupMenu(mn);

	}

	public JPanel_Restock(Map<String, Object> values) {
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
		DefaultTableModel table = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub

				if (columnIndex == 4) {
					return Integer.class;
				}
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				if (row == row && column == 4) {

					return true;
				}
				return false;
			}

		};
		table.addColumn("Item ID");
		table.addColumn("Item Name");
		table.addColumn("Store Price");
		table.addColumn("Import Price");
		table.addColumn("Quantity");
		table.addColumn("Unit");
		table.addColumn("Status");
		table.addColumn("Category ID");
		for (iTem item : items) {
			table.addRow(new Object[] { item.getItem_id(), item.getItem_name(), item.getItem_store_price(),
					item.getItem_import_price(), item.getStore_quantity(), item.getItem_unit(), item.getStatus(),
					item.getCategory_id() });
		}
		tableItem.setModel(table);
	}

	public void cbCate_actionPerformed(ActionEvent e) {
		try {
			cbiTem.removeAllItems();

			DefaultComboBoxModel iTemcbox = (DefaultComboBoxModel) cbiTem.getModel();
			Category cateID = (Category) cbCate.getSelectedItem();
			for (iTem item : modelItem.findiTemOnCateIDqtyOver10(cateID.getId())) {
				iTemcbox.addElement(item);
			}
			cbiTem.setModel(iTemcbox);
			cbiTem.setRenderer(new RenderiTEMcbox());

		} catch (Exception e2) {
			cbiTem.removeAllItems();
			// TODO: handle exception

		}
	}

	public void cbiTem_actionPerformed(ActionEvent e) {
		try {
			iTem itemID = (iTem) cbiTem.getSelectedItem();
			int qtyiTem = modelItem.itemQty(itemID.getItem_id());
			lblQuantity.setText("Quantity: " + qtyiTem);
		} catch (Exception e2) {
			// TODO: handle exception
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

	public void btnAddItem_actionPerformed(ActionEvent e) {
		iTem itemName = (iTem) cbiTem.getSelectedItem();
		DefaultTableModel table = (DefaultTableModel) tableItem.getModel();
		table.addRow(new Object[] { itemName.getItem_id(), itemName.getItem_name(), itemName.getItem_store_price(),
				itemName.getItem_import_price(), itemName.getStore_quantity(), itemName.getItem_unit(),
				itemName.getStatus(), itemName.getCategory_id() });
	}

	public void deleteRow_actionPerformed(ActionEvent e) {
		int[] tableRow = tableItem.getSelectedRows();
		if (tableRow.length > 0) {
			DefaultTableModel table = (DefaultTableModel) tableItem.getModel();
			for (int i = tableRow.length - 1; i >= 0; i--) {
				table.removeRow(tableRow[i]);
			}
		}
	}
	public void btnTotalPrice_actionPerformed(ActionEvent e) {
		int tableRow = tableItem.getRowCount();
		DefaultTableModel table = (DefaultTableModel) tableItem.getModel();
		double convert = 0;
		
		for (int i = tableRow -1 ; i>=0 ;i--) {
			int id= Integer.parseInt(table.getValueAt(i, 0).toString());
			int qty = Integer.parseInt(table.getValueAt(i, 4).toString());
			double import_price = Double.parseDouble(table.getValueAt(i, 3).toString());
			if (qty == 0 || qty < 0) {
				JOptionPane.showMessageDialog(null, "The item which ID: "+id+",quantity column of that item should not be 0", "",JOptionPane.PLAIN_MESSAGE);
				table.removeRow(i);
			}else {
				double price = (double) qty * import_price ;
				double roundedPrice = Math.round(price * 100.0) / 100.0;
				convert += roundedPrice;
			}
		}
		lblPrice.setText(Double.toString(convert));
		btnImport.setEnabled(true);
	}
	public void btnImport_actionPerformed(ActionEvent e) {
		try {
		User user = (User) values.get("account");
int userID = user.getId();
			Import billImport = new Import();
			billImport.setDate_import(new Date());
			billImport.setBill_satus("RESTOCK");
			billImport.setTotal_price(Double.parseDouble(lblPrice.getText().toString()));
			billImport.setEmp_id(userID);
			billImport.setSupplier_id(7);

			BillModel dbBill = new BillModel();
			int bill_import_id = dbBill.createBillImport(billImport);

			SimpleDateFormat time = new SimpleDateFormat("ddMMyyyy-HHmmss");
			String fileName = time.format(new Date());
			
			String username = System.getProperty("user.name");
            String osname = System.getProperty("os.name");
            String folder = "";
            if (osname.equalsIgnoreCase("linux")) {
    			File theDir = new File("/home/" + username +"/Documents/importBill");
    			if (!theDir.exists())theDir.mkdirs();
    			folder = String.valueOf(theDir);
			}//home/pixie/Documents
            else if(osname.equalsIgnoreCase("windows 10")) {
            	File theDir = new File("C:/Users/" + username +"/importBill");
            	if (!theDir.exists())theDir.mkdirs();            	
            	folder = String.valueOf(theDir);
            }
			FileOutputStream streamTo = new FileOutputStream(folder+"/ReStock-Bill-"+fileName+".txt", true);

    String save = "						Restock Import Bill" + "\n\r";
    save += "ID Bill: " + bill_import_id + " - Date: " + time.format(new Date()) + "\n\r";

    save += "Employee ID: " +  userID;
			save += "\n\r" + "Item Name |" + " | Quantity |" + " | Import Price |" + " | Store Price |";

//					Bill Import detail
			int row = tableItem.getRowCount();
			ImportDetail detailImport = new ImportDetail();

//////	System.out.println(model.itemQty());

			for (int i = row - 1; i >= 0; i--) {
				String itemName;
				double priceImport;
				double priceStore;
				String unit;
				int quantity;
				int category_id;
				int idItem;
				String status;
				String temp, temp1, temp2, temp3, temp4;
				temp = String.valueOf(tableItem.getValueAt(i, 1));
				itemName = temp.trim();
				temp1 = String.valueOf(tableItem.getValueAt(i, 3));
				priceImport = Double.parseDouble(temp1.toString());
				temp2 = String.valueOf(tableItem.getValueAt(i, 2));
				priceStore = Double.parseDouble(temp2.toString());
				temp3 = String.valueOf(tableItem.getValueAt(i, 4));
				quantity = Integer.parseInt(temp3);
				unit = String.valueOf(tableItem.getValueAt(i, 5));
				status = "ReStock";
				temp4 = String.valueOf(tableItem.getValueAt(i, 0));
				idItem = Integer.parseInt(temp4);
				
				detailImport.setItem_name(itemName);
				detailImport.setImport_price(priceImport);
				detailImport.setStore_price(priceStore);
				detailImport.setItem_quantity(quantity);
				detailImport.setItem_unit(unit);
				detailImport.setBill_status(status);
				detailImport.setBill_import_id(bill_import_id);

				save += "\n\r" + itemName + " -  " + quantity;
				dbBill.createBillImportDetail(detailImport, bill_import_id);
				dbBill.reStock(quantity, idItem);
				if (modelItem.itemQty(idItem) > 10) {
					modelItem.updateReStockStatus(idItem);
				}
				
				this.removeAll();
				this.revalidate();
				this.repaint();
			}
		    save += "\n\r" +"Total: " + Double.parseDouble(lblPrice.getText().toString());

		    streamTo.write(save.getBytes());
		    streamTo.flush();
		    streamTo.close();
			JOptionPane.showConfirmDialog(null, "Restock succesfully","",JOptionPane.YES_OPTION);

		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
}
