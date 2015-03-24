/*
 * SonarQube OAuth2 Plugin
 * Copyright (C) 2015 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.oauth2;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.sonar.api.config.Settings;
import org.sonar.api.ServerExtension;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class OAuth2Client implements ServerExtension {
    
  public static final String PROPERTY_SONAR_URL = "sonar.oauth2.sonarServerUrl";
  public static final String PROPERTY_OAUTH2_PROVIDER = "sonar.oauth2.provider";
  public static final String PROPERTY_OAUTH2_CLIENT_ID = "sonar.oauth2.clientid";
  public static final String PROPERTY_OAUTH2_LOCATION = "sonar.oauth2.location";
  public static final String PROPERTY_OAUTH2_CLIENT_SECRET = "sonar.oauth2.secret";

  static final String OAUTH2_SCOPE_EMAIL = "email";
  
  OAuthProviderType providerType = null;
  
  AuthenticationRequestBuilder reqBuilder = null;

  /**
   * Default constructor. Accepts Sonar {@link Settings} in order to bootstrap
   * the class.
   * @param settings The {@link Settings} from the currently running Sonar instance.
   * @throws OAuthSystemException If there is insufficient or conflicting configurations.
   */
  public OAuth2Client(Settings settings) throws OAuthSystemException {
    final String provider = settings.getString(PROPERTY_OAUTH2_PROVIDER);
    if (provider!=null) {
      providerType = OAuthProviderType.valueOf(provider.toUpperCase());
    }
    String authLocation;
    if (providerType==null) {
      authLocation = settings.getString(PROPERTY_OAUTH2_LOCATION);
      if (authLocation==null) {
        throw new OAuthSystemException("You must specify either an OAuth2 "
              + "provider or an authentication location.");
      }
      reqBuilder = OAuthClientRequest.authorizationLocation(authLocation);
    } else {
      reqBuilder = OAuthClientRequest.authorizationProvider(providerType);
    }
    final String baseUrl = settings.getString(PROPERTY_SONAR_URL);
    final String callback = baseUrl+(baseUrl.endsWith("/")?"":"/")+"oauth2/callback";
    final String clientId = settings.getString(PROPERTY_OAUTH2_CLIENT_ID);
    reqBuilder
              .setClientId(clientId)
              .setScope(OAUTH2_SCOPE_EMAIL);
    reqBuilder.setRedirectURI(callback);
  }
  
  /**
   * Create an instance of {@link OAuthClientRequest} which uses the {@link Settings}
   * from Sonar to determine the auth location, redirect URL, and client ID.
   * @return An instance of {@link OAuthClientRequest} ready to be used to send messages.
   * @throws OAuthSystemException If there is insufficient or conflicting configurations.
   */
  public OAuthClientRequest getClientRequest() throws OAuthSystemException {
      return reqBuilder.buildQueryMessage();
  }
}
