package ar.com.personal.srvnews;

public class Member {
  
  /**
   * el ID del usuario en la base de datos - tabla oemp_members
   */
  private int memberID;

  /**
   * La direccion de correo
   */
  private String email;  
  
  /**
   * El custom ID del usuario, este customID consiste en el ID de la mailing list y<br>
   * el String ID con 4 campos separados por pipes que envia BI
   */
  private MailingCustomID mailingCustomID;
  
  public Member() {
    
  }
  
  public Member(int id) {
    this.memberID = id;
  }

  public int getMemberID() {
    return memberID;
  }

  public void setMemberID(int memberID) {
    this.memberID = memberID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public MailingCustomID getMailingCustomID() {
    return mailingCustomID;
  }

  public void setMailingCustomID(MailingCustomID mailingCustomID) {
    this.mailingCustomID = mailingCustomID;
  }

}
