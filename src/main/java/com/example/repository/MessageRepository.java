package com.example.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.example.entity.Message;

public interface MessageRepository {

    ArrayList<Message> getAllMessages();

    Optional<Message> getMessageById(int id);
}
