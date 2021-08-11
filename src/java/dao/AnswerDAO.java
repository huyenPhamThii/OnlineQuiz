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

/**
 * This interface will retrieve data from Answer table in the database
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public interface AnswerDAO {

    /**
     * This method get correct answer by questionID from Answer table in the
     * database
     *
     * @param questionID id of question. It is an Integer number.
     * @return a string contains correct answers. It is
     * <code>java.lang.String</code> object
     * @throws java.lang.Exception
     */
    public String getCorrectAnsByQuesID(int questionID) throws Exception;

}
