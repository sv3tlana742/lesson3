package HomeWork;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class VideoPositiveTest extends BaseTest {
    String uploadVideoId;

    @Test
    void uploadMp4VideoTest() {
        uploadVideoId = given()
                    .headers("Authorization", token)
                    .multiPart("video", new File(VideoFormat.MP4.getTitle()))
                    .expect()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("data.account_url",equalTo(username))
                    .body("data.account_id",equalTo(145270851))
                    .body("data.type",equalTo("video/mp4"))
                    .body("data.mp4",notNullValue())
                    .when()
                    .post("https://api.imgur.com/3/upload")
                    .prettyPeek()
                    .then()
                    .extract()
                    .response()
                    .jsonPath()
                    .getString("data.deletehash");
        }

    @Test
    void uploadAviVideoTest() {
        uploadVideoId = given()
                .headers("Authorization", token)
                .multiPart("video", new File(VideoFormat.AVI.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("video/x-ms"))
                .body("data.mp4",notNullValue())
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @Test
    void uploadM4vVideoTest() {
        uploadVideoId = given()
                .headers("Authorization", token)
                .multiPart("video", new File(VideoFormat.M4V.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("video/mp4"))
                .body("data.mp4",notNullValue())
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }

    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username, uploadVideoId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
