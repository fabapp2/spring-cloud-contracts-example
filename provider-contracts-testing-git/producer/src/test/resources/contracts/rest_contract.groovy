package contracts

import com.github.tomakehurst.wiremock.http.Response

org.springframework.cloud.contract.spec.Contract.make {
    request{
            method "POST"
            url "/messages"
            body("Hello, Producer!")
            headers{
                contentType("application/json")
            }
    }
    response{
            status OK()
            body("Hello Consumer!")
            headers {
                contentType("application/json")
            }
    }
}