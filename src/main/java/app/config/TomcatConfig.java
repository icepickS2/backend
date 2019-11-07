// package app.config;

// import org.apache.catalina.Context;
// import org.apache.catalina.connector.Connector;
// import org.apache.tomcat.util.descriptor.web.SecurityCollection;
// import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
// import
// org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
// import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class TomcatConfig {

// @Value("${server.port}")
// int https;
// @Value("8080")
// int http;

// @Bean
// public ServletWebServerFactory serverFactory() {
// TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
// tomcat.addAdditionalTomcatConnectors(httpConnector());
// tomcat.addContextCustomizers(securityCustomizer());
// return tomcat;
// }

// private TomcatContextCustomizer securityCustomizer() {
// return new TomcatContextCustomizer() {
// @Override
// public void customize(Context context) {
// SecurityConstraint securityConstraint = new SecurityConstraint();
// securityConstraint.setUserConstraint("CONFIDENTIAL");
// SecurityCollection collection = new SecurityCollection();
// collection.addPattern("/*");
// securityConstraint.addCollection(collection);
// context.addConstraint(securityConstraint);
// }
// };
// }

// private Connector httpConnector() {
// Connector connector = new
// Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
// connector.setScheme("http");
// connector.setPort(http);
// connector.setSecure(false);
// connector.setRedirectPort(https);
// return connector;
// }
// }