package com.se.shal.line.component;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.action.URIAction.AltUri;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.component.Button.ButtonHeight;
import com.linecorp.bot.model.message.flex.component.Button.ButtonStyle;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.component.Image.ImageSize;
import com.linecorp.bot.model.message.flex.component.Text.TextWeight;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.*;
import com.se.shal.trading.entity.Bid;

import java.net.URI;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class OverTakenFlexMessageSupplier {

    public FlexMessage get(Bid bid) {
        Bubble bubble = createBubble(bid);
        return FlexMessage.builder()
                .altText("YOU WERE OVERTAKEN")
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
                        .action(new URIAction("label", URI.create("https://example.com"),
                                new AltUri(URI.create("https://example.com/desktop"))))
                        .build();

        final Box bodyBlock = createBodyBlock(bid);
        final Box footerBlock = createFooterBlock();
        return Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
    }

    private static Box createBodyBlock(Bid bid) {
        final Text title =
                Text.builder()
                        .text("YOU WERE OVERTAKEN")
                        .style(Text.TextStyle.NORMAL)
                        .position(FlexPosition.RELATIVE)
                        .align(FlexAlign.CENTER)
                        .weight(TextWeight.BOLD)
                        .style(Text.TextStyle.NORMAL)
                        .wrap(true)
                        .size(FlexFontSize.XXL)
                        .color("#cf2f48")
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
                                .color("#7d1e35")
                                .size(FlexFontSize.SM)
                                .flex(4)
                                .maxLines(10)
                                .weight(Text.TextWeight.BOLD)
                                .build(),
                        Text.builder()
                                .text(bid.getAuction().getProduct().getProductName())
                                .wrap(true)
                                .color("#7d1e35")
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
                                .text("Current Bid:")
                                .color("#7d1e35")
                                .size(FlexFontSize.SM)
                                .flex(4)
                                .maxLines(10)
                                .weight(Text.TextWeight.BOLD)
                                .build(),
                        Text.builder()
                                .text(String.format("%s THB", bid.getAuction().getMaxBidding().getBidAmount().toString()))
                                .wrap(true)
                                .color("#7d1e35")
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

    private static Box createFooterBlock() {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
        final Button callAction = Button
                .builder()
                .style(Button.ButtonStyle.PRIMARY)
                .color("#cf2f48")
                .height(Button.ButtonHeight.SMALL)
                .action(new URIAction("CONTINUE AUCTION", URI.create("https://www.google.com"),
                        new URIAction.AltUri(URI.create("https://www.google.com"))))
                .build();
        final Separator separator = Separator.builder().build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(asList(spacer, callAction, separator))
                .build();
    }
}
