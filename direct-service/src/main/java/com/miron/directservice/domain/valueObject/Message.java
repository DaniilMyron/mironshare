package com.miron.directservice.domain.valueObject;

import java.util.UUID;

public class Message implements ValueObject<String> {
    private MessageID messageId;
    private String text;
    private int senderId;

    public Message() {
    }

    public Message(String text, int senderId) {
        this.messageId = new MessageID();
        if(!text.isBlank() && !text.isEmpty())
            this.text = text;
        else
            throw new NullPointerException("name is blank or empty");

        this.senderId = senderId;
    }

    @Override
    public String getValue() {
        return this.text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public UUID getId() {
        return messageId.getValue();
    }

    public int getSenderId() {
        return senderId;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Message) {
            Message message = (Message) object;
            return this.messageId.equals(message.messageId);
        }
        return false;
    }

    public static Builder Builder() {
        return new Message().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setId() {
            Message.this.messageId = new MessageID();
            return this;
        }

        public Builder setText(String text) {
            Message.this.text = text;
            return this;
        }

        public Builder setSenderId(int senderId) {
            Message.this.senderId = senderId;
            return this;
        }

        public Message build() {
            return Message.this;
        }

    }
}
