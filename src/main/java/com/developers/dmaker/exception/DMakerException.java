package com.developers.dmaker.exception;

@SuppressWarnings("unused")
public class DMakerException extends RuntimeException {
    private final DMakerErrorCode dMakerErrorCode;
    private final String detailMessage;

    public DMakerException(DMakerErrorCode dMakerErrorCode) {
        super(dMakerErrorCode.getMessage());
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = dMakerErrorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode dMakerErrorCode, String detailMessage) {
        super(detailMessage);
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = detailMessage;
    }

    public DMakerErrorCode getdMakerErrorCode() {
        return dMakerErrorCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
