// package com.store.models;

// public class OrderItem {
//     private int orderItemId;
//     private int orderId;
//     private int productId;
//     private int quantity;
//     private double price;

//     // Getters and setters
//     public int getOrderItemId() {
//         return orderItemId;
//     }

//     public void setOrderItemId(int orderItemId) {
//         this.orderItemId = orderItemId;
//     }

//     public int getOrderId() {
//         return orderId;
//     }

//     public void setOrderId(int orderId) {
//         this.orderId = orderId;
//     }

//     public int getProductId() {
//         return productId;
//     }

//     public void setProductId(int productId) {
//         this.productId = productId;
//     }

//     public int getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(int quantity) {
//         this.quantity = quantity;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }
// }


package com.store.models;

public class OrderItem {
    private int productId;
    private int quantity;

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
