package pico.erp.comment


import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import pico.erp.comment.subject.type.CommentSubjectType
import pico.erp.comment.subject.type.CommentSubjectTypeId

@ContextConfiguration
class CommentConfig {

  @Bean
  CommentSubjectType testCommentSubjectType() {
    return new CommentSubjectType() {

      @Override
      CommentSubjectTypeId getId() {
        return CommentSubjectTypeId.from("test")
      }

      @Override
      URI toUri(CommentInfo info) {
        return URI.create("http://test/${info.subjectId}")
      }
    }
  }

}
