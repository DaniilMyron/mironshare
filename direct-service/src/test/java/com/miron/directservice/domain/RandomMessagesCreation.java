package com.miron.directservice.domain;

import com.miron.directservice.domain.valueObject.Message;

import java.util.ArrayList;
import java.util.List;

public class RandomMessagesCreation {
    public static List<Message> createRandomMessages(int numberOfMessages) {
        List<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < numberOfMessages; i++) {
            Message message = Message.Builder()
                    .setId()
                    .setText(createRandomString(10))
                    .setSenderId(1)
                    .build();
            messages.add(message);
        }
        return messages;
    }

    private static String createRandomString(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (Math.random() % 57 + 65);
        }
        return new String(chars);
    }
}
