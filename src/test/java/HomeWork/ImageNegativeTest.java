package HomeWork;

import HomeworkMain.ImageFormat;
import HomeworkMain.ImageSize;
import HomeworkMain.ImageWeight;
import HomeworkMain.dto.PostImageResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static HomeworkMain.EndPoints.DELETE_HASH;
import static HomeworkMain.EndPoints.UPLOAD_IMAGE;
import static io.restassured.RestAssured.given;

public class ImageNegativeTest extends BaseTest{
    String uploadedImageId;
    MultiPartSpecification multiPartImageOverSize;
    MultiPartSpecification multiPartImageFormatTxt;
    MultiPartSpecification multiPartImageSizeSmall;

    @BeforeEach
    void beforeTest(){
        multiPartImageOverSize = new MultiPartSpecBuilder(new File(ImageWeight.OVERSIZE.getTitle()))
                .controlName("image")
                .build();

        multiPartImageFormatTxt = new MultiPartSpecBuilder(new File(ImageFormat.TXT.getTitle()))
                .controlName("image")
                .build();

        multiPartImageSizeSmall = new MultiPartSpecBuilder(new File(ImageSize.SMALL.getTitle()))
                .controlName("image")
                .build();
    }

    @Test
    void uploadOversizeImageTest() {
        given(requestSpecificationWithToken)
                .multiPart(multiPartImageOverSize)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(responseSpecificationImageOverSize);
    }

    @Test
    void uploadTxtImageTest() {
        given(requestSpecificationWithToken)
                .multiPart(multiPartImageFormatTxt)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(responseSpecificationImageFormatTxt);
    }

    @Test
    void uploadImageNull() {
        given(requestSpecificationWithToken)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .spec(responseSpecificationImageNull);
    }

    @Test
    void uploadImageWithoutToken() {
        uploadedImageId = given()
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


        tearDown();
    }

    void tearDown() {
        given(requestSpecificationWithToken)
                .when()
                .delete(DELETE_HASH, username, uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
