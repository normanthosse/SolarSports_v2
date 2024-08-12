package unal.todosalau.solarsportsv2;

import androidx.annotation.Nullable;

public class SportSpace {
    private String name;
    private String address;
    private String city;
    private String phone;
    private Double power;
    private Double generated;
    private Double consumed;
    private String month;

    public SportSpace(String name, String address, String city, String phone, Double power, Double generated, Double consumed, String month) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.power = power;
        this.generated = generated;
        this.consumed = consumed;
        this.month = month;
    }

    public SportSpace(){

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getGenerated() {
        return generated;
    }

    public void setGenerated(Double generated) {
        this.generated = generated;
    }

    public Double getConsumed() {
        return consumed;
    }

    public void setConsumed(Double consumed) {
        this.consumed = consumed;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    //=================================================================
    //permite comparar si un objeto de la lista SportSpace es igual a otro, pero según el nombre
    @Override
    public boolean equals(@Nullable Object obj) {
        return name.equals(((SportSpace) obj).name);
    }

    //Para facilitar la presentación de los Espacios deportivos


    @Override
    public String toString() {
        return "SportSpace{" +
                "Nombre='" + name + '\n' +
                ", Direccion='" + address + '\n' +
                ", Ciudad='" + city + '\n' +
                ", Teléfono='" + phone + '\n' +
                ", Potencia Instalada=" + power +
                ", Energía Generada=" + generated +
                ", Energía Consumida=" + consumed +
                ", Mes=" + month +
                '}';
    }
}
