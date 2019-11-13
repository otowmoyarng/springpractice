/**
 * 
 */
package jp.co.product.system.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
public class ProductInterceptor implements HandlerInterceptor {

//	private SystemLogger logger = new SystemLogger(ProductInterceptor.class);
	
	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// TODO 自動生成されたメソッド・スタブ
//		logger.info(request.getContextPath());
//		logger.info(request.getRemoteAddr());
		return true;
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自動生成されたメソッド・スタブ
	}
}
