package com.jbank.olb.customerProfile.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbank.olb.common.exception.ResourceNotFoundException;
import com.jbank.olb.customerProfile.model.Article;
import com.jbank.olb.customerProfile.service.ArticlesService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/article")
@Validated
public class CachingArticlesController {
	private static final Logger logger = LoggerFactory.getLogger(CachingArticlesController.class);

	@Autowired
	private ArticlesService articlesService;
	
	@GetMapping("/{articleId}")
	public Optional<Article> getArticle(@PathVariable("articleId") Long articleId,     HttpServletRequest request) {
		Optional<Article> article = this.articlesService.getArticle(articleId);
		
		if (!article.isPresent()) {
			if(logger.isDebugEnabled()) logger.debug("Article with id: " + articleId + " not found");
			
			throw new ResourceNotFoundException("Article with id " + articleId + " not found");
		}
		
		return article;
	}

	@Operation(summary = "Save new or update existing article")
	@PostMapping
	public Article saveArticle(@Valid @RequestBody Article article) {
		if (logger.isDebugEnabled()) {
			logger.debug("Article saved is: " + article);
		}
		
		return this.articlesService.saveArticle(article);
	}
	
	@PutMapping("/{articleId}")
	public Article updateLikes(@PathVariable("articleId") Long articleId, @Min(0) @RequestParam(value = "likes") int likes) {
		// The service/dao may throw ResourceNotFoundExcepition which is handled by ***ExceptionHandler in api-common
		return this.articlesService.updateLikes(articleId, likes);
	}
	
	@DeleteMapping("/{articleId}")
	public ResponseEntity<String> removeArticle(@PathVariable("articleId") Long articleId) {
		this.articlesService.removeArticle(articleId);
		
		// We can return a string or not return anything, status 200 returned is enough for remove
		return ResponseEntity.ok("Article is removed");
	}
}
