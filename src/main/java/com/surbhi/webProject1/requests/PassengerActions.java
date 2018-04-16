package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surbhi.webProject1.app.Pager;
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
			Map<String, Object> pages = new HashMap<String, Object>();
			final int pagerSize=10;
			String limit=(String)request.getParameter("limit");
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			PassengerTableService pts = new PassengerTableService();
			System.out.println("after function "+uid);
			int pageLimit = Integer.parseInt(limit);
			System.out.println("page limit "+pageLimit);
			String page = (String)request.getParameter("page");
			int currentPage = Integer.parseInt(page);
			
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
				int totalPage = (int) Math.ceil((double)count/pageLimit);
				System.out.println("Total pages are "+totalPage);
				System.out.println(map.get("count"));
				utility.getHbs(response,"passengerTable",hmap);
				Pager pager = new Pager();
				pages = pager.pager(currentPage,totalPage,pageLimit,pagerSize);
				System.out.println(pages);
				System.out.println(pages.get("pager"));
				hmap.put("pager",pages.get("pager"));
				System.out.println("Another pager "+hmap.get("pager"));
				hmap.put("pages",pages);
				System.out.println("Pages "+pages);
				utility.getHbs(response,"pager",hmap);
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
	public void pager(HttpServletRequest request, HttpServletResponse response){
		
		
	}
}
