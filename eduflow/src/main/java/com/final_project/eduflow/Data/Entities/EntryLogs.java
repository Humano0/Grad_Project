package com.final_project.eduflow.Data.Entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "entry_logs")
public class EntryLogs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter private Long id;

    @Column(name = "entry_time")
    @Getter private OffsetDateTime entryTime;

    @Column(name = "users_ip")
    @Getter @Setter private String userIp;

    @Column(name = "users_id")
    @Getter @Setter private long userId;


    public EntryLogs( String userIp, long userId) {
        this.entryTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.userIp = userIp;
        this.userId = userId;
    }

}
