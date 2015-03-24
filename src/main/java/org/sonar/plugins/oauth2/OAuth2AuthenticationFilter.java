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

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.sonar.api.web.ServletFilter;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Slf4j
public class OAuth2AuthenticationFilter extends ServletFilter {
  
  private OAuth2Client client;

  public OAuth2AuthenticationFilter(OAuth2Client client) {
    this.client = client;
  }
  
  @Override
  public UrlPattern doGetPattern() {
    return UrlPattern.create("/sessions/new");
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
      
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      OAuthClientRequest req = client.getClientRequest();
      
    } catch (OAuthSystemException ex) {
      LOG.error("Error creating OAuthClientRequest", ex);
    }
  }

  @Override
  public void destroy() {
  }

}
