package com.surbhi.webProject1.requestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.highchart.Chart;
import com.surbhi.webProject1.highchart.Column;
import com.surbhi.webProject1.highchart.Data;
import com.surbhi.webProject1.highchart.DataLabels;
import com.surbhi.webProject1.highchart.HighCharts;
import com.surbhi.webProject1.highchart.Pie;
import com.surbhi.webProject1.highchart.PlotOptions;
import com.surbhi.webProject1.highchart.Series;

import com.surbhi.webProject1.highchart.Title;
import com.surbhi.webProject1.highchart.ToolTip;
import com.surbhi.webProject1.highchart.XAxis;
import com.surbhi.webProject1.highchart.YAxis;
import com.surbhi.webProject1.model.Friend;
import com.surbhi.webProject1.model.Invite;
import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requests.DBConnection;

public class UserService  {

	DBConnection db1 = new DBConnection();
	public boolean registerUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob,String bgcolor,String imagepath,String email,String reference,String referenceId){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User registration = new User();
		Date date = null;
		try {	
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------- Creating Collection ------------
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		query.put("email", email);
		DBCursor<User> cursor = coll.find(query);

		if (cursor.hasNext()) {
			return false;
		}
		registration.setuType("User");
		registration.setName(fname,lname);
		registration.setUsername(uname);
		registration.setEmail(email);
		registration.setGender(gender);
		registration.setCountry(country);
		registration.setCity(city);
		registration.setMobile(mobile);
		registration.setPassword(password);
		registration.setDob(date);
		registration.setBgcolor(bgcolor);
		registration.setLastLoggedInAt(null);
		registration.setImagepath(imagepath);
		registration.setLoggedIn(false);
		registration.setReference(reference);
		registration.setReferenceId(referenceId);
		registration.setPoints(50);

		org.mongojack.WriteResult<User, String> reg = coll.insert(registration);
		registration = reg.getSavedObject();
		DBCollection collection = mongo.getCollection("invitation");
		JacksonDBCollection<Invite, String> coll1 = JacksonDBCollection.wrap(collection,Invite.class, String.class);

		NotificationService notificationservice = new NotificationService();
		String link = "points";
		Date Ndate = new Date();
		BasicDBObject query1 = new BasicDBObject();
		query1.put("recieverEmail", email);
		DBCursor<Invite> cursor1 = coll1.find(query1);

		while(cursor1.hasNext()){
			Invite invite = cursor1.next();
			String userId = invite.getSenderId();

			User user = coll.findOneById(userId);
			EmailService emailservice = new EmailService();
			String mailStatus = emailservice.checkStatus(email, user.getUsername(),"inviteToJoin");
			if(mailStatus.equalsIgnoreCase("Sent")){
				user.setPoints(user.getPoints()+50);
				String userEmail = user.getEmail();
				coll.updateById(userId, user);
				String username = user.getName();
				BasicDBObject query2 = new BasicDBObject();

				query2.put("recieverEmail", userEmail);
				DBCursor<Invite> cursor2 = coll1.find(query2);
				while(cursor2.hasNext()){
					Invite invite1 = cursor2.next();
					String secondaryUserId = invite1.getSenderId();
					User user1 = coll.findOneById(secondaryUserId);
					user1.setPoints(user1.getPoints()+10);
					coll.updateById(secondaryUserId, user1);
					coll1.remove(query2);
					notificationservice.send(secondaryUserId,"Congratulation!!! You have earned 10 points reward on joining of "+registration.getName()+" invited by your friend "+username,link,Ndate);
				}

				System.out.println("REGISTRATION ID....................."+ registration.getId());

				notificationservice.send(userId,"Congratulation!!! You have earned 50 points reward on joining of "+registration.getName(),link,Ndate);
			}
		}

		notificationservice.send(registration.getId(),"Welcome "+fname+" "+lname+"\n Congratulation!!! You have been rewarded by 50 points in your account",link,Ndate);



		return true;

	}
	public void updateUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob,String email){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User registration = new User();
		Date date = null;
		try {	
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<User> cursor = coll.find(query);
		if (cursor.hasNext()) {

			registration.setuType("User");
			registration.setName(fname,lname);
			registration.setUsername(uname);
			registration.setEmail(email);
			registration.setGender(gender);
			registration.setCountry(country);
			registration.setCity(city);
			registration.setMobile(mobile);
			registration.setPassword(password);
			registration.setDob(date);

		}
		coll.updateById(registration.getUsername(), registration);
	}
	public User updateColor(String bgcolor,String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> collection = JacksonDBCollection.wrap(collec,User.class, String.class);

		System.out.println("color "+uid);
		User user = collection.findOneById(uid);
		user.setBgcolor(bgcolor);
		collection.updateById(uid, user);
		return user;
	}


	public User updatePic(String path,String uid){
		try{
			System.out.println("pic "+uid);
			DB mongo;
			mongo=db1.getDB();

			DBCollection collec = mongo.getCollection("registration");
			JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
			System.out.println("uid is ..."+uid);

			User user = coll.findOneById(uid);

			System.out.println("uid is ..."+uid);
			user.setImagepath(path);
			System.out.println("path is ..."+user.getImagepath());

			coll.updateById(uid, user);

			return user;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;


	}
	public User findOneById(String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User user = coll.findOneById(uid);
		return user;

	}
	public Boolean login(String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User user = coll.findOneById(uid);
		System.out.println(uid);
		System.out.println(user.getName());
		user.setLoggedIn(true);
		coll.updateById(uid, user);
		return user.getLoggedIn();

	}
	public void logout(String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		Date date = new Date();		

		//System.out.println("Date is "+now);
		User user = coll.findOneById(uid);
		user.setLoggedIn(false);
		user.setLastLoggedInAt(date);
		coll.updateById(uid, user);
	}

	public String invite(String senderId,String recieverEmail){

		DB mongo;
		mongo=db1.getDB();

		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
		User user = coll1.findOneById(senderId);
		BasicDBObject query = new BasicDBObject();
		query.put("email", recieverEmail);
		DBCursor<User> cursor = coll1.find(query);
		if(cursor.hasNext())
			return null;


		DBCollection collec = mongo.getCollection("invitation");
		JacksonDBCollection<Invite, String> coll = JacksonDBCollection.wrap(collec,Invite.class, String.class);
		query.put("senderId", senderId);
		DBCursor<Invite> cursor1 = coll.find(query);
		if(cursor1.hasNext()){
			return "Already invited";
		}
		Invite invite = new Invite();
		invite.setSenderId(senderId);
		invite.setRecieverEmail(recieverEmail);
		coll.insert(invite);



		return user.getUsername();

	}
	public Map<String,Object> stackedgraph(String uid) {

		DB mongo;
		mongo=db1.getDB();
		List<Data> Oaccept = new ArrayList<Data>();
		List<Data> Ounresponded =new  ArrayList<Data>();
		List<Data> Oreject =new  ArrayList<Data>();
		List<Data> Iaccept =new  ArrayList<Data>();
		List<Data> Iunresponded =new  ArrayList<Data>();
		List<Data> Ireject =new  ArrayList<Data>();
		
		Map<String,Object> hmap = new HashMap<String,Object>();
		
		DBCollection collection = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collection,Friend.class, String.class);
		BasicDBObject request = new BasicDBObject();
		request.put("uid", uid);
		
	//	DBCursor<Friend> cursor1 = coll.find(sentRequest);
		for(int i = 0;i<12;i++){
			DBCursor<Friend> cursor1 = coll.find(request);
		//int oucount = 0;
		int oucount = 0;
		int oacount = 0;
		int orcount = 0;
		int iucount = 0;
		int iacount = 0;
		int ircount = 0;
		
			while(cursor1.hasNext()){
				Friend friend = cursor1.next();
				Calendar cal = Calendar.getInstance();
				cal.setTime(friend.getRequestDate());
				int requestMonth = cal.get(Calendar.MONTH);
				String status = friend.getStatus();
				if(requestMonth==i){
					if(status.equalsIgnoreCase("Request sent")){
						oucount += 1;
						//graph.add(1);
					}
					else if(status.equalsIgnoreCase("Request pending")){
						iucount += 1;
						//graph.add(1);
					}
					else if(friend.getResponseDate()!=null){
						cal.setTime(friend.getResponseDate());
						int responseMonth = cal.get(Calendar.MONTH);
						System.out.println("Month..........." + requestMonth);
						
						if(status.equalsIgnoreCase("My request Accepted")){
							if(requestMonth!=responseMonth){
								oucount += 1;
								
							}
							oacount += 1;

						}
						else if(status.equalsIgnoreCase("My request rejected")){
							orcount += 1;
						}
						
						if(status.equalsIgnoreCase("I accepted Request")){
							if(requestMonth!=responseMonth){
								iucount += 1;
							}
							iacount += 1;

						}
						else if(status.equalsIgnoreCase("I rejected request")){
							ircount += 1;
						}
					}
				}
				
			}
			Data d = new Data();
			d.setY(oacount);
			Oaccept.add(d);
			
			d = new Data();
			d.setY(oucount);
			Ounresponded.add(d);
			

			d = new Data();
			d.setY(orcount);
			Oreject.add(d);
			

			d = new Data();
			d.setY(iacount);
			Iaccept.add(d);
			
			d = new Data();
			d.setY(iucount);
			Iunresponded.add(d);
			
			d = new Data();
			d.setY(ircount);
			Ireject.add(d);
			
			
			
		}

		
		Chart chart = new Chart();
		chart.setType("column");
		
		Title title = new Title();
		title.setText("Monthly record of making new friends");
		
		String[] list = {"jan","Feb","Mar", "Apr", "May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		List<String> categories = new ArrayList<String>();
		categories.addAll(Arrays.asList(list));
		
		XAxis xaxis = new XAxis();
		xaxis.setCategories(categories);
		
		Title yTitle = new Title();
		yTitle.setText("Total Requests");
		YAxis yaxis = new YAxis();
		yaxis.setAllowDecimals(false);
		yaxis.setMin(0);
		yaxis.setTitle(yTitle);
		
		ToolTip tooltip = new ToolTip();
		tooltip.setFormatter("function(){return '<b>' + this.x + '</b><br/>' +this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;}");
		
		Column column = new Column();
		column.setStacking("normal");
		PlotOptions plotoptions = new PlotOptions();
		plotoptions.setColumn(column);
		
/*		Data iaccept = new Data();
		iaccept.setData(Iaccept);
		Data ireject = new Data();
		ireject.setData(Ireject);
		Data iunresponded = new Data();
		iunresponded.setData(Iunresponded);

		Data oreject = new Data();
		oreject.setData(Oreject);
		Data ounresponded = new Data();
		ounresponded.setData(Ounresponded);
		List<Data> ia = new ArrayList<Data>();
		ia.add(iaccept);
		List<Data> ir = new ArrayList<Data>();
		ir.add(ireject);
		List<Data> iu = new ArrayList<Data>();
		iu.add(iunresponded);
		List<Data> oa = new ArrayList<Data>();
		oa.add(Oaccept);
		List<Data> or = new ArrayList<Data>();
		or.add(oreject);
		List<Data> ou = new ArrayList<Data>();
		ou.add(ounresponded);
		*/
		
		Series iAccept = new Series();
		iAccept.setName("I Accepted");
		iAccept.setStack("Incoming requests");
		iAccept.setData(Iaccept);
		Series iReject = new Series();
		iReject.setName("I Rejected");
		iReject.setStack("Incoming requests");
		iReject.setData(Ireject);
		Series iUnresponded = new Series();
		iUnresponded.setName("I did not respond to");
		iUnresponded.setStack("Incoming requests");
		iUnresponded.setData(Iunresponded);
		Series oAccept = new Series();
		oAccept.setName("My Accepted Requests");
		oAccept.setStack("Outgoing requests");
		oAccept.setData(Oaccept);
		Series oReject = new Series();
		oReject.setName("My Rejected Requests");
		oReject.setStack("Outgoing requests");
		oReject.setData(Oreject);
		Series oUnresponded = new Series();
		oUnresponded.setName("My unresponded requests");
		oUnresponded.setStack("Outgoing requests");
		oUnresponded.setData(Ounresponded);
		
		List<Series> series = new ArrayList<Series>();
		series.add(iAccept);
		series.add(iReject);
		series.add(iUnresponded);
		series.add(oAccept);
		series.add(oReject);
		series.add(oUnresponded);
		
		HighCharts stackedChart = new HighCharts();
		stackedChart.setChart(chart);
		stackedChart.setPlotOptions(plotoptions);
		stackedChart.setSeries(series);
		stackedChart.setTitle(title);
		//stackedChart.setTooltip(tooltip);
		stackedChart.setxAxis(xaxis);
		stackedChart.setyAxis(yaxis);
		
		
		hmap.put("stackedChart", stackedChart);
		
		return hmap;
	}
	public Map<String, Object> requestpiechart(String uid) {
		Map<String,Object> hmap = new HashMap<String,Object>();
		DB mongo;
		mongo=db1.getDB();
		DBCollection collection = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collection,Friend.class, String.class);
		BasicDBObject request = new BasicDBObject();
		request.put("uid", uid);
		request.put("status","I accepted request");
		long iacount = coll.getCount(request);
		request.replace("status", "I rejected request");
		long ircount = coll.getCount(request);
		System.out.println("I accepted..............." + iacount);
		System.out.println("I rejected..............." + ircount);
		request.replace("status", "Request pending");
		long iucount = coll.getCount(request);
		request.replace("status", "My request accepted");
		long oacount = coll.getCount(request);
		request.replace("status", "My request rejected");
		long orcount = coll.getCount(request);
		request.replace("status", "Request sent");
		long oucount = coll.getCount(request);
		System.out.println("I unresponded..............." + iucount);
		System.out.println("My rejected..............." + orcount);
		System.out.println("MY accepted..............." + oacount);
		System.out.println("MY unresponded..............." + oucount);
		Data iAccept = new Data();
		iAccept.setY(iacount);
		iAccept.setName("I Accept");
		iAccept.setSelected(true);
		iAccept.setSliced(true);
		
		Data iReject = new Data();
		iReject.setY(ircount);
		iReject.setName("I Reject");
		
		Data iUnresponded = new Data();
		iUnresponded.setY(iucount);
		iUnresponded.setName("I did not respond");
		
		Data oAccept = new Data();
		oAccept.setY(oacount);
		oAccept.setName("Accepted");
		
		Data oReject = new Data();
		oReject.setY(orcount);
		oReject.setName("Rejected");
		
		Data oUnresponded = new Data();
		oUnresponded.setY(oucount);
		oUnresponded.setName("Unresponded");
		List<Data> data = new ArrayList<Data>();
		
		data.add(iAccept);
		data.add(iReject);
		data.add(iUnresponded);
		data.add(oAccept);
		data.add(oReject);
		data.add(oUnresponded);
		
		Series serie = new Series();
		serie.setName("Friends");
		serie.setData(data);
		serie.setColorByPoint(true);
		List<Series> series = new ArrayList<Series>();
		series.add(serie);
		
		DataLabels datalabels = new DataLabels();
		datalabels.setEnabled(false);
		
		Pie pie = new Pie();
		pie.setAllowPointSelect(true);
		pie.setCursor("pointer");
		pie.setDataLabels(datalabels);
		pie.setShowInLegend(true);
		
		
		PlotOptions plotOptions = new PlotOptions();
		plotOptions.setPie(pie);
		
		ToolTip tooltip = new ToolTip();
		tooltip.setPointFormat("{series.name}: <b>{point.y}</b>");
		
		Title title = new Title();
		title.setText("Total Friend Requests");
		
		Chart chart = new Chart();
		chart.setPlotBackgroundColor(null);
		chart.setPlotBorderWidth(null);
		chart.setPlotShadow(false);
		chart.setType("pie");
		
		HighCharts highcharts = new HighCharts();
		highcharts.setChart(chart);
		highcharts.setPlotOptions(plotOptions);
		highcharts.setSeries(series);
		highcharts.setTitle(title);
		highcharts.setTooltip(tooltip);
		
		
		hmap.put("highchart", highcharts);
		
		return hmap;
		
	}

	public List<User> getAllUsers(){
		
		List<User> userList = new ArrayList<User>();
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("reminder", false);
		DBCursor<User> cursor = coll.find(query);
		while(cursor.hasNext()){
			User user = cursor.next();
			userList.add(user);
			System.out.println("Users.........................." + user.getName());
			
		}
		return userList;
		
	}
	public long checkPendingRequests(String uid){
		
		//List<User> userList = getAllUsers();
		DB mongo;
		mongo=db1.getDB();
		//int count = 0;
		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);
		System.out.println("UID................" + uid);
			BasicDBObject query = new BasicDBObject();
			query.put("uid", uid);
			query.put("status", "Request pending");
			long count = coll.getCount(query);
			
		return count;


	}
	public User updateReminder(String uid,Boolean reminder){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User user = coll.findOneById(uid);
		user.setReminder(reminder);
		coll.updateById(uid, user);
		return user;
				
	}
	
public User getUser(){
		
	//	List<User> userList = new ArrayList<User>();
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("reminder", false);
		User user = coll.findOne(query);
		if(user!=null)
		System.out.println(user.getName() + " found");
		
		return user;
		
	}

}
