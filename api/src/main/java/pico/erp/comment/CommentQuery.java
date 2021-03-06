package pico.erp.comment;

import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pico.erp.comment.subject.CommentSubjectId;

public interface CommentQuery {

  Page<CommentView> retrieve(@NotNull CommentSubjectId subjectId, @NotNull Pageable pageable);

}
