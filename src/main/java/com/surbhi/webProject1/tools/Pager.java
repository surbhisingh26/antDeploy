package com.surbhi.webProject1.tools;

public class Pager {

	public static void main(String args[]){
		int currentPage = 8;
		int pager=10;
		if(currentPage<=10){
			for(int i =1;i<=pager;i++)
			System.out.print(i+" ");
		}
		else{
			for(int i =currentPage-4;i<=currentPage+5;i++){
				System.out.print(i+" ");
			}
		}
	}
}
