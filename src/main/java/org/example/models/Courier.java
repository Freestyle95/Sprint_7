package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.example.utils.JsonUtils;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Courier {
    private String login;
    private String password;
    private String firstName;
    private Integer id;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
