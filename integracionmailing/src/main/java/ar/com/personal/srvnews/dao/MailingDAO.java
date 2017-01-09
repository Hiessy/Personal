package ar.com.personal.srvnews.dao;

import java.util.List;

import ar.com.personal.srvnews.pojo.Campaign;
import ar.com.personal.srvnews.pojo.Mailing;

public interface MailingDAO {

  /**
   * Retorna una lista de Mailings relacionados a la campaña
   * @param campaign
   * @param customID
   * @return
   */
  public List<Mailing> findMailingsForCampaign(Campaign campaign, int customID);
  
}
