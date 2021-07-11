package HomeWork;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ImageNegativeTest extends BaseTest{

    @Test
    void uploadOversizeImageTest() {
        given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageWeight.OVERSIZE.getTitle()))
                .expect()
                .statusCode(417)
                .body("success", is(false))
                .body("data.error", is("Internal expectation failed"))
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
    }

    @Test
    void uploadTxtImageTest() {
        given()
                .headers("Authorization", token)
                .multiPart("image", new File(ImageFormat.TXT.getTitle()))
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error", is("We don't support that file type!"))
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
    }
}
