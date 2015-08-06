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

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.sonar.api.security.UserDetails;
import org.sonar.api.web.ServletFilter;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
public class OAuth2ValidationFilter extends ServletFilter {
    
    public final static String OAUTH2_CODE = "sonar.oauth2.code";
    
    final private OAuth2Client client;

    public OAuth2ValidationFilter(OAuth2Client client) {
        this.client = client;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserDetails user = (UserDetails)((HttpServletRequest) request).getSession().getAttribute(OAuth2AuthenticationFilter.USER_ATTRIBUTE);
        String code = (String)((HttpServletRequest) request).getSession().getAttribute(OAUTH2_CODE);
        try {
          OAuthClientRequest clientReq = client.getTokenClientRequest(user, code);
          OAuthClient client = new OAuthClient(new URLConnectionClient());
          OAuthJSONAccessTokenResponse tokenResponse = client.accessToken(clientReq);
          ((HttpServletRequest) request).getSession().setAttribute("tokenJson", tokenResponse);
        } catch (OAuthSystemException oase) {
          chain.doFilter(request, response);
        } catch (OAuthProblemException oape) {
          chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
