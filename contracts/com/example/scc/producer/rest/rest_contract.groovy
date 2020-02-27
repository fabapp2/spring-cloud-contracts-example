package contracts.rest

org.springframework.cloud.contract.spec.Contract.make {
    request{
            method "POST"
            url "/messages"
            body(
                    $(consumer(regex('Hello.*')), producer("Hello Bob"))
            )
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