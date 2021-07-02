package com.example.middlemanv007;

public class AuthorizedVendorDto {

    private String vendorName;
    private String email;
    private String vendorPhoneNo;
    private String password;
    private String vendorStartYear;
    private String vendorSourceType;
    private String userName;
    private String userType;

    public AuthorizedVendorDto() {
    }

    public AuthorizedVendorDto(String vendorName, String email, String vendorPhoneNo, String password, String vendorStartYear, String vendorSourceType, String userName, String userType) {
        this.vendorName = vendorName;
        this.email = email;
        this.vendorPhoneNo = vendorPhoneNo;
        this.password = password;
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

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getVendorPhoneNo() {
        return vendorPhoneNo;
    }

    public void setVendorPhoneNo(String vendorPhoneNo) {
        this.vendorPhoneNo = vendorPhoneNo;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
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
                ", email='" + email + '\'' +
                ", vendorPhoneNo='" + vendorPhoneNo + '\'' +
                ", password='" + password + '\'' +
                ", vendorStartYear='" + vendorStartYear + '\'' +
                ", vendorSourceType='" + vendorSourceType + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
