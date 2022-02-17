package com.github.kai9026.birthday.model;

public class Response {

  private static final String BIRTHDAY_MSG = "Faltan %d día/s para tu cumpleaños";

  private String daysLeft;

  private String error;

  private Status status;

  public String getDaysLeft() {

    return daysLeft;
  }

  public String getError() {

    return error;
  }

  public String getStatus() {

    return status.name();
  }

  public Response setDaysLeft(long daysLeft) {

    this.daysLeft = String.format(BIRTHDAY_MSG, daysLeft);
    return this;
  }

  public Response setStatus(Status status) {

    this.status = status;
    return this;
  }

  public Response setError(String error) {

    this.error = error;
    return this;
  }

  public enum Status {
    SUCCESS,
    INVALID_FORMAT_DATE
  }
}
