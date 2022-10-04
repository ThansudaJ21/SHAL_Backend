package com.se.shal.util.qrPromptPay.controller;

import com.google.zxing.WriterException;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.PromptPayType;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.dao.ProductOrderDao;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.util.qrPromptPay.service.ThaiQRPromptPay;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class ThaiQRPromptPayController {
    @Autowired
    ShopDao shopDao;
    @Autowired
    ProductOrderDao productOrderDao;

    @GetMapping(value = "/qrcode/{id}/{productOrder}", produces = "image/png")
    void getQrCode(@PathVariable String id, @PathVariable String productOrder) throws IOException, WriterException {
        Shop shop = shopDao.findById(Long.valueOf(id));
        ProductOrder p = productOrderDao.findById(Long.valueOf(productOrder));
        if (shop.getPromptPayType().equals(PromptPayType.PHONE)) {
            ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                    .staticQR()
                    .creditTransfer()
                    .mobileNumber(shop.getPromptPay())
                    .amount(new BigDecimal(p.getTotalPrice().toString()))
                    .build();
            File tmpFile = new File("tmp/qr-tmp-file.png");
            qr.draw(500, 500, tmpFile);
        } else if (shop.getPromptPayType().equals(PromptPayType.NATIONAL_ID)) {
            ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                    .staticQR()
                    .creditTransfer()
                    .nationalId(shop.getPromptPay())
                    .amount(new BigDecimal(p.getTotalPrice().toString()))
                    .build();
            File tmpFile = new File("tmp/qr-tmp-file.png");
            qr.draw(500, 500, tmpFile);
        } else {
            ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                    .staticQR()
                    .creditTransfer()
                    .eWalletId(shop.getPromptPay())
                    .amount(new BigDecimal(p.getTotalPrice().toString()))
                    .build();
            File tmpFile = new File("tmp/qr-tmp-file.png");
            qr.draw(500, 500, tmpFile);
        }
    }
}

