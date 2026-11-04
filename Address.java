public class Address {
    private int number;
    private String street;
    private String ward;
    private String district;
    private String city;

    public Address(int number, String street, String ward, String district, String city) {
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return this.ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void printFullAddress() {
        System.out
                .println(this.number + " " + this.street + ", " + this.ward + ", " + this.district + ", " + this.city);
    }
}
