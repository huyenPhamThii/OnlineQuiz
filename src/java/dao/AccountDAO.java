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
package dao;

import entity.Account;

/**
 * This interface will retrieve data from Account table in the database
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public interface AccountDAO {

    /**
     * Get account by account. Account based on userName and password will be
     * returned. The result is a <code>Account</code> objects with ID, userName,
     * password, type, email
     *
     * @param userName the user name. It is a <code>java.lang.String</code>
     * object
     * @param password the password. It is a <code>java.lang.String</code>
     * object
     * @return a <code>Account</code> objects.
     * @throws java.lang.Exception
     */
    public Account login(String userName, String password) throws Exception;

    /**
     * Insert <code>Account</code> object into Account table. User can login
     * after register successfully
     *
     * @param account It is a <code>Account</code> object
     * @throws java.lang.Exception
     */
    public void register(Account account) throws Exception;

    /**
     * Check account existed. Account based on userName will be returned. The
     * result is true if account existed, false if account does not exist
     *
     * @param userName the user name. It is a <code>java.lang.String</code>
     * object
     * @return a <code>java.lang.Boolean</code> objects.
     * @throws java.lang.Exception
     */
    public boolean isExisted(String userName) throws Exception;
}
