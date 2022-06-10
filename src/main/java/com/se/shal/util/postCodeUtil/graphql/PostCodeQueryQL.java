package com.se.shal.util.postCodeUtil.graphql;



import com.se.shal.util.postCodeUtil.PostCodeUtilService;
import com.se.shal.util.postCodeUtil.entity.PostCodeProvinceDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostCodeQueryQL implements GraphQLQueryResolver {
    @Autowired
    PostCodeUtilService service;
    public PostCodeProvinceDto getAddressFromPostCode(String postCode){
        return service.getPostCodeDto(postCode);
    }
}
