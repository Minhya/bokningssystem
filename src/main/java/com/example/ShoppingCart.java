package com.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> items = new HashMap<>();
    private double discount = 0.0;

    public void addItem(String productId, String name, double price, int quantity) {
        if(price < 0 || quantity <= 0){
            throw new IllegalArgumentException("Pris och mängd måste vara positiva");
        }
        items.putIfAbsent(productId, new CartItem (name,price,0));
        items.get(productId).increaseQuantity(quantity);



    }
    public double calculateTotal(){
        double total =items.values().stream().mapToDouble(CartItem::totalPrice).sum();
        return total * (1 - discount / 100);
    }
    public void removeItem(String productId){
        if(!items.containsKey(productId)){
            throw new IllegalArgumentException("Produkten finns inte i kundvagnen");
        }
        items.remove(productId);
    }
    public void applyDiscount(double discountPercentage){
        if(discountPercentage < 0 || discountPercentage > 100){
            throw new IllegalArgumentException("Rabatten måste vara mellan 0 och 100%");
        }
        this.discount += discountPercentage;

    }
    public boolean isEmpty(){

        return items.isEmpty();
    }
    public void updateQuantity(String productId, int newQuantity){
        if(!items.containsKey(productId)){
            throw new IllegalArgumentException("Produkten finns inte i kundvagnen");
        }
        if(newQuantity < 0){
            removeItem(productId);
        }else{
            items.get(productId).setQuantity(newQuantity);
        }
    }
}
