package fr.ydelouis.selfoss.model;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import fr.ydelouis.selfoss.entity.Article;

@EBean
public class ArticleActionHelper {

	@RootContext Context context;
	@OrmLiteDao(helper = DatabaseHelper.class)
	protected ArticleDao articleDao;
	@OrmLiteDao(helper = DatabaseHelper.class)
	protected ArticleSyncActionDao articleSyncActionDao;

	@AfterInject
	protected void init() {
		articleDao.setContext(context);
	}

	public void markRead(Article article) {
		article.setUnread(false);
		articleDao.createOrUpdate(article);
		articleSyncActionDao.markRead(article);
	}

	public void markRead(List<Article> articles) {
		for (Article article : articles) {
			markRead(article);
		}
	}

	public void markUnread(Article article) {
		article.setUnread(true);
		articleDao.createOrUpdate(article);
		articleSyncActionDao.markUnread(article);
	}

	public void markUnread(List<Article> articles) {
		for (Article article : articles) {
			markUnread(article);
		}
	}

	public void markStarred(Article article) {
		article.setStarred(true);
		articleDao.createOrUpdate(article);
		articleSyncActionDao.markStarred(article);
	}

	public void markStarred(List<Article> articles) {
		for (Article article : articles) {
			markStarred(article);
		}
	}

	public void markUnstarred(Article article) {
		article.setStarred(false);
		articleDao.createOrUpdate(article);
		articleSyncActionDao.markUnstarred(article);
	}

	public void markUnstarred(List<Article> articles) {
		for (Article article : articles) {
			markUnstarred(article);
		}
	}

}
