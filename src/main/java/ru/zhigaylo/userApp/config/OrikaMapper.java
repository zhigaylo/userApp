package ru.zhigaylo.userApp.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.zhigaylo.userApp.model.Document;
import ru.zhigaylo.userApp.model.DocumentDto;
import ru.zhigaylo.userApp.model.User;
import ru.zhigaylo.userApp.model.UserDto;

@Component
public class OrikaMapper extends ConfigurableMapper {
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(User.class, UserDto.class).byDefault().register();
        mapperFactory.classMap(Document.class, DocumentDto.class)
                .field("user.id", "userId")
                .byDefault()
                .register();
    }
}
