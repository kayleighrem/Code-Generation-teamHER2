package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Entity

public class Transaction   {
  @Id
  @Column(name="Trans_ID")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer id = null;

  @Column(name="Trans_From")
  @JsonProperty("from")
  private String from = null;

  @Column(name="Trans_To")
  @JsonProperty("to")
  private String to = null;

  @Column(name="Trans_userPerf")
  @JsonProperty("userPerforming")
  private Integer userPerforming = null;

  @Column(name="Trans_Date")
  @JsonProperty("transactionDate")
  private Date transactionDate = null;

  @Column(name="Trans_Amount")
  @JsonProperty("amount")
  private Double amount = 100d;




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

  public Transaction id(Integer id) {
    this.id = id;
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

  /**
   * Get amount
   * @return amount
   **/
  @ApiModelProperty(example = "10.00", value = "")

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double Amount) {
    this.amount = amount;
  }

  public Transaction amount(Double amount) {
    this.amount = amount;
    return this;
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
        Objects.equals(this.status, transaction.status) && Objects.equals(this.amount, transaction.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, from, to, userPerforming, transactionDate, status, amount);
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
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

  public Transaction(Integer id, String to, String from, Integer userPerforming, Date transactionDate, Double amount)
  {
    this.id = id;
    this.to = to;
    this.from = from;
    this.userPerforming = userPerforming;
    this.transactionDate = transactionDate;
    this.amount = amount;
  }

  public Transaction()
  {
    super();
  }


}


