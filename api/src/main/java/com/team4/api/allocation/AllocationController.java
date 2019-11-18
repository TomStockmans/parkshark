package com.team4.api.allocation;

import com.team4.domain.allocation.Allocation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AllocationController.RESOURCE_URL)
public class AllocationController {

    public static final String RESOURCE_URL = "/allocations";

}
