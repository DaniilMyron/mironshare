package com.miron.directservice.infrastructure.config;

import com.miron.directservice.domain.BasePackageClassesSkanMarker;
import com.miron.directservice.domain.entity.GroupChat;
import com.miron.directservice.domain.entity.PersonalChat;
import com.miron.directservice.domain.repository.GroupChatInMemoryRepository;
import com.miron.directservice.domain.repository.MessagesInMemoryRepository;
import com.miron.directservice.domain.repository.PersonalChatInMemoryRepository;
import com.miron.directservice.domain.spi.ChatRepository;
import com.miron.directservice.domain.springAnnotations.DomainQualifier;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        basePackageClasses = {BasePackageClassesSkanMarker.class, DomainService.class, MessagesInMemoryRepository.class, GroupChatInMemoryRepository.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, DomainRepository.class, DomainUseCase.class, DomainQualifier.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MessagesInMemoryRepository.class, GroupChatInMemoryRepository.class})})
public class DomainConfiguration {
    @Bean
    @Scope("prototype")
    public ChatRepository<PersonalChat> personalChatInMemoryRepository() {
        return new PersonalChatInMemoryRepository();
    }

    @Bean
    @Scope("prototype")
    public ChatRepository<GroupChat> groupChatInMemoryRepository() {
        return new GroupChatInMemoryRepository();
    }
}
