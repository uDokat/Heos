package ua.dokat.services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface MainData {
    void processTextMessage(Update update);
}
