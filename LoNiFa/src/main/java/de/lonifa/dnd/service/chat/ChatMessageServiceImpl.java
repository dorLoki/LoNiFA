package de.lonifa.dnd.service.chat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.chat.ChatMessage;
import de.lonifa.dnd.domain.chat.ChatMessageRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void addChatMessageToGameSession(@NonNull @Valid ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
