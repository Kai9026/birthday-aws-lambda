package com.github.kai9026.birthday;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class LambdaHandlerBirthday {

  @Test
  void testSimpleLambdaSuccess() throws Exception {
    // you test your lambas by invoking on http://localhost:8081
    // this works in dev mode too

        /*Request in = new Request();
        in.setBirthDate("Stu");
        in.setGreeting("Hello");
        given()
                .contentType("application/json")
                .accept("application/json")
                .body(in)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(containsString("Hello Stu"));*/
  }

}
