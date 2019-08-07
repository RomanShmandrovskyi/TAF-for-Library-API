package ua.com.epam.utils.helpers;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusCode {
    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),

    OK(200, "OK"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),

    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported");

    private final int code;
    private final String reasonPhrase;

    StatusCode(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public Group group() {
        return Group.valueOf(this);
    }

    public boolean is1xxInformational() {
        return this.group() == Group.INFORMATIONAL;
    }

    public boolean is2xxSuccessful() {
        return this.group() == Group.SUCCESSFUL;
    }

    public boolean is3xxRedirection() {
        return this.group() == Group.REDIRECTION;
    }

    public boolean is4xxClientError() {
        return this.group() == Group.CLIENT_ERROR;
    }

    public boolean is5xxServerError() {
        return this.group() == Group.SERVER_ERROR;
    }

    public boolean isError() {
        return this.is4xxClientError() || this.is5xxServerError();
    }

    public static StatusCode resolve(int statusCode) {
        return Stream.of(values())
                .filter(s -> s.code == statusCode)
                .findFirst()
                .orElse(null);
    }

    public enum Group {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int value;

        Group(int value) {
            this.value = value;
        }

        public static Group valueOf(StatusCode status) {
            return valueOf(status.code);
        }

        public static Group valueOf(int statusCode) {
            Group series = resolve(statusCode);
            if (series == null) {
                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
            } else {
                return series;
            }
        }

        public static Group resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            return Stream.of(values())
                    .filter(s -> s.value == seriesCode)
                    .findFirst()
                    .orElse(null);
        }
    }
}
