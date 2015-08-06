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
    @Property(key=OAuth2Client.PROPERTY_PROVIDER, name="OAuth2 Provider Name "
            + "(possible values are: FACEBOOK, FOURSQUARE, GITHUB, GOOGLE, INSTAGRAM, "
            + "LINKEDIN, MICROSOFT, PAYPAL, REDDIT, SALESFORCE, YAMMER)"),
    @Property(key=OAuth2Client.PROPERTY_CLIENT_ID, name="OAuth2 Client ID"),
    @Property(key=OAuth2Client.PROPERTY_SECRET, name="OAuth2 Client Secret"),
    @Property(key=OAuth2Client.PROPERTY_AUTH_LOCATION, name="Authorization URL (Use if predefined OAuth2 "
            + "provider is not listed).")
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
