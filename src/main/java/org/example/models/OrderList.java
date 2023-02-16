package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderList {
    private Order[] orders;
    private PageInfo pageInfo;
    private Station[] availableStations;
}
