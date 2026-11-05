package com.klef.ms.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DiscoveryClient discoveryClient;


    // =========================================================
    // COMMON METHOD
    // LOGIN AND GET JWT TOKEN
    // =========================================================

    private String getJWTToken() throws Exception {

        String email = "admin@gmail.com";
        String password = "admin";


        String loginRequest =
                "{"
                + "\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\""
                + "}";


        MvcResult result =
                mockMvc.perform(
                        post("/user/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginRequest)
                )
                .andExpect(status().isOk())
                .andReturn();


        String response =
                result.getResponse()
                        .getContentAsString();


        JsonNode jsonNode =
                objectMapper.readTree(response);


        JsonNode tokenNode =
                jsonNode.get("token");


        assertNotNull(
                tokenNode,
                "JWT token was not generated"
        );


        String token =
                tokenNode.asText();


        assertFalse(
                token.isBlank(),
                "JWT token is empty"
        );


        return token;
    }


    // =========================================================
    // TEST CASE 1
    // VALID WORKFLOW
    //
    // Login → JWT → Protected API → Success
    // =========================================================

    @Test
    void testValidWorkflow() throws Exception {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 1 : VALID WORKFLOW");
        System.out.println("==========================================");


        // -----------------------------------------------------
        // STEP 1 : LOGIN
        // -----------------------------------------------------

        String token =
                getJWTToken();


        System.out.println(
                "JWT generated successfully"
        );

        System.out.println(
                "Token : " + token
        );


        // -----------------------------------------------------
        // STEP 2 : USE JWT
        // -----------------------------------------------------

        MvcResult result =
                mockMvc.perform(
                        get("/user/displayall")
                                .header(
                                        "Authorization",
                                        "Bearer " + token
                                )
                )
                .andExpect(status().isOk())
                .andReturn();


        // -----------------------------------------------------
        // STEP 3 : VERIFY RESPONSE
        // -----------------------------------------------------

        String response =
                result.getResponse()
                        .getContentAsString();


        System.out.println();
        System.out.println(
                "Protected API : /user/displayall"
        );

        System.out.println(
                "Status Code : "
                + result.getResponse().getStatus()
        );

        System.out.println(
                "Response : "
                + response
        );


        assertNotNull(response);

        assertFalse(
                response.isBlank()
        );


        System.out.println();
        System.out.println(
                "TC-1 PASSED"
        );

        System.out.println(
                "Valid credentials → JWT → Protected API → Success"
        );
    }


    // =========================================================
    // TEST CASE 2
    // INVALID JWT
    //
    // Call protected API with invalid token
    // Expected : 401 Unauthorized
    // =========================================================

    @Test
    void testInvalidJWT() throws Exception {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 2 : INVALID JWT");
        System.out.println("==========================================");


        String invalidToken =
                "invalid-jwt-token";


        MvcResult result =
                mockMvc.perform(
                        get("/user/displayall")
                                .header(
                                        "Authorization",
                                        "Bearer " + invalidToken
                                )
                )
                .andReturn();


        int status =
                result.getResponse()
                        .getStatus();


        String response =
                result.getResponse()
                        .getContentAsString();


        System.out.println(
                "Status Code : " + status
        );

        System.out.println(
                "Response : " + response
        );


        assertEquals(
                401,
                status
        );


        System.out.println();
        System.out.println(
                "TC-2 PASSED : INVALID JWT → 401"
        );
    }


    // =========================================================
    // TEST CASE 3
    // NO JWT TOKEN
    //
    // Call protected API without Authorization header
    //
    // Your current JwtFilter returns 401
    // =========================================================

    @Test
    void testNoJWTToken() throws Exception {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 3 : NO JWT TOKEN");
        System.out.println("==========================================");


        MvcResult result =
                mockMvc.perform(
                        get("/user/displayall")
                )
                .andReturn();


        int status =
                result.getResponse()
                        .getStatus();


        String response =
                result.getResponse()
                        .getContentAsString();


        System.out.println(
                "Status Code : " + status
        );

        System.out.println(
                "Response : " + response
        );


        assertEquals(
                401,
                status
        );


        System.out.println();
        System.out.println(
                "TC-3 PASSED : NO JWT → 401"
        );
    }


    // =========================================================
    // TEST CASE 4
    // LOAD BALANCING CHECK
    //
    // Multiple ProductService instances must be running.
    //
    // UserService
    //      ↓
    // OpenFeign
    //      ↓
    // Eureka
    //      ↓
    // LoadBalancer
    //      ↓
    // ProductService instance
    // =========================================================

    @Test
    void testLoadBalancing() throws Exception {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 4 : LOAD BALANCING CHECK");
        System.out.println("==========================================");


        // -----------------------------------------------------
        // STEP 1 : CHECK PRODUCT SERVICE INSTANCES
        // -----------------------------------------------------

        List<ServiceInstance> instances =
                discoveryClient.getInstances(
                        "ProductService"
                );


        System.out.println();
        System.out.println(
                "ProductService instances registered in Eureka:"
        );


        for (ServiceInstance instance : instances) {

            System.out.println(
                    "Instance : "
                    + instance.getInstanceId()
            );

            System.out.println(
                    "Host     : "
                    + instance.getHost()
            );

            System.out.println(
                    "Port     : "
                    + instance.getPort()
            );

            System.out.println(
                    "URI      : "
                    + instance.getUri()
            );

            System.out.println("--------------------------------");
        }


        // At least two instances are required
        assertTrue(
                instances.size() >= 2,
                "At least two ProductService instances must be running"
        );


        // -----------------------------------------------------
        // STEP 2 : GET JWT
        // -----------------------------------------------------

        String token =
                getJWTToken();


        // -----------------------------------------------------
        // STEP 3 : SEND MULTIPLE REQUESTS
        // -----------------------------------------------------

        for (int i = 1; i <= 10; i++) {

            MvcResult result =
                    mockMvc.perform(
                            get("/user/allproducts")
                                    .header(
                                            "Authorization",
                                            "Bearer " + token
                                    )
                    )
                    .andExpect(status().isOk())
                    .andReturn();


            String response =
                    result.getResponse()
                            .getContentAsString();


            System.out.println();
            System.out.println(
                    "Request " + i
            );

            System.out.println(
                    "Status : "
                    + result.getResponse().getStatus()
            );

            System.out.println(
                    "Response : "
                    + response
            );
        }


        System.out.println();
        System.out.println(
                "TC-4 COMPLETED : LOAD BALANCING"
        );

        System.out.println(
                "Multiple ProductService instances were discovered"
        );

        System.out.println(
                "and requests were sent through UserService → Feign → Eureka → LoadBalancer."
        );
    }


    // =========================================================
    // TEST CASE 5
    // SERVICE DISCOVERY / INSTANCE FAILURE
    //
    // MANUAL SETUP:
    // 1. Start Eureka
    // 2. Start ProductService instance 1
    // 3. Start ProductService instance 2
    // 4. Verify both are registered
    // 5. Stop ONE ProductService instance
    // 6. Wait for Eureka to update
    // 7. Run this test
    //
    // Expected:
    // Remaining ProductService instance handles request
    // =========================================================

    @Test
    void testServiceDiscoveryFailure() throws Exception {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 5 : SERVICE DISCOVERY FAILURE");
        System.out.println("==========================================");


        // -----------------------------------------------------
        // STEP 1 : CHECK CURRENT PRODUCT INSTANCES
        // -----------------------------------------------------

        List<ServiceInstance> instances =
                discoveryClient.getInstances(
                        "ProductService"
                );


        System.out.println();
        System.out.println(
                "Currently available ProductService instances:"
        );


        for (ServiceInstance instance : instances) {

            System.out.println(
                    instance.getInstanceId()
                    + " → "
                    + instance.getUri()
            );
        }


        assertTrue(
                instances.size() >= 1,
                "At least one ProductService instance must be available"
        );


        // -----------------------------------------------------
        // STEP 2 : GET JWT
        // -----------------------------------------------------

        String token =
                getJWTToken();


        // -----------------------------------------------------
        // STEP 3 : CALL PRODUCT SERVICE
        // -----------------------------------------------------

        MvcResult result =
                mockMvc.perform(
                        get("/user/allproducts")
                                .header(
                                        "Authorization",
                                        "Bearer " + token
                                )
                )
                .andReturn();


        int status =
                result.getResponse()
                        .getStatus();


        String response =
                result.getResponse()
                        .getContentAsString();


        System.out.println();
        System.out.println(
                "Status Code : " + status
        );

        System.out.println(
                "Response : " + response
        );


        // -----------------------------------------------------
        // STEP 4 : VERIFY SYSTEM STILL WORKS
        // -----------------------------------------------------

        assertEquals(
                200,
                status
        );


        System.out.println();
        System.out.println(
                "TC-5 PASSED : SYSTEM CONTINUED USING AVAILABLE INSTANCE"
        );
    }
      
   
    @Test
    void testPublicTestAPI() throws Exception
    {
    	mockMvc.perform(get("/user/test"))
    	          .andExpect(status().isOk())
    	          .andExpect(content().string("Test API Call"));
    }
    
    
    
    
    
    
    
    
    
    
    
    
}