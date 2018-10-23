/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fmg
 */
public class TopicBean {

    private String Id;
    private String username;
    private String title;
    private String content;
    private String city;
    private String topicDate;
    private String replycount;

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param author the author to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the topicDate
     */
    public String getTopicDate() {
        return topicDate;
    }

    /**
     * @param topicDate the topicDate to set
     */
    public void setTopicDate(String topicDate) {
        this.topicDate = topicDate;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the replycount
     */
    public String getReplycount() {
        return replycount;
    }

    /**
     * @param replycount the replycount to set
     */
    public void setReplycount(String replycount) {
        this.replycount = replycount;
    }
}
