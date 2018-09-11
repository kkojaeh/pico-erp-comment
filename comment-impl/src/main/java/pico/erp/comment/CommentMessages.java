package pico.erp.comment;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import pico.erp.comment.subject.data.CommentSubjectId;
import pico.erp.comment.subject.type.data.CommentSubjectType;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Auditor;
import pico.erp.shared.event.Event;

public interface CommentMessages {

  @Data
  class AddRequest {

    @Valid
    @NotNull
    CommentSubjectType subjectType;

    @Valid
    @NotNull
    CommentSubjectId subjectId;

    @NotNull
    @Size(min = 2, max = TypeDefinitions.COMMENT_LENGTH)
    String comment;

    @NotNull
    Auditor createdBy;

  }

  @Data
  class RemoveRequest {

  }

  @Value
  class AddResponse {

    Collection<Event> events;

  }

  @Value
  class RemoveResponse {

    Collection<Event> events;

  }
}
