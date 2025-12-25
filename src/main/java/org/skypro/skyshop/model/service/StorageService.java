package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();
    private final Map<UUID, Product> availableProducts = new HashMap<>();

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

    public Collection<Article> getAllArticles() {
        return Collections.unmodifiableCollection(articles.values());
    }

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(products.values());
    }

    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> allSearchables = new ArrayList<>(products.size() + articles.size());
        allSearchables.addAll(products.values());
        allSearchables.addAll(articles.values());
        return allSearchables;
    }

    public StorageService() {
        initTestData();
    }

    private void initTestData() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID productId3 = UUID.randomUUID();
        UUID productId4 = UUID.randomUUID();
        UUID productId5 = UUID.randomUUID();
        UUID productId6 = UUID.randomUUID();

        UUID articleId1 = UUID.randomUUID();
        UUID articleId2 = UUID.randomUUID();
        UUID articleId3 = UUID.randomUUID();
        UUID articleId4 = UUID.randomUUID();
        UUID articleId5 = UUID.randomUUID();

        products.put(productId1, new Product(productId1, "Молоко", 100));
        products.put(productId2, new Product(productId2, "Хлеб", 30));
        products.put(productId3, new Product(productId3, "Сыр", 200));
        products.put(productId4, new Product(productId4, "Картофель", 100));
        products.put(productId5, new Product(productId5, "Яблоки", 150));
        products.put(productId6, new Product(productId6, "Лимон", 250));

        articles.put(articleId1, new Article(articleId1, "Молоко полезно для здоровья", "Стакан молока в день укрепляет здоровье"));
        articles.put(articleId2, new Article(articleId2, "Сезон яблок", "В этом году огромный урожай яблок"));
        articles.put(articleId3, new Article(articleId3, "Виды сыров", "В нашем магазине огромное количество сыров разных видов"));
        articles.put(articleId4, new Article(articleId4, "Скидки на яблоки", "Скидки по 75% на яблоки любых сортов"));
        articles.put(articleId5, new Article(articleId5, "Весенние молодые яблоки", "Ранний урожай яблок"));
    }
}
