package ar.com.personal.srvnews.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import ar.com.personal.srvnews.Campaign;
import ar.com.personal.srvnews.Mailing;
import ar.com.personal.srvnews.Member;

public class MemberDAOImpl extends SqlMapClientDaoSupport implements MemberDAO {

	@SuppressWarnings("unchecked")
	public List<Member> findMembersWhoReadCampaign(Campaign campaign, Mailing mailing) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("RelCampaignID", campaign.getCampaignID());
		params.put("RelMailingID", mailing.getMailListID());
		return super.getSqlMapClientTemplate().queryForList("findMembersWhoReadCampaign", params);
	}

	@SuppressWarnings("unchecked")
	public List<Member> findUnsubscribedMembers(Campaign campaign) {
		return getSqlMapClientTemplate().queryForList("findUnsubscribedMembers", campaign);
	}

	public void deleteUnsubscribedMembers(Campaign campaign) {
		super.getSqlMapClientTemplate().delete("deleteUnsubscribedMembers", campaign);

	}

	public void insertUnsubscribedMembers(Campaign campaign, int statisticID, int date, Member member) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("RelCampaignID", campaign.getCampaignID());
		params.put("RelCampaignStatisticsID", statisticID);
		params.put("RelMemberID", member.getMemberID());
		params.put("UnsubscriptionDate", date);
		super.getSqlMapClientTemplate().insert("insertUnsubscribedMembers", params);

	}

	@SuppressWarnings("unchecked")
	public List<Member> findMembersWhoClickCampaign(Campaign campaign, Mailing mailing) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("RelCampaignID", campaign.getCampaignID());
		params.put("RelMailingID", mailing.getMailListID());
		return super.getSqlMapClientTemplate().queryForList("findMembersWhoClickCampaign", params);
	}

}
