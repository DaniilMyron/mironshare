package com.miron.directservice.infrastructure.config;

import com.miron.directservice.domain.BasePackageClassesSkanMarker;
import com.miron.directservice.domain.repository.BasicChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = {BasePackageClassesSkanMarker.class, DomainService.class, MessagesInMemoryRepository.class, BasicChatInMemoryRepository.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, DomainRepository.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MessagesInMemoryRepository.class, BasicChatInMemoryRepository.class})})
public class DomainConfiguration {
}
