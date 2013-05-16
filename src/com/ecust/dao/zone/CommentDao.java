package com.ecust.dao.zone;

import java.util.List;

import com.ecust.model.temp.CommentTemp;
import com.ecust.model.zone.Comment;

public interface CommentDao extends BaseDao<Comment,Long> {

	/**
	 * 
	 * @param activity_id
	 * @return
	 */
	public List<CommentTemp> getCommentByActivity(long activity_id);
}
