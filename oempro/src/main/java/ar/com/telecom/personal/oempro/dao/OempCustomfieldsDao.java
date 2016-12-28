package ar.com.telecom.personal.oempro.dao;

import ar.com.telecom.personal.oempro.model.OempCustomfields;

public abstract interface OempCustomfieldsDao
{
  public abstract OempCustomfields getCustomFieldByName(String paramString);
}
