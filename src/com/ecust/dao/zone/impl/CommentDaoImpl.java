package com.ecust.dao.zone.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.CommentDao;
import com.ecust.model.temp.CommentTemp;
import com.ecust.model.zone.Comment;

@Scope("prototype")
@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment,Long> implements CommentDao{

	public CommentDaoImpl() {
		super(Comment.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentTemp> getCommentByActivity(long activity_id) {
		String hql = "select new com.ecust.model.temp.CommentTemp(comment.id, comment.comment_text, comment.createdate,comment.user_own.id," +
				" comment.user_own.name, comment.user_own.picture,comment.user_at_id, comment.user_at_name," +
				" comment.activity_own.id) from Comment comment where comment.activity_own.id = ? order by comment.id ASC";
		return getTempList(hql, new Object[]{activity_id});
	}

}
