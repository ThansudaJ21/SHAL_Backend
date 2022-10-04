package com.se.shal.util.qrPromptPay.component;

import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.PromptPayType;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.dao.ProductOrderDao;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.util.qrPromptPay.service.ThaiQRPromptPay;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

@Component
public class QRPromptPay {
    @Autowired
    ShopDao shopDao;
    @Autowired
    ProductOrderDao productOrderDao;

    @SneakyThrows
    public BufferedImage createQRCode(String shopId, String productOrderId) {
        Shop shop = shopDao.findById(Long.valueOf(shopId));
        ProductOrder p = productOrderDao.findById(Long.valueOf(productOrderId));
        if (shop.getPromptPayType().equals(PromptPayType.PHONE)) {
            ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                    .staticQR()
                    .creditTransfer()
                    .mobileNumber(shop.getPromptPay())
                    .amount(new BigDecimal(p.getTotalPrice().toString()))
                    .build();
            return qr.draw(500, 500);
        } else if (shop.getPromptPayType().equals(PromptPayType.NATIONAL_ID)) {
            ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                    .staticQR()
                    .creditTransfer()
                    .nationalId(shop.getPromptPay())
                    .amount(new BigDecimal(p.getTotalPrice().toString()))
                    .build();
            return qr.draw(500, 500);
        } else {
            ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                    .staticQR()
                    .creditTransfer()
                    .eWalletId(shop.getPromptPay())
                    .amount(new BigDecimal(p.getTotalPrice().toString()))
                    .build();
            return qr.draw(500, 500);
        }
    }
}
