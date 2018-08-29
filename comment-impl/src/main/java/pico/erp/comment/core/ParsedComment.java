package pico.erp.comment.core;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParsedComment {

  Set<String> mentions;

}
