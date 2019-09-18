package com.lidong.webiot.h003;

public class ApiConfig {


    public static final String SEND_COMMAND_PATH = "/api/Command/SendCommand";
    public static final String SET_H002VOICE_PATH = "/api/Command/SetH002Voice";

    public static final String TIME_ZONE_LIST_PATH = "/api/DataList/TimeZoneList";
    public static final String LANGUAGE_LIST_PATH = "/api/DataList/LanguageList";

    public static final String TRACKING_PATH = "/api/Location/Tracking";
    public static final String HISTORY_PATH = "/api/Location/History";
    public static final String ADDRESS_PATH = "/api/Location/Address";
    public static final String MONTH_HISTORY_DAYS_PATH = "/api/Location/MonthHistoryDays";

    public static final String GET_DEVICE_BY_IMEI_PATH = "/api/Health/getDeviceByIMEI";
    public static final String GET_HEALTH_FORDAY_PATH = "/api/Health/getHealthForDay";
    public static final String GET_STEPS_FOR_DAY_PATH = "/api/Health/getStepsForDay";
    public static final String GET_STEPS_FOR_HOUR_PATH = "/api/Health/getStepsForHour";
    public static final String GET_HEALTH_FOR_PERIOD_PATH = "/api/Health/getHealthForPeriod";
    public static final String GET_HEALTH_PATH = "/Api/Health/getHealth";
    public static final String GET_SLEEP_DATA_PATH = "/api/Health/getSleepData";
    public static final String GET_HEALTH_BY_TYPE_PATH = "/api/Health/getHealthByType";
    public static final String UPLOAD_STEP_DATA_PATH = "/api/Health/UploadStepData";
    public static final String STEP_RANKING_LIST_PATH = "/api/Health/StepRankingList";
    public static final String USER_STEP_RANKING_INFO_PATH = "/api/Health/UserStepRankingInfo";

    public static final String EXCDEPTION_LIST_WHITOUT_CODE_PATH = "/api/ExceptionMessage/ExcdeptionListWhitoutCode";
}
