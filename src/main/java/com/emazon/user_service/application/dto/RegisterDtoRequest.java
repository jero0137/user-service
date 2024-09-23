package com.emazon.user_service.application.dto;

import com.emazon.user_service.Utils.Constats;
import com.emazon.user_service.Utils.RegexConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Constant;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class RegisterDtoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @Pattern(regexp = RegexConstants.DOCUMENT_REGEX, message = "Document must be numeric")
    private String document;

    @Pattern(regexp = RegexConstants.PHONE_REGEX, message = "Phone must be numeric and start with +")
    private String phone;

    @Past
    private LocalDate birthdate;

    @Email
    private String email;

    private String password;
}
