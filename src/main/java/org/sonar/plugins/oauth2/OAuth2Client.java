/*
 * Copyright 2015, Joseph "Deven" Phillips
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sonar.plugins.oauth2;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.sonar.api.config.Settings;
import org.sonar.api.ServerExtension;
import org.sonar.api.security.UserDetails;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class OAuth2Client implements ServerExtension {
    
  public static final String PROPERTY_SONAR_URL = "sonar.oauth2.sonarServerUrl";
  public static final String PROPERTY_PROVIDER = "sonar.oauth2.provider";
  public static final String PROPERTY_CLIENT_ID = "sonar.oauth2.clientid";
  public static final String PROPERTY_AUTH_LOCATION = "sonar.oauth2.authLocation";
  public static final String PROPERTY_TOKEN_LOCATION = "sonar.oauth2.tokenLocation";
  public static final String PROPERTY_SECRET = "sonar.oauth2.secret";

  static final String OAUTH2_SCOPE_EMAIL = "email";
  
  OAuthProviderType providerType = null;
  AuthenticationRequestBuilder redirReqBuilder = null;
  String clientSecret = null;
  Settings settings = null;
  String authLocation = null;
  String tokenLocation = null;

  /**
   * Default constructor. Accepts Sonar {@link Settings} in order to bootstrap
   * the class.
   * @param settings The {@link Settings} from the currently running Sonar instance.
   * @throws OAuthSystemException If there is insufficient or conflicting configurations.
   */
  public OAuth2Client(Settings settings) throws OAuthSystemException {
    this.settings = settings;
    final String provider = settings.getString(PROPERTY_PROVIDER);
    if (provider!=null) {
      providerType = OAuthProviderType.valueOf(provider.toUpperCase());
    }
    if (providerType==null) {
      authLocation = settings.getString(PROPERTY_AUTH_LOCATION);
      tokenLocation = settings.getString(PROPERTY_TOKEN_LOCATION);
      if (authLocation==null) {
        throw new OAuthSystemException("You must specify either an OAuth2 "
              + "provider or an authentication location.");
      }
    } else {
      authLocation = providerType.getAuthzEndpoint();
      tokenLocation = providerType.getTokenEndpoint();
    }
    redirReqBuilder = OAuthClientRequest.authorizationLocation(authLocation);
    final String baseUrl = settings.getString(PROPERTY_SONAR_URL);
    final String clientId = settings.getString(PROPERTY_CLIENT_ID);
    clientSecret = settings.getString(PROPERTY_SECRET);
    if (baseUrl==null || clientId==null || clientSecret==null) {
      throw new OAuthSystemException("'"+PROPERTY_SONAR_URL+"', '"+PROPERTY_CLIENT_ID+"', AND '"+PROPERTY_SECRET
            +"' MUST be set to configure OAuthClientRequest.");
    }
    final String callback = baseUrl+(baseUrl.endsWith("/")?"":"/")+"oauth2/callback";
    redirReqBuilder
              .setClientId(clientId)
              .setScope(OAUTH2_SCOPE_EMAIL);
    redirReqBuilder.setRedirectURI(callback);
  }
  
  /**
   * Create an instance of {@link OAuthClientRequest} which uses the {@link Settings}
   * from Sonar to determine the auth location, redirect URL, and client ID.
   * @return An instance of {@link OAuthClientRequest} ready to be used to send messages.
   * @throws OAuthSystemException If there is insufficient or conflicting configurations.
   */
  public OAuthClientRequest getClientRequest() throws OAuthSystemException {
      return redirReqBuilder.buildQueryMessage();
  }
  
  public OAuthClientRequest getTokenClientRequest(UserDetails user, String code) throws OAuthSystemException {
      OAuthClientRequest request = OAuthClientRequest
                                                    .tokenLocation(tokenLocation)
                                                    .setGrantType(GrantType.PASSWORD)
                                                    .setClientId(settings.getString(PROPERTY_CLIENT_ID))
                                                    .setRedirectURI(settings.getString(PROPERTY_SONAR_URL)+"/oauth/callback")
                                                    .setCode(code)
                                                    .buildQueryMessage();
      return request;
  }
}