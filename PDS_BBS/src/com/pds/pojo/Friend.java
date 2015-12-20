package com.pds.pojo;

import java.sql.Timestamp;


/**
 * Friend entity. @author MyEclipse Persistence Tools
 */

public class Friend  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private User userByUserId;
     private User userByFriendId;
     private String userUname;
     private String friendUname;
     private Timestamp addTime;


    // Constructors

    /** default constructor */
    public Friend() {
    }

	/** minimal constructor */
    public Friend(User userByUserId, User userByFriendId) {
        this.userByUserId = userByUserId;
        this.userByFriendId = userByFriendId;
    }
    
    /** full constructor */
    public Friend(User userByUserId, User userByFriendId, String userUname, String friendUname, Timestamp addTime) {
        this.userByUserId = userByUserId;
        this.userByFriendId = userByFriendId;
        this.userUname = userUname;
        this.friendUname = friendUname;
        this.addTime = addTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserByUserId() {
        return this.userByUserId;
    }
    
    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public User getUserByFriendId() {
        return this.userByFriendId;
    }
    
    public void setUserByFriendId(User userByFriendId) {
        this.userByFriendId = userByFriendId;
    }

    public String getUserUname() {
        return this.userUname;
    }
    
    public void setUserUname(String userUname) {
        this.userUname = userUname;
    }

    public String getFriendUname() {
        return this.friendUname;
    }
    
    public void setFriendUname(String friendUname) {
        this.friendUname = friendUname;
    }

    public Timestamp getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
   








}