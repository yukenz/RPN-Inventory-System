package com.awanrpn.invenmanager.webclient;

import com.awanrpn.invenmanager.model.dto.elastic.AuthIndex;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ElasticSearchClient {

    @PostExchange("/auth-is/_doc")
    String createAuthRecord(@RequestBody AuthIndex authIndex);

}
