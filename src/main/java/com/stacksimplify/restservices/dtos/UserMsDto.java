package com.stacksimplify.restservices.dtos;

public class UserMsDto {

    private Long id;
    private String username;
    private String emailAddress;
    private String roleName;

    public UserMsDto(Long id, String username, String emailAddress, String roleName) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public UserMsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
