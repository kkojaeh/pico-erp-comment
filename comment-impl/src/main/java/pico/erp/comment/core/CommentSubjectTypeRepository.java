package pico.erp.comment.core;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import pico.erp.comment.data.CommentSubjectType;
import pico.erp.comment.data.CommentSubjectTypeId;

public interface CommentSubjectTypeRepository {

  Stream<CommentSubjectType> findAll();

  Optional<CommentSubjectType> findBy(@NotNull CommentSubjectTypeId id);

}
