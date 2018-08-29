package pico.erp.comment.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pico.erp.comment.CommentEvents;
import pico.erp.comment.CommentEvents.MentionedEvent;
import pico.erp.comment.CommentExceptions.NotFoundException;
import pico.erp.comment.data.CommentData;
import pico.erp.comment.domain.Comment;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("unused")
@Component
@Transactional
public class CommentEventListener {

  private static final String LISTENER_NAME = "listener.comment-event-listener";

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private CommentParser commentParser;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private CommentRepository commentRepository;

  @EventListener
  @JmsListener(destination = LISTENER_NAME + "." + CommentEvents.AddedEvent.CHANNEL)
  public void onCommentAdded(CommentEvents.AddedEvent event) {
    Comment comment = commentRepository.findBy(event.getCommentId())
      .orElseThrow(NotFoundException::new);
    CommentData commentData = commentMapper.map(comment);
    ParsedComment parsedComment = commentParser.parse(comment.getComment());

    parsedComment.getMentions().stream()
      .map(mention -> new MentionedEvent(event.getCommentId(), mention))
      .forEach(eventPublisher::publishEvent);
  }

}
