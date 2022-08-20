package com.se.shal.line.handler;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.*;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
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
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.trading.dao.AuctionDao;
import com.se.shal.trading.dao.BidDao;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@RequiredArgsConstructor
@LineMessageHandler
@Slf4j
public class LineHandler {
    int state = 1;

    final LineInitComponent lineInitComponent;
    final LineMessagingClient lineMessagingClient;
    final UserDao userDao;
    final AuctionDao auctionDao;
    final BidDao bidDao;
    int counter = 1;

//    @EventMapping
//    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
//        System.out.println("event: " + event);
//        TextMessageContent message = event.getMessage();
//        return new TextMessage(message.getText());
//    }
//
//    private void handleTextForAuctionResultWinner(String replyToken, Event event, TextMessageContent content) {
//        Auction auction = auctionDao.findById(1L);
//        final TextMessage textMessage = new TextMessage("You are winner");
//        final PushMessage pushMessage = new PushMessage(
//                auction.getMaxBidding().getUser().getUserId(),
//                textMessage);
//
//        final BotApiResponse botApiResponse;
//        try {
//            botApiResponse = client.pushMessage(pushMessage).get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//            return;
//        }
//
//    }


}
