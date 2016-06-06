package com.excilys.validator;

import com.excilys.model.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope("singleton")
public class ComputerValidator {

    private Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

    public void assertIdValid(Long id) {
        if (id != null || id <= 0) {
            logger.error("Error Computer Validator : id invalid");
            throw new ValidatorException("Error Computer Validator : id invalid");
        }
    }

    /**
     * Throws exception if name length is superior to 0 and if name is not null.
     *
     * @param name value to test
     */
    public void assertNameValid(String name) {
        if (name == "" || name == null) {
            logger.error("Error Computer Validator : name invalid");
            throw new ValidatorException("Error Computer Validator : name invalid : " + name);
        }
    }

    /**
     * Throws exception if date is between timestamp's limits.
     *
     * @param date value to test
     */
    public void assertDateConvertibleToTimestamp(LocalDate date) {
        if (date.isBefore(LocalDate.MIN) || date.isAfter(LocalDate.MAX)) {
            logger.error("Error Computer Validator : date invalid");
            throw new ValidatorException("Error Computer Validator : date invalid");
        }
    }

    /**
     * Throws exception if date1 is after date2. Throws exception if date2 is not null but date1 is
     * null.
     *
     * @param date1 first date.
     * @param date2 last date.
     */
    public void assertDatesValid(LocalDate date1, LocalDate date2) {
        if (date1 == null && date2 != null) {
            throw new ValidatorException(
                    "Error Computer Validator : date1 is null but date2 is not null");
        }

        if (date1 != null && date2 != null) {
            if (date1.isAfter(date2)) {
                throw new ValidatorException("Error Computer Validator : timeline invalid");
            }
        }

    }

    /**
     * Check if each parameter of computer is valid.
     *
     * @param computer computer to test
     */
    public void assertValid(Computer computer) {
        assertIdValid(computer.getId());
        assertNameValid(computer.getName());
        if (computer.getIntroduced() != null) {
            assertDateConvertibleToTimestamp(computer.getIntroduced());
        }
        if (computer.getDiscontinued() != null) {
            assertDateConvertibleToTimestamp(computer.getDiscontinued());
        }
        assertDatesValid(computer.getIntroduced(), computer.getDiscontinued());
    }

}
