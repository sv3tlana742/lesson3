package HomeWork;

import HomeworkMain.ImageFormat;
import HomeworkMain.ImageSize;
import HomeworkMain.VideoFormat;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String username;
    static String accountId;
    static String accountUrl;
    static String clientId;
    static ResponseSpecification allPositiveResponseSpecification;
    static ResponseSpecification responseSpecificationImageOverSize;
    static ResponseSpecification responseSpecificationSmallImage;
    static ResponseSpecification responseSpecificationMediumImage;
    static ResponseSpecification responseSpecificationHdImage;
    static ResponseSpecification responseSpecificationImageFormatJpg;
    static ResponseSpecification responseSpecificationImageFormatPng;
    static ResponseSpecification responseSpecificationImageFormatGif;
    static ResponseSpecification responseSpecificationImageFormatTiff;
    static ResponseSpecification responseSpecificationVideoFormatMp4;
    static ResponseSpecification responseSpecificationImageFormatTxt;
    static ResponseSpecification responseSpecificationImageNull;
    static ResponseSpecification responseSpecificationWithoutToken;
    static ResponseSpecification responseSpecificationPositiveDelete;
    static ResponseSpecification responseSpecificationVideoAvi;
    static ResponseSpecification responseSpecificationVideoM4v;
    static ResponseSpecification responseSpecificationVideoMkv;
    static ResponseSpecification responseSpecificationVideoTxt;
    static ResponseSpecification responseSpecificationVideoJpeg;
    static ResponseSpecification responseSpecificationDeleteWithOutDeleteHash;
    static ResponseSpecification responseSpecificationDeleteWithInvalidDeleteHash;
    static ResponseSpecification responseSpecificationDeleteWithOutToken;
    static ResponseSpecification ResponseSpecificationWithClientId;

    static RequestSpecification requestSpecificationWithToken;
    static RequestSpecification requestSpecificationWithClientId;

    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = "https://api.imgur.com/3";
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");
        accountId = properties.getProperty("accountId");
        accountUrl = properties.getProperty("accountUrl");
        clientId = properties.getProperty("clientId");

        requestSpecificationWithToken = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();

        requestSpecificationWithClientId = new RequestSpecBuilder()
                .addHeader("Authorization", clientId)
                .build();

        responseSpecificationPositiveDelete = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("data", is(true))
                .expectBody("success",is(true))
                .expectBody("status", equalTo(200))
                .build();


        ResponseSpecificationWithClientId = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("data.account_url",equalTo(null))
                .expectBody("data.account_id",equalTo(null))
                .expectBody("data.id", is(notNullValue()))
                .expectBody("success",is(true))
                .expectBody("status", equalTo(200))
                .build();

        allPositiveResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("data.account_url",equalTo(accountUrl))
                .expectBody("data.account_id",equalTo(152343352))
                .expectBody("data.id", is(notNullValue()))
                .expectBody("success",is(true))
                .expectBody("status", equalTo(200))
                .build();

        responseSpecificationWithoutToken = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("data.account_url",equalTo(null))
                .expectBody("data.account_id",equalTo(null))
                .expectBody("data.id", is(notNullValue()))
                .expectBody("success",is(true))
                .expectBody("status", equalTo(200))
                .build();

        responseSpecificationSmallImage = new ResponseSpecBuilder()
                .expectBody("data.width",equalTo(ImageSize.WIDTH_SMALL.getVal()))
                .expectBody("data.height",equalTo(ImageSize.HEIGHT_SMALL.getVal()))
                .build();

        responseSpecificationMediumImage = new ResponseSpecBuilder()
                .expectBody("data.width",equalTo(ImageSize.WIDTH_MEDIUM.getVal()))
                .build();

        responseSpecificationHdImage = new ResponseSpecBuilder()
                .expectBody("data.width",equalTo(ImageSize.WIDTH_HD.getVal()))
                .build();

        responseSpecificationImageFormatJpg = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(ImageFormat.TYPE_JPG.getTitle()))
                .build();

        responseSpecificationImageFormatPng = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(ImageFormat.TYPE_PNG.getTitle()))
                .build();

        responseSpecificationImageFormatGif = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(ImageFormat.TYPE_GIF.getTitle()))
                .build();

        responseSpecificationImageFormatTiff = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(ImageFormat.TYPE_TIFF.getTitle()))
                .build();

        responseSpecificationVideoFormatMp4 = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(VideoFormat.TYPE_MP4.getTitle()))
                .expectBody("data.mp4",is(notNullValue()))
                .expectBody("data.mp4_size",is(notNullValue()))
                .build();

        responseSpecificationVideoAvi = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(VideoFormat.TYPE_AVI.getTitle()))
                .expectBody("data.mp4",is(notNullValue()))
                .expectBody("data.mp4_size",is(notNullValue()))
                .build();

        responseSpecificationVideoM4v = new ResponseSpecBuilder()
                .expectBody("data.type",equalTo(VideoFormat.TYPE_M4V.getTitle()))
                .expectBody("data.mp4",is(notNullValue()))
                .expectBody("data.mp4_size",is(notNullValue()))
                .build();

        responseSpecificationImageFormatTxt = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", is(false))
                .expectBody("data.error", is("We don't support that file type!"))
                .build();

        responseSpecificationImageOverSize = new ResponseSpecBuilder()
                .expectStatusCode(417)
                .expectBody("success", is(false))
                .expectBody("data.error", is("Internal expectation failed"))
                .build();

        responseSpecificationImageNull = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", is(false))
                .expectBody("data.error", is("Bad Request"))
                .build();

        responseSpecificationVideoMkv = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", is(false))
                .expectBody("data.error", is("File exceeds max duration"))
                .build();

        responseSpecificationVideoTxt = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", is(false))
                .expectBody("data.error", is("We don't support that file type!"))
                .build();

        responseSpecificationVideoJpeg = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", is(false))
                .expectBody("data.error", is("Unable to determine file duration"))
                .build();

        responseSpecificationDeleteWithOutDeleteHash = new ResponseSpecBuilder()
            .expectBody("success", is(false))
            .expectBody("status", equalTo(400))
            .expectBody("data.error", is("An ID is required."))
            .build();

        responseSpecificationDeleteWithInvalidDeleteHash = new ResponseSpecBuilder()
                .expectBody("success", is(false))
                .expectBody("status", equalTo(403))
                .expectBody("data.error", is("Unauthorized"))
                .build();

        responseSpecificationDeleteWithOutToken = new ResponseSpecBuilder()
                .expectBody("success", is(false))
                .expectBody("status", equalTo(401))
                .expectBody("data.error", is("Authentication required"))
                .build();
}

    private static void getProperties(){
        try (InputStream output = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(output);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
