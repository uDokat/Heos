package ua.dokat.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Producer {
    void producerAnswer(SendMessage sendMessage);
}
