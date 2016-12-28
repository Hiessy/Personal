package ar.com.personal.srvnews.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import ar.com.personal.srvnews.Campaign;
import ar.com.personal.srvnews.Administrator;

/**
 * @author Alejandro D. Garin
 */
public class CampaignDAOImpl extends SqlMapClientDaoSupport implements CampaignDAO {

  @SuppressWarnings("unchecked")
  public List<Campaign> findCampaingToProcess(Administrator admin, String fromDate) {
    
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("fromDate", fromDate);
    params.put("administratorID", admin.getAdministratorID());
    
    return getSqlMapClientTemplate().queryForList("findCampaingToProcess", params);
  }

  public void setCampaignAsProcessed(Campaign campaign) {
    
    super.getSqlMapClientTemplate().insert("setCampaignAsProcessed", campaign);
  }

  public void cleanAllProcessed() {
    super.getSqlMapClientTemplate().delete("deleteCampaignProcessed");
    
  }

  @SuppressWarnings("unchecked")
  public List<Campaign> findAllCampaign(Administrator admin, String fromDate) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("fromDate", fromDate);
    params.put("administratorID", admin.getAdministratorID());
    
    return getSqlMapClientTemplate().queryForList("findAllCampaign", params);
  }

}
