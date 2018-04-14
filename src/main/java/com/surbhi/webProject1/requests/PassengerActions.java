package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.requestService.BookingService;
import com.surbhi.webProject1.requestService.PassengerTableService;

/**
 * Servlet implementation class PassengerActions
 */
public class PassengerActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utility utility = new Utility();
	String uid;
	String msg;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassengerActions() {
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
		PassengerActions passengeractions = new PassengerActions();


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
				Method method = PassengerActions.class.getDeclaredMethod(ur,HttpServletRequest.class,HttpServletResponse.class);
				//				System.out.println("method is "+method);
				//				System.out.println("method name is "+method.getName());

				method.invoke(passengeractions,request,response);
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
	}
	public void bookpassenger(HttpServletRequest request, HttpServletResponse response){
		try {

			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap=utility.checkSession(request);
			uid = (String) hmap.get("uid");
			if(uid==null){
				msg = "Please login first!!!";
				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);
				utility.getHbs(response,"login",null);
			}
			else{


				String fname = request.getParameter("name");
				int Tick =Integer.parseInt(request.getParameter("tickets"));
				String Email =request.getParameter("email");
				String Date =(String)request.getParameter("date");
				String Time =(String)request.getParameter("time");
				String Place =(String)request.getParameter("place");
				String pid = (String)request.getParameter("pid");
				System.out.println("pid is   "+pid);
				BookingService buy = new BookingService();
				buy.bookPassenger(pid,uid,fname,Tick,Email,Date,Time,Place);
				request.getRequestDispatcher("passengers").forward(request,response);
				//response.sendRedirect("passengers");
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void passengers(HttpServletRequest request, HttpServletResponse response){
		try {

			Map<String, Object> hmap = new HashMap<String, Object>();

			int num=0;
			String page=null;
			int currentPage=0;
			int nextPage=0;
			int prevPage=0;

			String limit=request.getParameter("limit");

			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			PassengerTableService pts = new PassengerTableService();
			System.out.println("after function "+uid);

			/*if(button!=null){

				Cookie[] cookies = request.getCookies();
				if(cookies !=null){
					for(Cookie cookie : cookies){
						System.out.println("cookies "+cookie);

						if(cookie.getName().equals("page")){
							//page = cookie.getValue();


						}}
				}
				Cookie cookie=new Cookie("page","");  
				cookie.setMaxAge(0);  
				response.addCookie(cookie); 

				//page = (String) request.getAttribute("page");
				num = Integer.parseInt(page);
				if(button.equals("prev")){
					if(num>1)
					num -= 1;
				}
				else{

					num += 1;
				}
			}
			else{
				page = (String)request.getParameter("page");
				currentPage = Integer.parseInt(page);
				nextPage = currentPage+1;
				previousPage = currentPage-1;

				System.out.println("Page number is "+num);

			}
			Cookie cookie = new Cookie("page",Integer.toString(num));
			response.addCookie(cookie);
			System.out.println("page is "+num);*/
			int pageLimit = Integer.parseInt(limit);
			page = (String)request.getParameter("page");
			currentPage = Integer.parseInt(page);

			if(uid==null){
				msg = "Please login first!!!";
				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);
				utility.getHbs(response,"login",null);
			}
			else{
				Map<String, Object> map = pts.Passengers(uid,currentPage,pageLimit);	
				//	System.out.println("\n Passenger List is \n" + passengerList);
				hmap.put("passengerList", map.get("passengerList"));
				hmap.put("count", map.get("count"));
				long count = (Long) (map.get("count"));

				if(currentPage>2)					
				prevPage = currentPage-1;
				else
					hmap.put("prev",true);
					
				if(currentPage<Math.ceil(count/pageLimit))
					nextPage = currentPage+1;
				else
					hmap.put("next",true);
				hmap.put("nextPage", nextPage);
				hmap.put("prevPage", prevPage);
				hmap.put("limit",limit);
				
				System.out.println(map.get("count"));
				utility.getHbs(response,"passengerTable",hmap);

			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deletePassengers(HttpServletRequest request, HttpServletResponse response){
		try {

			BookingService bts = new BookingService();
			String pid = request.getParameter("pid");
			bts.deletePassenger(pid);
			request.getRequestDispatcher("passengers").forward(request, response);
			return;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
