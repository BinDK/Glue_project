package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import entities.iTem;

public class ItemModel {

	public List<iTem> findAll() {
		List<iTem> items = new ArrayList<iTem>();
		try {
			PreparedStatement querry = ConnectDB.getConnection().prepareStatement("Select * from db_item");

			ResultSet result = querry.executeQuery();
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			while (result.next()) {
				iTem item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
				items.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return items;
	}
	public iTem findiTemOnID(int id) {
		iTem item = null;
		try {
			PreparedStatement querry = ConnectDB.getConnection()
					.prepareStatement("Select * from db_item where item_id = ?");
			querry.setInt(1, id);
			ResultSet result = querry.executeQuery();
			if (result.next()) {
				item = new iTem();
				item.setItem_id(result.getInt("item_id"));
				item.setItem_name(result.getString("item_name"));
				item.setItem_store_price(result.getDouble("item_store_price"));
				item.setItem_import_price(result.getDouble("item_import_price"));
				item.setStore_quantity(result.getInt("store_quantity"));
				item.setItem_unit(result.getString("item_unit"));
				item.setStatus(result.getString("status"));
				item.setCategory_id(result.getInt("category_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			item = null;
			System.err.println(e.getMessage());
		}
		return item;
	}
	
}
