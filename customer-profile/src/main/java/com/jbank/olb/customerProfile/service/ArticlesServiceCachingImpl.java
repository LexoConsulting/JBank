package com.jbank.olb.customerProfile.service;


import org.springframework.stereotype.Service;

import com.jbank.olb.customerProfile.model.Article;
import com.jbank.olb.customerProfile.repository.ArticalesRepository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

@Service
public class ArticlesServiceCachingImpl implements ArticlesService {
	private static final Logger logger = LoggerFactory.getLogger(ArticlesServiceCachingImpl.class);
	
	@Autowired
	private ArticalesRepository articalesRepo;

	@Autowired
	private CacheManager cacheManager;

	public ArticlesServiceCachingImpl() {
	}

    @Override
    @Cacheable("articles")
    public Optional<Article> getArticle(Long articleId) {
    	if (logger.isDebugEnabled()) logger.debug("get article for " + articleId);
    	
        Article article = this.articalesRepo.get(articleId);
        
        return Optional.ofNullable(article);
    }

    @Override
    @CacheEvict(value="articles", allEntries=true)
    public void removeArticle(Long articleId) {
    	this.articalesRepo.remove(articleId);
    }

    @Override
    @CachePut(value="articles", key="#article.articleId")
    public Article saveArticle(Article article) {
    	return this.articalesRepo.save(article);
    }

    @Override
    @CachePut(value="articles", key="#articleId")
    public Article updateLikes(Long articleId, int likes) {
    	Article article = this.articalesRepo.updateLikes(articleId, likes);
    	
        return article;
    }
}