package org.example.models;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private Integer page;
    private Integer total;
    private Integer limit;
}
