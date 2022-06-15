package com.se.shal.product.service;

import com.se.shal.product.dto.input.InputProductDto;
import com.se.shal.product.entity.*;
import com.se.shal.product.entity.enumeration.*;
import com.se.shal.product.repository.*;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.repository.FailureReasonListRepository;
import com.se.shal.shop.repository.FailureReasonRepository;
import com.se.shal.shop.repository.ShopRepository;
import com.se.shal.shop.repository.ShopStatusRepository;
import com.se.shal.util.ShalMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    ShopStatusRepository shopStatusRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    OptionsRepository optionsRepository;
    @Autowired
    VariationRepository variationRepository;
    @Autowired
    FailureReasonRepository failureReasonRepository;
    @Autowired
    FailureReasonListRepository failureReasonListRepository;
    @Autowired
    ProductAttributeRepository productAttributeRepository;
    @Autowired
    ShopDao shopDao;


    @Test
    void saveProduct() {
        Attribute brand = attributeRepository.save(Attribute.builder().attribute("Brand").build());
        Attribute country = attributeRepository.save(Attribute.builder().attribute("Country of origin").build());
        Attribute weight = attributeRepository.save(Attribute.builder().attribute("Weight").build());
        Attribute size = attributeRepository.save(Attribute.builder().attribute("Size").build());
        Attribute materialCountry = attributeRepository.save(Attribute.builder().attribute("Material country").build());
        Attribute material = attributeRepository.save(Attribute.builder().attribute("Material").build());
        Attribute age = attributeRepository.save(Attribute.builder().attribute("Recommended age").build());
        Attribute fda = attributeRepository.save(Attribute.builder().attribute("FDA license number").build());

        Options opt_bodycon_Dress = optionsRepository.save(Options.builder()
                .image("Multicolor.png")
                .optionName("Multicolor")
                .stock(2)
                .build());

        Options opt_bodycon_Dress_s = optionsRepository.save(Options.builder()
                .optionName("S")
                .stock(2)
                .price(170)
                .build());

        Options opt_bodycon_Dress_m = optionsRepository.save(Options.builder()
                .optionName("M")
                .stock(5)
                .price(170)
                .build());

        Options opt_bodycon_Dress_l = optionsRepository.save(Options.builder()
                .optionName("L")
                .stock(5)
                .price(170)
                .build());

        Variations var_bodycon_Dress_color = variationRepository.save(Variations.builder()
                .variationName("Color")
                .options(List.of(opt_bodycon_Dress))
                .build());
        Variations var_bodycon_Dress_size = variationRepository.save(Variations.builder()
                .variationName("Size")
                .options(Arrays.asList(opt_bodycon_Dress_s, opt_bodycon_Dress_m, opt_bodycon_Dress_l))
                .build());

        ProductAttribute att_bodycon_Dress1 = productAttributeRepository.save(ProductAttribute.builder()
                .text("SHEIN")
                .attribute(brand)
                .build());
        ProductAttribute att_bodycon_Dress2 = productAttributeRepository.save(ProductAttribute.builder()
                .text("China")
                .attribute(country)
                .build());
        ProductAttribute att_bodycon_Dress5 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Short")
                .attribute(weight)
                .build());
        ProductAttribute att_bodycon_Dress3 = productAttributeRepository.save(ProductAttribute.builder()
                .text("S M L")
                .attribute(size)
                .build());
        ProductAttribute att_bodycon_Dress4 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Polyester")
                .attribute(material)
                .build());

        Shipment Registered = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.REGISTEREDMAILS).build());
        Shipment EMS = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.EMS).build());
        Shipment kerry = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.KERRY).build());
        Shipment jandt = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.JANDT).build());
        Shipment flash = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.FLASH).build());
        Shipment dhl = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.DHL).build());
        Shipment ninja = shipmentRepository.save(Shipment.builder().shipmentName(ShipmentName.NINJA).build());

        Product bodycon_Dress = productRepository.save(Product.builder()
                .productName("Twist Front Cut Out Ruched Bodycon Dress")
                .details("Twist Front Cut Out Ruched Bodycon Dressl")
                .category(CategoryName.FASHION)
                .productStatus(ProductStatus.ACTIVE)
                .saleTypeName(SaleTypeName.AUCTION)
                .auctionPeriod(5)
                .nextAuction(2)
                .timeUnitForAuctionPeriod(TimeUnit.HOUR)
                .timeUnitForNextAuction(TimeUnit.HOUR)
                .salePrice(200.0)
                .storage(10)
                .startingBid(20000.0)
                .productAttribute(Arrays.asList(att_bodycon_Dress1, att_bodycon_Dress2, att_bodycon_Dress3, att_bodycon_Dress4, att_bodycon_Dress5))
                .variations(List.of(var_bodycon_Dress_color, var_bodycon_Dress_size))
                .shipments(Arrays.asList(kerry, ninja, dhl))
                .imagesPath(Arrays.asList("https://img.ltwebstatic.com/images3_pi/2022/04/22/16506075404a2c35a3844fa81fb45c921921613c48_thumbnail_600x.webp",
                        "https://img.ltwebstatic.com/images3_pi/2022/04/22/16506075410d79db9ae971d8d35b6af5ec5f5e31cc_thumbnail_600x.webp"))
                .build());

        InputProductDto newProduct = ShalMapper.INSTANCE.saveProductTest(bodycon_Dress);
        long shopId = 1;
        Product output = productService.saveProduct(shopId, newProduct);
        System.out.println(output);
    }


}
