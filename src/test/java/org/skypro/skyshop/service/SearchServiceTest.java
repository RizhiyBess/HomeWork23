package org.skypro.skyshop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    @DisplayName("Поиск продукта")
        public void Search1  (){
        Collection<Searchable> allSearchables = new ArrayList();
        Product melon = new Product(UUID.randomUUID(),"Дыня", 200);
        Article melon1 = new Article(UUID.randomUUID(), "полезные Дыни", "Дыни полезны для здоровья");
        allSearchables.add(melon);
        allSearchables.add(melon1);

        when(storageService.getAllSearchables()).thenReturn(allSearchables);
        List<SearchResult> results = searchService.search("Дын");
        assertEquals(2,results.size());
    }

    //Пустой тест
    @Test
    @DisplayName("Поиск в пустом магазине")
    public void Search2  (){
        Collection<Searchable> allSearchables = new ArrayList();

        when(storageService.getAllSearchables()).thenReturn(allSearchables);
        List<SearchResult> results = searchService.search("Арбуз");
        assertEquals(0,results.size());
    }

    @Test
    @DisplayName("Поиск не существующего продукта")
    public void Search3  (){
        Collection<Searchable> allSearchables = new ArrayList();
        Product melon = new Product(UUID.randomUUID(),"Дыня", 200);
        Article melon1 = new Article(UUID.randomUUID(), "полезные Дыни", "Дыни полезны для здоровья");
        allSearchables.add(melon);
        allSearchables.add(melon1);

        when(storageService.getAllSearchables()).thenReturn(allSearchables);
        List<SearchResult> results = searchService.search("Арбуз");
        assertEquals(0,results.size());
    }
}