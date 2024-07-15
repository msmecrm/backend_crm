package com.msme.crm.core.controller;

import com.msme.crm.core.listOfValues.IListResult;
import com.msme.crm.core.listOfValues.service.ListOfValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/crmCore")
public class CRMCoreController {

    @Autowired
    final ListOfValuesService listOfValuesService;

    public CRMCoreController(ListOfValuesService listOfValuesService) {
        this.listOfValuesService = listOfValuesService;
    }

    @GetMapping("/getLOVDetails/{lovName}")
    public ResponseEntity getListOfValues(@PathVariable String lovName, @RequestBody List<String> inputfields) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // inputfields
        System.out.println(inputfields);
        return  ResponseEntity.ok(listOfValuesService.processLOVRequest(lovName, inputfields));


    }
}


