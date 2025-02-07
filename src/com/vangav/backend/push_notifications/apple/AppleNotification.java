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

package com.vangav.backend.push_notifications.apple;

import java.io.Serializable;
import java.util.HashMap;

import com.vangav.backend.content.formatting.SerializationInl;
import com.vangav.backend.push_notifications.apple.dispatch_message.AppleNotificationDispatchable;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * AppleNotification class represents an Apple Push Notification to be sent
 *   to Apple devices through Appler's APNS (Apple Push Notifications Server)
 * Serializable to be embedded into a JSON object and dispatched to a worker
 *   instance if needed
 * */
public class AppleNotification implements Serializable {

  /**
   * Generated serial version ID
   */
  private static final long serialVersionUID = -8584619114492996393L;

  private static final int kInvalidBadgeNumber = -1;
  
  /**
   * MANDATORY
   * deviceToken (e.g.: an iPhone's token) - i.e.: a device's ID for the push
   *   notification to reach to the right device
   */
  private String deviceToken;
  /**
   * OPTIONAL
   * overrides the number in the red circle on an app's icon that identifies
   *   the number of new events (notifications, messages, photos, etc ...)
   */
  private int badgeNumber;
  /**
   * OPTIONAL
   * sound is used to override the device's default notification sound
   *   possibly a silent notification
   */
  private String sound;
  /**
   * OPTIONAL
   * the notification's message (e.g.: the one that appears on the iPhone's
   *   lock screen)
   * Can be left empty for silent notifications. Silent notifications are
   *   used for events like marking seen messages in real-time without having
   *   to pop-it-up in the form of a regular notification, so that it reaches
   *   the app without distracting the person using the device.
   */
  private String alertBody;
  /**
   * OPTIONAL
   * customProperties doesn't show to the person using the device
   * Custom properties are used by the app. e.g.: ID of the user who created
   *   the notification's event, ID of the photo to add the comment to, etc ...
   */
  private HashMap<String, String> customProperties;
  
  // disable default instantiation
  private AppleNotification () {}
  
  /**
   * Constructor AppleNotification
   * @param appleNotificationBuilder
   * @return new AppleNotification Object
   */
  private AppleNotification (
    AppleNotificationBuilder appleNotificationBuilder) throws Exception {
    
    this.deviceToken = appleNotificationBuilder.deviceToken;
    this.badgeNumber = appleNotificationBuilder.badgeNumber;
    this.sound = appleNotificationBuilder.sound;
    this.alertBody = appleNotificationBuilder.alertBody;
    this.customProperties = appleNotificationBuilder.customProperties;
  }
  
  /**
   * fromAppleNotificationDispatchable
   * usually used on the worker instance side where dispatch messages are
   *   received then gets executed
   * @param appleNotificationDispatchable is a JSON Object with a serialized
   *          version of an AppleNotification Object
   * @return the deserialized version of the AppleNotification Object
   * @throws Exception
   */
  public static AppleNotification fromAppleNotificationDispatchable (
    AppleNotificationDispatchable
      appleNotificationDispatchable) throws Exception {
    
    return
      SerializationInl.<AppleNotification>deserializeObject(
        appleNotificationDispatchable.serialized_message);
  }
  
  /**
   * getDeviceToken
   * @return device token
   */
  public String getDeviceToken () throws Exception {
    
    return this.deviceToken;
  }
  
  /**
   * isValidBadgeNumber
   * @return true if badge number is valid and false otherwise
   */
  public boolean isValidBadgeNumber () throws Exception {
    
    if (this.badgeNumber != kInvalidBadgeNumber) {
      
      return true;
    }
    
    return false;
  }
  
  /**
   * getBadgeNumber
   * @return badge number
   */
  public int getBadgeNumber () throws Exception {
    
    return this.badgeNumber;
  }
  
  /**
   * getSound
   * @return notification's sound
   */
  public String getSound () throws Exception {
    
    return this.sound;
  }
  
  /**
   * getAlertBody
   * @return alert's body
   */
  public String getAlertBody () throws Exception {
    
    return this.alertBody;
  }
  
  /**
   * hasCustomProperties
   * @return true if this notification has custom properties and false
   *           otherwise
   */
  public boolean hasCustomProperties () throws Exception {
    
    if (this.customProperties.isEmpty() == true) {
      
      return false;
    }
    
    return true;
  }
  
  /**
   * addCustomProperty
   * adds a custom property (e.g.: key=user_id, value=1234)
   * @param key
   * @param value
   */
  public void addCustomProperty (String key, String value) throws Exception {
    
    this.customProperties.put(key, value);
  }
  
  /**
   * getCustomProperty
   * @param key
   * @return custom property's value that corresponds to param key
   */
  public String getCustomProperty (String key) throws Exception {
    
    return this.customProperties.get(key);
  }
  
  /**
   * getCustomProperties
   * @return custom properties
   */
  public HashMap<String, String> getCustomProperties () throws Exception {
    
    return this.customProperties;
  }
  
  @Override
  public String toString () {
    
    return
      "Apple Notification:\n"
      + "Device Token ["
      + this.deviceToken
      + "]\nBadge Number ["
      + this.badgeNumber
      + "]\nSound ["
      + this.sound
      + "]\nAlert Body ["
      + this.alertBody
      + "]\nCustom Properties ["
      + this.customProperties.toString()
      + "]";
  }
  
  /**
   * MANDATORY, the only way to create an AppleNotification is to use this
   *              builder
   * AppleNotificationBuilder: is a builder for ApplePushNotification,
   *                             all class member variables are optional
   *                             except for deviceToken
   *                                 
   * Usage example:
   *   AppleNotification appleNotification =
   *     AppleNotification
   *       .AppleNotificationBuilder(
   *         "740f4707bebcf74f9b7c25d48e3358945f6aa01da5ddb387462c7eaf61bb78ad")
   *       .badgeNumber(5)
   *       .alertBody("John sent you a new message")
   *       .build();
   */
  public static class AppleNotificationBuilder {
    
    private final String deviceToken;
    private int badgeNumber;
    private String sound;
    private String alertBody;
    private HashMap<String, String> customProperties;
    
    /**
     * Constructor AppleNotificationBuilder
     * @param deviceToken: (e.g: an iPhone device token)
     */
    public AppleNotificationBuilder (String deviceToken) throws Exception {
      
      this.deviceToken = deviceToken;
      this.badgeNumber = kInvalidBadgeNumber;
      this.sound = null;
      this.alertBody = "";
      this.customProperties = new HashMap<String, String>();
    }
    
    /**
     * badgeNumber
     * sets badge number value (badge number is the number in the red circle
     *   on an app's icon as new notifications are received for that app)
     * @param badgeNumber
     * @return AppleNotificationBuilder with badge number value set
     */
    public AppleNotificationBuilder badgeNumber (
      int badgeNumber) throws Exception {
      
      this.badgeNumber = badgeNumber;
      
      return this;
    }
    
    /**
     * sound
     * sets a custom sound for this notification to override the default one
     *   or sets sound to a silent one
     * @param sound
     * @return AppleNotificationBuilder with sound value set
     */
    public AppleNotificationBuilder sound (String sound) throws Exception {
      
      this.sound = sound;
      
      return this;
    }
    
    /**
     * alertBody
     * sets the message that appears as part of the notification (e.g.:
     *   on the iPhone's lock screen)
     * @param alertBody
     * @return AppleNotificationBuilder with alert body value set
     */
    public AppleNotificationBuilder alertBody (
      String alertBody) throws Exception {
      
      this.alertBody = alertBody;
      
      return this;
    }
    
    /**
     * customProperties
     * sets values for the receiving app to use while processing this
     *   notification
     * @param customProperties
     * @return AppleNotificationBuilder with customProperties value set
     */
    public AppleNotificationBuilder customProperties (
      HashMap<String, String> customProperties) throws Exception {
      
      if (customProperties == null) {
        
        return this;
      }
      
      this.customProperties = customProperties;
      
      return this;
    }
    
    /**
     * addCustomProperty
     * adds a custom property
     * @param key
     * @param value
     * @return AppleNotificationBuilder with  a custom property added
     */
    public AppleNotificationBuilder addCustomProperty (
      String key,
      String value) throws Exception {
      
      this.customProperties.put(key, value);
      
      return this;
    }
    
    /**
     * build
     * @return new AppleNotification Object configured with this builder's
     *           values
     */
    public AppleNotification build () throws Exception {
      
      return new AppleNotification (this);
    }
  }
}
