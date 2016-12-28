package ar.com.telecom.personal.oempro.service;

import java.io.File;

public abstract interface ImportMaillistService
{
  public abstract void importar();
  
  public abstract void setFile(File paramFile);
}

