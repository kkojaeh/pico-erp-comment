package pico.erp.comment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.comment.subject.CommentSubjectId
import pico.erp.comment.subject.type.CommentSubjectType
import pico.erp.comment.subject.type.CommentSubjectTypeId
import pico.erp.shared.IntegrationConfiguration
import spock.lang.Specification

@SpringBootTest(classes = [IntegrationConfiguration])
@Transactional
@Rollback
@ActiveProfiles("test")
@Configuration
class CommentQuerySpec extends Specification {

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

  @Autowired
  CommentQuery commentQuery

  def text = """해당 테스트는 아래의 분들과 함께 해야 할것으로 보입니다
@고재훈, @임성환
감사합니다
"""
  def text2 = """해당 테스트 완료 했습니다
"""

  def setup() {
    commentService.add(
      new CommentRequests.AddRequest(
        subjectId: CommentSubjectId.from("test-subject"),
        subjectTypeId: CommentSubjectTypeId.from("test"),
        comment: text
      )
    )
    commentService.add(
      new CommentRequests.AddRequest(
        subjectId: CommentSubjectId.from("test-subject"),
        subjectTypeId: CommentSubjectTypeId.from("test"),
        comment: text2
      )
    )
  }

  def "subjectId 에 해당하는 댓글이 검색됨"() {
    expect:
    def page = commentQuery.retrieve(subjectId, pageable)
    page.totalElements == totalElements

    where:
    subjectId                             | pageable               || totalElements
    CommentSubjectId.from("test-subject") | new PageRequest(0, 10) || 2
  }
}
