package net.fast.lbcs.data.entities;

import java.util.Date;

import org.simpleframework.xml.Default;

@Default
public class MyDate {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	
	
	
	public MyDate() {
		Date d=new Date();
		year=d.getYear() + 1900;
		month=d.getMonth();
		day=d.getDate();
		hour=d.getHours();
		minute=d.getMinutes();
		second=d.getSeconds();
		
	}
	
	public MyDate(Date d) {
		year=d.getYear() + 1900;
		month=d.getMonth();
		day=d.getDate();
		hour=d.getHours();
		minute=d.getMinutes();
		second=d.getSeconds();
		
	}

	public MyDate(String date) {
		this.setDate(date);		
	}
	

	public MyDate(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	
	public void setDate(String date){
		String temp="";
		int i;
		for(i=0;date.charAt(i)!='-' ; i++){
			temp+=date.charAt(i);
		}
		year = Integer.parseInt(temp);
		temp="";
		i++;

		for(;date.charAt(i)!='-' ; i++){
			temp+=date.charAt(i);
		}
		month = Integer.parseInt(temp);
		temp="";
		i++;

		for(;date.charAt(i)!='-' ; i++){
			temp+=date.charAt(i);
		}
		day = Integer.parseInt(temp);
		temp="";
		i++;

		for(;date.charAt(i)!=':' ; i++){
			temp+=date.charAt(i);
		}
		hour = Integer.parseInt(temp);
		temp="";
		i++;

		for(;date.charAt(i)!=':' ; i++){
			temp+=date.charAt(i);
		}
		minute = Integer.parseInt(temp);
		temp="";
		i++;

		for(;i<date.length() ; i++){
			temp+=date.charAt(i);
		}
		second = Integer.parseInt(temp);
	}
	
	public int compare(MyDate date){
		if(year==date.year && month==date.month && day==date.day && hour==date.hour && minute==date.minute && second==date.second){
			return 0;
		}
		else if(year<date.year && month<date.month && day<date.day && hour<date.hour && minute<date.minute && second<date.second){
			return 1;
		}
		else{
			return -1;
		}
		
	}
	
	@Override
	public String toString() {
		return "" + year + "-" + month + "-" + day
				+ "-" + hour + ":" + minute + ":"
				+ second;
	}
	
	
	

}
