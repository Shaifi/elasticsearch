package com.bansal.elasticsearch.controller;

/**
 * @author shaifibansal
 */

import com.bansal.elasticsearch.dto.UpdateBusinessRequest;
import com.bansal.elasticsearch.model.Business;
import com.bansal.elasticsearch.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/business/")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping(value = "insert")
    public void insertBusiness(@RequestBody Business business){
        businessService.insertBusiness(business);
    }

    @GetMapping(value = "searchAll")
    public List<Business> getAllBusiness(){
        return businessService.getAllBusiness();
    }

    @GetMapping(value = "search/{businessName}")
    public List<Business> getBusinessByName(@PathVariable String businessName){
        return businessService.getBusinessByName(businessName);
    }

    @GetMapping(value = "searchById/{businessId}")
    public Optional<Business> getBusinessById(@PathVariable Long businessId){
       return businessService.getBusinessById(businessId);
    }

    @GetMapping(value = "search/category/{categoryName}")
    public List<Business> getBusinessByCategory(@PathVariable String categoryName){
        return businessService.getBusinessByCategory(categoryName);
    }

    @PostMapping(value = "updateField")
    public void updateBusinessField(@RequestBody UpdateBusinessRequest updateBusinessRequest){
        businessService.updateBusinessField(updateBusinessRequest);
    }

    @PostMapping(value = "update")
    public void updateBusiness(@RequestBody Business business){
        businessService.updateBusiness(business);
    }

    @PostMapping(value = "delete/{id}")
    public void deleteBusiness(@PathVariable String id){
        businessService.deleteBusiness(id);
    }

}
