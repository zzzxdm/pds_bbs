package com.pds.pojo;



/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String username;
     private String password;
     private String name;
     private String gender;
     private Integer age;
     private String email;
     private String phone;
     private String picture;
     private Short scale;


    // Constructors

    /** default constructor */
    public Admin() {
    }

	/** minimal constructor */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /** full constructor */
    public Admin(String username, String password, String name, String gender, Integer age, String email, String phone, String picture, Short scale) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.picture = picture;
        this.scale = scale;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return this.picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Short getScale() {
        return this.scale;
    }
    
    public void setScale(Short scale) {
        this.scale = scale;
    }
   








}