package com.surbhi.webProject1.highchart;

import java.util.List;

public class Series {

	String name;
	Boolean colorByPoint;
	List<Data> data;
	String stack;
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getColorByPoint() {
		return colorByPoint;
	}
	public void setColorByPoint(Boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> iaccept) {
		this.data = iaccept;
	}
	
}
