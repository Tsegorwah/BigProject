/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Reply {
	private int id;
	private String username;
    private String replyDate;
    private String replyContent;
    private int topicId;
	
    public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reply(int id, String username, String replyDate, String replyContent,int topicId) {
		super();
		this.id = id;
		this.username = username;
		this.replyDate = replyDate;
		this.replyContent = replyContent;
                this.topicId=topicId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplycontent() {
		return replyContent;
	}

	public void setReplycontent(String replyContent) {
		this.replyContent = replyContent;
	}
        public int gettopicId() {
		return topicId;
	}

	public void settopicId(int id) {
		this.topicId = topicId;
	}
	
    
}