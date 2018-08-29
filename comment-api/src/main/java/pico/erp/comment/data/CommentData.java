package pico.erp.comment.data;

import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pico.erp.shared.data.Auditor;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentData {

  String comment;

  CommentSubjectId subjectId;

  CommentId id;

  Auditor createdBy;

  OffsetDateTime createdDate;

}
