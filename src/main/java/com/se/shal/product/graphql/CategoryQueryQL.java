package com.se.shal.product.graphql;

import com.se.shal.product.dto.CategoryDto;
import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.CategoryName;
import com.se.shal.product.service.CategoryService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryQueryQL implements GraphQLQueryResolver {

    @Autowired
    CategoryService categoryService;

    @Transactional
    CategoryDto getCategory(Long id) {
        Category category = categoryService.getCategory(id);
        return ShalMapper.INSTANCE.getCategoryDto(category);
    }

    List<CategoryName> getAllCategory() {
        List<CategoryName> categoryNames = Arrays.asList(CategoryName.values()).stream()
                .sorted((c1, c2) -> c1.getOrder().compareTo(c2.getOrder())).collect(Collectors.toList());
        return categoryNames;
    }



}
