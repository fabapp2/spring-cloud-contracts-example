package com.example.scc.consumer;

		import org.junit.jupiter.api.Test;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.context.SpringBootTest;
		import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
		import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@SpringBootTest(properties = "producer.url=http://localhost:${stubrunner.runningstubs.producer.port}")
@AutoConfigureStubRunner(
		ids = "com.example.scc:producer:0.0.1-SNAPSHOT:stubs",
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class ConsumerApplicationTests {

	@Autowired
	ConsumerOfHelloService consumerOfHelloService;

	@Test
	void contextLoads() {
		consumerOfHelloService.useHelloService("Hello");
	}

}
