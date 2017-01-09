package ar.com.personal.srvnews.dao;

import java.util.List;
import java.util.Map;

import ar.com.personal.srvnews.pojo.Campaign;
import ar.com.personal.srvnews.pojo.Mailing;
import ar.com.personal.srvnews.pojo.Member;
import ar.com.personal.srvnews.pojo.MetaMember;

/**
 * @author Alejandro D. Garin
 */
public interface MemberDAO {

	/**
	 * Obtiene una lista de usuarios que leyeron al menos una vez<br>
	 * la campania
	 * 
	 * @param campaign
	 * @return
	 */
	public Member getMember(String tableName, int campaignID);
	
	/**
	 * Obtiene una lista de usuarios que leyeron al menos una vez<br>
	 * la campania
	 * 
	 * @param campaign
	 * @return
	 */
	public List<MetaMember> getMemberInteraction(String tableDate, String tableName, int campaignID);

	/**
	 * Obtiene una lista de usuarios que leyeron al menos una vez<br>
	 * la campania
	 * 
	 * @param campaign
	 * @return
	 */
	public List<Member> findMembersWhoReadCampaign(Campaign campaign, Mailing mailing);

	/**
	 * Obtiene la lista de usuarios que realizaron al menos 1 click<br>
	 * sobre algun link de la campania enviada
	 * 
	 * @param campaign
	 * @return
	 */
	public List<Member> findMembersWhoClickCampaign(Campaign campaign, Mailing mailing);

	/**
	 * Obtener la lista de usuarios que se desubscribieron de la campaña
	 * 
	 * @param campaign
	 * @return
	 */
	public List<Member> findUnsubscribedMembers(Campaign campaign);

	/**
	 * Borrar fisicamente los usuarios marcados como unsubscribed para la
	 * campaña.<br/>
	 * 
	 * @param campaign
	 */
	public void deleteUnsubscribedMembers(Campaign campaign);

	/**
	 * Para Unit test
	 * 
	 * @param campaign
	 * @param StatisticID
	 * @param Date
	 * @param member
	 */
	public void insertUnsubscribedMembers(Campaign campaign, int StatisticID, int Date, Member member);

}
