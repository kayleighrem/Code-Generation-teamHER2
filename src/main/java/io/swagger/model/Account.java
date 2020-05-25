package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;
/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
public class Account   {
  @JsonProperty("showAccounts")
  private Integer showAccounts = null;

  @JsonProperty("showSavings")
  private Integer showSavings = null;

  public Account showAccounts(Integer showAccounts) {
    this.showAccounts = showAccounts;
    return this;
  }

  /**
   * Get showAccounts
   * @return showAccounts
  **/
  @ApiModelProperty(value = "")
  
    public Integer getShowAccounts() {
    return showAccounts;
  }

  public void setShowAccounts(Integer showAccounts) {
    this.showAccounts = showAccounts;
  }

  public Account showSavings(Integer showSavings) {
    this.showSavings = showSavings;
    return this;
  }

  /**
   * Get showSavings
   * @return showSavings
  **/
  @ApiModelProperty(value = "")
  
    public Integer getShowSavings() {
    return showSavings;
  }

  public void setShowSavings(Integer showSavings) {
    this.showSavings = showSavings;
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
    return Objects.equals(this.showAccounts, account.showAccounts) &&
        Objects.equals(this.showSavings, account.showSavings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(showAccounts, showSavings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    showAccounts: ").append(toIndentedString(showAccounts)).append("\n");
    sb.append("    showSavings: ").append(toIndentedString(showSavings)).append("\n");
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
  public enum enumAccounts {
    BASIC_ACCOUNT("basic account"),
    SAVINGS_ACCOUNT("savings account");

    private String value;

    enumAccounts(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static enumAccounts fromValue(String text) {
      for (enumAccounts b : enumAccounts.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
}



