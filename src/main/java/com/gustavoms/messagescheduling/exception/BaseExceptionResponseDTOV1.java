package com.gustavoms.messagescheduling.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("BaseExceptionResponseDTOV1")
public class BaseExceptionResponseDTOV1 {

    @ApiModelProperty(value = "Exception name")
    private String code;

    @ApiModelProperty(value = "Exception message")
    private String message;

    @ApiModelProperty(value = "Exception detail")
    private String detail;

    @ApiModelProperty(value = "Exception time")
    private String time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
