package utils;

import java.io.IOException;
import java.net.http.HttpClient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	private int statusCode;
	private String body;
	

	public static ResponseUtilBuilder response(HttpServletResponse response) {
		return new ResponseUtilBuilder(response);
	}
	
	public static class ResponseUtilBuilder {
		private HttpServletResponse response;
		
		public ResponseUtilBuilder(HttpServletResponse response) {
			this.response = response;
		}
		
		public ResponseUtilBuilder of(int statusCode) {
			response.setStatus(statusCode);
			return this;
		}
		
		public void body(Object body) throws IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
		}
	}
}
