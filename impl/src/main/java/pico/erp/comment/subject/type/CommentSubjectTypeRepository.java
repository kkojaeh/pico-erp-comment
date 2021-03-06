package pico.erp.comment.subject.type;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;

public interface CommentSubjectTypeRepository {

  Stream<CommentSubjectType> findAll();

  Optional<CommentSubjectType> findBy(@NotNull CommentSubjectTypeId id);

}
