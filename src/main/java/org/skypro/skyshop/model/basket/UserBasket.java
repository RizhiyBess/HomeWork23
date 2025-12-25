package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {

    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> basketItems) {
        this.items = basketItems;
        this.total = basketItems.stream()
                .mapToDouble(item -> item.getProduct().getPriceProduct() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
