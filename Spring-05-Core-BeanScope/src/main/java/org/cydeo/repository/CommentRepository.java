package org.cydeo.repository;

import org.cydeo.model.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
