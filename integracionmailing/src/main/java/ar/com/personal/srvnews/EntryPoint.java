package ar.com.personal.srvnews;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.personal.srvnews.service.ExportService;

public class EntryPoint {

  private static final Logger logger = Logger.getLogger(EntryPoint.class);
  
  public static void main(String[] args) {
    
    try {
      logger.info("Iniciando version 1.0.2 ...");
      ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
      ExportService service = (ExportService) context.getBean("exportService");
      service.exportUnsubscriptions();
      service.exportClickAndRead();
      logger.info("Terminando...");
    } catch (Throwable e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }

  }

}
