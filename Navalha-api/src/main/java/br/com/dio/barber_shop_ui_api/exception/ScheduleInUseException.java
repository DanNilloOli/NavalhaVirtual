package br.com.dio.barber_shop_ui_api.exception;

public class ScheduleInUseException extends RuntimeException {
    public ScheduleInUseException(String message) {
        super(message);
    }
}
