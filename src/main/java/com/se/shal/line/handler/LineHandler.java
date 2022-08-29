package com.se.shal.line.handler;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
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

    @Value("${line.bot.channel-token}")
    String channelAccessToken;

    private void pushMessage(Bid bid, TextMessage textMessage) {
        final PushMessage pushMessage = new PushMessage(
                bid.getUser().getUserId(),
                textMessage);

        BotApiResponse botApiResponse = null;
        try {
            botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(botApiResponse);
    }

    private void pushMessage(User user, TextMessage textMessage) {
        final PushMessage pushMessage = new PushMessage(
                user.getUserId(),
                textMessage);

        BotApiResponse botApiResponse = null;
        try {
            botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(botApiResponse);
    }

    public void pushMessageForAuctionWinner(Bid winner) {
        final TextMessage textMessage = new TextMessage(
                String.format("You win %s THB \n %s", winner.getBidAmount(), winner.getAuction().getProduct().getProductName()));
        pushMessage(winner, textMessage);
    }

    public void pushMessageForAuctionLoser(Bid loser) {
        final TextMessage textMessage = new TextMessage(
                String.format("You lose auction for %s", loser.getAuction().getProduct().getProductName()));
        pushMessage(loser, textMessage);
    }

    public void pushMessageForSeller(User seller, Bid bid) {
        final TextMessage textMessage = new TextMessage(
                String.format("%s wins %s THB for \n %s", bid.getUser().getFirstname(), bid.getBidAmount(), bid.getAuction().getProduct().getProductName()));
        pushMessage(seller, textMessage);
    }


    public void pushMessageForOverTaken(Bid overtaken) {
        final TextMessage textMessage = new TextMessage(
                String.format("You were overtaken for %s", overtaken.getAuction().getProduct().getProductName()));
        pushMessage(overtaken, textMessage);
    }
}
