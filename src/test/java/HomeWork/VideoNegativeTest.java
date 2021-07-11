package HomeWork;

import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class VideoNegativeTest extends BaseTest{

    @Test
    void uploadMkvVideoTest() {
        given()
                .headers("Authorization", token)
                .multiPart("video", new File(VideoFormat.MKV.getTitle()))
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error",equalTo("File exceeds max duration"))
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
    void uploadTxtVideoTest() {
        given()
                .headers("Authorization", token)
                .multiPart("video", new File(VideoFormat.TXT.getTitle()))
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error",equalTo("We don't support that file type!"))
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
    void uploadJpegVideoTest() {
        given()
                .headers("Authorization", token)
                .multiPart("video", new File(ImageWeight.MIDDLE.getTitle()))
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error",equalTo("Unable to determine file duration"))
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }
}
