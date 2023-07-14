package com.farmatodo.test.controller;

import com.farmatodo.test.dto.HappyNumberRequest;
import com.farmatodo.test.dto.HappyNumbersResponse;
import com.farmatodo.test.dto.SumAllNumberResponse;
import com.farmatodo.test.service.HappyNumberService;
import com.farmatodo.test.service.SumNatureNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("utils")
@RequiredArgsConstructor
public class UtilsController {
    private final HappyNumberService happyNumberService;
    private final SumNatureNumberService sumNatureNumberService;

    @PostMapping("happy/number")
    public ResponseEntity<HappyNumbersResponse> isHappy(@Valid @RequestBody HappyNumberRequest request) {
        return ok(happyNumberService.areHappy(request));
    }

    @GetMapping("sum/{number}")
    public ResponseEntity<SumAllNumberResponse> sum (@Min(0) @PathVariable("number") int number) {
        return ok(sumNatureNumberService.sum(number));
    }
}
