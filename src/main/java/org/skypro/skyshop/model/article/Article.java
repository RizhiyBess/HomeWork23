package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.UUID;
import java.util.Objects;

public class Article implements Searchable {

    private String titleArticle;
    private String textArticle;
    private final UUID id;

    public Article(UUID id, String titleArticle, String textArticle) {
        this.id = id;
        this.titleArticle = titleArticle;
        this.textArticle = textArticle;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Название статьи - " + titleArticle + "\nТекст статьи - " + textArticle;
    }

    @JsonIgnore
    public String getSearchTerm() {
        return "Название статьи - " + titleArticle + "\nТекст статьи - " + textArticle;
    }

    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}