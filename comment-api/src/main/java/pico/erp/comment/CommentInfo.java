package pico.erp.comment;

import pico.erp.comment.subject.CommentSubjectId;
import pico.erp.comment.subject.type.CommentSubjectTypeId;

public interface CommentInfo {

  CommentId getId();

  CommentSubjectId getSubjectId();

  CommentSubjectTypeId getSubjectTypeId();

}
