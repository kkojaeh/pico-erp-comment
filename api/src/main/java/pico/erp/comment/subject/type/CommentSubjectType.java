package pico.erp.comment.subject.type;

import java.net.URI;
import java.util.function.Function;
import lombok.Getter;
import pico.erp.comment.CommentInfo;

public interface CommentSubjectType {

  CommentSubjectTypeId getId();

  URI toUri(CommentInfo info);

  class CommentSubjectTypeImpl implements CommentSubjectType {

    @Getter
    private final CommentSubjectTypeId id;

    private final Function<CommentInfo, URI> generator;

    public CommentSubjectTypeImpl(CommentSubjectTypeId id,
      Function<CommentInfo, URI> generator) {
      this.id = id;
      this.generator = generator;
    }

    @Override
    public URI toUri(CommentInfo info) {
      return generator.apply(info);
    }
  }

}
