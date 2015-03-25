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

import org.junit.Test;
import static org.fest.assertions.Assertions.*;
import org.sonar.api.security.Authenticator;
import org.sonar.api.security.ExternalUsersProvider;

/**
 *
 * @author dphillips
 */
public class OAuth2SecurityRealmTest {
    
    private OAuth2SecurityRealm realm;
    
    public OAuth2SecurityRealmTest() {
        realm = new OAuth2SecurityRealm();
    }

    @Test
    public void testDoGetAuthenticator() {
        Authenticator a = realm.doGetAuthenticator();
        assertThat(a).isNotNull().as("The Authenticator MUST NOT be null");
        assertThat(a).isInstanceOf(OAuth2Authenticator.class).as("Authenticator MUST be of type OAuth2Authenticator");
    }
    
    @Test
    public void testGetName() {
        String name = realm.getName();
        assertThat(name).isNotNull().as("Name MUST NOT be NULL");
        assertThat(name).isEqualTo("oauth2");
    }
    
    @Test
    public void testGetUsersProvider() {
        ExternalUsersProvider p = realm.getUsersProvider();
        assertThat(p).isNotNull().as("ExternalUsersProvider MUST NOT be NULL");
        assertThat(p).isInstanceOf(OAuth2UserProvider.class).as("Instance MUST be of type OAuth2UserProvider");
    }
}
