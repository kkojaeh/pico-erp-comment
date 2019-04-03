package pico.erp.comment.subject.type;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import kkojaeh.spring.boot.component.ComponentAutowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentSubjectTypeRepositoryImpl implements CommentSubjectTypeRepository {

  @ComponentAutowired(required = false)
  List<CommentSubjectType> types;

  @Override
  public Stream<CommentSubjectType> findAll() {
    return types.stream();
  }

  @Override
  public Optional<CommentSubjectType> findBy(CommentSubjectTypeId id) {
    return types.stream()
      .filter(type -> type.getId().equals(id))
      .findFirst();
  }

}
