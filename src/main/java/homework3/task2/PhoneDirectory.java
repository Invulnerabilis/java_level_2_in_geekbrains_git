package homework3.task2;

import java.util.*;

public class PhoneDirectory {
    Map<String, List<String>> data;

    public PhoneDirectory() {
        data = new HashMap<>();
    }

    public void add(String lastname, String phoneNumber) {
        List<String> phones = data.get(lastname);
        if (phones != null) {
            phones.add(phoneNumber);
        } else {
            data.put(lastname, new ArrayList<>() {{
                add(phoneNumber);
            }});
        }
    }

    public List<String> get(String lastname) {
        return data.get(lastname);
    }

    @Override
    public String toString() {
        return "PhoneDirectory {" +
                "data = " + data +
                '}';
    }
}
