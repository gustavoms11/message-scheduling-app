package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.converter.DateConverter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {
        ScheduledMessageControllerV1Test.Initializer.class
})
@Testcontainers
public class ScheduledMessageControllerV1Test {

    @LocalServerPort
    private int port;

    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.22")
            .withDatabaseName("msgapp-test")
            .withUsername("msgapp")
            .withPassword("root");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    String.format("spring.datasource.url=%s", mySQLContainer.getJdbcUrl()),
                    String.format("spring.datasource.username=%s", mySQLContainer.getUsername()),
                    String.format("spring.datasource.password=%s", mySQLContainer.getPassword())
            ).applyTo(context.getEnvironment());
        }
    }

    @Test
    public void whenCreateByPostThenReturnResponse() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .body("receiverId", equalTo(receiverId))
                .body("message", equalTo("MESSAGE"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .body("scheduledTo", equalTo(scheduledTo))
                .body("platforms", containsInAnyOrder("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .body("status", equalTo("SCHEDULED"))
                .statusCode(201);
    }

    @Test
    public void whenFindByIdThenReturnResponseDTO() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .extract()
                .as(ScheduledMessageResponseDTOV1.class);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .get(String.format("http://localhost:%d%s%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages/",
                        response.getId()))
                .then()
                .body("receiverId", equalTo(receiverId))
                .body("message", equalTo("MESSAGE"))
                .body("id", equalTo(response.getId()))
                .body("createdAt", notNullValue())
                .body("scheduledTo", equalTo(scheduledTo))
                .body("status", equalTo("SCHEDULED"))
                .body("platforms", containsInAnyOrder("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .statusCode(200);
    }

    @Test
    public void whenDeleteThenReturnResponseStatus204() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .extract()
                .as(ScheduledMessageResponseDTOV1.class);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .delete(String.format("http://localhost:%d%s%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages/",
                        response.getId()))
                .then()
                .statusCode(204);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .get(String.format("http://localhost:%d%s%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages/",
                        response.getId()))
                .then()
                .statusCode(404);
    }

    @Test
    public void whenSearchByReceiverId() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .extract()
                .as(ScheduledMessageResponseDTOV1.class);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .queryParam("receiverId", receiverId)
                .get(String.format("http://localhost:%d%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .body("[0].receiverId", equalTo(receiverId))
                .body("[0].message", equalTo("MESSAGE"))
                .body("[0].id", equalTo(response.getId()))
                .body("[0].createdAt", notNullValue())
                .body("[0].scheduledTo", equalTo(scheduledTo))
                .body("[0].status", equalTo("SCHEDULED"))
                .body("[0].platforms", containsInAnyOrder("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .statusCode(200);
    }

    @Test
    public void whenSearchByReceiverIdAndStatus() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .extract()
                .as(ScheduledMessageResponseDTOV1.class);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .queryParam("receiverId", receiverId)
                .queryParam("status", "SCHEDULED")
                .get(String.format("http://localhost:%d%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .body("[0].receiverId", equalTo(receiverId))
                .body("[0].message", equalTo("MESSAGE"))
                .body("[0].id", equalTo(response.getId()))
                .body("[0].createdAt", notNullValue())
                .body("[0].scheduledTo", equalTo(scheduledTo))
                .body("[0].status", equalTo("SCHEDULED"))
                .body("[0].platforms", containsInAnyOrder("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .statusCode(200);
    }

    @Test
    public void whenSearchByReceiverIdAndScheduledToDate() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .extract()
                .as(ScheduledMessageResponseDTOV1.class);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .queryParam("receiverId", receiverId)
                .queryParam("scheduledToStartDate", DateConverter.fromDate(LocalDateTime.now().plusDays(2)))
                .queryParam("scheduledToEndDate", DateConverter.fromDate(LocalDateTime.now().plusDays(4)))
                .get(String.format("http://localhost:%d%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .body("[0].receiverId", equalTo(receiverId))
                .body("[0].message", equalTo("MESSAGE"))
                .body("[0].id", equalTo(response.getId()))
                .body("[0].createdAt", notNullValue())
                .body("[0].scheduledTo", equalTo(scheduledTo))
                .body("[0].status", equalTo("SCHEDULED"))
                .body("[0].platforms", containsInAnyOrder("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .statusCode(200);
    }

    @Test
    public void whenSearchByReceiverIdAndCreatedAtDate() {
        var receiverId = UUID.randomUUID().toString();
        var scheduledTo = DateConverter.fromDate(LocalDateTime.now().plusDays(3));

        var createDTO = ScheduledMessageCreateDTOV1Builder
                .aScheduledMessageCreateDTOV1()
                .scheduledTo(scheduledTo)
                .message("MESSAGE")
                .receiverId(receiverId)
                .platforms(Set.of("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .build();

        var response = given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .post(String.format("http://localhost:%d%s", port, "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .extract()
                .as(ScheduledMessageResponseDTOV1.class);

        given()
                .contentType(ContentType.JSON)
                .body(createDTO)
                .when()
                .queryParam("receiverId", receiverId)
                .queryParam("createdAtStartDate", DateConverter.fromDate(LocalDateTime.now().minusDays(1)))
                .queryParam("createdAtEndDate", DateConverter.fromDate(LocalDateTime.now().plusDays(1)))
                .get(String.format("http://localhost:%d%s",
                        port,
                        "/message-scheduling/api/v1/scheduled-messages"))
                .then()
                .body("[0].receiverId", equalTo(receiverId))
                .body("[0].message", equalTo("MESSAGE"))
                .body("[0].id", equalTo(response.getId()))
                .body("[0].createdAt", notNullValue())
                .body("[0].scheduledTo", equalTo(scheduledTo))
                .body("[0].status", equalTo("SCHEDULED"))
                .body("[0].platforms", containsInAnyOrder("SMS", "PUSH", "MAIL", "WHATSAPP"))
                .statusCode(200);
    }
}
