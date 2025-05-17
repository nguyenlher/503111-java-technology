package edu.tdtu.Lab10.execption;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    ENTITY_EXISTS(1001, "Entity exists"),
    ENTITY_NOT_EXISTS(1002, "Entity exists"),
    UNAUTHORIZED(1003, "Unauthorized"),
    OTHER_EXCEPTION(9999, "Other Exception");

    int code;
    String message;
}