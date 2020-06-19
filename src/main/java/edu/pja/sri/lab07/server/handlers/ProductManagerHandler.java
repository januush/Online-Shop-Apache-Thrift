package edu.pja.sri.lab07.server.handlers;

import java.util.ArrayList;
import java.util.List;

import edu.pja.sri.lab07.OrderItem;
import edu.pja.sri.lab07.Product;
import edu.pja.sri.lab07.ProductManager;
import edu.pja.sri.lab07.db.DatabaseMock;
import org.apache.thrift.TException;


public class ProductManagerHandler implements ProductManager.Iface {
	@Override
	public List<Product> getAllProducts() throws TException {
		// Method for getting all the products from database
		System.out.println("Downloading products list from the database...");
		return DatabaseMock.getInstance().getProductsList();
	}

	@Override
	public List<OrderItem> getAvailableItems() throws TException {
		System.out.println("Downloading products on stock from the database...");
		return DatabaseMock.getInstance().getProductsAvailable();
	}

}
