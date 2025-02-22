package com.vetManagement.spring.core.config;

import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>("201", Msg.CREATED, true, data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>("400", Msg.VALIDATE_ERROR, false, data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>("200", Msg.OK, true, data);
    }

    public static <T> ResultData<T> recordAlreadyExistsError(Long id, T data) {
        return new ResultData<>("409", Msg.RECORD_ALREADY_EXISTS + id, false, data);
    }

    public static <T> ResultData<T> appointmentAlreadyExistError(Long id, T data) {
        return new ResultData<>("409", Msg.DOCTOR_ERROR + id, false, data);
    }


    public static Result ok() {
        return new Result("200", Msg.OK, true);
    }

    public static Result notFoundError(String message) {
        return new Result("404", Msg.NOT_FOUND, false);
    }

    public static Result notFoundError() {
        return new Result("404", Msg.NOT_FOUND, false);
    }

    public static Result notAvailableError() {
        return new Result("400", Msg.NOT_AVAILABLE_ERROR, false);
    }

    public static Result recordNotFoundWithId(Long id) {
        return new Result("404", Msg.RECORD_NOT_FOUND_WITH_ID + id, false);
    }

    public static Result vaccineValidityError() {
        return new Result("400", Msg.VACCINE_ERROR, false);
    }

    public static Result vaccineNotApplicableError() {
        return new Result("400", Msg.DATE_ERROR, false);
    }

    public static Result appointmentHoursError() {
        return new Result("409", Msg.APPOINTMENT_ERROR, false);
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {

        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return ResultHelper.success(cursor);
    }

    public static <T> ResultData<T> ok( T data) {
        return new ResultData<>("200", Msg.OK , false, data);

    }
}