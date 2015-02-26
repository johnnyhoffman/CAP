package common;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DateTimePicker extends JPanel {

    public interface DateTimePickerChangeListener {
        public void onChange();
    }

    private DateTimePickerChangeListener changeListener;
    private JSpinner daySpinner;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private JSpinner timeSpinner;

    public DateTimePicker() {
        super();
        constructorCommon(new Date());
    }

    public DateTimePicker(Date startDate) {
        super();
        constructorCommon(startDate);
    }

    private void constructorCommon(Date startDate) {
        daySpinner = new JSpinner(new SpinnerDateModel());
        monthSpinner = new JSpinner(new SpinnerDateModel());
        yearSpinner = new JSpinner(new SpinnerDateModel());
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dayEditor = new JSpinner.DateEditor(daySpinner, GlobalConstants.DAY_FORMAT);
        JSpinner.DateEditor monthEditor = new JSpinner.DateEditor(monthSpinner, GlobalConstants.MONTH_FORMAT);
        JSpinner.DateEditor yearEditor = new JSpinner.DateEditor(yearSpinner, GlobalConstants.YEAR_FORMAT);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, GlobalConstants.TIME_FORMAT);
        daySpinner.setEditor(dayEditor);
        monthSpinner.setEditor(monthEditor);
        yearSpinner.setEditor(yearEditor);
        timeSpinner.setEditor(timeEditor);
        setDate(startDate);
        this.add(daySpinner);
        this.add(monthSpinner);
        this.add(yearSpinner);
        this.add(timeSpinner);
        ChangeListener swingChangeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                changePerformed();
            }
        };
        daySpinner.addChangeListener(swingChangeListener);
        monthSpinner.addChangeListener(swingChangeListener);
        yearSpinner.addChangeListener(swingChangeListener);
        timeSpinner.addChangeListener(swingChangeListener);
    }

    public void setDate(Date date) {
        daySpinner.setValue(date);
        monthSpinner.setValue(date);
        yearSpinner.setValue(date);
        timeSpinner.setValue(date);
    }

    public void setDate(String dateString) {
        try {
            Date date = new SimpleDateFormat(GlobalConstants.DATETIME_FORMAT).parse(dateString);
            setDate(date);
        } catch (ParseException e) {
            // TODO: Maybe better error handling in case database sends wrongly
            // formmatted dateTime?
            e.printStackTrace();
        }
    }

    public void setDate(long dateLong) {
        setDate(new Date(dateLong));
    }

    public Date getDate() {
        Calendar c = new GregorianCalendar();
        c.setTime((Date) daySpinner.getValue());
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.setTime((Date) monthSpinner.getValue());
        int month = c.get(Calendar.MONTH);
        c.setTime((Date) yearSpinner.getValue());
        int year = c.get(Calendar.YEAR);
        c.setTime((Date) timeSpinner.getValue());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        c.set(year, month, day, hour, minute);
        return c.getTime();
    }

    public String getDateString() {
        Date d = getDate();
        return new SimpleDateFormat(GlobalConstants.DATETIME_FORMAT).format(d);
    }

    public long getDateLong() {
        Date d = getDate();
        return d.getTime();
    }

    public void setChangeListener(DateTimePickerChangeListener l) {
        this.changeListener = l;
    }

    public void changePerformed() {
        if (changeListener != null) {
            changeListener.onChange();
        }
    }

    public void setUneditable() {
        timeSpinner.setEnabled(false);
        daySpinner.setEnabled(false);
        monthSpinner.setEnabled(false);
        yearSpinner.setEnabled(false);
    }

}
