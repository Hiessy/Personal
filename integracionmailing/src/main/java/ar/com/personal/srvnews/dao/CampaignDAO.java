package ar.com.personal.srvnews.dao;

import java.util.List;

import ar.com.personal.srvnews.pojo.Administrator;
import ar.com.personal.srvnews.pojo.Campaign;

/**
 * @author Alejandro D. Garin
 */
public interface CampaignDAO {
  
  /**
   * Obtiene un listado de campañas a procesar segun un administrador y a partir de <br/>
   * la fecha especificada por parametro (esta fecha debe ser igual a la fecha de <br/>
   * implementacion para evitar procesar campañas viejas que no poseen los ID de <br/>
   * relacion para la exportacion. <br/>
   * @param admin - Administrador del newsletter
   * @param fromDate - la fecha de implementacion
   * @return una lista de Campañas
   */
  public List<Campaign> findCampaingToProcess(Administrator admin, String fromDate);
  
  /**
   * Retorna todas las campañas que se encuentran en la base de datos
   * @param admin
   * @param fromDate
   * @return
   */
  public List<Campaign> findAllCampaign(Administrator admin, String fromDate);

  /**
   * Marca la campaña pasada por parametro como procesada, esto evita un doble procesamiento<br/>
   * @param campaign - la campania a procesar
   */
  public void setCampaignAsProcessed(Campaign campaign);
  
  /**
   * Borra el flag de procesados para todas las campañas
   */
  public void cleanAllProcessed();

}
