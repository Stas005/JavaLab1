package exception;

public class InvalidDataException extends Exception {
    private final String fieldName;
    private final String invalidValue;

    public InvalidDataException(String message) {
        super(message);
        this.fieldName = null;
        this.invalidValue = null;
    }
    
    public InvalidDataException(String message, String fieldName, String invalidValue) {
        super(message);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }
    
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
        this.fieldName = null;
        this.invalidValue = null;
    }
    
    public InvalidDataException(Throwable cause) {
        super(cause);
        this.fieldName = null;
        this.invalidValue = null;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public String getInvalidValue() {
        return invalidValue;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (fieldName != null) {
            sb.append(" [Field: ").append(fieldName);
            if (invalidValue != null) {
                sb.append(", Invalid Value: '").append(invalidValue).append("'");
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
