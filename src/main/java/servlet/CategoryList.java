package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;

@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String[] categoryArray = {
		"한식",
		"체험관광",
		"카페",
		"자연명소",
		"양식",
		"문화예술"
	};
	
	private class Feed {
		private String feedName;
		private String categoryName;
		
		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedInfo() {
			return "feedName :" + feedName + ", categoryName : " + categoryName + "\n";
		}
	}
	
	private Feed[] feedArray = {
			new Feed("1번피드", "한식"),
			new Feed("2번피드", "체험관광"),
			new Feed("3번피드", "카페"),
			new Feed("4번피드", "자연명소"),
			new Feed("5번피드", "양식"),
			new Feed("6번피드", "한식"),
			new Feed("7번피드", "체험관광"),
			new Feed("8번피드", "카페"),
			new Feed("9번피드", "카페"),
			new Feed("11번피드", "양식"),
			new Feed("12번피드", "한식"),
			new Feed("13번피드", "체험관광"),
			new Feed("14번피드", "카페"),
			new Feed("15번피드", "문화예술"),
			new Feed("16번피드", "양식"),
			new Feed("17번피드", "문화예술"),
			
	};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request.getRequestURI() : " + request.getRequestURI());
		System.out.println("request.getMethod() : " + request.getMethod());
		String categoryName = request.getParameter("categoryName");
		
		if(checkCategory(categoryName)) {
			response.setCharacterEncoding("UTF-8");
			response.setStatus(400);
			response.getWriter().println("해당 카테고리는 존재하지 않습니다");
			return;
		}
		
//		//람다식 안에서 밖에있는 변수의 값을 바꾸는것은 불가능하므로, 전역으로 주소를 공유하는 Atomic을 사용
//		AtomicReference<String> responseData = new AtomicReference<String>("");
//		
//		findFeedByCategoryName(categoryName).forEach(feed -> {
//			//concat : 문자열 합쳐줌
//			responseData.set(responseData.get() + feed.getFeedInfo());
//		});
//		response.getWriter().println("responseData : " + responseData.getPlain());
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		
		//객체 타입으로 보내줌
		response.setContentType("application/json");
		
		//Json형식으로 받아와서 문자열로 변환
		response.getWriter().println(gson.toJson(findFeedByCategoryName(categoryName)).toString());
		//getWriter : PrintWriter임
	}
	
	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return false;
			}
		}
	return true;
	}
	
	private List<Feed> findFeedByCategoryName(String categoryName) {
//		배열에서 피드 찾는법
		List<Feed> feeds = new ArrayList<>();
		
		for(int i = 0; i < feedArray.length; i++) {
			//요청받은 값과 Array의 값이 같으면 feeds라는 Array에 담는다
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
				
			}
		}
		return feeds;
	}
}
