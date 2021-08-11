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
import dao.AnswerDAO;
import entity.Account;
import entity.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has methods for retrieving data from Answer tables in database.
 * The method will return an object of the class
 * <code> java.lang.Exception</code> when there is any error querying the data.
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class AnswerDAOImpl extends DBContext implements AnswerDAO {

    /**
     * This method get correct answer by questionID from Answer table in the
     * database
     *
     * @param questionID id of question. It is an Integer number.
     * @return a string contains correct answers. It is
     * <code>java.lang.String</code> object
     * @throws java.lang.Exception
     */
    @Override
    public String getCorrectAnsByQuesID(int questionID) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT questionID, answer FROM Answer where questionID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, questionID);
            rs = ps.executeQuery();
            String correctAnswer = "";
            while (rs.next()) {
                correctAnswer += rs.getInt("answer");
            }
            return correctAnswer;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
    }


}
