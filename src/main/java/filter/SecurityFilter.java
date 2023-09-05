package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.SecurityContextHolder;
import utils.ResponseUtil;


@WebFilter({ "/*", "/user/*", "/admin/*" })
public class SecurityFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String rootPath = "/servlet_study_seonggwang";
		String[] antMatchers = {"/auth"}; //인증이 필요없는 path들
		
		String uri = req.getRequestURI();
		
		//인증이 필요 없는 경우
		for(String antMatcher : antMatchers) {
			if(uri.startsWith(rootPath + antMatcher)) {
				chain.doFilter(request, response);
				return;
			}
		}
		String token = req.getHeader("Authorization");
		System.out.println(req.getMethod().equalsIgnoreCase("options"));
		System.out.println(SecurityContextHolder.isAuthenticated(token));
		//요청이 두번가니까 하나는 막으려고
		//둘다 실패했을 경우 + 인증x 401응답. !false + !false => true
		if(!req.getMethod().equalsIgnoreCase("options") && !SecurityContextHolder.isAuthenticated(token)) {
			ResponseUtil.response(resp).of(401).body("인증실패");
			return;
		}
		
		chain.doFilter(request, response);
		System.out.println("req.getRequestURL : " + req.getRequestURL());
	}

}
