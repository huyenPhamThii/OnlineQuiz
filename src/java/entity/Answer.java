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

import java.util.List;

/**
 * This class contains properties, constructor, getter, setter of
 * <code>entity.Answer</code> object
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class Answer {

    private int id;
    private int questionID;
    private List<String> optList;
    private int answer;

    /**
     * Empty constructor
     */
    public Answer() {
    }

    /**
     * Full constructor
     *
     * @param id id of <code>entity.Answer</code> object. It is an Integer
     * number.
     * @param questionID question id of <code>entity.Question</code> object. It
     * is <code>java.lang.String</code> object
     * @param optList the list option answer of <code>entity.Question</code>. It
     * is a <code>java.util.List</code>
     * @param answer answer of <code>entity.Question</code> object. It is
     * <code>java.lang.String</code> object
     */
    public Answer(int id, int questionID, List<String> optList, int answer) {
        this.id = id;
        this.questionID = questionID;
        this.optList = optList;
        this.answer = answer;
    }

    public Answer(List<String> optList) {
        this.optList = optList;
    }

    /**
     * Get value from id attribute of <code>entity.Question</code> object
     *
     * @return id. It is an integer number.
     */
    public int getId() {
        return id;
    }

    /**
     * Set value from id attribute of <code>entity.Question</code> object
     *
     * @param id It is an integer number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get value from question id attribute of <code>entity.Question</code>
     * object
     *
     * @return question id. It is an integer number.
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * Set value from question id attribute of <code>entity.Question</code>
     * object
     *
     * @param questionID question id. It is an integer number.
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * Get value from optList attribute of <code>entity.Question</code> object
     *
     * @return list of option. It is a <code>java.util.List</code> object
     */
    public List<String> getOptList() {
        return optList;
    }

    /**
     * Get value from optList attribute of <code>entity.Question</code> object
     *
     * @param optList list of option. It is a <code>java.util.List</code>
     * object.
     */
    public void setOptList(List<String> optList) {
        this.optList = optList;
    }

    /**
     * Get value from answer attribute of <code>entity.Question</code> object
     *
     * @return answer. It is an integer number.
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * Set value from answer attribute of <code>entity.Question</code> object
     *
     * @param answer It is an integer number.
     */
    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
