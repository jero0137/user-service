package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.Role;

public interface IRolePersistencePort {
    Role findByName(String roleName);
}
