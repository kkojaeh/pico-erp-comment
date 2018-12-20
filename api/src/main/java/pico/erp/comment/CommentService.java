package pico.erp.comment;

import pico.erp.comment.CommentRequests.AddRequest;
import pico.erp.comment.CommentRequests.RemoveRequest;

public interface CommentService {

  CommentData add(AddRequest request);

  void remove(RemoveRequest request);

}
