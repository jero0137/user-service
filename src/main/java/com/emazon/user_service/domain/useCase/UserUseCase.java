package com.emazon.user_service.domain.useCase;

import com.emazon.user_service.Utils.Constants;
import com.emazon.user_service.Utils.RegexConstants;
import com.emazon.user_service.domain.api.IUserService;
import com.emazon.user_service.domain.exception.*;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserService {

    IUserPersistencePort userPersistencePort;
    IRolePersistencePort rolePersistencePort;
    PasswordEncoder passwordEncoder;


    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void registerUser(User user) {

        if (user.getName() == null) {
            throw new UserNotNullException();
        }
        if (user.getPassword() == null) {
            throw new PasswordNotNullException();
        }
        if(!user.getDocument().matches(RegexConstants.DOCUMENT_REGEX)){
            throw new InvalidDocumentException();
        }
        if (!user.getPhone().matches(RegexConstants.PHONE_REGEX)) {
            throw new IllegalPhoneFormatException();
        }
        if (!user.getEmail().matches(RegexConstants.EMAIL_REGEX)) {
            throw new InvalidEmailFormatException();
        }

        boolean isAdult = Period.between(user.getBirthDate(), LocalDate.now()).getYears() >= 18;
        if (!isAdult) {
            throw new NotAdultException();
        }


        Role role = rolePersistencePort.findByName(Constants.ROLE_AUX_BODEGA);
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userPersistencePort.registerUser(user);
    }
}
