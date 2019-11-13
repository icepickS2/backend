package app.security.oauth2.platform;

import java.util.Map;

import app.security.oauth2.OAuth2Account;

public class GithubOAuth2Account extends OAuth2Account {

    public GithubOAuth2Account(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("avatar_url");
    }
}
