package HomeWork;

import HomeworkMain.EndPoints;
import HomeworkMain.ImageWeight;
import HomeworkMain.VideoFormat;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static HomeworkMain.EndPoints.UPLOAD_IMAGE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class VideoNegativeTest extends BaseTest{
    MultiPartSpecification multiPartSpecificationVideoMkv;
    MultiPartSpecification multiPartSpecificationVideoTxt;
    MultiPartSpecification multiPartSpecificationVideoJpeg;

    @BeforeEach
    void beforeTest(){

        multiPartSpecificationVideoMkv = new MultiPartSpecBuilder(new File(VideoFormat.MKV.getTitle()))
                .controlName("video")
                .build();
        multiPartSpecificationVideoTxt = new MultiPartSpecBuilder(new File(VideoFormat.TXT.getTitle()))
                .controlName("video")
                .build();
        multiPartSpecificationVideoJpeg = new MultiPartSpecBuilder(new File(ImageWeight.MIDDLE.getTitle()))
                .controlName("video")
                .build();
    }

    @Test
    void uploadMkvVideoTest() {
        given(requestSpecificationWithToken)
                .multiPart(multiPartSpecificationVideoMkv)
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error",equalTo("File exceeds max duration"))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(responseSpecificationVideoMkv);
    }

    @Test
    void uploadTxtVideoTest() {
        given(requestSpecificationWithToken)
                .multiPart(multiPartSpecificationVideoTxt)
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error",equalTo("We don't support that file type!"))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek();
    }

    @Test
    void uploadJpegVideoTest() {
        given(requestSpecificationWithToken)
                .multiPart(multiPartSpecificationVideoJpeg)
                .expect()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error",equalTo("Unable to determine file duration"))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek();
    }
}
