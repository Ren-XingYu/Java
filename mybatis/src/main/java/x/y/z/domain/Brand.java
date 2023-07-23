package x.y.z.domain;

import lombok.Data;

@Data
public class Brand {
    private int id;
    private String brandName;
    private String companyName;
    private Integer ordered;
    private String description;
    private Integer status;
}
