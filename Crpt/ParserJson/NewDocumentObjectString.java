import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NewDocumentObjectString {
    public Object object;
    public String string;
    @Override
    public String toString() {
        return "NewDocumentObjectString{" +
                "object=" + object +
                ", string='" + string + '\'' +
                '}';
    }

    public NewDocumentObjectString(Object object, String string) {
        this.object = object;
        this.string = string;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
