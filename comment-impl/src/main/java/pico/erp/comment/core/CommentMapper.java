package pico.erp.comment.core;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import pico.erp.comment.CommentExceptions.SubjectTypeNotFoundException;
import pico.erp.comment.CommentRequests;
import pico.erp.comment.data.CommentData;
import pico.erp.comment.data.CommentSubjectType;
import pico.erp.comment.data.CommentSubjectTypeId;
import pico.erp.comment.domain.Comment;
import pico.erp.comment.domain.CommentMessages.AddRequest;
import pico.erp.comment.domain.CommentMessages.RemoveRequest;
import pico.erp.shared.data.Auditor;

@Mapper
public abstract class CommentMapper {

  @Autowired
  protected AuditorAware<Auditor> auditorAware;

  @Autowired
  protected CommentSubjectTypeRepository commentSubjectTypeRepository;

  protected CommentSubjectType map(CommentSubjectTypeId subjectTypeId) {
    return Optional.ofNullable(subjectTypeId)
      .map(id -> commentSubjectTypeRepository.findBy(id)
        .orElseThrow(SubjectTypeNotFoundException::new)
      ).orElse(null);
  }

  abstract CommentData map(Comment comment);

  @Mappings({
    @Mapping(target = "subjectType", source = "subjectTypeId"),
    @Mapping(target = "createdBy", expression = "java(auditorAware.getCurrentAuditor())")
  })
  abstract AddRequest map(CommentRequests.AddRequest request);

  abstract RemoveRequest map(CommentRequests.RemoveRequest request);

}
