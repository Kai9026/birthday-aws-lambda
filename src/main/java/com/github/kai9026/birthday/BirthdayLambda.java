package com.github.kai9026.birthday;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.kai9026.birthday.model.Request;
import com.github.kai9026.birthday.model.Response;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import javax.inject.Named;

@Named("birthday")
public class BirthdayLambda implements RequestHandler<Request, Response> {

  @Override
  public Response handleRequest(Request input, Context context) {

    final LocalDate birthDate;
    try {

      birthDate = LocalDate.parse(input.getBirthDate());
    } catch (DateTimeException ex) {
      return new Response().setError("Formato de fecha inválido. Requiere yyyy-MM-dd (1980-04-04)");
    }
    return calculateDaysLeftToBirthdate(birthDate);
  }

  private Response calculateDaysLeftToBirthdate(TemporalAccessor birthDate) {

    final var today = LocalDate.now();
    final var original = LocalDate.from(birthDate);

    final var currentBirthdate = LocalDate
        .of(Year.now().getValue(), original.getMonth().getValue(), original.getDayOfMonth());
    if (currentBirthdate.isAfter(today)) {

      final var days = ChronoUnit.DAYS.between(today, currentBirthdate);
      return new Response()
          .setDaysLeft(days);
    } else {

      final var nextBirthdate = LocalDate
          .of(Year.now().plus(1, ChronoUnit.YEARS).getValue(),
              original.getMonth().getValue(),
              original.getDayOfMonth());
      final var days = ChronoUnit.DAYS.between(today, nextBirthdate);
      return new Response()
          .setDaysLeft(days);
    }
  }
}
