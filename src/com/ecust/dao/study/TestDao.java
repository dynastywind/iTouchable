package com.ecust.dao.study;

import com.ecust.dao.zone.BaseDao;
import com.ecust.model.study.Test;

public interface TestDao extends BaseDao<Test,String>{
	public String getAddress(String id) ;
}
