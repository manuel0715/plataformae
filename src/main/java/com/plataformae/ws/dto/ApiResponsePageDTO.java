package com.plataformae.ws.dto;

public class ApiResponsePageDTO<T> {

    private String message;
    private T data;
    private int statusCode;
    private long totalElements;
    private int totalPages;

    public ApiResponsePageDTO(String message, T data, int statusCode,long totalElements, int totalPages) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
