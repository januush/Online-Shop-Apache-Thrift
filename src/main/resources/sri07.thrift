namespace java edu.pja.sri.lab07

service ProductCart {
    void addItem(1: OrderItem orderItem),
    void deleteItem(1:OrderItem orderItem),
    list<OrderItem> showCart(),
    void placeOrder()
}

service ProductManager {
   list<Product> getAllProducts(),
   list<OrderItem> getAvailableItems(),
}

struct Product {
    1: i32 id,
    2: string name
}

struct OrderItem {
    1: i32 id,
    2: i32 quantity
}
