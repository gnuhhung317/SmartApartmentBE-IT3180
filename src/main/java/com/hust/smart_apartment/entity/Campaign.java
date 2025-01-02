package com.hust.smart_apartment.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "total_amount")
    private Long total = 0l;

    public void addMoney(Long amount){
        total+=amount;
    }

    public void subtractMoney(long amount){
        total-=amount;
    }

//    @ManyToOne
//    @JoinColumn(name = "period_id")
//    private Period period;

}
