package com.restapi;



import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

    public class PetStoreApi {

        @Test
        public void userCreatePetStoreApiUserSuccess_ReturnsOk(){
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .body("{\n" +
                            "  \"id\": 0,\n" +
                            "  \"username\": \"ymail\",\n" +
                            "  \"firstName\": \"YmailT\",\n" +
                            "  \"lastName\": \"test\",\n" +
                            "  \"email\": \"test@gmail.com\",\n" +
                            "  \"password\": \"ymail\",\n" +
                            "  \"phone\": \"string\",\n" +
                            "  \"userStatus\": 0\n" +
                            "}")
                    .when()
                    .post("https://petstore.swagger.io/v2/user");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }



        @Test
        public void userGetUserByNameSuccess_ReturnOk(){
            Response response = given().accept("application/json")
                    .pathParam("user","ymail")
                    .when()
                    .get("https://petstore.swagger.io/v2/user/{user}");
            response.prettyPrint();
            response.then().statusCode(200);
            String emailText = response.path("username");
            System.out.println("userEmail : "+emailText);
        }


        @Test
        public void updateExistingUserInfoSuccess_ReturnOk(){
            Response response = given().accept("application/json")
                    .contentType("application/json")
                    .pathParam("username","ymail")
                    .body("{\n" +
                            "  \"id\": 123,\n" +
                            "  \"username\": \"ymail\",\n" +
                            "  \"firstName\": \"string\",\n" +
                            "  \"lastName\": \"string\",\n" +
                            "  \"email\": \"string@gmail.com\",\n" +
                            "  \"password\": \"string\",\n" +
                            "  \"phone\": \"string\",\n" +
                            "  \"userStatus\": 0\n" +
                            "}")
                    .when()
                    .put("https://petstore.swagger.io/v2/user/{username}");

            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }

        @Test
        public void deleteUserFromPetStoreSuccess_ReturnOk(){
            Response response = given().accept("application/json")
                    .pathParam("username","ymail")
                    .when()
                    .delete("https://petstore.swagger.io/v2/user/{username}");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void logsUserIntoSystem_ReturnOk(){
            Response response = given().accept("application/json")
                    .when()
                    .queryParam("username","soujanya")
                    .queryParam("password","soujanya131")
                    .get("https://petstore.swagger.io/v2/user/login?username");

            response.prettyPrint();
            response.then().statusCode(200);
        }

        @Test
        public void logsOutCurrentUserSession_ReturnOk(){
            Response response = given().accept("application/json")
                    .when()
                    .get("https://petstore.swagger.io/v2/user/logout");

            response.prettyPrint();
            response.then().statusCode(200);
        }
        @Test
        public void userCreateWthArray_ReturnsOk()
        {

            Response response =  given().accept("application/json")
                    .header("Content-Type","application/json")
                    .body("{\n" +
                            "    \"id\": 123,\n" +
                            "    \"username\": \"somu12\",\n" +
                            "    \"firstName\": \"somu\",\n" +
                            "    \"lastName\": \"mani\",\n" +
                            "    \"email\": \"somumani@gmail.com\",\n" +
                            "    \"password\": \"somu132\",\n" +
                            "    \"phone\": \" 9234517867\",\n" +
                            "    \"userStatus\": 0\n" +
                            "  },\n" +
                            "{\n" +
                            "    \"id\": 1234,\n" +
                            "    \"username\": \"kanyakumari\",\n" +
                            "    \"firstName\": \"kumari\",\n" +
                            "    \"lastName\": \"mai\",\n" +
                            "    \"email\": \"lavanya@gmail.com\",\n" +
                            "    \"password\": \"vanya12\",\n" +
                            "    \"phone\": \" 9234517678\",\n" +
                            "    \"userStatus\": 0\n" +
                            "}")
                    .when()
                    .post("https://petstore.swagger.io/v2/user/createWithArray");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void userCreateWthList_ReturnsOk(){
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .body("{\n" +
                            "    \"id\": 123,\n" +
                            "    \"username\": \"sonu12\",\n" +
                            "    \"firstName\": \"somu\",\n" +
                            "    \"lastName\": \"mani\",\n" +
                            "    \"email\": \"somumani@gmail.com\",\n" +
                            "    \"password\": \"somu132\",\n" +
                            "    \"phone\": \" 9234517867\",\n" +
                            "    \"userStatus\": 0\n" +
                            "  },\n" +
                            "{\n" +
                            "    \"id\": 1234,\n" +
                            "    \"username\": \"kanyakumari1\",\n" +
                            "    \"firstName\": \"kumari\",\n" +
                            "    \"lastName\": \"mai\",\n" +
                            "    \"email\": \"lavanya@gmail.com\",\n" +
                            "    \"password\": \"vanya12\",\n" +
                            "    \"phone\": \" 9234517678\",\n" +
                            "    \"userStatus\": 0\n" +
                            "  }")
                    .when()
                    .post("https://petstore.swagger.io/v2/user/createWithArray");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }

        @Test
        public void uploadImageForPetSuccess_ReturnOk(){
            File file = new File("C:\\Users\\ashok\\Downloads\\myDog.jpg");
            Response response = given().accept("application/json")
                    .multiPart(file)
                    .when()
                    .post("https://petstore.swagger.io/v2/pet/101/uploadImage");

            response.prettyPrint();
            response.then().statusCode(200);
        }



        @Test
        public void petAddNewPetToStoreSuccess_ReturnsOk(){
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .body("{\n" +
                            "  \"id\": 0,\n" +
                            "  \"category\": {\n" +
                            "    \"id\": 12,\n" +
                            "    \"name\": \"sambha\"\n" +
                            "  },\n" +
                            "  \"name\": \"nai\",\n" +
                            "  \"photoUrls\": [\n" +
                            "    \"string\"\n" +
                            "  ],\n" +
                            "  \"tags\": [\n" +
                            "    {\n" +
                            "      \"id\": 123,\n" +
                            "      \"name\": \"om\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"status\": \"available\"\n" +
                            "}")
                    .when()
                    .post("https://petstore.swagger.io/v2/pet");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void petUpdateAnExistingPetSuccess_ReturnsOk(){
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .body("{\n" +
                            "  \"id\": 0,\n" +
                            "  \"category\": {\n" +
                            "    \"id\": 78,\n" +
                            "    \"name\": \"guny\"\n" +
                            "  },\n" +
                            "  \"name\": \"doggie\",\n" +
                            "  \"photoUrls\": [\n" +
                            "    \"string\"\n" +
                            "  ],\n" +
                            "  \"tags\": [\n" +
                            "    {\n" +
                            "      \"id\": 90,\n" +
                            "      \"name\": \"krunul\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"status\": \"available\"\n" +
                            "}")
                    .when()
                    .put("https://petstore.swagger.io/v2/pet");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void findPetsByStatus_ReturnOk(){
            Response response = given().accept("application/json")
                    .when()
                    .queryParam("status ","available")
                    .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

            response.prettyPrint();
            response.then().statusCode(200);
        }
        @Test
        public void findPetsById_ReturnOk(){
            Response response = given().accept("application/json")
                    .when()
                    .pathParam("petId",12)
                    .get("https://petstore.swagger.io/v2/pet/{petId}");
            response.prettyPrint();
           response.then().statusCode(200);
        }
        @Test
        public void petUpdatesAPetInTheStoreWthFormDataSuccess_ReturnsOk()
        {
            Response response =  given().header("Content-Type","application/x-www-form-urlencoded")
                    .header("accept","application/json")
                    .when()
                    .pathParam("petId",12)
                    .post("https://petstore.swagger.io/v2/pet/{petId}");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void storePlaceAnOrderSuccess_ReturnsOk()
        {
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .when()
                    .pathParam("petId",12)
                    .post("https://petstore.swagger.io/v2/store/order");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void storePlaceAnOrder_Success_ReturnsOk()
        {
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .body("{\n" +
                            "  \"id\": 5,\n" +
                            "  \"petId\": 0,\n" +
                            "  \"quantity\": 0,\n" +
                            "  \"shipDate\": \"2023-04-24T10:57:05.877Z\",\n" +
                            "  \"status\": \"placed\",\n" +
                            "  \"complete\": true\n" +
                            "}")
                    .when()
                    .post("https://petstore.swagger.io/v2/store/order");
            response.prettyPrint();
            response.then().statusCode(200);
        }
        @Test
        public void storeFindPurchaseOrderById_Success_ReturnsOk()
        {
            Response response =  given().header("Content-Type","application/json")
                    .header("accept","application/json")
                    .when()
                    .pathParam("orderId",5)
                    .get("https://petstore.swagger.io/v2/store/order/{orderId}");
            response.prettyPrint();
            response.then().statusCode(200);
        }
        @Test
        public void storeDeletePurchaseOrderById_Success_ReturnsOk()
        {

            Response response = given().accept("application/json")
                    .header("accept","application/json")
                    .when()
                    .pathParam("orderId",5)
                    .delete("https://petstore.swagger.io/v2/store/order/{orderId}");
            response.prettyPrint();
            response.then().statusCode(200);
        }


        @Test
        public void StoreReturnPetInventoriesByStatus_ReturnOk(){
            Response response = given().accept("application/json")
                    .when()
                    .get("https://petstore.swagger.io/v2/store/inventory");

            response.prettyPrint();
            response.then().statusCode(200);
        }

    }
