package com.se.shal.line.component;


import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.component.Image.ImageSize;
import com.linecorp.bot.model.message.flex.component.Text.TextWeight;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.*;
import com.se.shal.trading.entity.Bid;

import java.net.URI;

import static java.util.Arrays.asList;

public class LoserFlexMessageSupplier {

    public FlexMessage get(Bid bid) {
        Bubble bubble = createBubble(bid);
        return FlexMessage.builder()
                .altText("YOU LOSE AUCTION")
                .contents(bubble)
                .build();
    }

    private static Bubble createBubble(Bid bid) {
        final Image heroBlock =
                Image.builder()
                        .url(URI.create(bid.getAuction().getProduct().getImagesPath().get(0)))
                        .size(ImageSize.FULL_WIDTH)
                        .aspectRatio(ImageAspectRatio.R20TO13)
                        .aspectMode(ImageAspectMode.Cover)
                        .build();

        final Box bodyBlock = createBodyBlock(bid);
        return Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .build();
    }

    private static Box createBodyBlock(Bid bid) {
        final Text title =
                Text.builder()
                        .text("YOU LOSE AUCTION")
                        .style(Text.TextStyle.NORMAL)
                        .position(FlexPosition.RELATIVE)
                        .align(FlexAlign.CENTER)
                        .weight(TextWeight.BOLD)
                        .style(Text.TextStyle.NORMAL)
                        .wrap(true)
                        .size(FlexFontSize.XL)
                        .color("#7a330d")
                        .build();
        final Box info = createInfoBox(bid);

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(title, info))
                .build();
    }

    private static Box createInfoBox(Bid bid) {
        final Box productName = Box
                .builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder()
                                .text("Product Name:")
                                .color("#943e0c")
                                .size(FlexFontSize.SM)
                                .flex(4)
                                .maxLines(10)
                                .weight(TextWeight.BOLD)
                                .build(),
                        Text.builder()
                                .text(bid.getAuction().getProduct().getProductName())
                                .wrap(true)
                                .color("#b75206")
                                .size(FlexFontSize.SM)
                                .flex(5)
                                .maxLines(10)
                                .build()
                ))
                .build();
        final Box currentBid = Box
                .builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder()
                                .text("Highest Bid:")
                                .color("#943e0c")
                                .size(FlexFontSize.SM)
                                .flex(4)
                                .maxLines(10)
                                .weight(TextWeight.BOLD)
                                .build(),
                        Text.builder()
                                .text(String.format("%s THB", bid.getAuction().getMaxBidding().getBidAmount().toString()))
                                .wrap(true)
                                .color("#b75206")
                                .size(FlexFontSize.SM)
                                .flex(5)
                                .maxLines(10)
                                .build()
                ))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(asList(productName, currentBid))
                .build();
    }

}