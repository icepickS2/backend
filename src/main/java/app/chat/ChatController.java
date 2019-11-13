// package app.chat;

// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.util.HtmlUtils;

// @Controller
// public class ChatController {

//   @MessageMapping("/hello") // config에서 /app등록했기 때문에 /app/hello로 접근해야함
//   @SendTo("/topic/greetings") // config에서 /app등록했는데, 이건 또 그냥 /topic/greetings으로 접근
//   public Greeting greeting(HelloMessage message) throws Exception {
//     Thread.sleep(1000); // simulated delay
//     return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//     // HtmlUtils.htmlEscape를 이용해 "<"와 같이 HTML에서 사용하는 문자를 "&lt"와같은걸로 바꿔줌
//   }

// }