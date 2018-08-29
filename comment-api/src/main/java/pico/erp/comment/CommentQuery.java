package pico.erp.comment;

import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pico.erp.comment.data.CommentSubjectId;
import pico.erp.comment.data.CommentView;

public interface CommentQuery {

  Page<CommentView> retrieve(@NotNull CommentSubjectId subjectId, @NotNull Pageable pageable);

}
