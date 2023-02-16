package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.example.utils.JsonUtils;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private Integer track;
    private boolean cancelled;
    private boolean finished;
    private boolean inDelivery;
    private String courierFirstName;
    private String createdAt;
    private String updatedAt;
    private Integer status;
    private Integer id;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
