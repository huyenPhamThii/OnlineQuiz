/**
 * Copyright(C) 2021, Pham Thi Huyen
 * J3.L.P0001
 * Online Quiz
 *
 * Record of change:
 * DATE          VERSION    AUTHOR      DESCRIPTION
 * 2021-07-10    1.0        HuyenPT     First Implement
 * 2021-07-13    2.0        HuyenPT     Fix header class, header method comment
 * 2021-07-15    3.0        HuyenPT     Fix header class, header method comment
 */
package entity;

/**
 * This class contains properties, constructor, getter, setter of
 * <code>entity.Account</code> object
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class Account {

    private String userName;
    private String password;
    private String email;
    private int type;

    /**
     * Empty constructor
     */
    public Account() {
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include userName, password,
     * type, email
     *
     * @param userName the userName of <code>entity.Account</code> object. It is
     * a <code>java.lang.String</code> object
     * @param password the password of <code>entity.Account</code> object. It is
     * a <code>java.lang.String</code> object
     * @param email the email of <code>entity.Account</code> object. It is a
     * <code>java.lang.String</code> object
     * @param type the type account of <code>entity.Account</code> object. It is
     * a <code>java.lang.boolean</code>
     */
    public Account(String userName, String password, String email, int type) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    /**
     * Get value from userName attribute of <code>entity.Account</code> object
     *
     * @return user name. It is a <code>java.lang.String</code> object
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set value for userName attribute of <code>entity.Account</code> object
     *
     * @param userName user name. It is a <code>java.lang.String</code> object
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get value from password attribute of <code>entity.Account</code> object
     *
     * @return password. It is a <code>java.lang.String</code> object
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set value for password attribute of <code>entity.Account</code> object
     *
     * @param password password. It is a <code>java.lang.String</code> object
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get value from email attribute of <code>entity.Account</code> object
     *
     * @return email. It is a <code>java.lang.String</code> object
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set value for email attribute of <code>entity.Account</code> object
     *
     * @param email email.It is a <code>java.lang.String</code> object
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get value from type attribute of <code>entity.Account</code> object
     *
     * @return type of user. It is an integer number
     */
    public int getType() {
        return type;
    }

    /**
     * Set value for type attribute of <code>entity.Account</code> object
     *
     * @param type type of user. It is an integer number
     */
    public void setType(int type) {
        this.type = type;
    }

}
