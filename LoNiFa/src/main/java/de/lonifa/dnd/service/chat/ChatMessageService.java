package de.lonifa.dnd.service.chat;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.chat.ChatMessage;

public interface ChatMessageService {
    
    void addChatMessageToGameSession(@NonNull @Valid ChatMessage chatMessage);

}
