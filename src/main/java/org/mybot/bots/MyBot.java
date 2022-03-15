package org.mybot.bots;

import lombok.SneakyThrows;
import org.mybot.response.OpenWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyBot extends TelegramLongPollingBot {
    private static final String BOT_USERNAME = "@user_open_wether_bot";
    private static final String BOT_TOKEN = "5105732887:AAEhXIuMUuwD_mF_v-a34eQP-SBcAxcin7o";
//    private String city;


//    public MyBot(@Value("${bot.BOT_TOKEN}") String BOT_TOKEN, @Value("${bot.BOT_USERNAME}") String BOT_USERNAME) {
//        this.BOT_TOKEN = BOT_TOKEN;
//        this.BOT_USERNAME = BOT_USERNAME;
//    }


    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String city = update.getMessage().getText();
        OpenWeather openWeather = new OpenWeather();

        String answer = openWeather.run(city);
        long chat_id = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chat_id));
        message.setText(answer);

        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
