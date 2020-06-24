package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Entity
public class Account   {
  @Id
  @Column(name="Iban")
  @JsonProperty("Iban ")
  private Integer Iban = null;


  @Column(name="userId")
  @JsonProperty("userId")
  private Integer userId = null;

  @Column(name="accountAmount")
  @JsonProperty("accountAmount")
  private Integer accountAmount = null;

  /**
   * Gets or Sets typeAccount
   */
  public enum TypeAccountEnum {
    BASIC("basic"),

    SAVING("saving");

    private String value;

    TypeAccountEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeAccountEnum fromValue(String text) {
      for (TypeAccountEnum b : TypeAccountEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("typeAccount")
  private TypeAccountEnum typeAccount = null;

  public Account id(Integer id) {
    this.userId = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(example = "1234", value = "")

  public Integer getId() {
    return userId;
  }

  public void setId(Integer id) {
    this.userId = id;
  }

  public Account acountAmount(Integer acountAmount) {
    this.accountAmount = acountAmount;
    return this;
  }

  /**
   * Get acountAmount
   * @return acountAmount
   **/
  @ApiModelProperty(value = "")

  public Integer getAcountAmount() {
    return accountAmount;
  }

  public void setAcountAmount(Integer acountAmount) {
    this.accountAmount = acountAmount;
  }

  public Account typeAccount(TypeAccountEnum typeAccount) {
    this.typeAccount = typeAccount;
    return this;
  }

  /**
   * Get typeAccount
   * @return typeAccount
   **/
  @ApiModelProperty(example = "basic", value = "")

  public TypeAccountEnum getTypeAccount() {
    return typeAccount;
  }

  public void setTypeAccount(TypeAccountEnum typeAccount) {
    this.typeAccount = typeAccount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.userId, account.userId) &&
            Objects.equals(this.accountAmount, account.accountAmount) &&
            Objects.equals(this.typeAccount, account.typeAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, accountAmount, typeAccount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    id: ").append(toIndentedString(userId)).append("\n");
    sb.append("    acountAmount: ").append(toIndentedString(accountAmount)).append("\n");
    sb.append("    typeAccount: ").append(toIndentedString(typeAccount)).append("\n");
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
}
