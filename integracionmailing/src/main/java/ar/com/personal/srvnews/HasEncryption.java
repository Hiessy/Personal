package ar.com.personal.srvnews;

import java.security.Key;

public interface HasEncryption {
  
  public String encrypt(String source);
  public String decrypt(String source);
  public Key getKey();
  public void setKey(String key);
  public boolean isEncrypted(String text);

}
