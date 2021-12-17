package com.app.gland.unit;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class GlobalResponse<T> {

	protected boolean success;

	private T data;

	private Integer code;

	private String message;

	private String cause;

	private Object[] traces;

	public GlobalResponse(T data) {
		this.data = data;
		this.success = true;
	}

	public static <T> GlobalResponse<T> success(T data) {
		return new GlobalResponse<>(data).setCode(HttpStatus.OK.value());
	}

	public static <T> GlobalResponse<T> fail(Integer code, String message) {
		GlobalResponse<T> res = new GlobalResponse<T>();
		res.setSuccess(false);
		res.setCode(code);
		res.setMessage(message);
		return res;
	}

	public static <T> GlobalResponse<T> fail(Integer code, String message, String cause, Object[] traces) {
		GlobalResponse<T> res = new GlobalResponse<T>();
		res.setSuccess(false);
		res.setCode(code);
		res.setMessage(message);
		res.setCause(cause);
		res.setTraces(traces);
		return res;
	}

	public static <T> GlobalResponse<T> fail(Exception exception, boolean devmode) {
		GlobalResponse<T> res = new GlobalResponse<T>();
		res.setSuccess(false);
		if (exception == null) {
			return res;
		}
		res.setCode(exception.getMessage() == null ? null : 200);
		res.setMessage(exception.getMessage());

		if (devmode) {
			Throwable cause = exception.getCause();
			res.setCause(cause == null ? null : cause.getMessage());
			res.setTraces(exception.getStackTrace());
		}
		return res;
	}

	public static <T> GlobalResponse<T> fail(Throwable exception, boolean devmode) {
		GlobalResponse<T> res = new GlobalResponse<T>();
		res.setSuccess(false);
		if (exception == null) {
			return res;
		}
		res.setCode(500);
		res.setMessage(exception.getMessage());

		if (devmode) {
			Throwable cause = exception.getCause();
			res.setCause(cause == null ? null : cause.getMessage());
			res.setTraces(exception.getStackTrace());
		}
		return res;
	}
}
