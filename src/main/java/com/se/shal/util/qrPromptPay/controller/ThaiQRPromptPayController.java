package com.se.shal.util.qrPromptPay.controller;

import com.se.shal.shop.dao.ShopDao;
import com.se.shal.trading.dao.ProductOrderDao;
import com.se.shal.util.qrPromptPay.component.QRPromptPay;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
public class ThaiQRPromptPayController {
    @Autowired
    ShopDao shopDao;
    @Autowired
    ProductOrderDao productOrderDao;
    @Autowired
    QRPromptPay qRcode;

    @SneakyThrows
    @GetMapping(value = "/qrcode/{id}/{productOrder}", produces = "image/png")
    BufferedImage getQrCode(@PathVariable String id, @PathVariable String productOrder) {
        return qRcode.createQRCode(id, productOrder);
    }
}

