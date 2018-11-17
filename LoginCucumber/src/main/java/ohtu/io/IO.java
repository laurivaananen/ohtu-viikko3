
package ohtu.io;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public interface IO {
    void print(String toPrint);
    int readInt(String prompt);
    String readLine(String prompt);
}
