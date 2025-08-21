package gov.upsc.post.migration.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hram_fulcom")
public class HramFulcom {

    @Id
    @Column(name = "account_no", length = 50, nullable = false)
    private String accountNo;

    @Column(name = "api_status", length = 1)
    private String apiStatus;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    public HramFulcom() {}

    public HramFulcom(String accountNo, String apiStatus, String updatedBy, LocalDateTime updatedTime) {
        this.accountNo = accountNo;
        this.apiStatus = apiStatus;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "HramFulcom{" +
                "accountNo='" + accountNo + '\'' +
                ", apiStatus='" + apiStatus + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}

