package pico.erp.comment

import kkojaeh.spring.boot.component.SpringBootTestComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.shared.ComponentDefinitionServiceLoaderTestComponentSiblingsSupplier
import pico.erp.shared.TestParentApplication
import spock.lang.Specification

@SpringBootTest(classes = [CommentApplication, CommentConfig])
@SpringBootTestComponent(parent = TestParentApplication, siblingsSupplier = ComponentDefinitionServiceLoaderTestComponentSiblingsSupplier.class)
@Transactional
@Rollback
@ActiveProfiles("test")
@ComponentScan(useDefaultFilters = false)
class CommentParserSpec extends Specification {

  @Autowired
  CommentParser commentParser

  def text = """해당 테스트는 아래의 분들과 함께 해야 할것으로 보입니다
@고재훈, @임성환
감사합니다
"""

  def setup() {

  }

  def "댓글 생성"() {
    when:
    def parsed = commentParser.parse(text)


    then:
    parsed.mentions.contains("고재훈")
    parsed.mentions.contains("임성환")
  }

}
