package com.ecust.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class IllegalCharacterFilter extends StrutsPrepareAndExecuteFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String param = "";
		String paramValue = "";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		@SuppressWarnings("rawtypes")
		Enumeration params = request.getParameterNames();
		//循环读取参数
		while(params.hasMoreElements()){
			param = (String) params.nextElement();
			String[] values = request.getParameterValues(param);
			for(int i = 0; i<values.length; i++){
				paramValue = values[i];
				paramValue = encoding(paramValue);
				values[i] = paramValue;
			}
			request.setAttribute(param, values);
		}
		
		super.doFilter(req, res, chain);
	}
	
	public static String encoding(String src){
        if (src==null)
            return "";
        StringBuilder result=new StringBuilder();
        if (src!=null){
            src=src.trim();
            for (int pos=0;pos<src.length();pos++){
                switch(src.charAt(pos)){ case '\"':result.append("&quot;");break;
                    case '<':result.append("&lt;");break;
                    case '>':result.append("&gt;");break;
                    case '\'':result.append("&apos;");break;
                    case '&':result.append("&amp;");break;
                    case '%':result.append("&pc;");break;
                    case '_':result.append("&ul;");break;
                    case '#':result.append("&shap;");break;
                    case '?':result.append("&ques;");break;
                    default:result.append(src.charAt(pos));break;
                }
            }
        }
        return result.toString();
    }


	
}
