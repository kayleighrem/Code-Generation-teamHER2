package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * Transaction
 */
@Configuration
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Entity

public class Transaction   {
  @Id
  @Column(name="Trans_ID")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer id;

  @Column(name="Trans_From")
  @JsonProperty("from")
  private String from;

  @Column(name="Trans_To")
  @JsonProperty("to")
  private String to;

  @Column(name="Trans_userPerf")
  @JsonProperty("userPerforming")
  private Integer userPerforming;

  @Column(name="Trans_Date")
  @JsonProperty("transactionDate")
  private Date transactionDate;

  @Column(name="Trans_Description")
  @JsonProperty("description")
  private String description;

  @Column(name="Trans_Money")
  @JsonProperty("money")
  public Double money;

  /**
   * Gets or Sets status
   */

  public enum StatusEnum {
    PENDING("pending"),
    
    COMPLETE("complete"),
    
    ERROR("error");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  @Column(name="Trans_Status")
  private StatusEnum status = null;

  public Transaction description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(example = "1234", value = "")

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getMoney(){return money;}

  public void setMoney(Double money){this.money = money;}

  public Transaction id(Integer id) {
    this.id = id;
    return this;
  }

  public Transaction money(Double money) {
    this.money = money;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1234", value = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Transaction from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
  **/
  @ApiModelProperty(value = "")
  
    public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Transaction to(String to) {
    this.to = to;
    return this;
  }

  /**
   * Get to
   * @return to
  **/
  @ApiModelProperty(value = "")
  
    public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Transaction userPerforming(Integer userPerforming) {
    this.userPerforming = userPerforming;
    return this;
  }

  /**
   * Get userPerforming
   * @return userPerforming
  **/
  @ApiModelProperty(example = "Hans", value = "")
  
    public Integer getUserPerforming() {
    return userPerforming;
  }

  public void setUserPerforming(Integer userPerforming) {
    this.userPerforming = userPerforming;
  }

  public Transaction transactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Transaction status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "complete", value = "")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }




  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.from, transaction.from) &&
        Objects.equals(this.to, transaction.to) &&
        Objects.equals(this.userPerforming, transaction.userPerforming) &&
        Objects.equals(this.transactionDate, transaction.transactionDate) &&
        Objects.equals(this.status, transaction.status) && Objects.equals(this.money, transaction.money);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, from, to, userPerforming, transactionDate, status, money);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    userPerforming: ").append(toIndentedString(userPerforming)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
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

  public Transaction(Integer id, String to, String from, Integer userPerforming, Date transactionDate,String description,Double money)
  {
    this.id = id;
    this.to = to;
    this.from = from;
    this.userPerforming = userPerforming;
    this.transactionDate = transactionDate;
    this.description = description;
    this.money = money;
  }

  public Transaction()
  {
    super();
  }


}


