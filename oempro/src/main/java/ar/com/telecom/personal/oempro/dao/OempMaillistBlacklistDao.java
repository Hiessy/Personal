package ar.com.telecom.personal.oempro.dao;

public abstract interface OempMaillistBlacklistDao
{
  public abstract boolean isBlacklisted(int paramInt, String paramString);
}
