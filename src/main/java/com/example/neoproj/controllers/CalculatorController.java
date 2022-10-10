package com.example.neoproj.controllers;

import com.example.neoproj.DTOs.CalculateExceptionDTO;
import com.example.neoproj.DTOs.ResultDTO;
import com.example.neoproj.services.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@AllArgsConstructor
public class CalculatorController {
    private CalculatorService calculatorService;

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<CalculateExceptionDTO> exceptionHandler(RuntimeException e){
        CalculateExceptionDTO calculateExceptionDTO=new CalculateExceptionDTO(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(calculateExceptionDTO);
    }

    @GetMapping("/calculate")
    public ResponseEntity<ResultDTO> calc(@RequestParam BigDecimal salary, @RequestParam(required = false, defaultValue = "0") Integer days, @RequestParam(required = false)String startDate, @RequestParam(required = false)String endDate){
        if (startDate !=null && endDate!=null){
            LocalDate start=LocalDate.parse(startDate);
            LocalDate end=LocalDate.parse(endDate);
            return ResponseEntity.ok(ResultDTO.builder().res(calculatorService.calc(salary, start,end)).build());
        }else if (startDate !=null) throw new RuntimeException("End date is not found");
        else if (endDate!=null) throw new RuntimeException("Start date is not found");
        return ResponseEntity.ok(ResultDTO.builder().res(calculatorService.calc(salary, days)).build());
    }
}
