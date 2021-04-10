package models;

import javax.persistence.*;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "space")
    private String space;

    @Column(name = "provider")
    private String provider;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Byte status;

    @Column(name = "date_start")
    private java.sql.Date dateStart;

    @Column(name = "date_end")
    private java.sql.Date dateEnd;

    @Column(name = "booker_id")
    private Integer bookerId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpace() {
        return this.space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public java.sql.Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(java.sql.Date dateStart) {
        this.dateStart = dateStart;
    }

    public java.sql.Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(java.sql.Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getBookerId() {
        return this.bookerId;
    }

    public void setBookerId(Integer bookerId) {
        this.bookerId = bookerId;
    }
}
