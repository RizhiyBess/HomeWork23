package org.skypro.skyshop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {

    @InjectMocks
    private StorageService storageService;

    @Test
    @DisplayName("Получение всех продуетов")
    void Test1 (){
        Collection<Product> products = storageService.getAllProducts();
        assertEquals(6, products.size());
    }

    @Test
    @DisplayName("Получение всех статей")
    void Test2 (){
        Collection<Article> articles = storageService.getAllArticles();
        assertEquals(5, articles.size());
    }

    @Test
    @DisplayName("Получение всего")
    void Test3 (){
        Collection<Searchable> searchables = storageService.getAllSearchables();
        assertEquals(11, searchables.size());
    }

    @Test
    @DisplayName("Получение ID")
    void Test4 (){
        List<Product> products = new ArrayList<>(storageService.getAllProducts());
        Product product = products.get(0);
        Product result = storageService.getProductById(product.getId()).orElse(null);
        assertNotNull(result);
        assertEquals(product.getNameProduct(), result.getNameProduct());
    }
}

