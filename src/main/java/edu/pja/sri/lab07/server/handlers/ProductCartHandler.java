package edu.pja.sri.lab07.server.handlers;

import edu.pja.sri.lab07.OrderItem;
import edu.pja.sri.lab07.ProductCart;
import edu.pja.sri.lab07.db.DatabaseMock;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class ProductCartHandler implements ProductCart.Iface {
    private List<OrderItem> myCart = new ArrayList<>();
    @Override
    public void addItem(OrderItem orderItem) throws TException {
        // TODO add to the database
        myCart.add(orderItem);
        System.out.println(orderItem.quantity + " new item(s) with id: " + orderItem.id + " have been added to the cart.");
    }

    @Override
    public void deleteItem(OrderItem orderItem) throws TException {
        // TODO delete from the database
        System.out.println(orderItem.quantity + " item(s) with id: " + orderItem.id + " have been removed from the cart");
        myCart.remove(orderItem);
    }

    @Override
    public List<OrderItem> showCart() throws TException {
        return this.myCart;
    }

    @Override
    public void placeOrder() throws TException {
        // TODO delete from the databse
        System.out.println("Passing a new order to the storage...");
        if (DatabaseMock.getInstance().placeOrderInStorage(this.myCart)) {
            System.out.println("Your order is done. Clearing the cart...");
            this.myCart.clear();
        } else {
            System.out.println("There was a problem while making an order. Please try again.");
        }

    }
}
