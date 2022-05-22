package com.se.shal.config;

import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.CategoryName;
import com.se.shal.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Category beauty = Category.builder().categoryName(CategoryName.BEAUTY).build();
        Category electronic = Category.builder().categoryName(CategoryName.ELECTRONIC).build();
        Category toy = Category.builder().categoryName(CategoryName.TOY).build();
        Category health = Category.builder().categoryName(CategoryName.HEALTH).build();
        Category sports = Category.builder().categoryName(CategoryName.SPORTS).build();
        Category home = Category.builder().categoryName(CategoryName.HOME).build();
        Category pets = Category.builder().categoryName(CategoryName.PETS).build();

        categoryRepository.save(beauty);
        categoryRepository.save(electronic);
        categoryRepository.save(toy);
        categoryRepository.save(health);
        categoryRepository.save(sports);
        categoryRepository.save(home);
        categoryRepository.save(pets);
    }
}
