package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Collectors;

@SessionScope
@Service
public class BasketService {

    @Autowired
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

        public void addToCart(UUID id){
            Product product = storageService.getProductById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket(){
        Map<UUID, Integer> productsInBasket = productBasket.getProducts();
        List<BasketItem> basketItems = productsInBasket.entrySet().stream()
                .filter(entry -> storageService.getProductById(entry.getKey()).isPresent())
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).get(), entry.getValue()))
                .collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}