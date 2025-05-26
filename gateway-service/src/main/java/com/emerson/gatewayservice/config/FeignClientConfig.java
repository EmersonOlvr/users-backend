package com.emerson.gatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientConfig {
	
	@Value("${app.security.oauth2.client-id}")
	private String clientId;

	@Bean
	RequestInterceptor oauth2FeignRequestInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				Authentication principal = new UsernamePasswordAuthenticationToken("feignClient", null, AuthorityUtils.createAuthorityList("ROLE_USER"));
				OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(clientId)
																				.principal(principal)
																				.build();

				OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);

				if (authorizedClient != null) {
					String accessToken = authorizedClient.getAccessToken().getTokenValue();
					requestTemplate.header("Authorization", "Bearer " + accessToken);
				}
			}
		};
	}
	
}
