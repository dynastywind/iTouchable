package com.ecust.dao.zone.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.ecust.dao.zone.BaseDao;
import com.ecust.util.Page;

/**
 * 
 * @author lbz
 *
 * @param <T>
 * @param <ID>
 */
@Scope("prototype")
@Transactional
public class BaseDaoImpl<T,ID extends Serializable> implements BaseDao<T,ID> {

	protected Class<T> entity;
	
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	
	public BaseDaoImpl(Class<T> entityClass){
		this.entity = entityClass;
	}
	
	public Session getSession(){
		return sf.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(ID id) {
		try{
			return (T)getSession().get(this.entity, id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public void delete(ID id) {
		getSession().delete(this.get(id));
	}

	//按页查询记录
  	@SuppressWarnings({ "unchecked" })
	@Override
  	public List<T> queryForPage(final String hql, Object[] args, final int offset, final int length) {
  		try {
			Query query = getQuery(hql, args);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			List<T> list = query.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
  	}
  	
  	public Page listForPage(String hql,Object[] args,int pageSize,int page,int pageNum){
		int allRow = getQuery(hql, args).list().size();
		int totalPage = Page.countTotalPage(pageSize, allRow);
		final int offset = Page.countOffset(pageSize, page);
		final int length = pageSize;
		final int currentPage = Page.countCurrentPage(page);
		int lower;
		int upper;
		
		List<T> list = queryForPage(hql,args, offset, length);
		
		if(currentPage<pageNum - 1){
			lower=0;
			upper=pageNum+1;
		}else if(currentPage>totalPage-pageNum/2-0.5){
			lower=totalPage-pageNum;
			upper=totalPage+1;
		}else{
			lower=(int) (currentPage-pageNum/2-0.5);
			upper=(int) (currentPage+pageNum/2+1.5);
		}
		
		Page pageBean = new Page();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setLower(lower);
		pageBean.setUpper(upper);
		
		int listPage[] = new int[totalPage];
		pageBean.setListPage(listPage);
		
		return pageBean;
  	}
	
	
    @Override
    public void flush() {
        getSession().flush();
    }

    //清除
    @Override
    public void clear() {
        getSession().clear();
    }
    
    /**
     * 从数据库中获取一个实体类的集合或一列值
     * @param hql hql语句
     * @param args 参数列表
     * @return List
     */
    @SuppressWarnings({"unchecked" })
	public List<T> getList(String hql,Object[] args){
    	try{
    		return getQuery(hql,args).list();
    	}catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * 从数据库中获取一个实体或一个值
     * @param hql hql语句
     * @param args 参数列表
     * @return Object
     */
    @SuppressWarnings("rawtypes")
	public Object getObject(String hql,Object[] args){
    	List list = getList(hql,args);
    	if(list.size()>0){
    		return list.get(0);
    	}
    	return null;
    }
    
    /**
     * 判断数据库是否有该记录
     * @param hql hql语句select count(*) from...
     * @param args 参数列表
     * @return Boolean
     */
    protected Boolean validate(String hql,Object[] args){
    	try{
    		Object value = getObject(hql,args);
    		if(null!=value && Integer.parseInt(value.toString())>0){
    			return true;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }
    
    //获取Query
    protected Query getQuery(String hql,Object[] args){
    	Query query = getSession().createQuery(hql);
    	if(null != args && args.length > 0) {
    		for (int i=0;i<args.length;i++){
    			query.setParameter(i, args[i]);
    		}
    	}
    	return query;
    }

    //简单的构造函数 T(long id)
	@Override
	public T getMiniModelById(String className,ID id) {
		try {
			String hql = "select new " + className + "(t.id) from " + className + " t where t.id=? ";
			return getList(hql, new Object[]{id}).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getTempList(String hql, Object[] args) {
		try{
    		return getQuery(hql,args).list();
    	}catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
	}
	
	/**
	 * 参数List
	 * @param hql
	 * @param args
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	private Query getQueryIn(String hql,Object[] args){
    	Query query = getSession().createQuery(hql);
    	if(null != args && args.length > 0) {
    		for (int i=0;i<args.length;i++){
    			query.setParameterList("args"+i, (Collection) args[i]);
    		}
    	}
    	return query;
    }
    
    /**
     * 参数List
     * @param hql
     * @param args
     * @param offset
     * @param length
     * @return
     */
    private List<T> queryForPageIn(final String hql,Object[] args, final int offset, final int length) {
  		try {
			Query query = getQueryIn(hql,args);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			@SuppressWarnings("unchecked")
			List<T> list = query.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
  	}

	@Override
	public Page listForPageIn(String hql,Object[] args, int pageSize, int page,
			int pageNum) {
		
		int allRow = getQueryIn(hql,args).list().size();
		int totalPage = Page.countTotalPage(pageSize, allRow);
		final int offset = Page.countOffset(pageSize, page);
		final int length = pageSize;
		final int currentPage = Page.countCurrentPage(page);
		int lower;
		int upper;
		
		List<T> list = queryForPageIn(hql,args, offset, length);
		
		if(currentPage<pageNum - 1){
			lower=0;
			upper=pageNum+1;
		}else if(currentPage>totalPage-pageNum/2-0.5){
			lower=totalPage-pageNum;
			upper=totalPage+1;
		}else{
			lower=(int) (currentPage-pageNum/2-0.5);
			upper=(int) (currentPage+pageNum/2+1.5);
		}
		
		Page pageBean = new Page();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setLower(lower);
		pageBean.setUpper(upper);
		
		int listPage[] = new int[totalPage];
		pageBean.setListPage(listPage);
		
		return pageBean;
	}

}

