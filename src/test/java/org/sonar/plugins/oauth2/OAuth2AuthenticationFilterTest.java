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
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class OAuth2AuthenticationFilterTest {
    
    public OAuth2AuthenticationFilterTest() {
    }

    /**
     * Test of init method, of class OAuth2AuthenticationFilter.
     */
    @Test
    public void testInit() throws Exception {
        FilterConfig filterConfig = mock(FilterConfig.class);
        OAuth2Client client = mock(OAuth2Client.class);
        OAuth2AuthenticationFilter instance = new OAuth2AuthenticationFilter(client);
        instance.init(filterConfig);
        assertThat(instance).isNotNull();
        assertThat(instance).isInstanceOf(OAuth2AuthenticationFilter.class);
    }

    /**
     * Test of doFilter method, of class OAuth2AuthenticationFilter.
     */
    @Test
    public void testDoFilter() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        OAuth2Client client = mock(OAuth2Client.class);
        OAuth2AuthenticationFilter instance = new OAuth2AuthenticationFilter(client);
        try {
            instance.doFilter(request, response, chain);
            verify(response).sendRedirect(anyString());
        } catch (IOException ioe) {
            assertThat(ioe).isNull();
        } catch (ServletException se) {
            assertThat(se).isNull();
        }
    }

    /**
     * Test of destroy method, of class OAuth2AuthenticationFilter.
     */
    @Test
    public void testDestroy() {
        OAuth2Client client = mock(OAuth2Client.class);
        OAuth2AuthenticationFilter instance = new OAuth2AuthenticationFilter(client);
        instance.destroy();
    }
    
}
