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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * This class contains properties, constructor, getter, setter of
 * <code>entity.Question</code> object
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class Question {

    private int id;
    private String content;
    private List<String> optList;
    private Date dateCreate;
    private String createBy;

    /**
     * Empty constructor
     */
    public Question() {
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include id, content, optList,
     * answer, dateCreate, createBy
     *
     * @param id the id of <code>entity.Question</code> object. It is an int
     * number
     * @param content the content of <code>entity.Question</code>. It is a
     * <code>java.lang.String</code> object
     * @param optList the list option answer of <code>entity.Question</code>. It
     * is a <code>java.util.List</code>
     * @param dateCreate the date created of <code>entity.Question</code>. It is
     * a <code>java.util.Date</code> object
     * @param createBy the author of <code>entity.Question</code>. It is a
     * <code>java.util.String</code> object
     */
    public Question(int id, String content, List<String> optList, Date dateCreate, String createBy) {
        this.id = id;
        this.content = content;
        this.optList = optList;
        this.dateCreate = dateCreate;
        this.createBy = createBy;
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include id, content, optList,
     * answer, dateCreate, createBy
     *
     * @param id the id of <code>entity.Question</code> object. It is an int
     * number
     * @param content the content of <code>entity.Question</code>. It is a
     * <code>java.lang.String</code> object
     * @param dateCreate the date created of <code>entity.Question</code>. It is
     * a <code>java.util.Date</code> object
     * @param createBy the author of <code>entity.Question</code>. It is a
     * <code>java.util.String</code> object
     */
    public Question(int id, String content, Date dateCreate, String createBy) {
        this.id = id;
        this.content = content;
        this.dateCreate = dateCreate;
        this.createBy = createBy;
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include content, optList
     *
     * @param content the content of <code>entity.Question</code>. It is a
     * <code>java.lang.String</code> object
     */
    public Question(String content) {
        this.content = content;
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include id, content, answer,
     * opt, createBy
     *
     * @param content the content of <code>entity.Question</code>. It is a
     * <code>java.lang.String</code> object
     * @param createBy the author of <code>entity.Question</code>. It is a
     * <code>java.util.String</code> object
     */
    public Question(String content, String createBy) {
        this.content = content;
        this.createBy = createBy;
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
     * Get value from content attribute of <code>entity.Question</code> object
     *
     * @return content. It is a <code>java.lang.String</code> object
     */
    public String getContent() {
        return content;
    }

    /**
     * Set value from content attribute of <code>entity.Question</code> object
     *
     * @param content It is a <code>java.lang.String</code> object
     */
    public void setContent(String content) {
        this.content = content;
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
     * Get value from dateCreate attribute of <code>entity.Question</code>
     * object
     *
     * @return date create. It is a <code>java.util.Date</code> object
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * Get value from dateCreate attribute of <code>entity.Question</code>
     * object
     *
     * @param dateCreate date create. It is a <code>java.util.Date</code> object
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * Get value from createBy attribute of <code>entity.Question</code> object
     *
     * @return author. It is a <code>java.lang.String</code> object
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Get value from createBy attribute of <code>entity.Question</code> object
     *
     * @param createBy author. It is a <code>java.lang.String</code> object
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * Get date format
     *
     * @return dateCreate dd-MMM-yyyy
     */
    public String getDateFormat() {
        return new SimpleDateFormat("dd-MMM-yyyy").format(this.dateCreate);
    }

}
