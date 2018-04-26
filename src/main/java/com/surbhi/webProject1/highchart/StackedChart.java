package com.surbhi.webProject1.highchart;

import java.util.ArrayList;
import java.util.List;

public class StackedChart {
	 String name,stack;
	 
	 List<Integer> data = new ArrayList<Integer>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> ounresponded) {
		this.data = ounresponded;
	}

	/*public StackedChart(String name,String stack,int[] data){
		this.name = name;
		this.stack = stack;
		this.data = data;
		
	}*/
}
