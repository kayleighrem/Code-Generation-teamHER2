package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Entity
@Table(name="account")
public class Account   {

  @Id
  @Column(name="Iban")
  @JsonProperty("Iban")
  private String Iban;

  @Column(name="userId")
  @JsonProperty("userId")
  private Integer userId;

  @Column(name="accountAmount")
  @JsonProperty("accountAmount")
  private Double accountAmount;

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
//private accountType type;
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




  public String getIBAN() {
    return Iban;
  }

  public void setIBAN(String Iban) {
    this.Iban = Iban;
  }

  public Account acountAmount(Double acountAmount) {
    this.accountAmount = acountAmount;
    return this;
  }

  /**
   * Get acountAmount
   * @return acountAmount
   **/
  @ApiModelProperty(value = "")

  public Double getAcountAmount() {
    return accountAmount;
  }

  public void setAcountAmount(Double acountAmount) {
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
            Objects.equals(this.Iban, account.Iban) &&
            Objects.equals(this.typeAccount, account.typeAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, accountAmount, typeAccount,Iban);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    id: ").append(toIndentedString(userId)).append("\n");
    sb.append("    acountAmount: ").append(toIndentedString(accountAmount)).append("\n");
    sb.append("    typeAccount: ").append(toIndentedString(typeAccount)).append("\n");
    sb.append("    IBan: ").append(toIndentedString(Iban)).append("\n");
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

  public Account(String Iban, Double accountAmount,Integer userId,TypeAccountEnum type)
  {
    this.Iban = Iban;
    this.accountAmount = accountAmount;
    this.userId = userId;
    this.typeAccount = type;
  }

  public Account()
  {super();}

}
