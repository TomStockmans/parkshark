package com.team4.api.allocation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AllocationControllerIntegrationTest {

    @LocalServerPort
    private int port;


}