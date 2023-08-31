package org.cydeo.proxy;

import org.cydeo.model.Comment;

public interface CommentNotificationProxy {
    void sendComment(Comment comment);
}
