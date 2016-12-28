package ar.com.telecom.personal.oempro.dao;

import ar.com.telecom.personal.oempro.model.OempAdministrators;

public abstract interface OempAdministratorsDao
{
  public abstract OempAdministrators obtenerUsuarioAdmin()
    throws Exception;
}
