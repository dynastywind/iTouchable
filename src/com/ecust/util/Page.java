package com.ecust.util;

import java.util.List;

/**
 * 分页实体模型
 * @author lbz
 *
 */
public class Page {
	
	//某一页的记录列表
	@SuppressWarnings("rawtypes")
	private List list;
	
	//总记录数
	private int allRow;
	//总页数
	private int totalPage;
	//当前页
	private int currentPage;
	//每页记录数
	private int pageSize;
	//页码数
	private int listPage[];
	//本页页码下限
	private int lower;
	//本页页码上限
	private int upper;
	
	//是否为第一页
	@SuppressWarnings("unused")
	private boolean isFirstPage;
	//是否为最后一页
	@SuppressWarnings("unused")
	private boolean isLastPage;
	//是否有前一页
	@SuppressWarnings("unused")
	private boolean hasPrePage;
	//是否有后一页
	@SuppressWarnings("unused")
	private boolean hasNexPage;
	
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int[] getListPage() {
		return listPage;
	}
	public void setListPage(int listPage[]) {
		this.listPage = listPage;
	}
	public int getLower() {
		return lower;
	}
	public void setLower(int lower) {
		this.lower = lower;
	}
	public int getUpper() {
		return upper;
	}
	public void setUpper(int upper) {
		this.upper = upper;
	}
	/**
	 * 初始化页信息
	 */
	public void init(){
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPrePage = hasPrePage();
		this.hasNexPage = hasNexPage();
	}
	
	public boolean isFirstPage(){
		return currentPage == 1;
	}
	
	public boolean isLastPage(){
		return currentPage == totalPage;
	}
	
	public boolean hasPrePage(){
		return currentPage != 1;
	}
	
	public boolean hasNexPage(){
		return currentPage !=totalPage;
	}
	
	/**
	 * 计算总页数
	 * @param pageSize 每页记录数
	 * @param allRow 总记录数
	 * @return int 总页数
	 */
	public static int countTotalPage(final int pageSize,final int allRow){
		int totalPage = allRow % pageSize == 0 ? allRow/pageSize:allRow/pageSize + 1;
		return totalPage;
	}
	
	/**
	 *  计算当前开始的记录
	 * @param pageSize 每页记录数
	 * @param currentPage 当前第几页
	 * @return int 当前页开始的记录号
	 */
	public static int countOffset(final int pageSize,final int currentPage){
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/**
	 * 计算当前页，若为0或者URL中没有"?page="，则用1代替
	 * @param page
	 * @return int
	 */
	public static int countCurrentPage(int page){
		final int currentPage = (page == 0 ? 1:page);
		return currentPage;
	}
	
}
