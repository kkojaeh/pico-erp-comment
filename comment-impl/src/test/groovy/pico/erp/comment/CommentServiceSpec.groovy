package pico.erp.comment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.comment.data.CommentInfo
import pico.erp.comment.data.CommentSubjectId
import pico.erp.comment.data.CommentSubjectType
import pico.erp.comment.data.CommentSubjectTypeId
import pico.erp.shared.IntegrationConfiguration
import spock.lang.Specification

@SpringBootTest(classes = [IntegrationConfiguration])
@Transactional
@Rollback
@ActiveProfiles("test")
@Configuration
class CommentServiceSpec extends Specification {

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

  @Autowired
  CommentService commentService

  def text = """해당 테스트는 아래의 분들과 함께 해야 할것으로 보입니다
@고재훈, @임성환
감사합니다
"""

  def setup() {

  }

  def "댓글 생성"() {
    when:
    def comment = commentService.add(
      new CommentRequests.AddRequest(
        subjectId: CommentSubjectId.from("test-subject"),
        subjectTypeId: CommentSubjectTypeId.from("test"),
        comment: text
      )
    )

    then:
    comment.comment == text
  }

}
