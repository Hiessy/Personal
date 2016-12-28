package ar.com.personal.srvnews;

import java.sql.Date;

/**
 * @author Alejandro D. Garin
 */
public class Campaign {
  
  /**
   * ID de la campaña
   */
  private int campaignID;
  
  /**
   * La fecha en que la campaña fue enviada al SMTP Server.
   */
  private Date processedOn;

  public int getCampaignID() {
    return campaignID;
  }

  public void setCampaignID(int campaignID) {
    this.campaignID = campaignID;
  }

  public Date getProcessedOn() {
    return processedOn;
  }

  public void setProcessedOn(Date processedOn) {
    this.processedOn = processedOn;
  }

}
