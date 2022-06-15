package com.se.shal.config;

import com.se.shal.product.entity.*;
import com.se.shal.product.entity.enumeration.*;
import com.se.shal.product.repository.*;
import com.se.shal.shop.entity.*;
import com.se.shal.shop.repository.FailureReasonListRepository;
import com.se.shal.shop.repository.FailureReasonRepository;
import com.se.shal.shop.repository.ShopRepository;
import com.se.shal.shop.repository.ShopStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

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

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        ShopStatus disable = shopStatusRepository.save(ShopStatus.builder()
                .shopStatusName(ShopStatusName.DISABLE)
                .build());
        ShopStatus enable = shopStatusRepository.save(ShopStatus.builder()
                .shopStatusName(ShopStatusName.ENABLE)
                .build());

        FailureReason not_clear = FailureReason.builder().reason("Selfie Photo with ID card does not clear").build();
        FailureReason logo = FailureReason.builder().reason("Inappropriate shop logo").build();
        FailureReason name = FailureReason.builder().reason("Inappropriate shop name").build();
        FailureReason selfie_id_card = FailureReason.builder().reason("ID card number does not match the selfie photo with ID card").build();
        FailureReason name_id_card = FailureReason.builder().reason("Name does not match the ID card").build();
        FailureReason other = FailureReason.builder().reason("Others").build();
        failureReasonRepository.save(not_clear);
        failureReasonRepository.save(logo);
        failureReasonRepository.save(name);
        failureReasonRepository.save(selfie_id_card);
        failureReasonRepository.save(name_id_card);
        failureReasonRepository.save(other);


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

        Shop shop = shopRepository.save(Shop.builder()
                .shopName("Thansuda shop")
                .email("Thansuda2010@gmail.com")
                .promptPay("0954475249")
                .idCard("1234567891234")
                .shopStatus(ShopStatusName.ENABLE)
                .shopLogoImagePath("shop image")
                .selfiePhotoWithIdCardPath("selfie")
                .shopAddress(ShopAddress.builder()
                        .district("meachan")
                        .houseNumber("322")
                        .moo("12").subDistrict("mea kham")
                        .postalCode("57240")
                        .province("chiang rai")
                        .build())
                .build());
        Shop shop2 = shopRepository.save(Shop.builder()
                .shopName("Patteeda shop")
                .email("Patteera@gmail.com")
                .promptPay("0953348895")
                .idCard("1234567891234")
                .shopStatus(ShopStatusName.ENABLE)
                .shopLogoImagePath("shop image")
                .selfiePhotoWithIdCardPath("selfie")
                .shopAddress(ShopAddress.builder()
                        .district("Fang")
                        .houseNumber("322")
                        .moo("12").subDistrict("Wiang")
                        .postalCode("57240")
                        .province("chiang mai")
                        .build())
                .build());
        Shop shop3 = shopRepository.save(Shop.builder()
                .shopName("GameZa shop")
                .email("GameZa@gmail.com")
                .promptPay("0885558125")
                .idCard("1111111111111")
                .shopStatus(ShopStatusName.DISABLE)
                .shopLogoImagePath("shop image")
                .selfiePhotoWithIdCardPath("selfie")
                .shopAddress(ShopAddress.builder()
                        .district("San kam pang")
                        .houseNumber("56")
                        .moo("5")
                        .subDistrict("San kam pang")
                        .postalCode("50130")
                        .province("chiang mai")
                        .build())
                .build());
        shop3.setFailureReasonLists(Arrays.asList(failureReasonListRepository.save(FailureReasonList.builder()
                .failureReasons(logo)
                .build()),
                failureReasonListRepository.save(FailureReasonList.builder()
                .failureReasons(name)
                .build())));

        Options options = optionsRepository.save(Options.builder()
                .optionName("128GB")
                .stock(20)
                .price(29000)
                .build());

        Options options1 = optionsRepository.save(Options.builder()
                .optionName("256GB")
                .stock(20)
                .price(39000)
                .build());

        Options options2 = optionsRepository.save(Options.builder()
                .image("Iphone-green.png")
                .optionName("128GB")
                .stock(20)
                .price(29000)
                .build());

        Options options3 = optionsRepository.save(Options.builder()
                .image("Iphone-red.png")
                .optionName("256GB")
                .stock(20)
                .price(39000)
                .build());

        Variations variations = variationRepository.save(Variations.builder()
                .variationName("Size")
                .options(Arrays.asList(options, options1))
                .build());

        Variations variations1 = variationRepository.save(Variations.builder()
                .variationName("Color")
                .options(Arrays.asList(options2, options3))
                .build());

        ProductAttribute att1 = productAttributeRepository.save(ProductAttribute.builder()
                .text("IPhone")
                .attribute(brand)
                .build());
        ProductAttribute att2 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Not have")
                .attribute(country)
                .build());
        ProductAttribute att3 = productAttributeRepository.save(ProductAttribute.builder()
                .text("135 g.")
                .attribute(weight)
                .build());
        ProductAttribute att4 = productAttributeRepository.save(ProductAttribute.builder()
                .text("146.7 x 71.5 x 7.65 mm.")
                .attribute(size)
                .build());
        ProductAttribute att5 = productAttributeRepository.save(ProductAttribute.builder()
                .text("China")
                .attribute(materialCountry)
                .build());

        Product product = productRepository.save(Product.builder()
                .productName("IPhone13")
                .details("IPhone13 128 GB")
                .category(CategoryName.ELECTRONIC)
                .productStatus(ProductStatus.ACTIVE)
                .auctionPeriod(1)
                .nextAuction(1)
                .shop(shop)
                .timeUnitForAuctionPeriod(TimeUnit.HOUR)
                .timeUnitForNextAuction(TimeUnit.HOUR)
                .salePrice(35000.0)
                .saleTypeName(SaleTypeName.AUCTIONANDSALE)
                .storage(10)
                .startingBid(20000.0)
                .productAttribute(Arrays.asList(att1, att2, att3, att4, att5))
                .variations(Arrays.asList(variations, variations1))
                .shipments(List.of(dhl, kerry, flash))
                .imagesPath(Arrays.asList("Image1", "Image2"))
                .build());

        Options options_dior = optionsRepository.save(Options.builder()
                .image("lipstick_dior01.png")
                .optionName("01")
                .stock(10)
                .price(3500)
                .build());

        Options options_dior1 = optionsRepository.save(Options.builder()
                .image("lipstick_dior02.png")
                .optionName("02")
                .stock(10)
                .price(3500)
                .build());

        Options options_dior2 = optionsRepository.save(Options.builder()
                .image("lipstick_dior03.png")
                .optionName("03")
                .stock(10)
                .price(3500)
                .build());

        Options options_dior3 = optionsRepository.save(Options.builder()
                .image("lipstick_dior04.png")
                .optionName("04")
                .stock(10)
                .price(3500)
                .build());

        Variations variations_dior = variationRepository.save(Variations.builder()
                .variationName("Color")
                .options(Arrays.asList(options_dior, options_dior1, options_dior2, options_dior3))
                .build());


        ProductAttribute att_dior1 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Dior")
                .attribute(brand)
                .build());
        ProductAttribute att_dior2 = productAttributeRepository.save(ProductAttribute.builder()
                .text("French")
                .attribute(country)
                .build());


        Product lipstick_dior = productRepository.save(Product.builder()
                .productName("lipstick dior")
                .details("lipstick dior")
                .category(CategoryName.BEAUTY)
                .productStatus(ProductStatus.ACTIVE)
                .shop(shop)
                .salePrice(3500.0)
                .saleTypeName(SaleTypeName.SALE)
                .storage(5)
                .productAttribute(Arrays.asList(att_dior1, att_dior2))
                .variations(List.of(variations_dior))
                .shipments(Arrays.asList(ninja, dhl))
                .imagesPath(Arrays.asList("lip-dior-all.jpg"))
                .build());

        Options options_chanel = optionsRepository.save(Options.builder()
                .image("Black.png")
                .optionName("Black")
                .stock(2)
                .build());

        Options options_chanel1 = optionsRepository.save(Options.builder()
                .image("White.png")
                .optionName("White")
                .stock(5)
                .build());

        Options options_chanel2 = optionsRepository.save(Options.builder()
                .optionName("S")
                .stock(2)
                .price(2500)
                .build());

        Options options_chanel3 = optionsRepository.save(Options.builder()
                .optionName("M")
                .stock(5)
                .price(3000)
                .build());

        Variations variations_chanel_color = variationRepository.save(Variations.builder()
                .variationName("Color")
                .options(Arrays.asList(options_chanel, options_chanel1))
                .build());
        Variations variations_chanel_size = variationRepository.save(Variations.builder()
                .variationName("Size")
                .options(Arrays.asList(options_chanel2, options_chanel3))
                .build());

        ProductAttribute att_chanel1 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Chanel")
                .attribute(brand)
                .build());
        ProductAttribute att_chanel2 = productAttributeRepository.save(ProductAttribute.builder()
                .text("French")
                .attribute(country)
                .build());
        ProductAttribute att_chanel3 = productAttributeRepository.save(ProductAttribute.builder()
                .text("S M")
                .attribute(size)
                .build());
        ProductAttribute att_chanel4 = productAttributeRepository.save(ProductAttribute.builder()
                .text("cutton 100%")
                .attribute(material)
                .build());

        Product dress_chanel = productRepository.save(Product.builder()
                .productName("Dress chanel")
                .details("Used dress chanel")
                .category(CategoryName.FASHION)
                .productStatus(ProductStatus.HIDDEN)
                .shop(shop)
                .salePrice(3500.0)
                .saleTypeName(SaleTypeName.SALE)
                .storage(5)
                .productAttribute(Arrays.asList(att_chanel1, att_chanel2, att_chanel3, att_chanel4))
                .variations(List.of(variations_chanel_size, variations_chanel_color))
                .shipments(Arrays.asList(kerry, ninja, dhl))
                .imagesPath(Arrays.asList("dress-chanel.jpg"))
                .build());


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
                .options(Arrays.asList(opt_bodycon_Dress))
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

        Product bodycon_Dress = productRepository.save(Product.builder()
                .productName("Twist Front Cut Out Ruched Bodycon Dress")
                .details("Twist Front Cut Out Ruched Bodycon Dress")
                .category(CategoryName.FASHION)
                .productStatus(ProductStatus.ACTIVE)
                .shop(shop)
                .saleTypeName(SaleTypeName.AUCTION)
                .auctionPeriod(5)
                .nextAuction(2)
                .shop(shop2)
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


        Options opt_floral_Dress_s = optionsRepository.save(Options.builder()
                .optionName("S")
                .stock(2)
                .price(130)
                .build());

        Options opt_floral_Dress_m = optionsRepository.save(Options.builder()
                .optionName("M")
                .stock(5)
                .price(130)
                .build());

        Options opt_floral_Dress_l = optionsRepository.save(Options.builder()
                .optionName("L")
                .stock(5)
                .price(130)
                .build());

        Variations var_floral_Dress_size = variationRepository.save(Variations.builder()
                .variationName("Size")
                .options(Arrays.asList(opt_floral_Dress_s, opt_floral_Dress_m, opt_floral_Dress_l))
                .build());

        ProductAttribute att_floral_Dress1 = productAttributeRepository.save(ProductAttribute.builder()
                .text("SHEIN")
                .attribute(brand)
                .build());
        ProductAttribute att_floral_Dress2 = productAttributeRepository.save(ProductAttribute.builder()
                .text("China")
                .attribute(country)
                .build());
        ProductAttribute att_floral_Dress5 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Short")
                .attribute(weight)
                .build());
        ProductAttribute att_floral_Dress3 = productAttributeRepository.save(ProductAttribute.builder()
                .text("S M L")
                .attribute(size)
                .build());
        ProductAttribute att_floral_Dress4 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Polyester")
                .attribute(material)
                .build());

        Product floral_Dress = productRepository.save(Product.builder()
                .productName("SHEIN Notched Neck Lettuce Trim Daisy Floral Dress")
                .details("Lettuce Trim" +
                        "Color: Black")
                .category(CategoryName.FASHION)
                .productStatus(ProductStatus.ACTIVE)
                .shop(shop)
                .saleTypeName(SaleTypeName.SALE)
                .shop(shop2)
                .salePrice(200.0)
                .storage(10)
                .productAttribute(Arrays.asList(att_floral_Dress1, att_floral_Dress2, att_floral_Dress3, att_floral_Dress4, att_floral_Dress5))
                .variations(List.of(var_floral_Dress_size))
                .shipments(List.of(jandt))
                .imagesPath(Arrays.asList("https://img.ltwebstatic.com/images3_pi/2022/04/18/16502517601737269fc448aa97a14810be3d41fccc_thumbnail_600x.webp",
                        "https://img.ltwebstatic.com/images3_pi/2022/04/18/16502517703654c7f1593e30f5b085e2deb3e4b5a0_thumbnail_600x.webp"))
                .build());


        Options opt_LIP_GLOW1 = optionsRepository.save(Options.builder()
                .image("opt_LIP_GLOW0134.png")
                .optionName("012")
                .stock(10)
                .price(3500)
                .build());

        Options opt_LIP_GLOW2 = optionsRepository.save(Options.builder()
                .image("opt_LIP_GLOW013.png")
                .optionName("013")
                .stock(10)
                .price(3500)
                .build());

        Variations var_LIP_GLOW = variationRepository.save(Variations.builder()
                .variationName("Color")
                .options(Arrays.asList(opt_LIP_GLOW1, opt_LIP_GLOW2))
                .build());


        ProductAttribute att_LIP_GLOW1 = productAttributeRepository.save(ProductAttribute.builder()
                .text("Dior")
                .attribute(brand)
                .build());
        ProductAttribute att_LIP_GLOW2 = productAttributeRepository.save(ProductAttribute.builder()
                .text("French")
                .attribute(country)
                .build());


        Product LIP_GLOW = productRepository.save(Product.builder()
                .productName("LIP GLOW")
                .details("Color-Awakening Lip Balm - 24h* Hydration - 97%** Natural-Origin Ingredient")
                .category(CategoryName.BEAUTY)
                .productStatus(ProductStatus.ACTIVE)
                .shop(shop)
                .salePrice(1200.0)
                .saleTypeName(SaleTypeName.SALE)
                .storage(2)
                .productAttribute(Arrays.asList(att_LIP_GLOW1, att_LIP_GLOW2))
                .variations(List.of(var_LIP_GLOW))
                .shipments(Arrays.asList(ninja))
                .imagesPath(List.of("https://cdn.shopify.com/s/files/1/0463/7432/2326/products/Y0996214_C021400015_E01_GHC_400x.jpg?v=1643651016"))
                .build());

    }


}
