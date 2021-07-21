package HomeWork;

import HomeworkMain.ImageSize;
import HomeworkMain.VideoFormat;
import HomeworkMain.dto.PostImageResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static HomeWork.BaseTest.requestSpecificationWithToken;
import static HomeworkMain.EndPoints.UPLOAD_IMAGE;
import static io.restassured.RestAssured.given;

public abstract class UploadForDelete {
    static String videoId;
    static String imageId;
    static String videoDeleteHash;
    static String imageDeleteHash;
    static MultiPartSpecification multiPartImagePng;
    static MultiPartSpecification multiPartVideoAvi;

    @BeforeEach
    void beforeTest() {
        multiPartImagePng = new MultiPartSpecBuilder(new File(ImageSize.SMALL.getTitle()))
                .controlName("image")
                .build();

        multiPartVideoAvi = new MultiPartSpecBuilder(new File(VideoFormat.AVI.getTitle()))
                .controlName("video")
                .build();
    }

    static void uploadImageForId(){
        imageId = given(requestSpecificationWithToken)
                .multiPart(multiPartImagePng)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getId();

    }

    static void uploadImageForDeleteHash(){
        imageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImagePng)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();

    }

    static void uploadVideoForId(){
        videoId = given(requestSpecificationWithToken)
                .multiPart(multiPartVideoAvi)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .as(PostImageResponse.class)
                .getData()
                .getId();
    }

    static void uploadVideoForDeleteHash(){
        videoDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartVideoAvi)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }
}
