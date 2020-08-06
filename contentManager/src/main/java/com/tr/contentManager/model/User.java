package com.tr.contentManager.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class User {
    private int id;

    @NotBlank(message = "Please enter username")
    @Size(max = 30, message= "Invalid username: Please enter user between 1-70 characters")
    private String username;

    @NotBlank(message = "Please enter password")
    @Size(max = 100, message= "Invalid password: Please enter password between 1-30 characters")
    private String password;

    @NotBlank(message = "Please enter first name")
    @Size(max = 30, message= "Invalid first name: Please enter first name between 1-30 characters")
    private String firstName;

    @NotBlank(message = "Please enter last name")
    @Size(max = 30, message= "Invalid last name: Please enter last name between 1-30 characters")
    private String lastName;

    @NotBlank(message = "Please enter Email")
    @Size(max = 30, message= "Invalid Email: Please enter Email between 1-30 characters")
    private String email;

    @Size(max = 10, message= "Invalid URL: Please enter valid phone number")
    private String phone;

    @Size(max = 255, message= "Invalid URL: please enter URL between 1-255 characters")
    private String profilePicture;

    private boolean enable;
    private List<Role> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (isEnable() != user.isEnable()) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null) return false;
        if (getProfilePicture() != null ? !getProfilePicture().equals(user.getProfilePicture()) : user.getProfilePicture() != null)
            return false;
        return getRoles() != null ? getRoles().equals(user.getRoles()) : user.getRoles() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getProfilePicture() != null ? getProfilePicture().hashCode() : 0);
        result = 31 * result + (isEnable() ? 1 : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }
}
