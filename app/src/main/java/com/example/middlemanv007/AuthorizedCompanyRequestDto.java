package com.example.middlemanv007;

public class AuthorizedCompanyRequestDto {

    private String companyMaterialType;
    private String companyStocksNeeded;
    private String companyRequiredWithIn;
    private String companyContactNo;
    private String userType;
    private String companyKey;

    public AuthorizedCompanyRequestDto() {
    }

    public AuthorizedCompanyRequestDto(String companyMaterialType, String companyStocksNeeded, String companyRequiredWithIn, String companyContactNo, String userType, String companyKey) {
        this.companyMaterialType = companyMaterialType;
        this.companyStocksNeeded = companyStocksNeeded;
        this.companyRequiredWithIn = companyRequiredWithIn;
        this.companyContactNo = companyContactNo;
        this.userType = userType;
        this.companyKey = companyKey;
    }

    public String getCompanyMaterialType() {
        return companyMaterialType;
    }

    public void setCompanyMaterialType(String companyMaterialType) {
        this.companyMaterialType = companyMaterialType;
    }

    public String getCompanyStocksNeeded() {
        return companyStocksNeeded;
    }

    public void setCompanyStocksNeeded(String companyStocksNeeded) {
        this.companyStocksNeeded = companyStocksNeeded;
    }

    public String getCompanyRequiredWithIn() {
        return companyRequiredWithIn;
    }

    public void setCompanyRequiredWithIn(String companyRequiredWithIn) {
        this.companyRequiredWithIn = companyRequiredWithIn;
    }

    public String getCompanyContactNo() {
        return companyContactNo;
    }

    public void setCompanyContactNo(String companyContactNo) {
        this.companyContactNo = companyContactNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    @Override
    public String toString() {
        return "AuthorizedCompanyRequestDto{" +
                "companyMaterialType='" + companyMaterialType + '\'' +
                ", companyStocksNeeded='" + companyStocksNeeded + '\'' +
                ", companyRequiredWithIn='" + companyRequiredWithIn + '\'' +
                ", companyContactNo='" + companyContactNo + '\'' +
                ", userType='" + userType + '\'' +
                ", companyKey='" + companyKey + '\'' +
                '}';
    }
}
