package com.facturacion.models.entity;

public class TimeApi {
	
	private String datetime;

	public TimeApi() {
		super();
	}

	public TimeApi(String datetime) {
		super();
		this.datetime = datetime;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "TimeApi [dateTime=" + datetime + ", getDateTime()=" + getDatetime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
