package com.ecust.action.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
 
/**
 * 封装Struts2 对 request、response、session的获取
 * @author lbz
 *
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements SessionAware,  
        ServletRequestAware, ServletResponseAware {  
  
	protected boolean PASS = true;//通行权限，访问权限，用于返回拦截越权ajax请求信号
	
    public HttpServletRequest   request;  
    public HttpServletResponse  response;  
    public Map<String,Object>   session;  
  
    public void setSession(Map<String, Object> session) {  
        this.session = session;  
    }  
  
    public void setServletRequest(HttpServletRequest request) {  
        this.request = request;  
    }  
  
    public void setServletResponse(HttpServletResponse response) {  
        this.response = response;  
    }  
    
    public Map<String,Object> getSesison(){
    	return session;
    }

	public boolean isPASS() {
		return PASS;
	}

	public void setPASS(boolean pASS) {
		PASS = pASS;
	}

}
