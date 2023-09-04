package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.UserData;
import entity.User;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		InputStream inputstream = request.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
//
//		StringBuilder requestBody = new StringBuilder("");
//
//		while (true) {
//			String data = bufferedReader.readLine();
//			if (data == null) {
//				break;
//			}
//			requestBody.append(data);
//		}
//		Gson gson = new Gson();
		// 괄호 안에 내용이 어떻게 구성되있는지 확인
//		Map<String, String> userMap = gson.fromJson(requestBody.toString(), Map.class);
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());

		System.out.println(userMap);
		List<User> userList = UserData.userList;
		
		User user = User.builder()
				.userId(userList.size() + 1)
				.username((String) userMap.get("username"))
				.password((String) userMap.get("password"))
				.name((String) userMap.get("name"))
				.email((String) userMap.get("email")).build();

		userList.add(user);

//		key를 통해 userMap의 value에 접근
//		System.out.println(userMap.get("key"));
//		System.out.println(userMap.get("username")); ...
		System.out.println(userMap);
		
		ResponseUtil.response(response).of(200).body(true);

	}
}
