package edu.pja.sri.lab07.client;

import edu.pja.sri.lab07.OrderItem;
import edu.pja.sri.lab07.Product;
import edu.pja.sri.lab07.ProductCart;
import edu.pja.sri.lab07.ProductManager;
import edu.pja.sri.lab07.db.DatabaseMock;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class ProductCartClient {
	private DatabaseMock dbHandler = DatabaseMock.getInstance();
	  public static void main(String [] args) {
			try {
			  TTransport transportManager;
			  TTransport transportCart;

			  transportManager = new TSocket("localhost", 9090);
			  transportManager.open();

			  transportCart = new TSocket("localhost", 9091);
			  transportCart.open();

			  TProtocol protocolManager = new  TBinaryProtocol(transportManager);
			  TProtocol protocolCart = new  TBinaryProtocol(transportCart);

			  ProductManager.Client productManagerclient = new ProductManager.Client(protocolManager);
			  ProductCart.Client cartClient = new ProductCart.Client(protocolCart);

			  perform(cartClient,productManagerclient);

			  transportManager.close();
			  transportCart.close();
			} catch (TException x) {
			  x.printStackTrace();
			}
		  }

		  private static void perform(ProductCart.Client cartClient,ProductManager.Client productManagerClient) throws TException {
	      System.out.println("Welcome in our shop!" + "\n" +
				  "Please, check out our products here: " + productManagerClient.getAllProducts());
	      System.out.println("Each product is available in the following quantity: " + productManagerClient.getAvailableItems().toString() + "\n");

	      OrderItem it1 = new OrderItem(1, 1);
	      cartClient.addItem(it1);

	      OrderItem it2 = new OrderItem(2, 10);
	      cartClient.addItem(it2);

	      OrderItem it3 = new OrderItem(3, 200); // To many products, the order will be refused.
	      cartClient.addItem(it3);

	      OrderItem it5 = new OrderItem(5, 2);
	      cartClient.addItem(it5);
	      cartClient.deleteItem(it5);

	      System.out.println("You have the following products in the cart: " + cartClient.showCart().toString() + "\n");
	      cartClient.placeOrder();
	      System.out.println("First order has been sent. Now you have now the following products in the cart: " + cartClient.showCart().toString());
	      System.out.println("After first order each product is now available in the following quantity: " + productManagerClient.getAvailableItems().toString() + "\n");

	      OrderItem it4 = new OrderItem(3, 20);
	      cartClient.addItem(it4);

	      System.out.println("Here is your second order. You have the following products in the cart: " + cartClient.showCart().toString() + "\n");
	      cartClient.placeOrder();
	      System.out.println("After making a second order you have now the following products in the cart: " + cartClient.showCart().toString());
	      System.out.println("After making the second order each product is now available in the following quantity: " + productManagerClient.getAvailableItems().toString());
		  }
}
