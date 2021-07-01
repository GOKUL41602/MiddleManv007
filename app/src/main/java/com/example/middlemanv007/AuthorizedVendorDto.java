package com.example.middlemanv007;

public class AuthorizedVendorDto {

    private String vendorName;
    private String vendorEmail;
    private String vendorPhoneNo;
    private String vendorPassword;
    private String vendorStartYear;
    private String vendorSourceType;
    private String userName;
    private String userType;

    public AuthorizedVendorDto() {
    }

    public AuthorizedVendorDto(String vendorName, String vendorEmail, String vendorPhoneNo, String vendorPassword, String vendorStartYear, String vendorSourceType, String userName, String userType) {
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPhoneNo = vendorPhoneNo;
        this.vendorPassword = vendorPassword;
        this.vendorStartYear = vendorStartYear;
        this.vendorSourceType = vendorSourceType;
        this.userName = userName;
        this.userType = userType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorPhoneNo() {
        return vendorPhoneNo;
    }

    public void setVendorPhoneNo(String vendorPhoneNo) {
        this.vendorPhoneNo = vendorPhoneNo;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }

    public String getVendorStartYear() {
        return vendorStartYear;
    }

    public void setVendorStartYear(String vendorStartYear) {
        this.vendorStartYear = vendorStartYear;
    }

    public String getVendorSourceType() {
        return vendorSourceType;
    }

    public void setVendorSourceType(String vendorSourceType) {
        this.vendorSourceType = vendorSourceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AuthorizedVendorDto{" +
                "vendorName='" + vendorName + '\'' +
                ", vendorEmail='" + vendorEmail + '\'' +
                ", vendorPhoneNo='" + vendorPhoneNo + '\'' +
                ", vendorPassword='" + vendorPassword + '\'' +
                ", vendorStartYear='" + vendorStartYear + '\'' +
                ", vendorSourceType='" + vendorSourceType + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
