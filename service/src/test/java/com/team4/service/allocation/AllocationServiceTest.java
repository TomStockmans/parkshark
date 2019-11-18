package com.team4.service.allocation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AllocationServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AllocationService allocationService;

    @Test
    void startAllocation_whenGivenCorrectValues_thenStartAllocation() {
        allocationService.startAllocation(1,"123",1);
    }
}