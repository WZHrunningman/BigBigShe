package com.example.wzh.bigbigshe;

/**
 * Author by wzh,Date on 2019/2/18.
 * PS: Not easy to write code, please indicate.
 */
public class Message {
    private String My_time;//骑行日期
    private String Bike_number;//自行车编号
    private String Cycling_time;//骑行时间
    private String Cycling_money;//骑行花费

    public Message(String my_time, String bike_number, String cycling_time, String cycling_money) {
        My_time = my_time;
        Bike_number = bike_number;
        Cycling_time = cycling_time;
        Cycling_money = cycling_money;
    }

    public String getMy_time() {
        return My_time;
    }

    public void setMy_time(String my_time) {
        My_time = my_time;
    }

    public String getBike_number() {
        return Bike_number;
    }

    public void setBike_number(String bike_number) {
        Bike_number = bike_number;
    }

    public String getCycling_time() {
        return Cycling_time;
    }

    public void setCycling_time(String cycling_time) {
        Cycling_time = cycling_time;
    }

    public String getCycling_money() {
        return Cycling_money;
    }

    public void setCycling_money(String cycling_money) {
        Cycling_money = cycling_money;
    }

}
