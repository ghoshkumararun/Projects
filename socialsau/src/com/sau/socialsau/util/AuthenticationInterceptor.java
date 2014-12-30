package com.sau.socialsau.util;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sau.socialsau.dto.User;

public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = -5178797793675597494L;

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        User user = (User) session.get("user");
        if(user == null){
            return Action.LOGIN;
        }else{
            return invocation.invoke();
        }
	}

}
