package com.bansal.elasticsearch.customRepo;

/**
 * @author shaifibansal
 */

import com.bansal.elasticsearch.constants.ESConstant;
import com.bansal.elasticsearch.dto.UpdateBusinessRequest;
import com.bansal.elasticsearch.model.Business;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Component
public class BusinessCustomRepository {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private RestHighLevelClient client;

    public List<Business> getBusinessByName(String name){
        QueryBuilder query = QueryBuilders.termQuery("businessName",name);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        return elasticsearchTemplate.queryForList(nativeSearchQuery,Business.class);
    }

    public List<Business> getBusinessByCategory(String name){
        QueryBuilder query = QueryBuilders.multiMatchQuery(name,"categories");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        return elasticsearchTemplate.queryForList(searchQuery,Business.class);
    }

    public void updateBusinessField(UpdateBusinessRequest businessRequest){
        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index(ESConstant.BUSINESS_INDEX_NAME).type(ESConstant.BUSINESS_TYPE_NAME).id(businessRequest.getId())
                    .doc(jsonBuilder().startObject().field(businessRequest.getFieldName(),businessRequest.getValue()).endObject());
            client.update(updateRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            // TODO :log error
        }
    }

    public void updateBusiness(Business business) {
        try {
            UpdateRequest updateRequest = new UpdateRequest();
            String businessStr = new ObjectMapper().writeValueAsString(business);
            //updateRequest.index("test_business_index").type("business").doc(business);
            updateRequest.index(ESConstant.BUSINESS_INDEX_NAME).type(ESConstant.BUSINESS_TYPE_NAME).id(business.getId().toString()).doc(businessStr, XContentType.JSON);
            client.update(updateRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            // TODO :log error
        }
    }

    public void deleteBusiness(String id){
        try {
            DeleteRequest deleteRequest = new DeleteRequest();
            deleteRequest.index(ESConstant.BUSINESS_INDEX_NAME).type(ESConstant.BUSINESS_TYPE_NAME).id(id);
            DeleteResponse deleteResponse = client.delete(deleteRequest,RequestOptions.DEFAULT);
        }catch (Exception e){
            // TODO :log error
        }
    }
}
