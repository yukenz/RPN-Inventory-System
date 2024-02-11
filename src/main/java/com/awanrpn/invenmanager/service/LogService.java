package com.awanrpn.invenmanager.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    String createAOPLog(

    ) {

        LocalDateTime.now();

        String.format(
                "[$] [LEVEL] [MODULE] [USER/IP] [MESSAGE]\n"
        );

        return null;

    }

}
