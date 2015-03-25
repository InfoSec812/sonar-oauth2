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
