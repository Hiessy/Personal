package ar.com.personal.srvnews;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * @author Alejandro D. Garin
 */
public class EncryptedDataSource extends BasicDataSource {

  private HasEncryption encryption;
  
  /** 
   * <p>Sets the {@link #password}.</p>
   * <p>
   * Note: this method currently has no effect once the pool has been
   * initialized.  The pool is initialized the first time one of the
   * following methods is invoked: <code>getConnection, setLogwriter,
   * setLoginTimeout, getLoginTimeout, getLogWriter.</code></p>
   * 
   * @param password new value for the password
   */
  public synchronized void setPassword(String password) {
    super.setPassword(encryption.decrypt(password));
  }

  public HasEncryption getEncryption() {
    return encryption;
  }

  public void setEncryption(HasEncryption encryption) {
    this.encryption = encryption;
  }

}
