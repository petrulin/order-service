package com.otus.orderservice.rabbitmq.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncSchools {

    Long combineId;

    Long schoolId;

    Long classId;

    Long pupilId;

    public SyncSchools() {
    }

    public Long getCombineId() {
        return combineId;
    }

    public void setCombineId(Long combineId) {
        this.combineId = combineId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getPupilId() {
        return pupilId;
    }

    public void setPupilId(Long pupilId) {
        this.pupilId = pupilId;
    }
}
