package com.nhn.pinpoint.web.alarm.checker;

import com.nhn.pinpoint.web.alarm.collector.DataCollector;
import com.nhn.pinpoint.web.alarm.collector.MapStatisticsCallerCollector;
import com.nhn.pinpoint.web.alarm.collector.MapStatisticsCallerCollector.DataCategory;
import com.nhn.pinpoint.web.alarm.vo.Rule;

public class SlowRateToCalleChecker extends AlarmChecker {

    public SlowRateToCalleChecker(DataCollector dataCollector, Rule rule) {
        super(rule, "%", dataCollector);
    }

    @Override
    protected long getDetectedValue() {
        String calleName = rule.getNotes();
        return ((MapStatisticsCallerCollector)dataCollector).getCountRate(calleName, DataCategory.SLOW_RATE);
    }
    
    @Override
    public String getEmailMessage() {
        return String.format("%s value is %s%s during the past 5 mins.(Threshold : %s%s) %s For From $s To $s.<br>", rule.getCheckerName(), getDetectedValue(), unit, rule.getThreshold(), unit, rule.getCheckerName(), rule.getApplicationId(), rule.getNotes());
    };

}
