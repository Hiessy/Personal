package ar.com.personal.srvnews.service;

import ar.com.personal.srvnews.ExportResult;

public interface ExportService {

  public ExportResult exportClickAndRead();
  public ExportResult exportUnsubscriptions();

}
