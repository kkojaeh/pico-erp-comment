package pico.erp.comment;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.ApplicationId;
import pico.erp.shared.data.Role;

public final class CommentApi {

  public static ApplicationId ID = ApplicationId.from("comment");

  @RequiredArgsConstructor
  public enum Roles implements Role {

    COMMENT_MANAGER;

    @Id
    @Getter
    private final String id = name();

  }
}
