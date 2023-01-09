package com.udacity.jdnd.c1.review.mapper;

import com.udacity.jdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES WHERE messageid = #{messageId}")
    ChatMessage getChatMessage(Integer messageId);

    @Insert("INSERT INTO MESSAGES (username, message) VALUES (#{username}, #{message})")
    @Options(useGeneratedKeys = true, keyColumn = "messageId")
    Integer addChatMessage(ChatMessage chatMessage);

    @Delete("DELETE FROM MESSAGES WHERE messageid = #{messageId}")
    void deleteMessage(Integer messageId);
}
