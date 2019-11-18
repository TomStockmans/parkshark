package com.team4.api.division;

import com.team4.domain.division.Division;
import com.team4.service.division.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(DivisionController.RESOURCE_URL)
public class DivisionController {

    public static final String RESOURCE_URL = "/division";
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
    private final DivisionService divisionService;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionDto createDivision(@RequestBody CreateDivisionDto divisionDto) {
        Division added = divisionService.createDivision(DivisionMapper.mapToDivision(divisionDto));
        return DivisionMapper.mapToDivisionDto(added);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<DivisionDto> getAllDivisions() {
        return divisionService.getAllDivisions().stream()
                .map(DivisionMapper::mapToDivisionDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DivisionDto getDivisionById(@PathVariable long id) {
        Division division = divisionService.getDivisionById(id);
        return DivisionMapper.mapToDivisionDto(division);
    }
}
