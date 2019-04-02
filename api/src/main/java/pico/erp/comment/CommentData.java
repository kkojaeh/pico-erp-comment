package pico.erp.comment;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pico.erp.comment.subject.CommentSubjectId;
import pico.erp.shared.data.Auditor;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentData {

  String comment;

  CommentSubjectId subjectId;

  CommentId id;

  Auditor createdBy;

  LocalDateTime createdDate;

}
