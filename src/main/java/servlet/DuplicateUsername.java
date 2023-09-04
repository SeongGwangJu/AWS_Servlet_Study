package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;

@WebServlet("/auth/signup/duplicate/username")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String[] usernames = { "aaa", "bbb", "ccc" };
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		
		//	ID 중복확인
		for (int i = 0; i < usernames.length; i++) {
			/* 만약 username.equals()를 쓰면, null일 경우 NullPointerException이 발생하므로
			 * Null체크(if (!null)) 를 한번 더 해야한다. 하지만
			 * Objects.equals를 하면 null을 체크할 필요x */
			if(Objects.equals(usernames[i], username)) {
				ResponseUtil.response(response).of(400).body(true);
				return;
			}
		}
		
		ResponseUtil.response(response).of(200).body(false);
	}
	
}
