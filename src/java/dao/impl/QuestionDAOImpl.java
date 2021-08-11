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
import dao.QuestionDAO;
import entity.Answer;
import entity.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has methods for retrieving data from Question tables in database.
 * The method will return an object of the class
 * <code> java.lang.Exception</code> when there is any error querying the data.
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class QuestionDAOImpl extends DBContext implements QuestionDAO {

    /**
     * Get 'num' question in Question table. The result contain a list of
     * <code>Question</code> objects with Id, content, answer, optList,
     * dateCreate, createBy
     *
     * @param numberOfQuestion the number of Question. It is an int number
     * @return a list of <code>entity.Question</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Question> getRandomQuestion(int numberOfQuestion) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT TOP (?) Q.id, Q.content, Q.dateCreate, Q.createBy, A.opt1, A.opt2, A.opt3, A.opt4 \n"
                    + "FROM Question AS Q\n"
                    + "INNER JOIN  Answer AS A\n"
                    + "ON Q.ID = A.QUESTIONID\n"
                    + "ORDER BY NEWID()");
            ps.setInt(1, numberOfQuestion);
            rs = ps.executeQuery();
            while (rs.next()) {
                List<String> optionList = new ArrayList<>();
                optionList.add(rs.getString("opt1"));
                optionList.add(rs.getString("opt2"));
                optionList.add(rs.getString("opt3"));
                optionList.add(rs.getString("opt4"));
                questionList.add(new Question(
                        rs.getInt("id"),
                        rs.getString("content"),
                        optionList,
                        rs.getDate("dateCreate"),
                        rs.getString("createBy"))
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
        return questionList;
    }

    /**
     * This method counts number of pages depend on the total question belong to
     * <code>userName</code> in Question table. The result contains an integer
     * number.
     *
     * @param userName the user name that login. It is
     * <code>java.lang.String</code> object
     * @param pageSize number of question will display each page. It is an
     * integer number.
     * @return number of pages that can be found. It is an integer number
     * @throws java.lang.Exception
     */
    @Override
    public int getNumberOfPage(String userName, int pageSize) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) FROM Question WHERE createBy = ?");
            ps.setString(1, userName);
            rs = ps.executeQuery();
            int numOfPage;
            while (rs.next()) {
                numOfPage = rs.getInt(1) / pageSize;
                if (rs.getInt(1) % pageSize == 0) {
                    return numOfPage;
                } else if (rs.getInt(1) < pageSize) {
                    return numOfPage;
                }
                return (numOfPage + 1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
        return 0;
    }

    /**
     * This method returns a list of <code>entity.Qustion</code> object has
     * found by user name. The result contains list of
     * <code>entity.Qustion</code> object.
     *
     * @param userName the user name that login. It is
     * <code>java.lang.String</code> object
     * @param pageIndex page index that user is stay in. It is an integer
     * number.
     * @param pageSize number of question will display each page. It is an
     * integer number.
     * @return list of <code>entity.Qustion</code> object.
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Question> getQuestionByPaging(String userName, int pageIndex, int pageSize) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(" SELECT * FROM Question "
                    + "WHERE createBy = ? ORDER BY id "
                    + "OFFSET ?*?-? ROWS FETCH NEXT ? ROWS ONLY");
            ps.setString(1, userName);
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageSize);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                questionList.add(new Question(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getDate("dateCreate"),
                        rs.getString("createBy"))
                );
            }
            return questionList;
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
    }

    /**
     * This method gets number of question belong to account that login.
     *
     * @param userName the user name that login. It is
     * <code>java.lang.String</code> object
     * @return number of question belong to account that login. It is an integer
     * number
     * @throws java.lang.Exception
     */
    @Override
    public int getNumberOfQuestionByAccount(String userName) throws Exception {
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try {
            String sql = "SELECT COUNT(*) AS rowNum FROM Question WHERE createBy = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("rowNum");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
        return count;
    }

    /**
     * This method insert new <code>entity.Question</code> object to Question
     * table in the database.
     *
     * @param question It is a <code>entity.Question</code> object.
     * @param answerList list of answer. It is a <code>java.util.List</code>
     * object.
     * @throws java.lang.Exception
     */
    @Override
    public void inserQuestion(Question question, Answer answer, String[] answerList) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int questionID = 0;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String insertQuestionSql = "INSERT INTO [dbo].[Question]\n"
                    + "           ([content]\n"
                    + "           ,[dateCreate]\n"
                    + "           ,[createBy])\n"
                    + "VALUES(?,GETDATE(),?)";
            ps = conn.prepareStatement(insertQuestionSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getContent());

            ps.setString(2, question.getCreateBy());
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                rs.next();
                questionID = rs.getInt(1);
            }

            String insertAnswerSql = "INSERT INTO [dbo].[Answer]\n"
                    + "           ([questionID]\n"
                    + "           ,[opt1]\n"
                    + "           ,[opt2]\n"
                    + "           ,[opt3]\n"
                    + "           ,[opt4]\n"
                    + "           ,[answer])\n"
                    + "VALUES (?, ?,?,?,?,?)";
            ps = conn.prepareStatement(insertAnswerSql);
            for (String a : answerList) {
                ps.setInt(1, questionID);
                ps.setString(2, answer.getOptList().get(0));
                ps.setString(3, answer.getOptList().get(1));
                ps.setString(4, answer.getOptList().get(2));
                ps.setString(5, answer.getOptList().get(3));
                ps.setInt(6, Integer.parseInt(a));
                ps.executeUpdate();
            }
            conn.commit();
        } catch (Exception ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    throw e1;
                }
            }
            throw ex;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
    }

    /**
     * This method gets number of all question in Question table.
     *
     * @return number of all question. It is a integer number.
     * @throws java.lang.Exception
     */
    @Override
    public int getNumberOfAllQuestion() throws Exception {
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try {
            String sql = "SELECT COUNT(*) AS rowNum FROM Question";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("rowNum");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRs(rs);
            closePS(ps);
            closeCon(conn);
        }
        return count;
    }

}
