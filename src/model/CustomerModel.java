package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.iTem;

public class CustomerModel {

	public List<Customer> findAll() {
		List<Customer> customers= new ArrayList<Customer>();
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement("Select * from db_customer");

			ResultSet result = querry.executeQuery();
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			while (result.next()) {
				Customer custom= new Customer();
				custom.setCustomer_id(result.getInt("customer_id"));
				custom.setCustomer_name(result.getString("customer_name"));
				custom.setCustomer_phone(result.getInt("customer_phone"));
				custom.setDescription(result.getString("description"));

				customers.add(custom);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return customers;
	}
}
