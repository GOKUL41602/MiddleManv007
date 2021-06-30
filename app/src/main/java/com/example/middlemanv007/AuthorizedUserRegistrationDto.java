package com.example.middlemanv007;

public class AuthorizedUserRegistrationDto {

    private String email;
    private String userType;
    private String companyName;
    private String companyLicenceNo;
    private String password;
    private String phoneNo;

    public AuthorizedUserRegistrationDto(String email, String userType, String companyName, String companyLicenceNo, String password, String phoneNo) {
        this.email = email;
        this.userType = userType;
        this.companyName = companyName;
        this.companyLicenceNo = companyLicenceNo;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLicenceNo() {
        return companyLicenceNo;
    }

    public void setCompanyLicenceNo(String companyLicenceNo) {
        this.companyLicenceNo = companyLicenceNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "AuthorizedUserRegistrationDto{" +
                "email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyLicenceNo='" + companyLicenceNo + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
