package com.skidata.x.hal;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.repository.support.DefaultRepositoryInvokerFactory;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.repository.support.RepositoryInvokerFactory;
import org.springframework.data.rest.core.UriToEntityConverter;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class HalDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HalDemoApplication.class, args);
	}

	@Autowired
	ApplicationContext appCtx;

	@Autowired(required = false)
	List<MappingContext<?, ?>> mappingContexts = Collections.emptyList();


	@Bean
	public Repositories repositories() {
		return new Repositories(appCtx);
	}

	@Bean
	public PersistentEntities persistentEntities() {
		return new PersistentEntities(mappingContexts);
	}

	@Bean
	public UriToEntityConverter uriToEntityConverter(){
		DefaultRepositoryInvokerFactory invokerFactory = new DefaultRepositoryInvokerFactory(repositories());
		UriToEntityConverter uriToEntityConverter = new UriToEntityConverter(persistentEntities(), invokerFactory,
				repositories());
		return uriToEntityConverter;
	}


}
