package pico.erp.comment;

import java.util.Optional;

public interface CommentRepository {

  Comment create(Comment comment);

  void deleteBy(CommentId id);

  boolean exists(CommentId id);

  Optional<Comment> findBy(CommentId id);

}
