package com.example.middlemanv007;

public class SalesMelaDto {

    private String companyMaterialType;
    private String companyStocksNeeded;
    private String companyRequiredWithIn;
    private String companyContactNo;
    private String userType;
    private String companyKey;
    private String VendorMaterialType;
    private String vendorStocksAvailable;
    private String vendorExpireWithIn;
    private String vendorContactNo;
    private String vendorKey;

    public SalesMelaDto() {
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

    public String getVendorKey() {
        return vendorKey;
    }

    public void setVendorKey(String vendorKey) {
        this.vendorKey = vendorKey;
    }

    @Override
    public String toString() {
        return "SalesMelaDto{" +
                "companyMaterialType='" + companyMaterialType + '\'' +
                ", companyStocksNeeded='" + companyStocksNeeded + '\'' +
                ", companyRequiredWithIn='" + companyRequiredWithIn + '\'' +
                ", companyContactNo='" + companyContactNo + '\'' +
                ", userType='" + userType + '\'' +
                ", companyKey='" + companyKey + '\'' +
                ", VendorMaterialType='" + VendorMaterialType + '\'' +
                ", vendorStocksAvailable='" + vendorStocksAvailable + '\'' +
                ", vendorExpireWithIn='" + vendorExpireWithIn + '\'' +
                ", vendorContactNo='" + vendorContactNo + '\'' +
                ", vendorKey='" + vendorKey + '\'' +
                '}';
    }
}
