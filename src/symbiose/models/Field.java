package symbiose.models;


import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class Field {
    public Field() {
    }


    private int serial_number;
    private String name;
    private String address;
    private String space;
    private String provider;
    private String price;
    private Date date_start;
    private Date date_end;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fielde")
    private List<Calendar> calendars;
    private int booker_id;

    public Collection<Calendar> getCalendars() {
        return calendars;
    }


    public Field(int serial_number, String name) {
        this.serial_number = serial_number;
        this.name = name;
    }

    public Field(int serial_number, String name, String address, String space, String provider) {
        this.serial_number = serial_number;
        this.name = name;
        this.address = address;
        this.space = space;
        this.provider = provider;
    }

    public Field(int serial_number, String name, String address, String space, String provider, String price) {
        this.serial_number = serial_number;
        this.name = name;
        this.address = address;
        this.space = space;
        this.provider = provider;
        this.price = price;
    }

    public Field(int serial_number, String name, String address, String space, String provider, String price, java.util.Date stat, java.util.Date end) {
    }

    public Field(int serial_number, String name, String address, String space, String provider, String price, Date date_start, Date date_end, int booker_id) {
        this.serial_number = serial_number;
        this.name = name;
        this.address = address;
        this.space = space;
        this.provider = provider;
        this.price = price;
        this.date_start = date_start;
        this.date_end = date_end;
        this.booker_id = booker_id;
    }

    public Field(int serial_number, String name, String address, String space, String provider, String price, Date date_start, Date date_end) {
        this.serial_number = serial_number;
        this.name = name;
        this.address = address;
        this.space = space;
        this.provider = provider;
        this.price = price;
        this.date_start = date_start;
        this.date_end = date_end;

    }

    public Field(String name) {
        this.name=name;
    }


    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public java.util.Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getBooker_id() {
        return booker_id;
    }

    public void setBooker_id(int booker_id) {
        this.booker_id = booker_id;
    }

    @Override
    public String toString() {
        return "Field{" +
                "serial_number=" + serial_number +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                '}';
    }
}