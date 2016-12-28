package ar.com.personal.srvnews.dao;

import java.util.List;

import ar.com.personal.srvnews.Campaign;
import ar.com.personal.srvnews.Mailing;

public interface MailingDAO {

  /**
   * Retorna una lista de Mailings relacionados a la campaña
   * @param campaign
   * @param customID
   * @return
   */
  public List<Mailing> findMailingsForCampaign(Campaign campaign, int customID);
  
}
