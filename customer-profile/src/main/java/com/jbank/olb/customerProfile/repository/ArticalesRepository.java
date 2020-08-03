package com.jbank.olb.customerProfile.repository;

import org.springframework.stereotype.Component;

import com.jbank.olb.customerProfile.model.Article;

public interface ArticalesRepository {
	Article save(Article article);
	
	Article get(Long articleId);
	
	void remove(Long articleId);
	
	Article updateLikes(Long articleId, int likes);
}
