package webProject1.requests;

import webProject1.requestService.RegisterService;

public class TestRegister {
	public static void main(String args[]){
		RegisterService re = new RegisterService();
		re.register("surbhi", "singh", "surbhisingh", "country", "city", "244", "password", "gender","26/07/1994");
	}
}
