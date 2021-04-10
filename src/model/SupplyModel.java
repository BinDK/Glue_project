package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Supplier;

public class SupplyModel {
	public List<Supplier> findAllSupplier() {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement("SELECT * FROM `db_supplier`");

			ResultSet result = querry.executeQuery();

			while (result.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(result.getInt("id"));
				supplier.setSupplier_name(result.getString("supplier_name"));
				suppliers.add(supplier);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return suppliers ;
	}
}
