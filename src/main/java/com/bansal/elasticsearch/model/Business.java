package com.bansal.elasticsearch.model;

/**
 * @author shaifibansal
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "test_business_index")
public class Business {

    @Id
    private Long id;
    private Long businessNumber;
    private String businessName;
    private String[] categories;

    public Business(){

    }

    public Business(Long id, Long businessNumber, String businessName, String[] categories) {
        this.id = id;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(Long businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
