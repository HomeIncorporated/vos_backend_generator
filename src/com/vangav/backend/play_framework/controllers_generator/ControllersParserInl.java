/**
 * "First, solve the problem. Then, write the code. -John Johnson"
 * "Or use Vangav M"
 * www.vangav.com
 * */

/**
 * MIT License
 *
 * Copyright (c) 2016 Vangav
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 * */

/**
 * Community
 * Facebook Group: Vangav Open Source - Backend
 *   fb.com/groups/575834775932682/
 * Facebook Page: Vangav
 *   fb.com/vangav.f
 * 
 * Third party communities for Vangav Backend
 *   - play framework
 *   - cassandra
 *   - datastax
 *   
 * Tag your question online (e.g.: stack overflow, etc ...) with
 *   #vangav_backend
 *   to easier find questions/answers online
 * */

package com.vangav.backend.play_framework.controllers_generator;

import com.vangav.backend.exceptions.VangavException.ExceptionType;
import com.vangav.backend.exceptions.handlers.ArgumentsInl;
import com.vangav.backend.play_framework.controllers_generator.json.ControllersJson;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * ControllersParserInl has inline static methods for parsing controllers
 *   config [controllers.json]
 * */
public class ControllersParserInl {
  
  // disable default instantiation
  private ControllersParserInl () {}

  /**
   * getJavaPackageName
   * @param controllersJson
   * @return java_package member from param controllersJson
   * @throws Exception
   */
  public static String getJavaPackageName (
    final ControllersJson controllersJson) throws Exception {
    
    ArgumentsInl.checkNotNull(
      "controllers json",
      controllersJson,
      ExceptionType.CODE_EXCEPTION);
    
    return controllersJson.java_package;
  }
  
  /**
   * getHasNotifications
   * @param controllersJson
   * @return notifications member from param controllersJson
   * @throws Exception
   */
  public static boolean getHasNotifications (
    final ControllersJson controllersJson) throws Exception {
    
    ArgumentsInl.checkNotNull(
      "controllers json",
      controllersJson,
      ExceptionType.CODE_EXCEPTION);
    
    return controllersJson.notifications;
  }
}
