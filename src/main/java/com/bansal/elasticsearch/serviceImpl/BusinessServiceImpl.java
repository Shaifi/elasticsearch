package com.bansal.elasticsearch.serviceImpl;

/**
 * @author shaifibansal
 */

import com.bansal.elasticsearch.dto.UpdateBusinessRequest;
import com.bansal.elasticsearch.model.Business;
import com.bansal.elasticsearch.customRepo.BusinessCustomRepository;
import com.bansal.elasticsearch.repository.BusinessRepository;
import com.bansal.elasticsearch.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessCustomRepository businessCustomRepository;

    @Override
    public void insertBusiness(Business business) {
        businessRepository.save(business);
    }

    @Override
    public List<Business> getAllBusiness() {
        List<Business> businessList = new ArrayList<>();
        businessRepository.findAll().forEach(businessList :: add);
        return businessList;
    }

    @Override
    public List<Business> getBusinessByName(String name) {
        return new ArrayList<>(businessCustomRepository.getBusinessByName(name));
    }

    @Override
    public Optional<Business> getBusinessById(Long id) {
        return businessRepository.findById(id);
    }

    @Override
    public List<Business> getBusinessByCategory(String name) {
        return new ArrayList<>(businessRepository.getBusinessByCategory(name));
    }

    @Override
    public void updateBusinessField(UpdateBusinessRequest businessRequest) {
        businessCustomRepository.updateBusinessField(businessRequest);
    }

    @Override
    public void updateBusiness(Business business) {
        businessCustomRepository.updateBusiness(business);
    }

    @Override
    public void deleteBusiness(String id) {
        businessCustomRepository.deleteBusiness(id);
    }
}
