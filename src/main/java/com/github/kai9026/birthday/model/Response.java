package com.github.kai9026.birthday.model;

public class Response {

  private static final String BIRTHDAY_MSG = "Faltan %d días para tu cumpleaños";

  private String daysLeft;

  private String error;

  public String getDaysLeft() {

    return daysLeft;
  }

  public String getError() {

    return error;
  }

  public Response setDaysLeft(long daysLeft) {

    this.daysLeft = String.format(BIRTHDAY_MSG, daysLeft);
    return this;
  }

  public Response setError(String error) {

    this.error = error;
    return this;
  }
}
