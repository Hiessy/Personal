Cambios al applicacion context:

1.  ApplicationContext-bean.xml: 13 :: original :<constructor-arg value="8"/> ; nuevo: <constructor-arg value="1"/>
   Comentario: 	Hace referencia al ID del adminitrador con el que va a buscar las campañas.
   				Parece ser un valor que no tiene mucho sentido en el nuevo esqema

Cambios al consultas:

1. Campaign.xml: findCampaingToProcess linea 14 :: 	original:	SELECT DISTINCT c.CampaignID, ProcessedOn				// 		nueva:	SELECT DISTINCT CampaignID, SendProcessStartedOn
																FROM oemp_campaigns_schedule_recursive p 								FROM oempro_campaigns WHERE	SendProcessStartedOn >= #fromDate# 
																INNER JOIN oemp_campaigns c	ON c.CampaignID = p.RelCampaignID			AND	RelOwnerUserID = #administratorID# ORDER BY CampaignID asc;
																WHERE RelCampaignID NOT IN (SELECT CampaignID FROM oemp_integracion) 
																AND	ProcessedOn >= #fromDate# AND RelAdministratorID = #administratorID# ORDER BY CampaignID asc;
   Comentario: 	Se obtiene el ID de las campañas y la fecha en que fueron procesadas. Se redujo la consulta ya que toda la informacion esta en la tabla 'oempro_campaigns'

2. Mailing.xml: linea 8 :: original:	<result property="mailListID" column="MailListID" // nuevo:	<result property="mailListID" column="EmailID"
   Comentario: 	El nombre de la columna se cambio a EmailID y el mapper deberia reflejar esto

3. Mailing.xml: findMailingsForCampaign linea 14 :: original:	select MailListID, cus.RelCustomFieldID from oemp_campaigns_maillists // 		nueva:	SELECT m.EmailID from oempro_campaigns cm
																cm INNER JOIN oemp_maillists m ON cm.RelMailListID = m.MailListID INNER JOIN 			INNER JOIN oempro_emails m ON cm.RelEmailID = m.EmailID
																oemp_maillist_customfields cus ON cus.RelMailListID = m.MailLIstID where 				WHERE cm.CampaignID = #campaignID#
																RelCampaignID = #campaignID# AND RelCustomFieldID = #customID#
   Comentario: 	Se cambio algo "!#$"#$!"#$"

Cambios al codigo: 

1. ExportServiceImpl.java: 72 :: original: if(campaingDate.getTime().compareTo(procesingDate) >= 0 && campaignDatePlusDaysToWait.getTime().compareTo(today.getTime()) < 0)	// nuevo: if (campaign.getProcessedOn().compareTo(procesingDate) >= 0 && campaignDatePlusDaysToWait.getTime().compareTo(today.getTime()) < 0)
   Comentario: 	