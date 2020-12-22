package cn.appsys.intercepoter;  
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appsys.pojo.Devuser;
import cn.appsys.pojo.User;
import cn.appsys.tools.Constants;
 
 
/**
 * 拦截器
 * @author 武伟硕wws
 *
 */
public class SysIntercepoter1 extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception { 
		HttpSession session = request.getSession();
		Devuser user = (Devuser)session.getAttribute(Constants.USER_SESSION);//保存在域中
		if(null == user){
			response.sendRedirect(request.getContextPath()+"/401.jsp");//添加到根目录中
			return false;
		}
		return true;
	}

}