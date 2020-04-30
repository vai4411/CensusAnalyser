package censusanalyser;

public enum  TestException {
    Census("CENSUS_PATH_IS_INVALID"),
    States("STATES_PATH_IS_INVALID"),
    TYPESET("TYPE_MISMATCH"),
    DELIMITER("DELIMITER_NOT_MATCH");

    private final String exception;

    TestException(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }
}
