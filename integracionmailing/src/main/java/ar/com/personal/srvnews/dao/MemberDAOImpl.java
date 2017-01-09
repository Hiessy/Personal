package ar.com.personal.srvnews.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import ar.com.personal.srvnews.pojo.Campaign;
import ar.com.personal.srvnews.pojo.Mailing;
import ar.com.personal.srvnews.pojo.Member;
import ar.com.personal.srvnews.pojo.MetaMember;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaMember> getMemberInteraction(String tableDate, String tableName, int campaignID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("TableDate", tableDate);
		params.put("TableName", tableName);
		params.put("RelCampaignID", campaignID);
		return super.getSqlMapClientTemplate().queryForList("Member.getMembersWhoClickCampaign", params);
	}

	@Override
	public Member getMember(String tableName,int subsciberID) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SubscriberID", subsciberID);
		parameters.put("TableName", tableName);
		
		return (Member) super.getSqlMapClientTemplate().queryForObject("Member.getDataEmail", parameters);
	}

}
