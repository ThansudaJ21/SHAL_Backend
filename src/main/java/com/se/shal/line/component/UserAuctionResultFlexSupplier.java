package com.se.shal.line.component;

import com.google.common.base.Strings;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import com.se.shal.trading.entity.Bid;
import org.jetbrains.annotations.NotNull;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UserAuctionResultFlexSupplier {

    public static Bubble get(Bid winner) {
        final Image heroBlock =
                Image.builder()
                        .url(URI.create(winner.getAuction().getProduct().getImagesPath().get(0)))
                        .size(Image.ImageSize.FULL_WIDTH)
                        .aspectRatio(Image.ImageAspectRatio.R20TO13)
                        .aspectMode(Image.ImageAspectMode.Cover)
                        .action(new URIAction("label", URI.create("http://example.com"), null))
                        .build();
        Box bodyBlock = getBodyBox(winner);
        Button footerBlock = Button.builder()
                .action(new URIAction("label", URI.create("http://example.com"), null))
                .build();

        return Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock).build();
    }

    private static Bubble createBubble(Bid winner) {
        Box header = null;
        Box body = getBodyBox(winner);

        Box footer = null;
        String imageUrl = null;
        if (winner.getAuction().getProduct().getImagesPath() != null && winner.getAuction().getProduct().getImagesPath().size() > 0) {
            int randomIndex = (new Random()).nextInt(winner.getAuction().getProduct().getImagesPath().size());
            imageUrl = winner.getAuction().getProduct().getImagesPath().get(randomIndex);
        }
        if (Strings.isNullOrEmpty(imageUrl)) {
            imageUrl = "https://img.freepik.com/free-photo/mand-holding-cup_1258-340.jpg?t=st=1653938199~exp=1653938799~hmac=d2ff1ee9e18d5022a2dfefa056b03867572b31194bfe764577d0c05f3886f918&w=1380";

        }


        Image heroBlock = Image.builder()
                .url(URI.create(imageUrl))
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectMode(Image.ImageAspectMode.Cover)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .build();
        String url = ("https://www.google.com/");

        return Bubble.builder()
                .body(body)
                .hero(heroBlock)
                .action(new URIAction("go to follow up", URI.create(url), null))
                .build();
    }

    private static Box getBodyBox(@NotNull Bid winner) {

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(
                        Text.builder()
                                .size(FlexFontSize.SM)
                                .text(winner.getAuction().getProduct().getProductName())
                                .weight(Text.TextWeight.BOLD)
                                .wrap(true)
                                .build()
                        ,
                        Box.builder()
                                .layout(FlexLayout.VERTICAL)
                                .content(Box.builder()
                                        .layout(FlexLayout.BASELINE)
                                        .spacing(FlexMarginSize.SM)
                                        .content(Text.builder()
                                                .text(winner.getAuction().getProduct().getProductName())
                                                .wrap(true)
                                                .flex(5)
                                                .size(FlexFontSize.SM)
                                                .color("#8c8c8c")
                                                .build())
                                        .build())

                                .build()
                ))
                .build();
    }

}
