package com.mitocode.yaba.exception;

import java.time.LocalDateTime;

public record CustomErrorResponse (
        LocalDateTime dateTime,
        String mensaje,
        String path
){

}

