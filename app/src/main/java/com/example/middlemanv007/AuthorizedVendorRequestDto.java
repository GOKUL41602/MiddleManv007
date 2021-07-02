package com.example.middlemanv007;

public class AuthorizedVendorRequestDto {
    private String materialType;
    private String stocksAvailable;
    private String expireWithIn;
    private String contactNo;
    private String userType;
    private String key;

    public AuthorizedVendorRequestDto() {
    }

    public AuthorizedVendorRequestDto(String materialType, String stocksAvailable, String expireWithIn, String contactNo, String userType, String key) {
        this.materialType = materialType;
        this.stocksAvailable = stocksAvailable;
        this.expireWithIn = expireWithIn;
        this.contactNo = contactNo;
        this.userType = userType;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getStocksAvailable() {
        return stocksAvailable;
    }

    public void setStocksAvailable(String stocksAvailable) {
        this.stocksAvailable = stocksAvailable;
    }

    public String getExpireWithIn() {
        return expireWithIn;
    }

    public void setExpireWithIn(String expireWithIn) {
        this.expireWithIn = expireWithIn;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "AuthorizedVendorRequestDto{" +
                "materialType='" + materialType + '\'' +
                ", stocksAvailable='" + stocksAvailable + '\'' +
                ", expireWithIn='" + expireWithIn + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", userType='" + userType + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
