package pico.erp.comment.data;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.comment.subject.data.CommentSubjectId;
import pico.erp.shared.data.Auditor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommentView {

  CommentId id;

  CommentSubjectId subjectId;

  String comment;

  Auditor createdBy;

  OffsetDateTime createdDate;

}
