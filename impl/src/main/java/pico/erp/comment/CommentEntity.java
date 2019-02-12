package pico.erp.comment;


import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pico.erp.comment.subject.CommentSubjectId;
import pico.erp.comment.subject.type.CommentSubjectTypeId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Auditor;

@Entity(name = "Comment")
@Table(name = "CMT_COMMENT", indexes = {
  @Index(name = "CMT_COMMENT_SUBJECT_ID_IDX", columnList = "SUBJECT_ID")
})
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  CommentId id;

  @Column(length = TypeDefinitions.COMMENT_LENGTH)
  String comment;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "SUBJECT_TYPE_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CommentSubjectTypeId subjectTypeId;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "SUBJECT_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CommentSubjectId subjectId;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "CREATED_BY_ID", updatable = false, length = TypeDefinitions.ID_LENGTH)),
    @AttributeOverride(name = "name", column = @Column(name = "CREATED_BY_NAME", updatable = false, length = TypeDefinitions.NAME_LENGTH))
  })
  @CreatedBy
  Auditor createdBy;

  @CreatedDate
  @Column(updatable = false)
  OffsetDateTime createdDate;

}
