package demo;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import demo.JPanel_Selling.RenderCaTecbox;
import entities.Bill;
import entities.BillDetail;
import entities.Category;
import entities.Customer;
import entities.Import;
import entities.ImportDetail;
import entities.Supplier;
import entities.User;
import entities.iTem;
import model.BillModel;
import model.ItemModel;
import model.SupplyModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class JPanel_AddItemCate extends JPanel {
	private JTable tableItemCate;
	private JTextField cateNameField;
	private JTextField itemNameField;
	private JComboBox cbCateDown;
	private JLabel lblPrice;
	private JComboBox cbSupply;
	private JButton btnTotalPrice;
	private JButton btnClearOrder;
	private Map<String, Object> values = new HashMap<String, Object>();
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

		cateNameField = new JTextField();
		panel.add(cateNameField);
		cateNameField.setColumns(10);

		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCategory_actionPerformed(e);
			}
		});
		panel.add(btnAddCategory);

		btnTotalPrice = new JButton("Total Price");
		btnTotalPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTotalPrice_actionPerformed(e);
			}
		});
		panel.add(btnTotalPrice);
		
		btnClearOrder = new JButton("Clear Table");
		btnClearOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClearOrder_actionPerformed(e);
			}
		});
		btnClearOrder.setForeground(new Color(178, 34, 34));
		btnClearOrder.setBackground(new Color(222, 184, 135));
		panel.add(btnClearOrder);

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
		btnCreateImportBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateImportBill_actionPerformed(e);
			}
		});
		panel_1.add(btnCreateImportBill);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableItemCate = new JTable();
		tableItemCate.getTableHeader().setReorderingAllowed(false);
		JPopupMenu mn = new JPopupMenu();
		JMenuItem mnDelete = new JMenuItem("Delete Row");
		mnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnDelete_actionPerformed(e);
			}
		});
		mn.add(mnDelete);
		tableItemCate.setComponentPopupMenu(mn);
		scrollPane.setViewportView(tableItemCate);
	}
public JPanel_AddItemCate(Map<String, Object> values) {
this();
this.values = values;
	loadData();
}
	public void loadData() {
		DefaultTableModel table = new DefaultTableModel() {

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				if(columnIndex == 1 || columnIndex == 2) return Double.class;
				if(columnIndex == 3 || columnIndex == 6) return Integer.class;
				
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				if (row == row && column == 6) {
					return false;
				}
				return super.isCellEditable(row, column);
			}
			
		};
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

		SupplyModel supplyModel = new SupplyModel();
		DefaultComboBoxModel<Supplier> suppliercbox = new DefaultComboBoxModel<Supplier>();
		for (Supplier supplier : supplyModel.findAllSupplier()) {
			suppliercbox.addElement(supplier);
		}
		cbSupply.setModel(suppliercbox);
		cbSupply.setRenderer(new RenderSupplycbox());
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

	public class RenderSupplycbox extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Supplier supply = (Supplier) value;
			value = supply.getSupplier_name();
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		}
	}

	public void btnAddorder_actionPerformed(ActionEvent e) {
		DefaultTableModel table = (DefaultTableModel) tableItemCate.getModel();
		Category cateName = (Category) cbCateDown.getSelectedItem();
		int idCate = cateName.getId();

		ItemModel modelItem = new ItemModel();
		String itemName = itemNameField.getText().trim();
		table.addRow(new Object[] { itemName, 0, 0, 100, "pcs", "APPROVED", idCate });
//		System.out.println(modelItem.findIDCate(cateConvert));
	}

	public void btnTotalPrice_actionPerformed(ActionEvent e) {
try {
	int tableRow = tableItemCate.getRowCount();
	DefaultTableModel table = (DefaultTableModel) tableItemCate.getModel();
	double convert = 0;
	
	for (int i = tableRow - 1; i >= 0; i--) {
		int qty = Integer.parseInt(table.getValueAt(i, 3).toString());
		double import_price = Double.parseDouble(table.getValueAt(i, 1).toString());
		if (import_price < 0) {
			JOptionPane.showMessageDialog(null, "Giá không được âm, sửa lại giá nhập và bấm Total Price lại!");
			break;
		}
		double price = qty * import_price;
		convert += price;
	}

	lblPrice.setText(Double.toString(convert));
} catch (Exception e2) {
	JOptionPane.showMessageDialog(null, "Cột Giá Nhập phải là số!!!","", JOptionPane.OK_OPTION);
}
	}

	public void btnCreateImportBill_actionPerformed(ActionEvent e) {
		try {
			Supplier supplierName = (Supplier) cbSupply.getSelectedItem();
			int convertSupplierID = supplierName.getId();
			Import billImport = new Import();
			billImport.setDate_import(new Date());
			billImport.setBill_satus("APPROVED");
			billImport.setTotal_price(Double.parseDouble(lblPrice.getText().toString()));
			User user = (User) values.get("account");
			int userID = user.getId();
			billImport.setEmp_id(userID);
			billImport.setSupplier_id(convertSupplierID);
			
			BillModel dbBill = new BillModel();
			int bill_import_id = dbBill.createBillImport(billImport);

			SimpleDateFormat time = new SimpleDateFormat("ddMMyyyy-HHmmss");
			String fileName = time.format(new Date());
    FileOutputStream streamTo = new FileOutputStream("src//importedBill//Bill-"+fileName+".txt", true);
    String save = "						Import Bill" + "\n\r";
    save += "ID Bill: " + bill_import_id + " - Date: " + time.format(new Date()) + "\n\r";

    save += "Employee ID: " +  1;//Integer.parseInt(empIDText.getText());
			save += "\n\r" + "Item Name |" + " | Quantity |" + " | Import Price |" + " | Store Price |";

			//		Bill Import detail
			int row = tableItemCate.getRowCount();
			ImportDetail detailImport = new ImportDetail();
			ItemModel modelItem = new ItemModel();
			iTem item = new iTem();
////	System.out.println(model.itemQty());

			for (int i = row - 1; i >= 0; i--) {
				String itemName;
				double priceImport;
				double priceStore;
				String unit;
				int quantity;
				int category_id;
				String status;
				String temp, temp1, temp2, temp3, temp4;
				temp = String.valueOf(tableItemCate.getValueAt(i, 0));
				if (temp.length() > 25) {
					itemName = temp.substring(0, 25);
				} else {
					itemName = temp.trim();
				}

				temp1 = String.valueOf(tableItemCate.getValueAt(i, 1));
				priceImport = Double.parseDouble(temp1.toString());
				temp2 = String.valueOf(tableItemCate.getValueAt(i, 2));
				priceStore = Double.parseDouble(temp2.toString());
				if (Double.isNaN(priceImport) || Double.isNaN(priceStore)) {
					break;
				}
				temp3 = String.valueOf(tableItemCate.getValueAt(i, 3));
				quantity = Integer.parseInt(temp3);
				unit = String.valueOf(tableItemCate.getValueAt(i, 4));
				status = String.valueOf(tableItemCate.getValueAt(i, 5));
				temp4 = String.valueOf(tableItemCate.getValueAt(i, 6));
				category_id = Integer.parseInt(temp4);

				detailImport.setItem_name(itemName);
				detailImport.setImport_price(priceImport);
				detailImport.setStore_price(priceStore);
				detailImport.setItem_quantity(quantity);
				detailImport.setItem_unit(unit);
				detailImport.setBill_status(status);
				detailImport.setBill_import_id(bill_import_id);
				item.setItem_name(itemName);
				item.setItem_import_price(priceImport);
				item.setItem_store_price(priceStore);
				item.setStore_quantity(quantity);
				item.setItem_unit(unit);
				item.setCategory_id(category_id);

				save += "\n\r" + itemName + " -  " + quantity + " - " + priceImport + " - " + priceStore;
					dbBill.createBillImportDetail(detailImport, bill_import_id);
//					dbBill.removeItemQuantity(quantity, id);
					modelItem.insertItem(item);
				
//			System.out.println(itemName+ " - " +priceImport+ " - " + unit + " - " +quantity);

				this.removeAll();
				this.revalidate();
				this.repaint();
			}
		    save += "\n\r" +"Total: " + Double.parseDouble(lblPrice.getText().toString());

		    streamTo.write(save.getBytes());
		    streamTo.flush();
		    streamTo.close();
			JOptionPane.showConfirmDialog(null, "Đã Import","",JOptionPane.YES_OPTION);

		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	public void btnAddCategory_actionPerformed(ActionEvent e) {
	String cateName = cateNameField.getText().toString().trim();
			if (cateName == "") {
				JOptionPane.showMessageDialog(null, "Cần tên danh mục để thêm danh mục!!","",JOptionPane.YES_OPTION);
			} else {
				Category cate = new Category();
				cate.setCate_name(cateName);
				ItemModel model = new ItemModel();
				model.insertCate(cate);
				JOptionPane.showMessageDialog(null, "Đã thêm tên danh mục","",JOptionPane.YES_OPTION);
				this.removeAll();
				this.revalidate();
				this.repaint();
			}
	}
	public void btnClearOrder_actionPerformed(ActionEvent e) {
		DefaultTableModel table = (DefaultTableModel) tableItemCate.getModel();
		int choice = JOptionPane.showConfirmDialog(null, "Xoá bản tất cả sản phẩm nhập về!","",JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) table.setRowCount(0);
	}
	public void mnDelete_actionPerformed(ActionEvent e) {
		int[] tableRow = tableItemCate.getSelectedRows();
		if (tableRow.length > 0 ) {
			DefaultTableModel table = (DefaultTableModel) tableItemCate.getModel();
			for (int i = tableRow.length -1; i >= 0; i--) {
				table.removeRow(tableRow[i]);
			}
		}
	}
}
