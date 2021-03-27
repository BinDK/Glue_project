package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Bill;
import entities.BillDetail;

public class BillModel {
	public List<Bill> findAll() {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement("select * from db_bill ");
			ResultSet resultSet = preparedStatement.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setBill_id(resultSet.getInt("bill_id"));
				bill.setDate_print(resultSet.getDate("date_print"));
				bill.setBill_status(resultSet.getString("bill_status"));
				bill.setPayment(resultSet.getString("payment"));
				bill.setTotal_price(resultSet.getDouble("total_price"));
				bill.setCustomer_id(resultSet.getInt("customer_id"));
				bill.setEmp_id(resultSet.getInt("emp_id"));
				bills.add(bill);
			}
		} catch (Exception e) {
			bills = null;
		}
		return bills;
	}

	public boolean createBill(Bill bill, List<BillDetail> detail) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement("START TRANSACTION;"
+ "INSERT INTO `db_bill` (`bill_id`, `date_print`, `bill_status`, `payment`, `total_price`, `customer_id`, `emp_id`) VALUES (NULL, ?, ?, ?, ?, ?, ?); "
+ "INSERT INTO `db_bill_detail` (`bill_detail_id`, `item_id`, `store_price`, `item_unit`, `item_quantity`, `bill_id`) VALUES (NULL, ?, ?, ?, ?, LAST_INSERT_ID());"
+ "COMMIT;");
			querry.setString(1, format.format(new Date()).toString());
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}