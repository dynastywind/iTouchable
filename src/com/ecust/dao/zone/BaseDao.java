package com.ecust.dao.zone;

import java.io.Serializable;
import java.util.List;

import com.ecust.util.Page;

/**
 * 公共方法
 * @author lbz
 *
 */
public interface BaseDao<T,ID extends Serializable> {
	
	/**
	  * 根据id号加载T实例
	 * @param t 需要加载的T实例的标识属性
	 * @return 指定id对应的T实例
	 */
	T get(ID id);
	
	/**
	  * 持久化指定的T实例
	 * @param t 需要被持久化的T实例
	 */
	void save(T t);
	
	/**
	  * 修改指定的T实例
	 * @param t 需要被修改的T实例
	 */
	void update(T t);
	
	/**
	  * 删除指定的T实例
	 * @param t 需要被删除的T实例
	 */
	void delete(T t);
	
	/**
	  * 根据删除指定的标识符删除T实例
	 * @param id 需要被删除的T实例的学号
	 */
	void delete(ID id);
	
    
    /**
     * 按页查询记录
     * @param hql hql语句
     * @param offset 开始记录
     * @param length 每页记录数量
     * @return
     */
    public List<T> queryForPage(final String hql, Object[] args,final int offset,final int length);
    
    /**
     * HQL查询
     * @param hql
     * @param args
     * @return
     */
    public List<T> getList(String hql,Object[] args); 
    
    /**
     * 多表查询指定字段
     * @param hql
     * @param args
     * @return
     */
    @SuppressWarnings("rawtypes")
	public List getTempList(String hql,Object[] args); 
    
    /**
     * 按页查询记录
     * @param hql 查询语句
     * @param pageSize 每页记录数
     * @param page 当前页码
     * @param pageNum 显示的页码数量(奇数)
     * @return
     */
    public Page listForPage(String hql,Object[] args,int pageSize,int page,int pageNum);
    
    
    /**
     * 简单的构造函数 T(long id)
     * @param className 区分大小写
     * @param id
     * @return
     */
    public T getMiniModelById(String className,ID id);
    
    /**
     * 刷新
     */
    public void flush();
    
    /**
     * 清除
     */
    public void clear();
    
    
    
    /**
	 * 分页，参数为List
	 * @param hql
	 * @param args 命名：args0,args1等
	 * @param pageSize
	 * @param page
	 * @param pageNum
	 * @return
	 */
	public Page listForPageIn(String hql,Object[] args,int pageSize,int page,int pageNum);
    
}