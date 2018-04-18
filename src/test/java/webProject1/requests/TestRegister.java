package webProject1.requests;

import com.surbhi.webProject1.requestService.UserService;

public class TestRegister {
	public static void main(String args[]){
		UserService re = new UserService();
		re.registerUser("surbhi", "singh", "surbhisingh", "country", "city", "244", "password", "gender","26/08/1994","#000000","","","","");
	}
}
