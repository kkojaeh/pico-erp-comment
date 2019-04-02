package pico.erp.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kkojaeh.spring.boot.component.Give;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.comment.subject.CommentSubjectId;
import pico.erp.shared.jpa.QueryDslJpaSupport;

@Give
@Service
@Transactional(readOnly = true)
@Validated
public class CommentQueryJpa implements CommentQuery {

  private final QCommentEntity comment = QCommentEntity.commentEntity;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private QueryDslJpaSupport queryDslJpaSupport;

  @Override
  public Page<CommentView> retrieve(CommentSubjectId subjectId, Pageable pageable) {
    val query = new JPAQuery<CommentView>(entityManager);
    val select = Projections.bean(CommentView.class,
      comment.id,
      comment.subjectId,
      comment.comment,
      comment.createdBy,
      comment.createdDate
    );

    query.select(select);
    query.from(comment);
    query.where(
      comment.subjectId.eq(subjectId)
    );
    query.orderBy(comment.createdDate.desc());
    return queryDslJpaSupport.paging(query, pageable, select);
  }

}
