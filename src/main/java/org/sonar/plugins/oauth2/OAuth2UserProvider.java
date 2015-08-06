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

import static org.sonar.plugins.oauth2.OAuth2AuthenticationFilter.USER_ATTRIBUTE;
import org.sonar.api.security.ExternalUsersProvider;
import org.sonar.api.security.UserDetails;

/**
 * 
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class OAuth2UserProvider extends ExternalUsersProvider {

  @Override
  public UserDetails doGetUserDetails(Context context) {
    return (UserDetails) context.getRequest().getAttribute(USER_ATTRIBUTE);
  }
}
