package co.com.hyunseda.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

/**
 *
 * @author ACER
 */
public class Permissions {
     @JsonProperty("id")
    private Long permissionId;
     
     @JsonProperty("name")
     private String permissionName;
}
