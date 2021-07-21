package HomeWork;

import HomeworkMain.ImageSize;
import HomeworkMain.VideoFormat;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static HomeWork.UploadForDelete.*;
import static HomeworkMain.EndPoints.*;
import static io.restassured.RestAssured.given;

public class DeletePositiveTest extends BaseTest{

    @Test
    void deleteImagePngWithId() {
        UploadForDelete.uploadImageForId();

        given(requestSpecificationWithToken)
                .delete(IMAGE_ID, imageId)
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositiveDelete);
    }

    @Test
    void deleteVideoAviWithId() {
        uploadVideoForId();

        given(requestSpecificationWithToken)
                .delete(IMAGE_ID, videoId)
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositiveDelete);
    }

    @Test
    void deleteImagePngWithDeleteHash() {
        uploadImageForDeleteHash();

        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, imageDeleteHash)
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositiveDelete);
    }

    @Test
    void deleteVideoAviWithDeleteHash() {
        uploadVideoForDeleteHash();

        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, videoDeleteHash)
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositiveDelete);
    }

    @Test
    void deleteImagePngWithDeleteRepeat() {
        uploadImageForDeleteHash();

        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, imageDeleteHash);

        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, imageDeleteHash)
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositiveDelete);
    }

    @Test
    void deleteAlienImage() {
        given(requestSpecificationWithToken)
                .delete(DELETE_HASH, "7X3jNDv")
                .prettyPeek()
                .then()
                .spec(responseSpecificationPositiveDelete);
    }
}