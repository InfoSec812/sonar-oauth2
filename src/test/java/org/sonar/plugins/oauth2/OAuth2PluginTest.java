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

import java.util.List;
import org.junit.Test;
import static org.fest.assertions.Assertions.*;

/**
 * Unit tests for the OAuth2Plugin class
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class OAuth2PluginTest {

  public OAuth2PluginTest() {
  }

  /**
   * Test of getExtensions method, of class OAuth2Plugin.
   */
  @Test
  public void testGetExtensions() {
    System.out.println("getExtensions");
    OAuth2Plugin instance = new OAuth2Plugin();
    List result = instance.getExtensions();
    assertThat(result).isNotNull().as("getExtensions MUST NOT return a NULL object.");
    assertThat(result).isNotEmpty().as("getExtensions MUST NOT return an empty list.");
  }

}
