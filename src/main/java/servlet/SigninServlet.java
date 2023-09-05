package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());
		Map<String, String> responseData = new HashMap<>();
//		Boolean responseData = false;
		
		System.out.println(signinUser);
		for(User user : UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username")) 
				&& Objects.equals(user.getPassword(), signinUser.get("password"))) {
				// 09/05
				String token = UUID.randomUUID().toString();
				SecurityContextHolder.addAuth(new Authentication(user, token));
//				ResponseUtil.response(response).of(200).body(token); //꺼내쓰기 힘듬
				responseData.put("token", token); //key, value
				break;
			}
		}
		System.out.println("In SigninServlet, responsed '200' and " + responseData);
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(responseData));
	}
}
