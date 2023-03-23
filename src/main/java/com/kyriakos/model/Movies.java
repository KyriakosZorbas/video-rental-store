
package com.kyriakos.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "movies",
        "totalPrice",
        "currency",
        "rent",
        "dateOfRent"
})
@Service
public class Movies {

    @JsonProperty("movies")
    private List<Movie> movies;
    @JsonProperty("totalPrice")
    private Integer totalPrice;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("rent")
    private List<String> rent;
    @JsonProperty("dateOfRent")
    private String dateOfRent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("movies")
    public List<Movie> getMovies() {
        return movies;
    }

    @JsonProperty("movies")
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @JsonProperty("totalPrice")
    public Integer getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("totalPrice")
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("rent")
    public List<String> getRent() {
        return rent;
    }

    @JsonProperty("rent")
    public void setRent(List<String> rent) {
        this.rent = rent;
    }

    @JsonProperty("dateOfRent")
    public String getDateOfRent() {
        return dateOfRent;
    }

    @JsonProperty("dateOfRent")
    public void setDateOfRent(String dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Movies.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("movies");
        sb.append('=');
        sb.append(((this.movies == null)?"<null>":this.movies));
        sb.append(',');
        sb.append("totalPrice");
        sb.append('=');
        sb.append(((this.totalPrice == null)?"<null>":this.totalPrice));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("rent");
        sb.append('=');
        sb.append(((this.rent == null)?"<null>":this.rent));
        sb.append(',');
        sb.append("dateOfRent");
        sb.append('=');
        sb.append(((this.dateOfRent == null)?"<null>":this.dateOfRent));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}