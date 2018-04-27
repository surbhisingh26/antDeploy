package com.surbhi.webProject1.highchart;

import java.util.List;

public class HighCharts {
	
	Chart chart;
	Title title;
	ToolTip tooltip;
	PlotOptions plotOptions;
	List<Series> series;
	XAxis xAxis;
	YAxis yAxis;
	public XAxis getxAxis() {
		return xAxis;
	}
	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}
	public YAxis getyAxis() {
		return yAxis;
	}
	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}
	public Chart getChart() {
		return chart;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public ToolTip getTooltip() {
		return tooltip;
	}
	public void setTooltip(ToolTip tooltip) {
		this.tooltip = tooltip;
	}
	public PlotOptions getPlotOptions() {
		return plotOptions;
	}
	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}
	public List<Series> getSeries() {
		return series;
	}
	public void setSeries(List<Series> series2) {
		this.series = series2;
	}
	

}
