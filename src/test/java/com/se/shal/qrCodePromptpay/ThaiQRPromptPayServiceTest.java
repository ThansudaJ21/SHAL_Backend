package com.se.shal.qrCodePromptpay;

import com.google.zxing.WriterException;
import com.se.shal.util.qrPromptPay.service.ThaiQRPromptPay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ThaiQRPromptPayServiceTest {
    @Autowired
    ThaiQRPromptPay thaiQRPromptPay;

    @Test
    public void testDraw_thenSuccess() throws IOException, WriterException {

        ThaiQRPromptPay qr = new ThaiQRPromptPay.Builder()
                .staticQR()
                .creditTransfer()
                .nationalId("0123456789123")
                .amount(new BigDecimal("2.36"))
//                .nationalId
                .build();

        File tmpFile = new File("tmp/qr-tmp-file.png");
        qr.draw( 100, 100,tmpFile);
    }
}