package com.example.demoSpring.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A CustomerInfo.
 */
public class CustomerInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String accountID;

    private String userID;

    private String customerName;

    private String email;

    private String phoneNumber;

    private String functionImpl;

    private String status;

    private LocalDate datePerform;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public CustomerInfo accountID(String accountID) {
        this.accountID = accountID;
        return this;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserID() {
        return userID;
    }

    public CustomerInfo userID(String userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public CustomerInfo customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public CustomerInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerInfo phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFunctionImpl() {
        return functionImpl;
    }

    public CustomerInfo functionImpl(String functionImpl) {
        this.functionImpl = functionImpl;
        return this;
    }

    public void setFunctionImpl(String functionImpl) {
        this.functionImpl = functionImpl;
    }

    public String getStatus() {
        return status;
    }

    public CustomerInfo status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDatePerform() {
        return datePerform;
    }

    public CustomerInfo datePerform(LocalDate datePerform) {
        this.datePerform = datePerform;
        return this;
    }

    public void setDatePerform(LocalDate datePerform) {
        this.datePerform = datePerform;
    }


    @Override
    public String toString() {
        return "CustomerInfo{" +
            "id=" + getId() +
            ", accountID='" + getAccountID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", functionImpl='" + getFunctionImpl() + "'" +
            ", status='" + getStatus() + "'" +
            ", datePerform='" + getDatePerform() + "'" +
            "}";
    }
}
