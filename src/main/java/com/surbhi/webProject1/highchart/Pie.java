package com.surbhi.webProject1.highchart;

public class Pie {

	Boolean allowPointSelect = true;
    String cursor = "pointer";
    DataLabels dataLabels; 
    public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}
	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	public DataLabels getDataLabels() {
		return dataLabels;
	}
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}
	public Boolean getShowInLegend() {
		return showInLegend;
	}
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}
	Boolean showInLegend = true;
	
}
