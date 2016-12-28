package ar.com.telecom.personal.oempro.dao;

import ar.com.telecom.personal.oempro.model.OempMembers;

public abstract interface OempMembersDao
{
  public abstract OempMembers getMember(String paramString);
  
  public abstract int getLastOempMemberId();
}
