package com.otus.orderservice.rabbitmq.domain.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PupilDTO {

    private Long souId;

    private Long souClassId;

    private String name;

    private LocalDate birthday;

    private Long phone;

    private String email;

    private Integer limitCount;

    private BigDecimal limitAmount;

    private Boolean active;

    private List<String> pupilCardList;

    public PupilDTO() {
    }

    public Long getSouId() {
        return souId;
    }

    public void setSouId(Long souId) {
        this.souId = souId;
    }

    public Long getSouClassId() {
        return souClassId;
    }

    public void setSouClassId(Long souClassId) {
        this.souClassId = souClassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {

        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<String> getPupilCardList() {
        return pupilCardList;
    }

    public void setPupilCardList(List<String> pupilCardList) {
        this.pupilCardList = pupilCardList;
    }
}
