Cambios al applicacion context:

1. ApplicationContext-bean.xml: 13 :: original :<constructor-arg value="8"/> ; nuevo: <constructor-arg value="1"/>
   Comentario: 	Hace referencia al ID del adminitrador con el que va a buscar las campañas.
   				Parece ser un valor que no tiene mucho sentido en el nuevo esqema

2. Campaign.xml: findCampaingToProcess linea 14 :: 	original:	SELECT DISTINCT c.CampaignID, ProcessedOn				// 		nueva:	SELECT DISTINCT CampaignID, SendProcessStartedOn
																FROM oemp_campaigns_schedule_recursive p 								FROM oempro_campaigns WHERE	SendProcessStartedOn >= #fromDate# 
																INNER JOIN oemp_campaigns c	ON c.CampaignID = p.RelCampaignID			AND	RelOwnerUserID = #administratorID# ORDER BY CampaignID asc;
																WHERE RelCampaignID NOT IN (SELECT CampaignID FROM oemp_integracion) 
																AND	ProcessedOn >= #fromDate# AND RelAdministratorID = #administratorID# ORDER BY CampaignID asc;
   Comentario: 	Se obtiene el ID de las campañas y la fecha en que fueron procesadas. Se redujo la consulta ya que toda la informacion esta en la tabla 'oempro_campaigns'

3. Mailing.xml: linea 8 :: original:	<result property="mailListID" column="MailListID" // nuevo:	<result property="mailListID" column="EmailID"
   Comentario: 	El nombre de la columna se cambio a EmailID y el mapper deberia reflejar esto

4. Mailing.xml: findMailingsForCampaign linea 14 :: original:	select MailListID, cus.RelCustomFieldID from oemp_campaigns_maillists // 		nueva:	SELECT m.EmailID from oempro_campaigns cm
																cm INNER JOIN oemp_maillists m ON cm.RelMailListID = m.MailListID INNER JOIN 			INNER JOIN oempro_emails m ON cm.RelEmailID = m.EmailID
																oemp_maillist_customfields cus ON cus.RelMailListID = m.MailLIstID where 				WHERE cm.CampaignID = #campaignID#
																RelCampaignID = #campaignID# AND RelCustomFieldID = #customID#
   Comentario: El objetivo es obtener cada una de las direcciones de correo de las campañas a procesar. En la query nueva solo se obtiene el MailListID now called EmailID, the RelCustomFieldID is not used, so it was removed from the new query.

5. ExportServiceImpl.java: 107 :: original: logger.info("--> Verificando si la fecha de la campania: " + campaingDate.getTime().toString() + " es mayor a la fecha de inicio de las exportaciones: " + procesingDate.toString()); // nuevo: logger.info("--> Verificando si la fecha de la campania: " + campaign.getProcessedOn().toString() + " es mayor a la fecha de inicio de las exportaciones: " + procesingDate.toString());
   Comentario: La fecha que esta logueando es la feach de ejecucion del programa y no la fecha en la que se proceso la camapaña. Se cambio a la fecha en que se proceso la campaña

7. ExportServiceImpl.java: 99 :: original: Calendar campaingDate = Calendar.getInstance(); // nuevo: "se removio"
   Comentario: Se removio el campaingDate ya que no se usa en el codigo

8. ExportServiceImpl.java: 114 :: original: if(campaingDate.getTime().compareTo(procesingDate) >= 0 && campaignDatePlusDaysToWait.getTime().compareTo(today.getTime()) < 0)	// nuevo: if (campaign.getProcessedOn().compareTo(procesingDate) >= 0 && campaignDatePlusDaysToWait.getTime().compareTo(today.getTime()) < 0)
   Comentario: 	

9. Member.xml: findMembersWhoClickCampaign linea 20 :: original:	SELECT d.RelMemberID as MemberID, 											//	nueva:
			   														EmailAddress as Email, 
			   														IPOnSubscription as CustomID,
			   														m.RelMailListID as MailListID,
			   														ClickDate as date FROM oemp_campaigns_linkclicks_detailed d
																	INNER JOIN oemp_maillist_members m ON m.RelMemberID = d.RelMemberID
																	WHERE relcampaignID = #RelCampaignID# and m.RelMailListID = #RelMailingID# 
																	GROUP BY d.RelMemberID		
   Comentario: 