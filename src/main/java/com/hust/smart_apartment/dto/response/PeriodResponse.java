package com.hust.smart_apartment.dto.response;


import com.hust.smart_apartment.annotations.QuickSearchDomain;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@QuickSearchDomain(tableName = "periods")
public class PeriodResponse {

    private Long periodId;

    private String name;
}
