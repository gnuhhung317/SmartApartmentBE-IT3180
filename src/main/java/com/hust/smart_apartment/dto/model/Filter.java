package com.hust.smart_apartment.dto.model;

import com.hust.smart_apartment.constants.Operator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Filter {
    @Schema(title = "Tên trường cần filter")
    private String name;
    @Schema(title = "Giá trị tìm kiếm")
    private Object value;
    @Schema(title = "toán tử tìm kiếm:  IN: in, NIN: nin, EQUAL: eq, LIKE: like, NOT_EQUAL: ne, GREATER_THAN: gt, LESS_THAN: lt, GREATER_THAN_OR_EQUAL: gte, LESS_THAN_OR_EQUAL: lte, NONE: none")
    private String operation = Operator.EQUAL.getOperator();
    private String type; //date
}
