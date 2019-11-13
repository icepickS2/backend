package app.security.oauth2;

import java.util.Map;

import app.api.account.Platform;
import app.security.oauth2.platform.FacebookOAuth2Account;
import app.security.oauth2.platform.GithubOAuth2Account;
import app.security.oauth2.platform.GoogleOAuth2Account;

public class OAuth2AccountFactory {

    public static OAuth2Account getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(Platform.GOOGLE.toString())) {
            return new GoogleOAuth2Account(attributes);
        } else if (registrationId.equalsIgnoreCase(Platform.FACEBOOK.toString())) {
            return new FacebookOAuth2Account(attributes);
        } else if (registrationId.equalsIgnoreCase(Platform.GITHUB.toString())) {
            return new GithubOAuth2Account(attributes);
        }
        return null;
    }
}
