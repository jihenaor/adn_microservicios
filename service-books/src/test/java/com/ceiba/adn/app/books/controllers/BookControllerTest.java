package com.ceiba.adn.app.books.controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import org.apache.http.HttpStatus;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

class BookControllerTest {

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:54559";
        basePath = "/";
        RestAssured.filters(new RequestLoggingFilter(),
                new RequestLoggingFilter());
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void listarBooks_DebeRetornarListaDeBooks() {
        String path = "book";

        // Realizar la solicitud GET al endpoint /book
        given()
                .when()
                    .get(path)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("$", hasSize(greaterThan(0))); ;

    }
}