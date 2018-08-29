package pico.erp.comment;

import java.util.Collections;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.data.Menu;
import pico.erp.shared.data.Role;

@RequiredArgsConstructor
public enum ROLE implements Role {

  COMMENT_MANAGER(Collections.emptySet());

  @Id
  @Getter
  private final String id = name();

  @Transient
  @Getter
  @NonNull
  private Set<Menu> menus;
}
