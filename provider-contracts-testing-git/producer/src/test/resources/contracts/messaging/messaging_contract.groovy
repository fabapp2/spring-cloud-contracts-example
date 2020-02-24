package contracts.messaging

org.springframework.cloud.contract.spec.Contract.make {
    // Human readable description
    description 'Test that a message gets send'
    // Label by means of which the output message can be triggered on consumer side
    label 'triggerHelloMessage'
    // input to the contract
    input {
        // the contract will be triggered by a method
        triggeredBy('sendMessage()')
        // could also be triggered by a inbound message
    }
    // output message of the contract
    outputMessage {
        // destination to which the output message will be sent
        sentTo('messages')
        // the body of the output message
        body("Hello")
        // the headers of the output message
        headers {
            header('some', $(producer(execute('assertDoubleEqualsWithTolerance($it, 80)'))))
        }
    }
}
