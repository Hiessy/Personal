package ar.com.personal.srvnews;

public class ExportResult {

  /**
   * Cantida TOTAL de campanias procesadas
   */
  private int processed;
  
  /**
   * Cantidad de campanias efectivamente procesadas y exportadas
   */
  private int exported;
  
  /**
   * Cantidad de usuarios exportados desubscriptos
   */
  private int unsubscribed;
  
  /**
   * Path al archivo de exportacion de desubscripciones
   */
  private String exportedBListFile;
  
  /**
   * Path al archivo de exportacion para usuarios que leyeron la campania
   */
  private String exportedOpenFile;
  
  /**
   * Path al archivo de exportacion de usuarios que hicieron clicks
   */
  private String exportedClickFile;
  

  public ExportResult(int processed, int exported) {
    this.processed = processed;
    this.exported = exported;
  }

  public ExportResult() {
  }

  public int getProcessed() {
    return processed;
  }

  public void setProcessed(int processed) {
    this.processed = processed;
  }

  public int getExported() {
    return exported;
  }

  public void setExported(int exported) {
    this.exported = exported;
  }

  public int getUnsubscribed() {
    return unsubscribed;
  }

  public void setUnsubscribed(int unsubscribed) {
    this.unsubscribed = unsubscribed;
  }

  public String getExportedBListFile() {
    return exportedBListFile;
  }

  public void setExportedBListFile(String exportedBListFile) {
    this.exportedBListFile = exportedBListFile;
  }

  public String getExportedOpenFile() {
    return exportedOpenFile;
  }

  public void setExportedOpenFile(String exportedOpenFile) {
    this.exportedOpenFile = exportedOpenFile;
  }

  public String getExportedClickFile() {
    return exportedClickFile;
  }

  public void setExportedClickFile(String exportedClickFile) {
    this.exportedClickFile = exportedClickFile;
  }
  
}
