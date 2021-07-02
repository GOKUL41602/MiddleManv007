package com.example.middlemanv007;

public class AuthorizedCompanyRequestDto {

    private String materialType;
    private String stocksNeeded;
    private String RequiredWithIn;
    private String contactNo;

    public AuthorizedCompanyRequestDto() {
    }

    public AuthorizedCompanyRequestDto(String materialType, String stocksNeeded, String requiredWithIn, String contactNo) {
        this.materialType = materialType;
        this.stocksNeeded = stocksNeeded;
        RequiredWithIn = requiredWithIn;
        this.contactNo = contactNo;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getStocksNeeded() {
        return stocksNeeded;
    }

    public void setStocksNeeded(String stocksNeeded) {
        this.stocksNeeded = stocksNeeded;
    }

    public String getRequiredWithIn() {
        return RequiredWithIn;
    }

    public void setRequiredWithIn(String requiredWithIn) {
        RequiredWithIn = requiredWithIn;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "AuthorizedCompanyRequestDto{" +
                "materialType='" + materialType + '\'' +
                ", stocksNeeded='" + stocksNeeded + '\'' +
                ", RequiredWithIn='" + RequiredWithIn + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
