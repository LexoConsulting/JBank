package com.jbank.olb.customerProfile.service;

import java.util.Optional;

import com.jbank.olb.customerProfile.model.Article;

public interface ArticlesService {
	public Optional<Article> getArticle(Long articleId);

    public void removeArticle(Long articleId);
    
    public Article saveArticle(Article article);
    
    public Article updateLikes(Long articleId, int likes);
}
