package pico.erp.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.comment.data.CommentId;
import pico.erp.shared.event.Event;

public interface CommentEvents {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class AddedEvent implements Event {

    public final static String CHANNEL = "event.comment.added";

    private CommentId commentId;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class MentionedEvent implements Event {

    public final static String CHANNEL = "event.comment.mentioned";

    private CommentId commentId;

    private String mention;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class RemovedEvent implements Event {

    public final static String CHANNEL = "event.comment.removed";

    private CommentId commentId;

    public String channel() {
      return CHANNEL;
    }

  }
}
