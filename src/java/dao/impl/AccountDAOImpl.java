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
package dao.impl;

import context.DBContext;
import dao.AccountDAO;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class has methods for retrieving data from User tables in database. The
 * method will return an object of the class <code> java.lang.Exception</code>
 * when there is any error querying the data.
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class AccountDAOImpl extends DBContext implements AccountDAO {

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
    @Override
    public Account login(String userName, String password) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM Account WHERE userName = ? AND password = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString("userName"), rs.getString("password"), rs.getString("email"), rs.getInt("type"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
        return null;

    }

    /**
     * Insert <code>Account</code> object into Account table. User can login
     * after register successfully
     *
     * @param account It is a <code>Account</code> object
     * @throws java.lang.Exception
     */
    @Override
    public void register(Account account) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("INSERT INTO [dbo].[Account]\n"
                    + "           ([userName]\n"
                    + "           ,[password]\n"
                    + "           ,[email]\n"
                    + "           ,[type])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)");
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setInt(4, account.getType());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
    }

    /**
     * Check account existed. Account based on userName will be returned. The
     * result is true if account existed, false if account does not exist
     *
     * @param userName the user name. It is a <code>java.lang.String</code>
     * object
     * @return a <code>java.lang.Boolean</code> objects.
     * @throws java.lang.Exception
     */
    @Override
    public boolean isExisted(String userName) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM Account WHERE userName = ?");
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
        return false;
    }
}
