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
