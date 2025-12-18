package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public class Product implements Searchable {

    private String nameProduct;
    protected final UUID id;
    private int price;

    public Product(UUID id, String nameProduct, int price) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        if (price <= 0){
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        if (nameProduct == null || nameProduct.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или null.");
        }
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getPriceProduct() {
        return price;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                '}';
    }

    @JsonIgnore
    public String getSearchTerm() {
        return nameProduct;
    }

    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product that = (Product) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}