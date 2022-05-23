package com.se.shal.config;

import com.se.shal.product.entity.*;
import com.se.shal.product.repository.AttributeRepository;
import com.se.shal.product.repository.CategoryRepository;
import com.se.shal.product.repository.ShipmentRepository;
import com.se.shal.shop.entity.ShopStatus;
import com.se.shal.shop.entity.ShopStatusName;
import com.se.shal.shop.repository.ShopStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    ShopStatusRepository shopStatusRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        ShopStatus disable = shopStatusRepository.save(ShopStatus.builder()
                .shopStatusName(ShopStatusName.DISABLE)
                .build());
        ShopStatus enable = shopStatusRepository.save(ShopStatus.builder()
                .shopStatusName(ShopStatusName.ENABLE)
                .build());
        
        Category beauty = Category.builder().categoryName(CategoryName.BEAUTY).build();
        Category fashion = Category.builder().categoryName(CategoryName.FASHION).build();
        Category electronic = Category.builder().categoryName(CategoryName.ELECTRONIC).build();
        Category toy = Category.builder().categoryName(CategoryName.TOY).build();
        Category health = Category.builder().categoryName(CategoryName.HEALTH).build();
        Category sports = Category.builder().categoryName(CategoryName.SPORTS).build();
        Category home = Category.builder().categoryName(CategoryName.HOME).build();
        Category pets = Category.builder().categoryName(CategoryName.PETS).build();

        categoryRepository.save(beauty);
        categoryRepository.save(fashion);
        categoryRepository.save(electronic);
        categoryRepository.save(toy);
        categoryRepository.save(health);
        categoryRepository.save(sports);
        categoryRepository.save(home);
        categoryRepository.save(pets);

        Attribute brand = attributeRepository.save(Attribute.builder().attribute("Brand").build());
        Attribute country = attributeRepository.save(Attribute.builder().attribute("Country of origin").build());
        Attribute weight = attributeRepository.save(Attribute.builder().attribute("Weight").build());
        Attribute size = attributeRepository.save(Attribute.builder().attribute("Size").build());
        Attribute materialCountry = attributeRepository.save(Attribute.builder().attribute("Material country").build());
        Attribute material = attributeRepository.save(Attribute.builder().attribute("Material").build());
        Attribute age = attributeRepository.save(Attribute.builder().attribute("Recommended age").build());
        Attribute fda = attributeRepository.save(Attribute.builder().attribute("FDA license number").build());

//      BEAUTY
        beauty.getAttributes().add(brand);
        beauty.getAttributes().add(country);

//      FASHION
        fashion.getAttributes().add(brand);
        fashion.getAttributes().add(country);
        fashion.getAttributes().add(size);
        fashion.getAttributes().add(weight);
        fashion.getAttributes().add(material);

//      ELEC
        electronic.getAttributes().add(brand);
        electronic.getAttributes().add(country);
        electronic.getAttributes().add(size);
        electronic.getAttributes().add(weight);
        electronic.getAttributes().add(materialCountry);

//      TOY
        toy.getAttributes().add(brand);
        toy.getAttributes().add(country);
        toy.getAttributes().add(material);
        toy.getAttributes().add(age);

//      HEALTH
        health.getAttributes().add(brand);
        health.getAttributes().add(country);
        health.getAttributes().add(fda);

//      SPORTS
        sports.getAttributes().add(brand);
        sports.getAttributes().add(country);
        sports.getAttributes().add(size);
        sports.getAttributes().add(weight);
        sports.getAttributes().add(material);

//      HOME
        home.getAttributes().add(brand);
        home.getAttributes().add(country);
        home.getAttributes().add(size);
        home.getAttributes().add(weight);
        home.getAttributes().add(material);

//      PETS
        pets.getAttributes().add(brand);
        pets.getAttributes().add(country);
        pets.getAttributes().add(size);
        pets.getAttributes().add(weight);
        pets.getAttributes().add(material);

        Shipment Registered = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.REGISTEREDMAILS).build());
        Shipment EMS = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.EMS).build());
        Shipment kerry = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.KERRY).build());
        Shipment jandt = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.JANDT).build());
        Shipment flash = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.FLASH).build());
        Shipment dhl = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.DHL).build());
        Shipment ninja = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.NINJA).build());


    }
}
