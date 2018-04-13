package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.surbhi.webProject1.app.SendEmail;
import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requestService.FriendService;

/**
 * Servlet implementation class FriendsAction
 */
public class FriendActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utility utility = new Utility();
	String uid;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendActions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendActions friendaction = new FriendActions();


		String path = request.getPathInfo();
		System.out.println("path "+ path);


		if(path==null||path.equals("/")){
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			utility.getHbs(response,"home",hmap);
		}

		else{
			try {
				String ur = path.replace("/", "");
				Method method = FriendActions.class.getDeclaredMethod(ur,HttpServletRequest.class,HttpServletResponse.class);
				

				method.invoke(friendaction,request,response);
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
	}
	public void friends(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");

			FriendService friendservice = new FriendService();
			List map[] = new List[2];
			map = friendservice.showFriends(uid);
			hmap.put("FriendsList",map[0]);
			System.out.println(map[0]);
			System.out.println(map[1]);
			hmap.put("RequestedList",map[1]);
			utility.getHbs(response, "friends", hmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	public void searchfriends(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			String search = request.getParameter("search");
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			System.out.println(search);
			FriendService friendService = new FriendService();
			User searcheduser = friendService.searchFriends(search);
			String status = friendService.getFriendStatus(searcheduser,uid);

			if(searcheduser==null){
				String message= "No such username found!!!";
				hmap.put("message", message);
				System.out.println("Not found");
				hmap.put("userfound",false);
				utility.getHbs(response, "friends", hmap);
			}
			else{

				System.out.println("found... "+searcheduser.getName());

				hmap.put("searchedUser", searcheduser);
				hmap.put("userfound",true);
				if(status!=null){

					//hmap.put("status",status.replaceAll(" ",""));

					if(status.equals("Request pending")){
						friendrequest(request,response);
						hmap.put("requestpending",true);
					}
					else if(status.equals("Friends"))
						hmap.put("Friends",true);
					else 
						hmap.put("Requestsent",true);
				}
				
				}
			utility.getHbs(response, "friends", hmap);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void addfriend(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();			
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			System.out.println("uid...");
			String fid = request.getParameter("fid");
			FriendService friendservice = new FriendService();
			String mailTo = friendservice.addFriend(uid,fid);
			if(mailTo!=null){
			String link= "http://localhost:8080/webProject1/friendrequest";
			SendEmail email = new SendEmail();
			email.send("surbhi.singh.ss05@gmail.com","You have a friend request\nClick on the link below to respond to friend request\n\n"+link);
			
			}
			response.sendRedirect("friends");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void friendrequest(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();	
			List<User> RequestList = new ArrayList<User>();
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			FriendService friendservice = new FriendService();
			RequestList = friendservice.friendRequest(uid);
			hmap.put("RequestList", RequestList);
			utility.getHbs(response, "friendrequest", hmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void friendresponse(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();	
			//List<User> RequestList = new ArrayList<User>();
			hmap = utility.checkSession(request);
			String button = request.getParameter("response");
			String fid = request.getParameter("fid");
			String sendTo = request.getParameter("email");
			uid = (String) hmap.get("uid");
			FriendService friendservice = new FriendService();
			friendservice.friendResponse(uid,fid,button);
			User user = (User) hmap.get("loggedInUser");
			System.out.println("userrrrr..................."+ hmap.get("loggedInUser"));
			SendEmail email = new SendEmail();
			if(button.equals("Accept"))
				email.send("surbhi.singh.ss05@gmail.com","Your friend request is accepted by "+ user.getName());
			else
			email.send("surbhi.singh.ss05@gmail.com","Your friend request is rejected by "+ user.getName());
			response.sendRedirect("friends");

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
