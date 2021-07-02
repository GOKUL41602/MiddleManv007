package com.example.middlemanv007;

public class AuthorizedVendorRequestDto {
    private String VendorMaterialType;
    private String vendorStocksAvailable;
    private String vendorExpireWithIn;
    private String vendorContactNo;
    private String userType;
    private String vendorKey;

    public AuthorizedVendorRequestDto() {
    }

    public AuthorizedVendorRequestDto(String vendorMaterialType, String vendorStocksAvailable, String vendorExpireWithIn, String vendorContactNo, String userType, String vendorKey) {
        VendorMaterialType = vendorMaterialType;
        this.vendorStocksAvailable = vendorStocksAvailable;
        this.vendorExpireWithIn = vendorExpireWithIn;
        this.vendorContactNo = vendorContactNo;
        this.userType = userType;
        this.vendorKey = vendorKey;
    }

    public String getVendorMaterialType() {
        return VendorMaterialType;
    }

    public void setVendorMaterialType(String vendorMaterialType) {
        VendorMaterialType = vendorMaterialType;
    }

    public String getVendorStocksAvailable() {
        return vendorStocksAvailable;
    }

    public void setVendorStocksAvailable(String vendorStocksAvailable) {
        this.vendorStocksAvailable = vendorStocksAvailable;
    }

    public String getVendorExpireWithIn() {
        return vendorExpireWithIn;
    }

    public void setVendorExpireWithIn(String vendorExpireWithIn) {
        this.vendorExpireWithIn = vendorExpireWithIn;
    }

    public String getVendorContactNo() {
        return vendorContactNo;
    }

    public void setVendorContactNo(String vendorContactNo) {
        this.vendorContactNo = vendorContactNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVendorKey() {
        return vendorKey;
    }

    public void setVendorKey(String vendorKey) {
        this.vendorKey = vendorKey;
    }

    @Override
    public String toString() {
        return "AuthorizedVendorRequestDto{" +
                "VendorMaterialType='" + VendorMaterialType + '\'' +
                ", vendorStocksAvailable='" + vendorStocksAvailable + '\'' +
                ", vendorExpireWithIn='" + vendorExpireWithIn + '\'' +
                ", vendorContactNo='" + vendorContactNo + '\'' +
                ", userType='" + userType + '\'' +
                ", vendorKey='" + vendorKey + '\'' +
                '}';
    }
}
