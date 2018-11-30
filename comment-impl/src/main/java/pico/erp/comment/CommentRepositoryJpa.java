package pico.erp.comment;

import java.util.Optional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface CommentEntityRepository extends CrudRepository<CommentEntity, CommentId> {

}

@Repository
@Transactional
public class CommentRepositoryJpa implements CommentRepository {

  @Autowired
  private CommentEntityRepository repository;

  @Autowired
  private CommentMapper mapper;

  @Override
  public Comment create(Comment comment) {
    val entity = mapper.jpa(comment);
    val created = repository.save(entity);
    return mapper.jpa(created);
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
      .map(mapper::jpa);
  }

}
