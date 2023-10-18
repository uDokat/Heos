package ua.dokat.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Consumer {
    void consumerTextMessageUpdate(Update update) throws JsonProcessingException, UnirestException;
    void consumerDocMessageUpdate(Update update);
    void consumerPhotoMessageUpdate(Update update);
}
