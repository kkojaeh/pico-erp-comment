package pico.erp.comment.core;

import java.util.Optional;
import pico.erp.comment.data.CommentId;
import pico.erp.comment.domain.Comment;

public interface CommentRepository {

  Comment create(Comment comment);

  void deleteBy(CommentId id);

  boolean exists(CommentId id);

  Optional<Comment> findBy(CommentId id);

}
