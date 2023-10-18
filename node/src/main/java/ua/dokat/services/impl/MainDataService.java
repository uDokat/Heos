package ua.dokat.services.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.dokat.dao.RawDataDAO;
import ua.dokat.dao.UserDataDAO;
import ua.dokat.entity.RawData;
import ua.dokat.entity.UserData;
import ua.dokat.services.MainData;

@Service
public class MainDataService implements MainData {
    private final RawDataDAO rawDataDAO;
    private final UserDataDAO userDataDAO;
    private final ProducerService service;

    public MainDataService(RawDataDAO rawDataDAO, UserDataDAO userDataDAO, ProducerService service) {
        this.rawDataDAO = rawDataDAO;
        this.userDataDAO = userDataDAO;
        this.service = service;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);
        saveUserName(update);

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setText("Response");
        sendMessage.setChatId(message.getChatId());
        service.producerAnswer(sendMessage);
    }

    private void saveUserName(Update update) {
        UserData user = UserData.builder()
                .userName(update.getMessage().getFrom().getUserName())
                .build();
        userDataDAO.save(user);
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                .event(update)
                .build();
        rawDataDAO.save(rawData);
    }
}
