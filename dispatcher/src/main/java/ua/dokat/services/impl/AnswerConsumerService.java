package ua.dokat.services.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.dokat.controllers.UpdateController;
import ua.dokat.services.AnswerConsumer;

import static ua.dokat.RabbitQueue.ANSWER_MESSAGE;

@Service
public class AnswerConsumerService implements AnswerConsumer {
    private final UpdateController updateController;

    public AnswerConsumerService(UpdateController updateController) {
        this.updateController = updateController;
    }

    @Override
    @RabbitListener(queues = ANSWER_MESSAGE)
    public void consumer(SendMessage sendMessage) {
        updateController.setView(sendMessage);
    }
}