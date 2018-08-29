package pico.erp.comment;

import pico.erp.comment.CommentRequests.AddRequest;
import pico.erp.comment.CommentRequests.RemoveRequest;
import pico.erp.comment.data.CommentData;

public interface CommentService {

  CommentData add(AddRequest request);

  void remove(RemoveRequest request);

}
