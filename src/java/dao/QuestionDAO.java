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

import entity.Answer;
import entity.Question;
import java.util.ArrayList;

/**
 * This interface will retrieve data from Question table in the database
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public interface QuestionDAO {

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
    public ArrayList<Question> getRandomQuestion(int numberOfQuestion) throws Exception;

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
    public int getNumberOfPage(String userName, int pageSize) throws Exception;

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
    public ArrayList<Question> getQuestionByPaging(String userName, int pageIndex, int pageSize) throws Exception;

    /**
     * This method gets number of question belong to account that login.
     *
     * @param userName the user name that login. It is
     * <code>java.lang.String</code> object
     * @return number of question belong to account that login. It is an integer
     * number
     * @throws java.lang.Exception
     */
    public int getNumberOfQuestionByAccount(String userName) throws Exception;

    /**
     * This method gets number of all question in Question table.
     *
     * @return number of all question. It is a integer number.
     * @throws java.lang.Exception
     */
    public int getNumberOfAllQuestion() throws Exception;

    /**
     * This method insert new <code>entity.Question</code> object to Question
     * table in the database.
     *
     * @param question It is a <code>entity.Question</code> object.
     * @throws java.lang.Exception
     */
    public void inserQuestion(Question question, Answer answer, String[] answerList) throws Exception;

}
