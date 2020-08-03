package com.jbank.olb.customerProfile.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jbank.olb.common.exception.ResourceNotFoundException;
import com.jbank.olb.customerProfile.model.Article;

@Component
public class ArticalesRepositoryDBImpl implements ArticalesRepository {
	private long index = 5;
	
	private Map<Long, Article> articles = new HashMap<Long, Article>();
	
	public ArticalesRepositoryDBImpl() {
		for (int i=1; i<index; i++) {
			Article article = new Article();
			article.setArticleId(Long.valueOf(i));
			article.setContents("Content for " + i);
			article.setLikes(10 + i);
			
			this.articles.put(article.getArticleId(), article);
		}
		
		
	}
	
	@Override
	public Article save(Article article) {
		if (article.getArticleId() == null || article.getArticleId() <= 0) {
			// New item added, create the Id
			article.setArticleId(index++);
		}
		
		this.articles.put(article.getArticleId(), article);
		return article;
	}

	@Override
	public Article get(Long articleId) {
		return this.articles.get(articleId);
	}

	@Override
	public void remove(Long articleId) {
		this.articles.remove(articleId);
	}

	@Override
	public Article updateLikes(Long articleId, int likes) {
		Article article = this.articles.get(articleId);
		
		if (article != null ) {
			article.setLikes(likes);
		} else {
			throw new ResourceNotFoundException("Article with id " + articleId + " not found");
		}
		
		return article;
	}

}
