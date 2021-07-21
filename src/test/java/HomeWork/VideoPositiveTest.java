package HomeWork;

import HomeworkMain.VideoFormat;
import HomeworkMain.dto.PostImageResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static HomeworkMain.EndPoints.DELETE_HASH;
import static HomeworkMain.EndPoints.UPLOAD_IMAGE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class VideoPositiveTest extends BaseTest {
    String uploadVideoId;
    MultiPartSpecification multiPartSpecificationVideoMP4;
    MultiPartSpecification multiPartSpecificationVideoAvi;
    MultiPartSpecification multiPartSpecificationVideoM4v;

    @BeforeEach
    void beforeTest(){

        multiPartSpecificationVideoMP4 = new MultiPartSpecBuilder(new File(VideoFormat.MP4.getTitle()))
                .controlName("video")
                .build();
        multiPartSpecificationVideoAvi = new MultiPartSpecBuilder(new File(VideoFormat.AVI.getTitle()))
                .controlName("video")
                .build();
        multiPartSpecificationVideoM4v = new MultiPartSpecBuilder(new File(VideoFormat.M4V.getTitle()))
                .controlName("video")
                .build();
    }

    @Test
    void uploadMp4VideoTest() {
        uploadVideoId = given(requestSpecificationWithToken)
                    .multiPart(multiPartSpecificationVideoMP4)
                    .post(UPLOAD_IMAGE)
                    .prettyPeek()
                    .then()
                    .spec(allPositiveResponseSpecification)
                    .spec(responseSpecificationVideoFormatMp4)
                    .extract()
                    .body()
                    .as(PostImageResponse.class)
                    .getData()
                    .getDeletehash();

    }

    @Test
    void uploadAviVideoTest() {
        uploadVideoId = given(requestSpecificationWithToken)
                .multiPart(multiPartSpecificationVideoAvi)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationVideoAvi)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadM4vVideoTest() {
        uploadVideoId = given(requestSpecificationWithToken)
                .multiPart(multiPartSpecificationVideoM4v)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationVideoM4v)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @AfterEach
    void tearDown() {
        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, uploadVideoId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
