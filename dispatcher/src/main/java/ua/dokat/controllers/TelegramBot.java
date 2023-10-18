package ua.dokat.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
@Log4j
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botUserName;
    @Value("${bot.token}")
    private String botToken;

    private final UpdateController updateController;

    public TelegramBot(UpdateController updateController){
        this.updateController = updateController;
    }

    @PostConstruct
    public void init(){
        updateController.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logUserMessage(update.getMessage());
        updateController.processUpdate(update);
    }

    public void sendMessage(SendMessage message){
        if (message != null){
            try{
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }

    public void logUserMessage(Message message){
        if (message != null){
            var user = message.getFrom();

            String id = String.valueOf(user.getId());
            String userName = user.getUserName();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String link = "https://t.me/" + userName;

            log.debug("[ " + id + " | " +
                        "@" + userName + " | " +
                        firstName + " " + lastName + " | " +
                        link + " ] " + message.getText());
        }else {
            log.error("Message is null");
        }
    }
}
