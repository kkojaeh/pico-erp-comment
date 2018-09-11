package pico.erp.comment;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.data.Role;

@RequiredArgsConstructor
public enum ROLE implements Role {

  COMMENT_MANAGER;

  @Id
  @Getter
  private final String id = name();

}
