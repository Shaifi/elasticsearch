package com.bansal.elasticsearch.service;

/**
 * @author shaifibansal
 */


import com.bansal.elasticsearch.dto.UpdateBusinessRequest;
import com.bansal.elasticsearch.model.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessService {

    void insertBusiness(Business business);

    List<Business> getAllBusiness();

    List<Business> getBusinessByName(String name);

    Optional<Business> getBusinessById(Long id);

    List<Business> getBusinessByCategory(String name);

    void updateBusinessField(UpdateBusinessRequest businessRequest);

    void updateBusiness(Business business);

    void deleteBusiness(String id);

}
