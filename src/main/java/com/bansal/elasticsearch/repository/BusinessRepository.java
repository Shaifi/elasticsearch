package com.bansal.elasticsearch.repository;

/**
 * @author shaifibansal
 */

import com.bansal.elasticsearch.model.Business;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends ElasticsearchRepository<Business, Long> {

    Optional<Business> findById(Long id);

    @Query("{\"match_phrase\" : {\"categories\" : {\"query\" : \"?0\"}}}")
    List<Business> getBusinessByCategory(String name);
}
