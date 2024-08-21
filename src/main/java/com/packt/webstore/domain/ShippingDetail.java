package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
public class ShippingDetail implements Serializable{
    private static final long serialVersionUID = 6350930334140807514L;
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date shippingDate;
    private Address shippingAddress;
    public ShippingDetail() {
        this.shippingAddress = new Address();
    }

    public ShippingDetail(String name, Date shippingDate, Address shippingAddress) {
        this.name = name;
        this.shippingDate = shippingDate;
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "ShippingDetail{" +
                "name='" + name + '\'' +
                ", shippingDate=" + shippingDate +
                ", shippingAddress=" + shippingAddress +
                '}';
    }
}
