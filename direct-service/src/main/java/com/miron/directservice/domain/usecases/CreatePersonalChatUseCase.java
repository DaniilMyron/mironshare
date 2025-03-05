package com.miron.directservice.domain.usecases;

import com.miron.directservice.domain.api.CreatePersonalChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.valueObject.User;


public class CreatePersonalChatUseCase implements CreatePersonalChat {
    private final ChatRepository<PersonalChat> chatRepository;

    public CreatePersonalChatUseCase(ChatRepository<PersonalChat> chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public PersonalChat createChat(String template, String username) {
        //TODO find user in repo by username or by rest
        User tempUser = new User(username, "danya", null, null, null);
        var receiver = convert(template);
        return chatRepository.save(new PersonalChat(tempUser, receiver));
    }

    private User convert(String template) {
        String[] parametersForUser = new String[5];
        template = template.substring(1, template.length() - 1).replace("\"", "");
        var parameters = template.split(",");
        for (int i = 0; i < parameters.length; i++) {
            parametersForUser[i] = parameters[i].substring(parameters[i].indexOf(':') + 1);
        }
        return new User(parametersForUser[0], parametersForUser[1], parametersForUser[2], parametersForUser[3], parametersForUser[4]);
    }
}
