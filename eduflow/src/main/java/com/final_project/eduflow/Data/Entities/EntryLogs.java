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
    @Setter @Getter private long id;

    @Column(name = "entry_time")
    @Getter private OffsetDateTime entryTime;

    @Column(name = "users_ip")
    @Getter @Setter private String userIp;

    @Column(name = "users_id")
    @Getter @Setter private long userId;


    public EntryLogs(long id, String userIp, long userId) {
        this.id = id;
        this.entryTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.userIp = userIp;
        this.userId = userId;
    }

}
