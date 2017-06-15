package com.nbnote.model;
import lombok.Data;

/**
 * Created by K on 2017. 6. 14..
 */
@Data
public class Note {
    private String id;
    private String title;
    private String writer;
    private String writeDate;
    private String weather;
    private String temperature;
    private String place;
    private String content;
    private String consumeTitle;
    private String consume;
    private String incomeTitle;
    private String income;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getWriteDate() {
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

    public String getConsumeTitle() {
        return consumeTitle;
    }

    public String getConsume() {
        return consume;
    }

    public String getIncomeTitle() {
        return incomeTitle;
    }

    public String getIncome() {
        return income;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setWriteDate(String writeDate) {
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

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public void setIncomeTitle(String incomeTitle) {
        this.incomeTitle = incomeTitle;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}