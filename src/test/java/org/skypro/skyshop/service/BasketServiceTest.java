package org.skypro.skyshop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    @DisplayName("Добавление несуществующего товара в корзину")
    public void testAddNonExistentProductThrowsException(){
        UUID nonExistingProductId = UUID.randomUUID();
        Throwable exception = assertThrows(
                NoSuchProductException.class,
                () -> basketService.addToCart(nonExistingProductId),
                "Должна была произойти ошибка 'NoSuchProductException'"
        );
        assertEquals("Продукт не найден", exception.getMessage());
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void testAddExistingProductCallsAddProductMethod(){
        UUID existingProductId = UUID.fromString("b8c6d7f1-f3e5-4a39-bae6-c1e6a1614863");
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(new Product(existingProductId, "Молоко", 100)));
        basketService.addToCart(existingProductId);
        verify(productBasket).addProduct(eq(existingProductId));
    }

    @Test
    @DisplayName("Возвращеие пустой корзины")
    public void testGetUserBasketReturnsEmptyWhenProductBasketIsEmpty(){
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getItems().isEmpty());
    }

    @Test
    @DisplayName("возвращает подходящую корзину, если есть товары")
    public void testGetUserBasketReturnsWhenProductBasketIs() {
        UUID existingProductId = UUID.fromString("b8c6d7f1-f3e5-4a39-bae6-c1e6a1614863");
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(new Product(existingProductId, "Молоко", 100)));
        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(existingProductId, 2);
        try (var mockedStatic = mockStatic(ProductBasket.class)) {
            mockedStatic.when(ProductBasket::getProducts).thenReturn(productMap);
            UserBasket userBasket = basketService.getUserBasket();
            Product existingProduct = storageService.getProductById(existingProductId).get();
            List<BasketItem> expectedBasketItems = List.of(new BasketItem(existingProduct, 2));
            assertEquals(expectedBasketItems, userBasket.getItems());
        }
    }
}