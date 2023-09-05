package filter;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;


//모든 요청이 들어올 때 마다 이 필터를 거친다. 일일히 안해도 된다.
@WebFilter("*")
public class CorsFilter extends HttpFilter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; //다운캐스팅을 해야 setHeader를 쓸 수 있따
		
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*"); //이 서버로 들어와야만 허락
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		httpServletResponse.setHeader("Access-Control-Max-Age", "*");
		
		//서블릿 호출, 이 코드 전과 후로 나뉜다 전처리->후처리->서블릿->응답
		chain.doFilter(request, response);
	}

}
