package com.loan.util;

import com.loan.logging.Logger;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public final class DateUtil implements Logger {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private DateUtil() {}

    public static Date addToNow(TimeUnit unit, long amount) {
        return add(new Date(), unit, amount);
    }

    public static Date add(Date date, TimeUnit unit, long amount) {
        return new Date(date.getTime() + unit.toMillis(amount));
    }

    public static long subtractFromNow(Date date, TimeUnit unit) {
        return subtract(new Date(), date, unit);
    }

    public static long subtract(Date first, Date second, TimeUnit unit) {
        long diff = first.getTime() - second.getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date tomorrow() {
        return nextDay(new Date());
    }

    public static Date yesterday() {
        Date today = midnight(new Date());
        return add(today, TimeUnit.DAYS, -1);
    }

    public static Date nextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        return midnight(calendar.getTime());
    }

    public static Date midnight(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date copy(Date date) {
        return new Date(date.getTime());
    }

    public static DateTime now() {
        return DateTime.now();
    }

    public static boolean isWeekend(LocalDateTime datetime) {
        return datetime.getDayOfWeek() == DayOfWeek.SATURDAY || datetime.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static boolean isFridayAfter10PM(LocalDateTime datetime) {
        return datetime.getDayOfWeek() == DayOfWeek.FRIDAY && datetime.getHour() >= 22;
    }

    public static Date getDate(String date) {
        return new DateTime(date).toDate();
    }

    public static boolean isAfterNow(Date date) {
        return date.getTime() > new Date().getTime();
    }

    public static boolean isBeforeNow(Date date) {
        return date.getTime() < new Date().getTime();
    }

    public static DateTime month() {
        return month(DateTime.now());
    }

    public static boolean sameDay(DateTime first, DateTime second){
        return sameMonth(first, second) && first.getDayOfMonth() == second.getDayOfMonth();
    }

    public static boolean sameMonth(DateTime first, DateTime second){
        return sameYear(first, second) && first.getMonthOfYear() == second.getMonthOfYear();
    }

    public static boolean sameYear(DateTime first, DateTime second){
        return first.getYear() == second.getYear();
    }

    public static DateTime month(DateTime dateTime) {
        return dateTime.monthOfYear().getDateTime();
    }

    public static DateTime startOfDay(DateTime dateTime) {
        return dateTime.withTimeAtStartOfDay();
    }

    public static DateTime startOfDay() {
        return DateTime.now().withTimeAtStartOfDay();
    }

    public static DateTime startOfMonth() {
        return DateTime.now().dayOfMonth().withMinimumValue();
    }

    public static DateTime startOfYear() {
        return DateTime.now().dayOfYear().withMinimumValue();
    }

    public static DateTime minutesAfter(int minutes) {
        return DateTime.now().plusMinutes(minutes);
    }

    public static DateTime minutesBefore(int minutes) {
        return DateTime.now().minusMinutes(minutes);
    }

    public static DateTime hoursAfter(int hours) {
        return DateTime.now().plusHours(hours);
    }

    public static DateTime hoursBefore(int hours) {
        return DateTime.now().minusHours(hours);
    }

    public static DateTime daysBefore(int days) {
        return DateTime.now().minusDays(days);
    }

    public static DateTime yearsBefore(int years) {
        return DateTime.now().minusYears(years);
    }

    public static DateTime monthsBefore(int months) {
        return monthsBefore(now(), months);
    }

    public static DateTime monthsBefore(DateTime dateTime, int months) {
        return dateTime.minusMonths(months);
    }

    public static DateTime monthsAfter(int months) {
        return monthsAfter(now(), months);
    }

    public static DateTime monthsAfter(DateTime dateTime, int months) {
        return dateTime.plusMonths(months);
    }

    public static DateTime endOfMonth() {
        return DateTime.now().dayOfMonth().withMaximumValue();
    }

    public static DateTime endOfMonth(DateTime dateTime) {
        return dateTime.dayOfMonth().withMaximumValue();
    }

    public static DateTime endOfDay(DateTime dateTime){
        return dateTime.millisOfDay().withMaximumValue();
    }

    public static DateTime now(DateTimeZone dateTimeZone) {
        return DateTime.now(dateTimeZone);
    }

    public static LocalDate javaNow() {
        return LocalDate.now();
    }

    public static long getAge(LocalDate birthday) {
        return birthday.until(DateUtil.javaNow(), ChronoUnit.YEARS);
    }

    public static String format(DateTime dateTime, String pattern) {
        return null != dateTime ? dateTime.toString(pattern) : null;
    }

    public static DateTime parse(String dateTime, String pattern) {
        try {
            return DateTime.parse(dateTime, DateTimeFormat.forPattern(pattern));
        }catch (Exception exception){
            return null;
        }
    }

    public static String parseDateTime(DateTime date) {
        return date == null ? DateTime.now().toString(YYYY_MM_DD) : date.toString(YYYY_MM_DD);
    }

    public static String format(DateTime dateTime, DateTimeZone timeZone, String pattern) {
        return dateTime.withZone(timeZone).toString(DateTimeFormat.forPattern(pattern));
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(DateTime dateTime) {
        return toXMLGregorianCalendar(dateTime.toDate());
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        XMLGregorianCalendar xmlCalendar = null;
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException ex) {
            logger.error("");
        }
        return xmlCalendar;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate) {
       return toXMLGregorianCalendar(Date.from(localDate.atStartOfDay(ZoneOffset.UTC).toInstant()));
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDateTime localDateTime) {
       return toXMLGregorianCalendar(Date.from(localDateTime.toInstant(ZoneOffset.UTC)));
    }

    public static Instant fromXmlGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar.toGregorianCalendar().toInstant();
    }

    public static LocalDate minus(Integer value, TemporalUnit unit) {
        return javaNow().minus(value, unit);
    }

    public static LocalDate plus(Integer value, TemporalUnit unit) {
        return javaNow().plus(value, unit);
    }

    public static DateTime lastWeekDayOfMonth(int weekDay) {
        return lastWeekDayOfMonth(now(), weekDay);
    }

    public static DateTime lastWeekDayOfMonth(DateTime dateTime, int weekDay) {
        DateTime endOfMonth = endOfMonth(dateTime);
        DateTime dayOfWeek = endOfMonth.withDayOfWeek(weekDay);
        if (dayOfWeek.getMonthOfYear() != endOfMonth.getMonthOfYear()) {
            dayOfWeek = dayOfWeek.minusDays(7);
        }
        return dayOfWeek;
    }

    public static boolean isWeekday() {
        return isWeekday(DateUtil.now());
    }

    public static boolean isWeekday(DateTime dateTime) {
        return dateTime.dayOfWeek().get() < DateTimeConstants.SATURDAY;
    }

    public static DateTime endOfWorkingDays() {
        return endOfWorkingDays(DateTime.now());
    }

    public static DateTime endOfWorkingDays(DateTime dateTime) {
        DateTime endOfWorkingDay = endOfMonth(dateTime);
        if (isWeekday(endOfWorkingDay)) {
            return endOfWorkingDay;
        } else {
            do {
                endOfWorkingDay = endOfWorkingDay.minusDays(1);
            } while (isWeekday(endOfWorkingDay));
            return endOfWorkingDay;
        }
    }

    public static DateTime dayOfMonth(int day) {
        return dayOfMonth(month(), day);
    }

    public static DateTime dayOfMonth(DateTime month, int day) {
        return month.withDayOfMonth(day);
    }

}
