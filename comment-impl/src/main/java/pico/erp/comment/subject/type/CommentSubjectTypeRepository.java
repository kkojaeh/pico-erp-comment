package pico.erp.comment.subject.type;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import pico.erp.comment.subject.type.data.CommentSubjectType;
import pico.erp.comment.subject.type.data.CommentSubjectTypeId;

public interface CommentSubjectTypeRepository {

  Stream<CommentSubjectType> findAll();

  Optional<CommentSubjectType> findBy(@NotNull CommentSubjectTypeId id);

}
