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

import java.util.ArrayList;
import java.util.List;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Properties(value = {
    @Property(key=OAuth2Client.PROPERTY_SONAR_URL, name="Sonar Server Base URL"),
    @Property(key=OAuth2Client.PROPERTY_OAUTH2_PROVIDER, name="OAuth2 Provider Name "
            + "(possible values are: FACEBOOK, FOURSQUARE, GITHUB, GOOGLE, INSTAGRAM, "
            + "LINKEDIN, MICROSOFT, PAYPAL, REDDIT, SALESFORCE, YAMMER)"),
    @Property(key=OAuth2Client.PROPERTY_OAUTH2_CLIENT_ID, name="OAuth2 Client ID"),
    @Property(key=OAuth2Client.PROPERTY_OAUTH2_CLIENT_SECRET, name="OAuth2 Client Secret"),
    @Property(key=OAuth2Client.PROPERTY_OAUTH2_LOCATION, name="Authorization URL (Use if predefined OAuth2 provider is not listed.")
})
public class OAuth2Plugin extends SonarPlugin {

  @Override
  public List getExtensions() {
    List<Class> extensions = new ArrayList<Class>();
    extensions.add(OAuth2AuthenticationFilter.class);
    extensions.add(OAuth2Authenticator.class);
    extensions.add(OAuth2Client.class);
    extensions.add(OAuth2LogoutFilter.class);
    extensions.add(OAuth2SecurityRealm.class);
    extensions.add(OAuth2UserProvider.class);
    extensions.add(OAuth2ValidationFilter.class);
    return extensions;
  }

}
