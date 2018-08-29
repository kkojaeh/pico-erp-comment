package pico.erp.comment.impl;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import pico.erp.comment.CommentExceptions.SubjectTypeNotFoundException;
import pico.erp.comment.core.CommentSubjectTypeRepository;
import pico.erp.comment.data.CommentSubjectType;
import pico.erp.comment.data.CommentSubjectTypeId;
import pico.erp.comment.domain.Comment;
import pico.erp.comment.impl.jpa.CommentEntity;

@Mapper
public abstract class CommentJpaMapper {

  @Autowired
  protected CommentSubjectTypeRepository commentSubjectTypeRepository;

  protected CommentSubjectType map(CommentSubjectTypeId subjectTypeId) {
    return Optional.ofNullable(subjectTypeId)
      .map(id -> commentSubjectTypeRepository.findBy(id)
        .orElseThrow(SubjectTypeNotFoundException::new)
      ).orElse(null);
  }

  protected Comment map(CommentEntity entity) {
    return Comment.builder()
      .id(entity.getId())
      .subjectId(entity.getSubjectId())
      .subjectType(map(entity.getSubjectTypeId()))
      .comment(entity.getComment())
      .createdBy(entity.getCreatedBy())
      .createdDate(entity.getCreatedDate())
      .build();
  }

  @Mappings({
    @Mapping(target = "subjectTypeId", source = "subjectType.id")
  })
  abstract CommentEntity map(Comment comment);

}
