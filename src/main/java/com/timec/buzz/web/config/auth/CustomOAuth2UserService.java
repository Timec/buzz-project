package com.timec.buzz.web.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import com.timec.buzz.web.config.auth.dto.OAuthAttributes;
import com.timec.buzz.web.config.auth.dto.SessionUser;
import com.timec.buzz.web.domain.user.BuzzUser;
import com.timec.buzz.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		//현재 로그인 진행 중인 서비스 구분 코드
		//구글, 네이버, 카카오 등의 종류 구분용
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		//OAuth2 로그인 진행 시 key가 되는 필드값을 이야기. Primary 키와 같은 의미.
		//구글의 경우 기본적으로 코드를 지원, 네이버 카카오등은 기본 지원하지 않음. 구글의 기본 코드는 "sub"
		//이후 네이버 구글 로그인을 동시에 지원할 때 사용.
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		//OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스.
		//이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용.
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

		BuzzUser user = saveOrUpdate(attributes);

		//세션에 사용자 정보를 저장하기 위한 Dto 클래스
		//User 클래스를 사용하면 직렬화 오류가 남. User클래스는 엔티티 클래스라 사이드 이팩트가 발생할 수 있어 별도로 dto 생성해서 사용.
		httpSession.setAttribute("user", new SessionUser(user));

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	private BuzzUser saveOrUpdate(OAuthAttributes attributes) {
		BuzzUser user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());

		return userRepository.save(user);
	}
}
