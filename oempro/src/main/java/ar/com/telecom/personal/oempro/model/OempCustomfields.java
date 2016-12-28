 package ar.com.telecom.personal.oempro.model;
 
 
 public class OempCustomfields
 {
   private Integer customFieldId;
   public static final String CUSTOM_FIELD_1 = "CUSTOM1";
   public static final String CUSTOM_FIELD_2 = "CUSTOM2";
   public static final String CUSTOM_FIELD_3 = "CUSTOM3";
   public static final String CUSTOM_FIELD_ID = "ID";
   
   public Integer getCustomFieldId()
   {
     return this.customFieldId;
   }
   
   public void setCustomFieldId(Integer customFieldId) {
     this.customFieldId = customFieldId;
   }
 }
