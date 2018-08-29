package pico.erp.comment.domain;

import java.io.Serializable;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import pico.erp.comment.CommentEvents.AddedEvent;
import pico.erp.comment.CommentEvents.RemovedEvent;
import pico.erp.comment.data.CommentId;
import pico.erp.comment.data.CommentInfo;
import pico.erp.comment.data.CommentSubjectId;
import pico.erp.comment.data.CommentSubjectType;
import pico.erp.comment.data.CommentSubjectTypeId;
import pico.erp.comment.domain.CommentMessages.AddResponse;
import pico.erp.comment.domain.CommentMessages.RemoveResponse;
import pico.erp.shared.data.Auditor;

@ToString
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment implements Serializable, CommentInfo {

  private static final long serialVersionUID = 1L;

  public static Pattern pattern = Pattern.compile("@([^@^\\s])+");

  CommentId id;

  CommentSubjectType subjectType;

  CommentSubjectId subjectId;

  String comment;

  Auditor createdBy;

  OffsetDateTime createdDate;

  public Comment() {
  }

  public AddResponse apply(CommentMessages.AddRequest request) {
    id = CommentId.generate();
    subjectType = request.getSubjectType();
    subjectId = request.getSubjectId();
    comment = request.getComment();
    createdBy = request.getCreatedBy();
    createdDate = OffsetDateTime.now();
    return new AddResponse(Arrays.asList(new AddedEvent(this.id)));
  }

  public RemoveResponse apply(CommentMessages.RemoveRequest request) {
    return new RemoveResponse(Arrays.asList(new RemovedEvent(this.id)));
  }

  @Override
  public CommentSubjectTypeId getSubjectTypeId() {
    return subjectType.getId();
  }

  public URI toUri() {
    return subjectType.toUri(this);
  }

}