package ua.dokat.services.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.dokat.services.Consumer;

import static ua.dokat.RabbitQueue.*;

@Service
@Log4j
public class ConsumerService implements Consumer {
    private final MainDataService mainDataService;

    public ConsumerService(MainDataService mainDataService) {
        this.mainDataService = mainDataService;
    }

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumerTextMessageUpdate(Update update){
        log.debug("NODE: You send text");
        mainDataService.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumerDocMessageUpdate(Update update) {
        log.debug("NODE: You send document");
    }

    @Override
    @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
    public void consumerPhotoMessageUpdate(Update update) {
        log.debug("NODE: You send photo");
    }
}
