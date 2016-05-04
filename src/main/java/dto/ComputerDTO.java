package dto;

public class ComputerDTO {

  private String id;
  private String name;
  private String introduced;
  private String discontinued;
  private String idCompany;
  private String nameCompany;

  public ComputerDTO() {

  }

  public ComputerDTO(String id, String name, String introduced, String discontinued,
      String idCompany, String nameCompany) {
    super();
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.idCompany = idCompany;
    this.nameCompany = nameCompany;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public String getIdCompany() {
    return idCompany;
  }

  public void setIdCompany(String idCompany) {
    this.idCompany = idCompany;
  }

  public String getNameCompany() {
    return nameCompany;
  }

  public void setNameCompany(String companyName) {
    this.nameCompany = companyName;
  }

}
