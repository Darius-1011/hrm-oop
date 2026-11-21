import java.util.Scanner;

public class Department {
  private String name;
  private String phone;
  private String position;

  public Department() {
    this.name = "";
    this.phone = "";
    this.position = "";
  }

  public Department(String name, String phone, String position) {
    this.name = name;
    this.phone = phone;
    this.position = position;
  }

  public String getInfo(Scanner sc) {
    String content = "";

    content += "Tên: " + getName() + "\n";
    content += "Điện thoại: " + getPhone() + "\n";
    content += "Chức vụ: " + getPosition() + "\n";

    return content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
