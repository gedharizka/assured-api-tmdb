package com.gedha;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Tmdb {
    String base_url = "https://api.themoviedb.org";
    String access_token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNTFiOWJkNmZlZjhjMjdlMTcxMTI4NjExZjk1MDhmYyIsInN1YiI6IjYyOTJkMTljZmM1ZjA2MDA1MDQ1ZTU3MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-PWPnDEMqA4tcE2Ihpb-vmrmm_sVrKlwdfRKI4EMDDA";

    @Test
    public void testGetNowPlaying() {
        Response res = given()
                .baseUri(base_url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("/3/movie/now_playing");
        res.then().assertThat().statusCode(200);
        res.then()
                .assertThat()
                .body("page", equalTo(1));

    }

    @Test
    public void testGetMoviePopular() {
        Response res = given()
                .baseUri(base_url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("/3/movie/popular");
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void testPostRating() {
        String movieId = "980489";
        String requestBody = "{\"value\": 8.5}";

        Response res = given()
                .baseUri(base_url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + access_token)
                .body(requestBody)
                .when()
                .post("/3/movie/"+movieId+"/rating");
        res.then().assertThat().statusCode(201);
        res.then().assertThat().body("success",equalTo(true));

    }
}
