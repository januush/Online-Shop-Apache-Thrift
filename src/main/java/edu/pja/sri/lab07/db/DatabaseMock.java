package edu.pja.sri.lab07.db;

import edu.pja.sri.lab07.OrderItem;
import edu.pja.sri.lab07.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMock {
    private static DatabaseMock instance;
    private List<Product> productsList;
    private List<OrderItem> productsAvailable;

    private DatabaseMock() {}

    public static synchronized DatabaseMock getInstance() {
        if (instance==null) {
            instance = new DatabaseMock();
        }
        return instance;
    }

    public List<Product> getProductsList() {
        productsList = new ArrayList<>();
        Product product1 = new Product(1,"Milk");
        Product product2 = new Product(2,"Cheese");
        Product product3 = new Product(3, "Bread");
        Product product4 = new Product(4, "Bannana");
        Product product5 = new Product(5, "Apple");
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        return productsList;
    }

    public List<OrderItem> getProductsAvailable() {
        if (productsAvailable == null) {
            productsAvailable = new ArrayList<>();
            OrderItem product1_qty = new OrderItem(1,10);
            OrderItem product2_qty = new OrderItem(2,20);
            OrderItem product3_qty = new OrderItem(3, 30);
            OrderItem product4_qty = new OrderItem(4, 40);
            OrderItem product5_qty = new OrderItem(5, 50);
            productsAvailable.add(product1_qty);
            productsAvailable.add(product2_qty);
            productsAvailable.add(product3_qty);
            productsAvailable.add(product4_qty);
            productsAvailable.add(product5_qty);
        }
        return productsAvailable;
    }

    public boolean placeOrderInStorage(List<OrderItem> itemsToBuy) {
        for (int i=0; i<itemsToBuy.size(); i++) {
           System.out.println("Found the following items in the cart: id(" + itemsToBuy.get(i).id + ")");
           for (int j=0; j<productsAvailable.size(); j++) {
               if (itemsToBuy.get(i).id == productsAvailable.get(j).id && itemsToBuy.get(i).quantity <= productsAvailable.get(j).quantity) {
                   productsAvailable.get(j).setQuantity(productsAvailable.get(j).quantity - itemsToBuy.get(i).quantity);
                   System.out.println("Removing " + itemsToBuy.get(i).quantity + " item (s) from the storage. There are " + productsAvailable.get(j).quantity +
                           " item(s) left on stock.");
               } else if (itemsToBuy.get(i).id == productsAvailable.get(j).id && itemsToBuy.get(i).quantity > productsAvailable.get(j).quantity){
                   System.out.println("We do not offer this product or you have order to many items.");
                   break;
               }
           }
        }
        return true;
    }

}
