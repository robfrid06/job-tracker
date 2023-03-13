package app.door2door.jobtracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JOBS")
public class Job {

    @Id
    @GeneratedValue
    private Integer id;

    private String address;

    @OneToMany(mappedBy = "job")
//    @JoinColumn(name = "job_id")
    private Set<WorkLog> workLogs;

}
