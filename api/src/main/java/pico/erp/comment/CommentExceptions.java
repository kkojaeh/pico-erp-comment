package pico.erp.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CommentExceptions {

  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "comment.not.found.exception")
  class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "comment.subject.type.not.found.exception")
  class SubjectTypeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

  }
}
