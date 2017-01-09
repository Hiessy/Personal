package ar.com.personal.srvnews.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import ar.com.personal.srvnews.pojo.Campaign;
import ar.com.personal.srvnews.pojo.Mailing;

public class MailingDAOImpl extends SqlMapClientDaoSupport implements MailingDAO {

  @SuppressWarnings("unchecked")
  public List<Mailing> findMailingsForCampaign(Campaign campaign, int customID) {
    
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("campaignID", campaign.getCampaignID());
   // params.put("customID", customID);
    
    return super.getSqlMapClientTemplate().queryForList("findMailingsForCampaign", params);
  }

}
