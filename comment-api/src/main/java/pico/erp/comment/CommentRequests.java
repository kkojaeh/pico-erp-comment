package pico.erp.comment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.comment.data.CommentId;
import pico.erp.comment.subject.data.CommentSubjectId;
import pico.erp.comment.subject.type.data.CommentSubjectTypeId;
import pico.erp.shared.TypeDefinitions;

public interface CommentRequests {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class AddRequest {

    @Valid
    @NotNull
    CommentSubjectTypeId subjectTypeId;

    @Valid
    @NotNull
    CommentSubjectId subjectId;

    @Size(min = 2, max = TypeDefinitions.COMMENT_LENGTH)
    String comment;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class RemoveRequest {

    @Valid
    @NotNull
    CommentId id;

  }
}
