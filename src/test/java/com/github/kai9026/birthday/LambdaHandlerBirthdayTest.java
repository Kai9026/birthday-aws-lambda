package com.github.kai9026.birthday;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import com.github.kai9026.birthday.model.Request;
import com.github.kai9026.birthday.model.Response.Status;
import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
class LambdaHandlerBirthdayTest {

  private static final String SUCCESS_MESSAGE = "Faltan 1 día/s para tu cumpleaños";

  private static final String KO_MESSAGE = "Formato de fecha inválido. Requiere: yyyy-MM-dd";

  @Test
  @DisplayName("Given a valid input date then return days left for birthday")
  void testSimpleLambdaSuccess() {

    Request in = new Request();
    final var tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);

    in.setBirthDate(tomorrow.format(DateTimeFormatter.ISO_LOCAL_DATE));
    given()
        .contentType("application/json")
        .accept("application/json")
        .body(in)
        .when()
        .post()
        .then()
        .statusCode(200)
        .body(containsString(Status.SUCCESS.name()))
        .body(containsString(SUCCESS_MESSAGE));
  }

  @Test
  @DisplayName("Given a date with invalid format then return an error message")
  void testSimpleLambdaKO() {

    Request in = new Request();

    in.setBirthDate("22/02/2027");
    given()
        .contentType("application/json")
        .accept("application/json")
        .body(in)
        .when()
        .post()
        .then()
        .statusCode(200)
        .body(containsString(Status.INVALID_FORMAT_DATE.name()))
        .body(containsString(KO_MESSAGE));
  }

}
