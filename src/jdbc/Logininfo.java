package jdbc;
// Generated Aug 23, 2017 7:58:06 PM by Hibernate Tools 4.3.1



/**
 * Logininfo generated by hbm2java
 */
public class Logininfo  implements java.io.Serializable {


     private Integer serialno;
     private String username;
     private String email;
     private String password;
     private String ip;

    public Logininfo() {
    }

    public Logininfo(String username, String email, String password, String ip) {
       this.username = username;
       this.email = email;
       this.password = password;
       this.ip = ip;
    }
   
    public Integer getSerialno() {
        return this.serialno;
    }
    
    public void setSerialno(Integer serialno) {
        this.serialno = serialno;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }




}


