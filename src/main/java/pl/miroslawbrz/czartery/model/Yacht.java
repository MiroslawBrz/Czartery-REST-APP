package pl.miroslawbrz.czartery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Yacht {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String yachtName;
    String yachtDescription;
    String yachtType;
    int yachtCapacity;
    double yachtLength;
    double pricePerDay;
    double pricePerWeek;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charter_place_id", referencedColumnName = "charterPlaceId")
    CharterPlace charterPlace;

    public static final class Builder{
        String yachtName = null;
        String yachtDescription = null;
        String yachtType = null;
        int yachtCapacity = 0;
        double yachtLength = 0.0;
        double pricePerDay = 0.0;
        double pricePerWeek = 0.0;

        public Builder yachtName(String yachtName){
            this.yachtName = yachtName;
            return this;
        }

        public Builder yachtDescription(String yachtDescription){
            this.yachtDescription = yachtDescription;
            return this;
        }

        public Builder yachtType(String yachtType){
            this.yachtType = yachtType;
            return this;
        }

        public Builder yachtCapacity(int yachtCapacity){
            this.yachtCapacity = yachtCapacity;
            return this;
        }

        public Builder yachtLength(double yachtLength){
            this.yachtLength = yachtLength;
            return this;
        }

        public Builder pricePerDay(double pricePerDay){
            this.pricePerDay = pricePerDay;
            return this;
        }

        public Builder pricePerWeek(double pricePerWeek){
            this.pricePerWeek = pricePerWeek;
            return this;
        }

        public Yacht build(){
            Yacht yacht = new Yacht();
            yacht.yachtName = this.yachtName;
            yacht.yachtCapacity = this.yachtCapacity;
            yacht.pricePerDay = this.pricePerDay;
            yacht.pricePerWeek = this.pricePerWeek;
            yacht.yachtDescription = this.yachtDescription;
            yacht.yachtLength = this.yachtLength;
            yacht.yachtType = this.yachtType;

            return yacht;
        }


    }

}
