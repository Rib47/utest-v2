package org.rib.observingclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class ObservingClientApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(ObservingClientApplication.class)
				.web(WebApplicationType.NONE)
				.build()
				.run(args);
	}

}
