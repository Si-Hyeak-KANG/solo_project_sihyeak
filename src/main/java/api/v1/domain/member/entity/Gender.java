package api.v1.domain.member.entity;

import lombok.Getter;

public enum Gender {
    MAN("M","남성"),
    WOMAN("W","여성");

    private String code;
    private String value;

    Gender(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
