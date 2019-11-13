package app.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import app.api.account.Platform;
import app.api.account.Account;
import app.api.account.AccountRepository;
import app.security.sign.Sign;
import app.security.oauth2.OAuth2Account;
import app.security.oauth2.OAuth2AccountFactory;

//@formatter:off
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    String clientId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
    OAuth2Account oAuth2Account = OAuth2AccountFactory.getOAuth2UserInfo(clientId, oAuth2User.getAttributes());

    Account account = accountRepository.findByEmail(oAuth2Account.getEmail()).orElse(null);
    if(account==null){
      account = createAccount(oAuth2UserRequest, oAuth2Account);
    }else if(account.getPlatform().toString().equalsIgnoreCase(clientId)){
      account = updateAccount(account, oAuth2Account);
    }else{
      throw new InternalAuthenticationServiceException("clientId"); 
    }
    return new Sign(account, oAuth2User.getAttributes());
  }

  private Account createAccount(OAuth2UserRequest oAuth2UserRequest, OAuth2Account oAuth2Account) {
    Account user = new Account();

    user.setPlatform(Platform.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setPlatformToken(oAuth2Account.getId());
    user.setName(oAuth2Account.getName());
    user.setEmail(oAuth2Account.getEmail());
    user.setImgUrl(oAuth2Account.getImageUrl());
    return accountRepository.save(user);
  }

  private Account updateAccount(Account account, OAuth2Account oAuth2Account) {
    account.setName(oAuth2Account.getName());
    account.setImgUrl(oAuth2Account.getImageUrl());
    return accountRepository.save(account);
  }
}