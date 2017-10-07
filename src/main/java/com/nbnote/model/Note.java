package com.nbnote.model;

import java.util.Date;

import lombok.Data;

/**
 * Created by K on 2017. 6. 14..
 */
@Data
public class Note {
	private int id;
	private String title;
	private String writer;
	private Date writeDate;
	private String weather;
	private String temperature;
	private String place;
	private String content;
	private String consumeTitle;
	private int consume;
	private String incomeTitle;
	private int income;

	public Note() {
	}

	public Note(int id, String title, String writer, Date writeDate, String weather, String temperature, String place,
			String content, String consumeTitle, int consume, String incomeTitle, int income) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.weather = weather;
		this.temperature = temperature;
		this.place = place;
		this.content = content;
		this.consumeTitle = consumeTitle;
		this.consume = consume;
		this.incomeTitle = incomeTitle;
		this.income = income;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getWriter() {
		return writer;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public String getWeather() {
		return weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getPlace() {
		return place;
	}

	public String getContent() {
		return content;
	}

	public int getConsume() {
		return consume;
	}

	public void setConsume(int consume) {
		this.consume = consume;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getConsumeTitle() {
		return consumeTitle;
	}

	public String getIncomeTitle() {
		return incomeTitle;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setConsumeTitle(String consumeTitle) {
		this.consumeTitle = consumeTitle;
	}

	public void setIncomeTitle(String incomeTitle) {
		this.incomeTitle = incomeTitle;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", writer=" + writer + ", writeDate=" + writeDate + ", weather="
				+ weather + ", temperature=" + temperature + ", place=" + place + ", content=" + content
				+ ", consumeTitle=" + consumeTitle + ", consume=" + consume + ", incomeTitle=" + incomeTitle
				+ ", income=" + income + "]";
	}

}