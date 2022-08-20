package com.se.shal.util.postCodeUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.se.shal.util.postCodeUtil.entity.PostCodeAmphoeDto;
import com.se.shal.util.postCodeUtil.entity.PostCodeProvinceDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostCodeUtilService {
    @Value("${shal.postcodeDb}")
    String dbUrl;

    Map<String,List<PostCodeData>> postCode;
    @SneakyThrows
    @PostConstruct
    void init(){
        Gson gson = new Gson();
        InputStream is = (new ClassPathResource(dbUrl)).getInputStream();
        String jsonString;
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            jsonString =  (String)reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
        TypeToken<List<PostCodeData>> returnType = new TypeToken<List<PostCodeData>>(){};
        List<PostCodeData> codeList = gson.fromJson(jsonString, (returnType.getType()));

        List<String> distinctZipcodes = codeList.stream().map(PostCodeData::getZipcode)
                .distinct().collect(Collectors.toList());
        postCode = new HashMap<>();
        distinctZipcodes.stream().forEach( code -> {
            postCode.put(code,codeList.stream()
                    .filter(cl -> cl.getZipcode().equals(code)).collect(Collectors.toList()));
        });


    }

//    @Cacheable(value = "postData")
    public PostCodeProvinceDto getPostCodeDto(String postCode){
        List<PostCodeData> pcData = this.postCode.get(postCode);
        if (pcData.size() == 0){
            throw new RuntimeException("the postcode is not in the database");
        }
        PostCodeProvinceDto output = PostCodeProvinceDto.builder()
                .province(pcData.get(0).province).build();
        List<String> amphoeList = pcData.stream().map(PostCodeData::getAmphoe)
                .distinct()
                .collect(Collectors.toList());
        amphoeList.stream().forEach(am ->{
            PostCodeAmphoeDto amphoeDto = PostCodeAmphoeDto.builder()
                    .amphoe(am)
                    .tumbons(pcData.stream()
                            .filter(postCodeData -> postCodeData.getAmphoe().equals(am))
                            .map(PostCodeData::getDistrict)
                            .collect(Collectors.toList()))
                    .build();
            output.getAmphoes().add(amphoeDto);
        });

        return output;
    }
}
