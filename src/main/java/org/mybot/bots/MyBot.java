package org.mybot.bots;

import lombok.SneakyThrows;
import org.mybot.response.OpenWeather;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyBot extends TelegramLongPollingBot {
    private final String username = "user_open_wether_bot";
    private final String token = "5105732887:AAEhXIuMUuwD_mF_v-a34eQP-SBcAxcin7o";
//    private String city;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
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
