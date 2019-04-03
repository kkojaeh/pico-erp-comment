package pico.erp.comment;

import kkojaeh.spring.boot.component.ComponentBean;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.comment.CommentExceptions.NotFoundException;
import pico.erp.comment.CommentRequests.AddRequest;
import pico.erp.comment.CommentRequests.RemoveRequest;
import pico.erp.shared.event.EventPublisher;

@ComponentBean
@Service
@Transactional
@Validated
public class CommentServiceLogic implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private CommentMapper mapper;

  @Override
  public CommentData add(AddRequest request) {
    val comment = new Comment();
    val response = comment.apply(mapper.map(request));
    val created = commentRepository.create(comment);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public void remove(RemoveRequest request) {
    val comment = commentRepository.findBy(request.getId())
      .orElseThrow(NotFoundException::new);
    val response = comment.apply(mapper.map(request));
    commentRepository.deleteBy(comment.getId());
    eventPublisher.publishEvents(response.getEvents());
  }
}
