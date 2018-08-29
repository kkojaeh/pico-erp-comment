package pico.erp.comment.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pico.erp.comment.core.CommentRepository;
import pico.erp.comment.data.CommentId;
import pico.erp.comment.domain.Comment;
import pico.erp.comment.impl.jpa.CommentEntity;

@Repository
interface CommentEntityRepository extends CrudRepository<CommentEntity, CommentId> {

}

@Repository
@Transactional
public class CommentRepositoryJpa implements CommentRepository {

  @Autowired
  private CommentEntityRepository repository;

  @Autowired
  private CommentJpaMapper mapper;

  @Override
  public Comment create(Comment comment) {
    CommentEntity entity = mapper.map(comment);
    entity = repository.save(entity);
    return mapper.map(entity);
  }

  @Override
  public void deleteBy(CommentId id) {
    repository.delete(id);
  }

  @Override
  public boolean exists(CommentId id) {
    return repository.exists(id);
  }

  @Override
  public Optional<Comment> findBy(CommentId id) {
    return Optional.ofNullable(repository.findOne(id))
      .map(mapper::map);
  }

}
