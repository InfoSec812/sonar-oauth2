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

import org.sonar.api.security.Authenticator;
import org.sonar.api.security.ExternalUsersProvider;
import org.sonar.api.security.SecurityRealm;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
public class OAuth2SecurityRealm extends SecurityRealm {
    
    private static final String KEY = "oauth2";

    @Override
    public Authenticator doGetAuthenticator() {
        return new OAuth2Authenticator();
    }

    @Override
    public ExternalUsersProvider getUsersProvider() {
        return new OAuth2UserProvider();
    }
    
    @Override
    public String getName() {
        return KEY;
    }
}
