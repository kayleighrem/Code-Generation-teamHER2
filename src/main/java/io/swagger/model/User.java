package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Entity
public class User {
  @Id
  @Column(name="uid")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer userId = null;

  @Column(name="uname")
  @JsonProperty("name")
  private String name = null;

  @Column(name="ulastname")
  @JsonProperty("lastname")
  private String lastname = null;

  @Column(name="uemail")
  @JsonProperty("email")
  private String email = null;

  @Column(name="uisemp")
  @JsonProperty("isEmployee")
  private Boolean isEmployee = null;

  @Column(name="upass")
  @JsonProperty("password")
  private String password = null;

  public User userId(Integer userId) {
    this.userId = userId;
    return this;
  }

public User(String name, String lastname, String email, Boolean isEmployee, String password,Integer userId) {
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.isEmployee = isEmployee;
    this.password = password;
    this.userId = userId;
}

  /**
   * Get userId
   * @return userId
   **/
  @ApiModelProperty(example = "35444", value = "")

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @ApiModelProperty(example = "Robin", value = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
   **/
  @ApiModelProperty(example = "de Jong", value = "")

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @ApiModelProperty(example = "robin.dejong@gmail.com", value = "")

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  public User isEmployee(Boolean isEmployee) {
    this.isEmployee = isEmployee;
    return this;
  }

  /**
   * Get password
   * @return password
   **/

  @ApiModelProperty(value = "")

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  /**
   * Get isEmployee
   * @return isEmployee
   **/
  @ApiModelProperty(example = "false", value = "")

  public Boolean getIsEmployee() {
    return isEmployee;
  }

  public void setIsEmployee(Boolean isEmployee) {
    this.isEmployee = isEmployee;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.userId, user.userId) &&
            Objects.equals(this.name, user.name) &&
            Objects.equals(this.lastname, user.lastname) &&
            Objects.equals(this.email, user.email) &&
            Objects.equals(this.isEmployee, user.isEmployee)&&
            Objects.equals(this.password, user.password) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, name, lastname, email, isEmployee,password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Users {\n");

    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    isEmployee: ").append(toIndentedString(isEmployee)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public User()
  {
    super();
  }
}
