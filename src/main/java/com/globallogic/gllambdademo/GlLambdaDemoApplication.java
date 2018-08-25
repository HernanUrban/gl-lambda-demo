package com.globallogic.gllambdademo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GlLambdaDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GlLambdaDemoApplication.class, args);
	}

    @Bean
    public AmazonSNS amazonSNS(){
        return new AmazonSNSClient().builder().withRegion(Regions.US_EAST_1).build();
    }

	@Bean
	public NotificationMessagingTemplate notificationMessagingTemplate(
			AmazonSNS amazonSNS) {
		return new NotificationMessagingTemplate(amazonSNS);
	}
}
