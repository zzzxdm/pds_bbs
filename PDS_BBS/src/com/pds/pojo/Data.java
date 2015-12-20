package com.pds.pojo;



/**
 * Data entity. @author MyEclipse Persistence Tools
 */

public class Data  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private User user;
     private String filename;
     private String filetype;
     private String filedesc;
     private String filepath;


    // Constructors

    /** default constructor */
    public Data() {
    }

	/** minimal constructor */
    public Data(User user, String filename, String filetype, String filepath) {
        this.user = user;
        this.filename = filename;
        this.filetype = filetype;
        this.filepath = filepath;
    }
    
    /** full constructor */
    public Data(User user, String filename, String filetype, String filedesc, String filepath) {
        this.user = user;
        this.filename = filename;
        this.filetype = filetype;
        this.filedesc = filedesc;
        this.filepath = filepath;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return this.filetype;
    }
    
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFiledesc() {
        return this.filedesc;
    }
    
    public void setFiledesc(String filedesc) {
        this.filedesc = filedesc;
    }

    public String getFilepath() {
        return this.filepath;
    }
    
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
   








}