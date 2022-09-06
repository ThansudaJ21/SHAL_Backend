package com.se.shal.line.handler;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.se.shal.line.component.LoserFlexMessageSupplier;
import com.se.shal.line.component.OverTakenFlexMessageSupplier;
import com.se.shal.line.component.SellerFlexMessageSupplier;
import com.se.shal.line.component.WinnerFlexMessageSupplier;
import com.se.shal.line.config.LineInitComponent;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.trading.dao.AuctionDao;
import com.se.shal.trading.dao.BidDao;
import com.se.shal.trading.entity.Bid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutionException;


@RequiredArgsConstructor
@LineMessageHandler
@Slf4j
public class LineHandler {


    final LineInitComponent lineInitComponent;
    final LineMessagingClient lineMessagingClient;
    final UserDao userDao;
    final AuctionDao auctionDao;
    final BidDao bidDao;
    @Value("${liff.frontendProductUrl}")
    String productDetailsUrl;
    @Value("${line.bot.channel-token}")
    String channelAccessToken;

    private void pushMessage(Bid bid, FlexMessage flexMessage) {
        final PushMessage pushMessage = new PushMessage(
                bid.getUser().getUserId(),
                flexMessage);

        BotApiResponse botApiResponse = null;
        try {
            botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(botApiResponse);
    }

    private void pushMessage(User user, FlexMessage flexMessage) {
        final PushMessage pushMessage = new PushMessage(
                user.getUserId(),
                flexMessage);

        BotApiResponse botApiResponse = null;
        try {
            botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(botApiResponse);
    }

    //
    public void pushMessageForAuctionWinner(Bid winner) {
        final FlexMessage flexMessage = new WinnerFlexMessageSupplier().get(winner);
        pushMessage(winner, flexMessage);
    }

    public void pushMessageForAuctionLoser(Bid loser) {
        final FlexMessage flexMessage = new LoserFlexMessageSupplier().get(loser);
        pushMessage(loser, flexMessage);
    }

    public void pushMessageForSeller(User seller, Bid bid) {
        final FlexMessage flexMessage = new SellerFlexMessageSupplier().get(bid);
        pushMessage(seller, flexMessage);
    }


    public void pushMessageForOverTaken(Bid overtaken) {
        final FlexMessage flexMessage = new OverTakenFlexMessageSupplier().get(overtaken, productDetailsUrl);
        pushMessage(overtaken, flexMessage);
    }

}
