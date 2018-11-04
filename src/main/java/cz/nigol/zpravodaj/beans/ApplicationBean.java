package cz.nigol.zpravodaj.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationBean {
    private Map<Integer, String> daysOfWeek;

    @PostConstruct
    public void init() {
	daysOfWeek = new HashMap<>();
	daysOfWeek.put(1, "neděle");
	daysOfWeek.put(2, "pondělí");
	daysOfWeek.put(3, "úterý");
	daysOfWeek.put(4, "středa");
	daysOfWeek.put(5, "čtvrtek");
	daysOfWeek.put(6, "pátek");
	daysOfWeek.put(7, "sobota");
    }

    public Date today() {
	return new Date();
    }

    public String todayNameOfDay() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(today());
	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	return daysOfWeek.get(dayOfWeek);
    }
}
