public class Position {
  private String name;
  private double positionCoefficient;
  private String responsibilities;

  public Position() {
    this.name = "";
    this.positionCoefficient = 0;
    this.responsibilities = "";
  }

  public Position(String responsibilities, String name, double positionCoefficient) {
    this.responsibilities = responsibilities;
    this.name = name;
    this.positionCoefficient = positionCoefficient;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPositionCoefficient() {
    return positionCoefficient;
  }

  public void setPositionCoefficient(double positionCoefficient) {
    this.positionCoefficient = positionCoefficient;
  }

  public String getResponsibilities() {
    return responsibilities;
  }

  public void setResponsibilities(String responsibilities) {
    this.responsibilities = responsibilities;
  }
}
