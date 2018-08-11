package org.rib.updateclient;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UpdateClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UpdateClientApplication.class)
				.web(WebApplicationType.NONE)
				.build()
				.run(args);
	}
}
