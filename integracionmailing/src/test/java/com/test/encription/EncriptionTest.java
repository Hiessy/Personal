package com.test.encription;

import ar.com.personal.srvnews.DESEncryptImpl;

public class EncriptionTest {

	
	
	public static void main(String[] args) {
		
		DESEncryptImpl encrip = new DESEncryptImpl("220-157-26-168-182-31-56-158");
		

		System.out.println(encrip.encrypt("oempro_CRM_2016#"));
		System.out.println(encrip.decrypt("81-227-141-244-207-138-215-161-50-231-149-165-138-54-120-222"));
		String encva = encrip.encrypt("oempro_CRM_2016#");
		
		System.out.println(encrip.decrypt("15-207-201-165-74-177-144-11-9-19-43-126-208-41-170-29-163-250-93-199-175-39-150-72"));
	}

}
