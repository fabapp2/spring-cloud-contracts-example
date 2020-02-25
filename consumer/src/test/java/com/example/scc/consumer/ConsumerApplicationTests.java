package com.example.scc.consumer;

		import org.junit.jupiter.api.Test;
		import org.junit.jupiter.api.extension.ExtendWith;
		import org.junit.jupiter.api.extension.RegisterExtension;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.context.SpringBootTest;
		import org.springframework.cloud.contract.stubrunner.StubTrigger;
		import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
		import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
		import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
		import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
		import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, StubRunnerExtension.class})
@SpringBootTest(properties = "producer.url=http://localhost:${stubrunner.runningstubs.producer.port}")
//@AutoConfigureStubRunner(
//		ids = "com.example.scc:producer:0.0.1-SNAPSHOT:stubs",
//		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class ConsumerApplicationTests {

	@RegisterExtension
	public StubRunnerExtension stubRunnerExtension = new StubRunnerExtension()
			.downloadStub("com.example.scc","producer", "0.0.1-SNAPSHOT")
			.repoRoot("git://git@github.com:fabapp2/spring-cloud-contract-contracts-repository.git")
			.stubsMode(StubRunnerProperties.StubsMode.REMOTE);

	@Autowired
	ConsumerOfHelloService consumerOfHelloService;

	@Autowired
	StubTrigger stubTrigger;

	@Test
	public void useHelloService_should_call_hello_service_using_http() {
		consumerOfHelloService.useHelloService("Hello");
	}

	@Test
	public void inbound_message_should_call_service() {
		stubTrigger.labels().forEach((k, v) -> System.out.println(k + " -> " + v));
		stubTrigger.trigger("triggerHelloMessage");
	}

}