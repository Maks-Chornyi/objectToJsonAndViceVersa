import java.util.Objects;

public class Car {
    private String color;
    private String type;

    public Car () {
    }

    private Car(Builder builder) {
        this.color = builder.color;
        this.type = builder.type;
    }

    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(color, car.color) &&
                Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    static class Builder {
        private String color;
        private String type;

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
