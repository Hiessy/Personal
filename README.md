# oempro

Se encuantra dos proyectos:

!!! Integraciones Mail !!! en la carpeta: integracionmailing

!!! oempro !!! en la carpeta: oempro


TODO:

Son tres SP en total con la misma dinámica --> parametro (RelCampaignID):

1.	Con open:
column="UserID" jdbcType="INTEGER" 	 oempro.oempro_subscribers_n.SubscriberID
column="EmailAddress" jdbcType="VARCHAR"	 oempro.oempro_subscribers_n.EmailAddress
column="CustomID" jdbcType="VARCHAR"	 oempro.oempro_subscribers_n.SubscriptionIP
column="MailListID" jdbcType="INTEGER"  	 oempro.oempro_stats_open.RelEmailID
column="date" jdbcType="TIMESTAMP"  	 oempro.oempro_stats_open.OpenDate


2.	Con click:
column="UserID" jdbcType="INTEGER" 	 oempro.oempro_subscribers_n.SubscriberID
column="EmailAddress" jdbcType="VARCHAR"	 oempro.oempro_subscribers_n.EmailAddress
column="CustomID" jdbcType="VARCHAR"	 oempro.oempro_subscribers_n.SubscriptionIP
column="MailListID" jdbcType="INTEGER"  	 oempro. oempro_stats_link.RelEmailID
column="date" jdbcType="TIMESTAMP"  	 oempro. oempro_stats_link.ClickDate


3.	Con unsubscribe:
column="UserID" jdbcType="INTEGER" 	 oempro.oempro_subscribers_n.SubscriberID
column="EmailAddress" jdbcType="VARCHAR"	 oempro.oempro_subscribers_n.EmailAddress
column="CustomID" jdbcType="VARCHAR"	 oempro.oempro_subscribers_n.SubscriptionIP
column="MailListID" jdbcType="INTEGER"  	 oempro. oempro_stats_unsubscription.RelEmailID
column="date" jdbcType="TIMESTAMP"  	 oempro. oempro_stats_unsubscription.UnsubscriptionDate
