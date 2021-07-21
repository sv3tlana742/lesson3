package HomeWork;

import HomeworkMain.ImageSize;
import HomeworkMain.VideoFormat;
import HomeworkMain.dto.PostImageResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static HomeworkMain.EndPoints.DELETE_HASH;
import static HomeworkMain.EndPoints.UPLOAD_IMAGE;
import static io.restassured.RestAssured.given;

public class DeleteNegativeTest extends BaseTest{
    String uploadedImageHash;
    MultiPartSpecification multiPartImagePng;
    MultiPartSpecification multiPartVideoAvi;

    @BeforeEach
    void beforeTest() {
        multiPartImagePng = new MultiPartSpecBuilder(new File(ImageSize.SMALL.getTitle()))
                .controlName("image")
                .build();

        multiPartVideoAvi = new MultiPartSpecBuilder(new File(VideoFormat.AVI.getTitle()))
                .controlName("video")
                .build();
    }

    @Test
    void deleteImageWithOutDeleteHash() {
        given(requestSpecificationWithToken)
                .delete("/image")
                .prettyPeek()
                .then()
                .spec(responseSpecificationDeleteWithOutDeleteHash);
    }

    @Test
    void deleteImageInvalidDeleteHash() {
        given(requestSpecificationWithToken)
                .delete("/image/wfINN7dVE3J0E")
                .prettyPeek()
                .then()
                .spec(responseSpecificationDeleteWithInvalidDeleteHash);
    }

    @Test
    void deleteImageWithOutToken() {
        uploadedImageHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImagePng)
                .post(UPLOAD_IMAGE)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();


        given()
                .delete(DELETE_HASH, uploadedImageHash)
                .prettyPeek()
                .then()
                .spec(responseSpecificationDeleteWithOutToken);
    }
}
