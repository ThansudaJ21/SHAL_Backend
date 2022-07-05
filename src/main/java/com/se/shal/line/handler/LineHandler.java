package com.se.shal.line.handler;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.*;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.*;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Icon;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.message.quickreply.QuickReplyItem;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.se.shal.line.config.LineInitComponent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@LineMessageHandler
@Slf4j
public class LineHandler {
    int state = 1;

    @Autowired
    LineInitComponent lineInitComponent;
    @Autowired
    private LineMessagingClient lineMessagingClient;

    int counter = 1;
    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String inputText = event.getMessage().getText();
        switch (inputText){
            case "Tracking" ->{
                    List<QuickReplyItem> items = Arrays.<QuickReplyItem>asList(
                            QuickReplyItem.builder()
                                    .action(LocationAction.withLabel("Tracking"))
                                    .build()
                    );

                    final QuickReply quickReply = QuickReply.items(items);

                    return TextMessage
                            .builder()
                            .text("Tracking")
                            .quickReply(quickReply)
                            .build();
            }
        }
        return TextMessage.builder().text(
                String.format("%d: %s",counter++, event.getMessage().getText())).build();
    }

    private void reply(String replyToken, Message message) {
        reply(replyToken, singletonList(message));
    }

    private void reply(String replyToken, List<Message> messages) {
        reply(replyToken, messages, false);
    }
    private void reply(@NonNull String replyToken,
                       @NonNull List<Message> messages,
                       boolean notificationDisabled) {
        try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages, notificationDisabled))
                    .get();
            log.info("Sent messages: {}", apiResponse);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public FlexMessage getMessage(String text){
        final Image heroBlock =
                Image.builder()
                        .url(URI.create("https://static.bkkmenu.com/files/2021/06/Polli_scafe-21-1005x670.jpg"))
                        .size(Image.ImageSize.FULL_WIDTH)
                        .aspectRatio(Image.ImageAspectRatio.R20TO13)
                        .aspectMode(Image.ImageAspectMode.Cover)
                        .action(new URIAction("label", URI.create("http://example.com"), null))
                        .build();
        Box bodyBlock = createBodyBlock();



                Bubble bubble =
                Bubble.builder()
                        .hero(heroBlock)
                        .body(bodyBlock).build();
        return new FlexMessage("flex message",bubble);
    }
    private Box createBodyBlock() {
        final Text title =
                Text.builder()
                        .text("Cafe บ้านเรา")
                        .weight(Text.TextWeight.BOLD)
                        .size(FlexFontSize.XL)
                        .build();

        final Box review = createReviewBox();

        final Box info = createInfoBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(title, review, info))
                .build();
    }
    private Box createInfoBox() {
        final Box place = Box
                .builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder()
                                .text("Place")
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM)
                                .flex(1)
                                .build(),
                        Text.builder()
                                .text("Shinjuku, Tokyo")
                                .wrap(true)
                                .color("#666666")
                                .size(FlexFontSize.SM)
                                .flex(5)
                                .build()
                ))
                .build();
        final Box time =
                Box.builder()
                        .layout(FlexLayout.BASELINE)
                        .spacing(FlexMarginSize.SM)
                        .contents(asList(
                                Text.builder()
                                        .text("Time")
                                        .color("#aaaaaa")
                                        .size(FlexFontSize.SM)
                                        .flex(1)
                                        .build(),
                                Text.builder()
                                        .text("10:00 - 23:00")
                                        .wrap(true)
                                        .color("#666666")
                                        .size(FlexFontSize.SM)
                                        .flex(5)
                                        .build()
                        ))
                        .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(asList(place, time))
                .build();
    }

    private Box createReviewBox() {
        final Icon goldStar =
                Icon.builder().size(FlexFontSize.SM).url(URI.create("https://example.com/gold_star.png")).build();
        final Icon grayStar =
                Icon.builder().size(FlexFontSize.SM).url(URI.create("https://example.com/gray_star.png")).build();
        final Text point =
                Text.builder()
                        .text("4.0")
                        .size(FlexFontSize.SM)
                        .color("#999999")
                        .margin(FlexMarginSize.MD)
                        .flex(0)
                        .build();

        return Box.builder()
                .layout(FlexLayout.BASELINE)
                .margin(FlexMarginSize.MD)
                .contents(asList(goldStar, goldStar, goldStar, goldStar, grayStar, point))
                .build();
    }
    String data = "";
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
