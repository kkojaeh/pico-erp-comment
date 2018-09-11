package pico.erp.comment.data;

import pico.erp.comment.subject.data.CommentSubjectId;
import pico.erp.comment.subject.type.data.CommentSubjectTypeId;

public interface CommentInfo {

  CommentId getId();

  CommentSubjectId getSubjectId();

  CommentSubjectTypeId getSubjectTypeId();

}
