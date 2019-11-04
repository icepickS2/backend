package app.util;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "prototype")
@Component
public class Msg extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;
}