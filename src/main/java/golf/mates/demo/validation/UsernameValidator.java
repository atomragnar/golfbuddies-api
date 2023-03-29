package golf.mates.demo.validation;

import golf.mates.demo.repositories.UserRepository;

import jakarta.persistence.NonUniqueResultException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!userRepository.existsByUsernameIgnoreCase(value)) {
            return true;
        }
        throw new NonUniqueResultException();
    }
}