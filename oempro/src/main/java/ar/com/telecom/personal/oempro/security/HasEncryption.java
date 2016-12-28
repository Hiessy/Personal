package ar.com.telecom.personal.oempro.security;

import java.security.Key;

public abstract interface HasEncryption
{
  public abstract String encrypt(String paramString);
  
  public abstract String decrypt(String paramString);
  
  public abstract Key getKey();
  
  public abstract void setKey(String paramString);
  
  public abstract boolean isEncrypted(String paramString);
}