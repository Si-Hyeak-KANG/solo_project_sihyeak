package api.v1.domain.member.entity;

import lombok.Getter;

public enum Gender {

    M("남성"),W("여성");

    @Getter
    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
