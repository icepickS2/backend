package app.config;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class Message extends HashMap<String,Object>{
  private static final long serialVersionUID = 1L;
}