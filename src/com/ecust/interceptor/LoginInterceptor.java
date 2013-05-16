package com.ecust.interceptor;

import java.util.Map;

import com.ecust.action.common.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @author lbz
 *
 */
@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		Map<String,Object> session = baseAction.getSesison();
		
		String method = invocation.getInvocationContext().getName();
		
		if(session.get("user")==null && !"*LoginAction".equals(method)){
			baseAction.request.getSession().invalidate();
			return "login";
		}
		return invocation.invoke();
	}


	
}
