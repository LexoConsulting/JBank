package com.jbank.olb.customerProfile.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;

public class Article {
	private Long articleId;
	
	@NotBlank(message = "contents is mandatory")
	@Size(min = 0, max = 20, message = "Invalid size of contents")
	private String contents;
	
	private int likes;
	
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", contents=" + contents + ", likes=" + likes + "]";
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
