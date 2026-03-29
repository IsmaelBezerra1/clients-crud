package ismaeldev.clients.services.exceptions;


import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() { return errors; }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

    public static class FieldMessage {
        private String fieldName;
        private String message;

        public FieldMessage(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }

        public String getFieldName() { return fieldName; }
        public String getMessage() { return message; }
    }
}
