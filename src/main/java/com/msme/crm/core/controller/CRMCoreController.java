package com.msme.crm.core.controller;

import com.msme.crm.core.service.ListOfValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crmCore")
public class CRMCoreController {

    @Autowired
    final ListOfValuesService listOfValuesService;

    public CRMCoreController(ListOfValuesService listOfValuesService) {
        this.listOfValuesService = listOfValuesService;
    }

    @GetMapping("/getLOVDetails/{lovName}")
    public ResponseEntity getListOfValues(@PathVariable String lovName, @RequestParam List<String> inputfields) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // inputfields
        List<String> processedInputFields = inputfields.stream()
                .map(field -> field.isEmpty() ? null : field)
                .collect(Collectors.toList());
        System.out.println(processedInputFields);
        return  ResponseEntity.ok(listOfValuesService.processLOVRequest(lovName, processedInputFields));


    }
}


