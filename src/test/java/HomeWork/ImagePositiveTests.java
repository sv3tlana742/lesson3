package HomeWork;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ImagePositiveTests extends BaseTest {
    static String encodedFile;
    String uploadedImageId;

//    @BeforeEach
//    void beforeTest() {
//        byte[] byteArray = getFileContent();
//        encodedFile = Base64.getEncoder().encodeToString(byteArray);
//    }
//
//    @Test
//    void uploadFileTest() {
//        uploadedImageId = given()
//                .headers("Authorization", token)
//                .multiPart("image", encodedFile)
//                .expect()
//                .body("success", is(true))
//                .body("data.id", is(notNullValue()))
//                .when()
//                .post("https://api.imgur.com/3/upload")
//                .prettyPeek()
//                .then()
//                .extract()
//                .response()
//                .jsonPath()
//                .getString("data.deletehash");
//    }

    @Test
    void uploadMiddleImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageWeight.MIDDLE.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
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
    void uploadBigImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageWeight.BIG.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
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
    void uploadMaxImageTest() {
        given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageWeight.MAX_SIZE.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
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
    void uploadSmallImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageSize.SMALL.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.width",equalTo(1))
                .body("data.height",equalTo(1))
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
    void uploadMediumImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageSize.MEDIUM.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.width",equalTo(1200))
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
    void uploadHDImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageSize.HD.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.width",equalTo(10000))
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
    void uploadJpgImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageFormat.JPG.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("image/jpeg"))
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
    void uploadJngImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageFormat.PNG.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("image/png"))
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
    void uploadTiffImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageFormat.GIF.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("image/gif"))
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
    void uploadGifImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageFormat.TIFF.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("image/jpeg"))
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
    void uploadNotImageTest() {
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageFormat.MP4_IMAGE.getTitle()))
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.account_url",equalTo(username))
                .body("data.account_id",equalTo(145270851))
                .body("data.type",equalTo("video/mp4"))
                .body("data.mp4",notNullValue())
                .body("data.mp4_size",notNullValue())
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
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username, uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

//    private byte[] getFileContent() {
//        byte[] byteArray = new byte[0];
//        try {
//            byteArray = FileUtils.readFileToByteArray(new File(ImageWeight.MIDDLE.getTitle()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return byteArray;
//    }
}
