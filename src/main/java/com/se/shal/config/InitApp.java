package com.se.shal.config;

import com.se.shal.line.config.LineInitComponent;
import com.se.shal.product.entity.*;
import com.se.shal.product.entity.enumeration.*;
import com.se.shal.product.repository.*;
import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;
import com.se.shal.security.entity.User;
import com.se.shal.security.repository.AuthorityRepository;
import com.se.shal.security.repository.UserRepository;
import com.se.shal.shop.entity.*;
import com.se.shal.shop.repository.FailureReasonListRepository;
import com.se.shal.shop.repository.FailureReasonRepository;
import com.se.shal.shop.repository.ShopRepository;
import com.se.shal.shop.repository.ShopStatusRepository;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;
import com.se.shal.trading.repository.AuctionRepository;
import com.se.shal.trading.repository.ProductOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;
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
    LineInitComponent lineInitComponent;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuctionRepository auctionRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        removeRedisCache();
        setAuthority();
        lineInitComponent.initLineApp();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .firstname("Thananya")
                .lastname("Keawchoi")
                .authorities(List.of(roleBuyer, roleAdmin))
                .email("thananya53@gmail.com")
                .userId("U6f88235e56c155f9ae2223b0109d7e67")
                .phoneNumber("095-4535854")
                .pictureUrl("https://profile.line-scdn.net/0hAeMwTCdcHkpgDjfCgmVgNRBeHSBDf0dYTm1YKFRcR39VPlAZRWtQKAUKQnJfaVtLGGFTfgELE3tsHWksfljifmc-QH1ZPF0aSG9Vrg")
                .displayName("Nong.53")
                .enabled(true)
                .build();
        userRepository.save(user);
        User user2 = User.builder()
                .firstname("Great")
                .lastname("Jelo")
                .authorities(List.of(roleSeller))
                .email("greateNa@gmail.com")
                .userId("U6f88fghe5e56c155f9aee23b0109d7e67")
                .phoneNumber("098-5478547")
                .pictureUrl("https://profile.line-scdn.net/0hAeMwTCdcHkpgDjfCgmVgNRBeHSBDf0dYTm1YKFRcR39VPlAZRWtQKAUKQnJfaVtLGGFTfgELE3tsHWksfljifmc-REQV89218448")
                .displayName("great")
                .enabled(true)
                .build();
        userRepository.save(user2);
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
                .email("Thansuda@gmail.com")
                .promptPay("0954475249")
                .idCard("1234567891234")
                .shopStatus(ShopStatusName.DISABLE)
                .promptPayType(PromptPayType.PHONE)
                .shopLogoImagePath("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20215820811-shopLogo1.png?generation=1655305100953281&alt=media")
                .selfiePhotoWithIdCardPath("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20215941465-selfiePhotoWithIdCardPath1.png?generation=1655305181566480&alt=media")
                .shopAddress(ShopAddress.builder()
                        .district("meachan")
                        .houseNumber("322")
                        .moo("12").subDistrict("mea kham")
                        .postalCode("57240")
                        .province("chiang rai")
                        .build())
                .build());
        shop.setUser(user);
        Shop shop2 = shopRepository.save(Shop.builder()
                .shopName("Patteeda shop")
                .email("Patteera@gmail.com")
                .promptPay("0953348895123")
                .idCard("1234567891234")
                .shopStatus(ShopStatusName.ENABLE)
                .promptPayType(PromptPayType.NATIONAL_ID)
                .shopLogoImagePath("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20220538725-shopLogo2.png?generation=1655305538800881&alt=media")
                .selfiePhotoWithIdCardPath("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20220520826-selfiePhotoWithIdCardPath2.png?generation=1655305520955480&alt=media")
                .shopAddress(ShopAddress.builder()
                        .district("Fang")
                        .houseNumber("322")
                        .moo("12").subDistrict("Wiang")
                        .postalCode("57240")
                        .province("chiang mai")
                        .build())
                .build());
        shop2.setUser(user2);
        Shop shop3 = shopRepository.save(Shop.builder()
                .shopName("GameZa shop")
                .email("GameZa@gmail.com")
                .promptPay("088555812512345")
                .idCard("1111111111111")
                .shopStatus(ShopStatusName.DISABLE)
                .promptPayType(PromptPayType.E_WALLET)
                .shopLogoImagePath("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20220146496-shopLogo3.png?generation=1655305306554049&alt=media")
                .selfiePhotoWithIdCardPath("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20220131035-selfiePhotoWithIdCardPath3.jpg?generation=1655305291158439&alt=media")
                .shopAddress(ShopAddress.builder()
                        .district("San kam pang")
                        .houseNumber("56")
                        .moo("5")
                        .subDistrict("San kam pang")
                        .postalCode("50130")
                        .province("chiang mai")
                        .build())
                .build());
        shop3.setUser(user2);
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
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20230345195-iphone-13-green.jpg?generation=1655309025198821&alt=media")
                .optionName("Green")
                .stock(20)
                .price(29000)
                .build());

        Options options3 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20230400845-iphone-13-red.jpg?generation=1655309040876595&alt=media")
                .optionName("Red")
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
                .shop(shop)
                .salePrice(35000.0)
                .saleTypeName(SaleTypeName.AUCTIONANDSALE)
                .storage(options.getStock() + options1.getStock() + options2.getStock() + options3.getStock())
                .productAttribute(Arrays.asList(att1, att2, att3, att4, att5))
                .variations(Arrays.asList(variations, variations1))
                .shipments(List.of(dhl, kerry, flash))
                .imagesPath(Arrays.asList("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20230114117-iphone-13.jpg?generation=1655308874417771&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20230153663-iphone-13-2.jpg?generation=1655308913808125&alt=media"))
                .build());

        Auction auction_product = Auction.builder()
                .auctionPeriod(2)
                .nextAuction(1)
                .timeUnitForAuctionPeriod(ChronoUnit.MINUTES)
                .timeUnitForNextAuction(ChronoUnit.MINUTES)
                .isNotification(false)
                .auctionTimes(product.getStorage())
                .startingBid(20000.0)
                .product(product)
                .build();
        auctionRepository.save(auction_product);
        Options options_dior = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012651809-lip-dior-1.jpg?generation=1655317611606883&alt=media")
                .optionName("01")
                .stock(10)
                .price(3500)
                .build());

        Options options_dior1 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012706879-lip-dior-2.jpg?generation=1655317626665394&alt=media")
                .optionName("02")
                .stock(10)
                .price(3500)
                .build());

        Options options_dior2 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012721835-lip-dior-3.jpg?generation=1655317641592829&alt=media")
                .optionName("03")
                .stock(10)
                .price(3500)
                .build());

        Options options_dior3 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012739870-lip-dior-4.jpg?generation=1655317659632874&alt=media")
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
                .storage(options_dior.getStock() + options_dior2.getStock() + options_dior1.getStock() + options_dior3.getStock())
                .productAttribute(Arrays.asList(att_dior1, att_dior2))
                .variations(List.of(variations_dior))
                .shipments(Arrays.asList(ninja, dhl))
                .imagesPath(Arrays.asList("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20011424660-lip-dior-all.jpg?generation=1655316866602294&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012454363-lip-dior-all2.jpg?generation=1655317495096511&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012511949-lip-dior-all3.jpg?generation=1655317511876856&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012526565-lip-dior-all4.jpg?generation=1655317526386419&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20012544539-lip-dior-all5.jpg?generation=1655317544410245&alt=media"))
                .build());

        Options options_chanel = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20014531610-dress-chanel-black.jpg?generation=1655318731518850&alt=media")
                .optionName("Black")
                .stock(2)
                .build());

        Options options_chanel1 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20014550961-dress-chanel-white.jpg?generation=1655318750807129&alt=media")
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
                .storage(options_chanel.getStock() + options_chanel1.getStock() + options_chanel2.getStock() + options_chanel3.getStock())
                .productAttribute(Arrays.asList(att_chanel1, att_chanel2, att_chanel3, att_chanel4))
                .variations(List.of(variations_chanel_size, variations_chanel_color))
                .shipments(Arrays.asList(kerry, ninja, dhl))
                .imagesPath(Arrays.asList("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20014513944-dress-chanel.jpg?generation=1655318713825088&alt=media"))
                .build());


        Options opt_bodycon_Dress = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20014938169-twis-multicolor.png?generation=1655318978027907&alt=media")
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
                .shop(shop2)
                .salePrice(200.0)
                .storage(opt_bodycon_Dress.getStock() + opt_bodycon_Dress_s.getStock() + opt_bodycon_Dress_m.getStock() + opt_bodycon_Dress_l.getStock())
                .productAttribute(Arrays.asList(att_bodycon_Dress1, att_bodycon_Dress2, att_bodycon_Dress3, att_bodycon_Dress4, att_bodycon_Dress5))
                .variations(List.of(var_bodycon_Dress_color, var_bodycon_Dress_size))
                .shipments(Arrays.asList(kerry, ninja, dhl))
                .imagesPath(Arrays.asList("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20015405902-twis-2.jpg?generation=1655319245728525&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-15%20232305548-twis.jpg?generation=1655310185606336&alt=media"))
                .build());

        Auction auction_bodycon_Dress = Auction.builder()
                .auctionPeriod(2)
                .nextAuction(1)
                .timeUnitForAuctionPeriod(ChronoUnit.MINUTES)
                .isNotification(false)
                .timeUnitForNextAuction(ChronoUnit.MINUTES)
                .startingBid(20000.0)
                .auctionTimes(bodycon_Dress.getStorage())
                .product(bodycon_Dress)
                .build();
        auctionRepository.save(auction_bodycon_Dress);
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
                .details("Lettuce Trim Color: Black")
                .category(CategoryName.FASHION)
                .productStatus(ProductStatus.ACTIVE)
                .shop(shop)
                .saleTypeName(SaleTypeName.SALE)
                .shop(shop2)
                .salePrice(200.0)
                .storage(opt_floral_Dress_s.getStock() + opt_floral_Dress_m.getStock() + opt_floral_Dress_l.getStock())
                .productAttribute(Arrays.asList(att_floral_Dress1, att_floral_Dress2, att_floral_Dress3, att_floral_Dress4, att_floral_Dress5))
                .variations(List.of(var_floral_Dress_size))
                .shipments(List.of(jandt))
                .imagesPath(Arrays.asList("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20015634517-daisy-1.jpg?generation=1655319394390071&alt=media",
                        "https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20015654048-daisy-2.jpg?generation=1655319413913978&alt=media"))
                .build());


        Options opt_LIP_GLOW1 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20020400152-lip-glow-012.jpg?generation=1655319839952333&alt=media")
                .optionName("012")
                .stock(10)
                .price(3500)
                .build());

        Options opt_LIP_GLOW2 = optionsRepository.save(Options.builder()
                .image("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20020528495-lip-glow-013.jpg?generation=1655319928569653&alt=media")
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
                .storage(opt_LIP_GLOW1.getStock() + opt_LIP_GLOW2.getStock())
                .productAttribute(Arrays.asList(att_LIP_GLOW1, att_LIP_GLOW2))
                .variations(List.of(var_LIP_GLOW))
                .shipments(Arrays.asList(ninja))
                .imagesPath(List.of("https://storage.googleapis.com/download/storage/v1/b/shal-f28ac.appspot.com/o/2565-06-16%20020156160-lip-glow.jpg?generation=1655319715951935&alt=media"))
                .build());


        ProductOrder p = ProductOrder.builder()
                .totalPrice(200.00)
                .quantity(2)
                .options(options)
                .dateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.BUY)
                .paymentStatus(PaymentStatus.UNPAID)
                .users(user)
                .products(product)
                .userAddress(null)
                .build();
        productOrderRepository.save(p);
    }

    @Autowired
    AuthorityRepository authorityRepository;
    Authority roleAdmin, roleBuyer, roleSeller, roleUser;

    @Autowired
    RedisTemplate redisTemplate;

    private void setAuthority() {
        roleAdmin = authorityRepository.save(Authority.builder()
                .name(AuthorityName.ADMIN)
                .build());
        roleBuyer = authorityRepository.save(Authority.builder()
                .name(AuthorityName.BUYER)
                .build());
        roleSeller = authorityRepository.save(Authority.builder()
                .name(AuthorityName.SELLER)
                .build());
        roleUser = authorityRepository.save(Authority.builder()
                .name(AuthorityName.USER)
                .build());
    }


    void removeRedisCache() {
        Set<String> keys = redisTemplate.keys(String.format("%s::*", "postData"));
        redisTemplate.delete(keys);
    }
}
