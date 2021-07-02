package com.example.middlemanv007;

public class AuthorizedVendorRequestDto {
    private String materialType;
    private String stocksAvailable;
    private String expireWithIn;
    private String contactNo;

    public AuthorizedVendorRequestDto() {
    }

    public AuthorizedVendorRequestDto(String materialType, String stocksAvailable, String expireWithIn, String contactNo) {
        this.materialType = materialType;
        this.stocksAvailable = stocksAvailable;
        this.expireWithIn = expireWithIn;
        this.contactNo = contactNo;
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
                '}';
    }
}
