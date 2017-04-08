package com.example.currency_exchange.model;

/**
 * Created by maciej on 20.03.17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Map;


@Data
public class ExchangeModel {

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("target")
    @Expose
    private String target;

    @Valid
    @Min(value = 0)
    @Max(value = 1000)
    @SerializedName("value")
    @Expose
    private Integer value;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("rates")
    @Expose
    private Map<String, String> rates;

}
