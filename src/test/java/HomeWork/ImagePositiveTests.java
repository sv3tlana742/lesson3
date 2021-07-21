package HomeWork;

import HomeworkMain.ImageFormat;
import HomeworkMain.ImageSize;
import HomeworkMain.ImageWeight;
import HomeworkMain.VideoFormat;
import HomeworkMain.dto.PostImageResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static HomeworkMain.EndPoints.*;
import static io.restassured.RestAssured.*;

public class ImagePositiveTests extends BaseTest {
    static String uploadedImageDeleteHash;
    MultiPartSpecification multiPartImageSizeSmall;
    MultiPartSpecification multiPartImageWeightMiddle;
    MultiPartSpecification multiPartImageWeightBig;
    MultiPartSpecification multiPartImageWeightMaxSize;
    MultiPartSpecification multiPartImageSizeMedium;
    MultiPartSpecification multiPartImageSizeHd;
    MultiPartSpecification multiPartImageFormatJpg;
    MultiPartSpecification multiPartImageFormatPng;
    MultiPartSpecification multiPartImageFormatGif;
    MultiPartSpecification multiPartImageFormatTiff;
    MultiPartSpecification multiPartImageFormatMp4;

    @BeforeEach
    void beforeTest() {
        multiPartImageSizeSmall = new MultiPartSpecBuilder(new File(ImageSize.SMALL.getTitle()))
                .controlName("image")
                .build();
        multiPartImageWeightMiddle = new MultiPartSpecBuilder(new File(ImageWeight.MIDDLE.getTitle()))
                .controlName("image")
                .build();
        multiPartImageWeightBig = new MultiPartSpecBuilder(new File(ImageWeight.BIG.getTitle()))
                .controlName("image")
                .build();
        multiPartImageWeightMaxSize = new MultiPartSpecBuilder(new File(ImageWeight.MAX_SIZE.getTitle()))
                .controlName("image")
                .build();
        multiPartImageSizeMedium = new MultiPartSpecBuilder(new File(ImageSize.MEDIUM.getTitle()))
                .controlName("image")
                .build();
        multiPartImageSizeHd = new MultiPartSpecBuilder(new File(ImageSize.HD.getTitle()))
                .controlName("image")
                .build();
        multiPartImageFormatJpg = new MultiPartSpecBuilder(new File(ImageFormat.JPG.getTitle()))
                .controlName("image")
                .build();
        multiPartImageFormatPng = new MultiPartSpecBuilder(new File(ImageFormat.PNG.getTitle()))
                .controlName("image")
                .build();
        multiPartImageFormatGif = new MultiPartSpecBuilder(new File(ImageFormat.GIF.getTitle()))
                .controlName("image")
                .build();
        multiPartImageFormatTiff = new MultiPartSpecBuilder(new File(ImageFormat.TIFF.getTitle()))
                .controlName("image")
                .build();
        multiPartImageFormatMp4 = new MultiPartSpecBuilder(new File(VideoFormat.MP4.getTitle()))
                .controlName("image")
                .build();
    }

    @Test
    void uploadMiddleImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageWeightMiddle)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadBigImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageWeightBig)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadMaxImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageWeightMaxSize)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadSmallImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageSizeSmall)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationSmallImage)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadMediumImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageSizeMedium)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationMediumImage)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }
    @Test
    void uploadHDImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageSizeHd)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationHdImage)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadJpgImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageFormatJpg)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationImageFormatJpg)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadPngImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageFormatPng)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationImageFormatPng)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadTiffImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageFormatTiff)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationImageFormatTiff)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadGifImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageFormatGif)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(allPositiveResponseSpecification)
                .spec(responseSpecificationImageFormatGif)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadNotImageTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithToken)
                .multiPart(multiPartImageFormatMp4)
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
    void uploadImageWithoutToken() {
        uploadedImageDeleteHash = given()
                .multiPart(multiPartImageSizeSmall)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(responseSpecificationWithoutToken)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @Test
    void uploadClientIdTest() {
        uploadedImageDeleteHash = given(requestSpecificationWithClientId)
                .multiPart(multiPartImageSizeSmall)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(ResponseSpecificationWithClientId)
                .spec(responseSpecificationSmallImage)
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData()
                .getDeletehash();
    }

    @AfterEach
    void tearDown() {
        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, uploadedImageDeleteHash)
//                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
