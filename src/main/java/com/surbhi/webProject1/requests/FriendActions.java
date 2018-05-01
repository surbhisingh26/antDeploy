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

import com.surbhi.webProject1.requests.EmailActions;

import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requestService.EmailService;
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
			hmap.putAll(friendservice.showFriends(uid));
			//System.out.println("Friend list  .. "+hmap.get("FriendsList"));
			//System.out.println(hmap.get("LastLoggedInAt"));
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
			
			

			if(searcheduser==null){
				String message= "No such username found!!!";
				hmap.put("message", message);
				System.out.println("Not found");
				hmap.put("userfound",false);
				utility.getHbs(response, "friends", hmap);
			}
			else{
				String status = friendService.getFriendStatus(searcheduser,uid);
				System.out.println("found... "+searcheduser.getName());

				hmap.put("searchedUser", searcheduser);
				hmap.put("userfound",true);
				if(status!=null){

					//hmap.put("status",status.replaceAll(" ",""));

					if(status.equals("Request pending")){
						showfriendrequest(request,response);
						hmap.put("requestpending",true);
					}
					else if(status.contains("accepted"))
						hmap.put("Friends",true);
					else if(status.equalsIgnoreCase("Request Sent"))
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
			System.out.println("uid..."+uid);
			User user =  (User) hmap.get("loggedInUser");
			String username = user.getName();
			//System.out.println("username is .... "+ username);
			String fid = request.getParameter("fid");
			System.out.println("FID......................" + fid);
			FriendService friendservice = new FriendService();
			User friend = friendservice.addFriend(uid,fid);
			System.out.println("............"+friend.getEmail());
			if(friend.getEmail()!=null){
			EmailService emailservice = new EmailService();
			Boolean subscription = emailservice.checkEmailSubscription(friend.getEmail());
			System.out.println("SUBSCRIPTION "+ subscription);
			System.out.println("Subscription for "+friend.getEmail()+"...."+subscription);
			String sender = "singh.surabhi.055@gmail.com";
			String purpose = "friendRequest";
			String subject = "Friend Request";
			String status="Pending...";
			
			
			//Date date = new Date();
			//E reminder = new Reminder(100);
			
			String id = emailservice.email(purpose,subject,friend.getEmail(),username,status);
			System.out.println("Id is add friend........................." + id);
			//EmailService emailserve = new EmailService(50,id,request);
			
			if(subscription==true){
			
			EmailActions email = new EmailActions();
			
			email.send(request,friend.getName(),sender,purpose,subject,id,"EmailTemplate",0,null);
			status="Sent";
			}
			else{
				status="Failed";
			}
			emailservice.updateStatus(id,status);
			}
			
			response.sendRedirect("friends");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void showfriendrequest(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();	
			List<User> RequestList = new ArrayList<User>();
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			FriendService friendservice = new FriendService();
			RequestList = friendservice.showfriendRequest(uid);
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
		//	String sendTo = request.getParameter("email");
			uid = (String) hmap.get("uid");
			User user =  (User) hmap.get("loggedInUser");
			String username = user.getName();
			FriendService friendservice = new FriendService();
			hmap.putAll(friendservice.friendResponse(uid,fid,button));
			//User user = (User) hmap.get("loggedInUser");
			System.out.println("userrrrr..................."+ hmap.get("loggedInUser"));
			EmailActions email = new EmailActions();
			String sender = "singh.surabhi.055@gmail.com";
			String purpose = null;
			String subject = null;
			String status = "Pending...";
			EmailService emailservice = new EmailService();
			Boolean subscription = emailservice.checkEmailSubscription((String)hmap.get("recieveremail"));
			System.out.println("Subscription for "+(String)hmap.get("recieveremail")+"...."+subscription);
			String id = null;
			if(subscription==true){
			if(button.equalsIgnoreCase("accept")){
				
				purpose = "requestAccepted";
				subject = "Request Accepted";
			id =	emailservice.email(purpose,subject,(String)hmap.get("recieveremail"),username,status);
				email.send(request,(String)hmap.get("recievername"),sender,purpose,subject,id,"EmailTemplate",0,null);
			
			
			}
			else{
				purpose = "requestRejected";
				subject = "Request Rejected";
			id =	emailservice.email(purpose,subject,(String)hmap.get("recieveremail"),username,status);
			email.send(request,(String)hmap.get("recievername"),sender,purpose,subject,id,"EmailTemplate",0,null);
			
			
			}
			status = "Sent";
			}
			else
				status = "Failed";
			
			emailservice.updateStatus(id,status);
			response.sendRedirect("showfriendrequest");
			

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
