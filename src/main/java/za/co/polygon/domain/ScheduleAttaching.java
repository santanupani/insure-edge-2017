package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "schedule_attachings")
public class ScheduleAttaching {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "schedule_attaching_values")
    private String scheduleAttchnigValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScheduleAttchnigValues() {
        return scheduleAttchnigValues;
    }

    public void setScheduleAttchnigValues(String scheduleAttchnigValues) {
        this.scheduleAttchnigValues = scheduleAttchnigValues;
    }
      

}
