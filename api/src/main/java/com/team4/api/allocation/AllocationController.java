package com.team4.api.allocation;

        import com.team4.domain.allocation.AllocationFilter;
        import com.team4.service.allocation.AllocationService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping(AllocationController.RESOURCE_URL)
public class AllocationController {

    public static final String RESOURCE_URL = "/allocations";

    @Autowired
    private AllocationService allocationService;

    @GetMapping
    public List<AllocationDtoResponse> getAllAllocations(@RequestParam int limit, @RequestParam AllocationFilter filter){
        allocationService.getAllAllocations(limit, filter);
        return null;
    }

}
