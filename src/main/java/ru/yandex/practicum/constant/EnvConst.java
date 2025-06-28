package ru.yandex.practicum.constant;


public class EnvConst {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    public static final String LOGIN_USER_ENDPOINT = "api/auth/login";
    public static final String DELETE_USER_ENDPOINT = "api/auth/user";
    public static final String CREATE_USER_ENDPOINT = "api/auth/register";

    public static final String[] DOMAINS = { "@gmail.com", "@yandex.ru", "@mail.ru" };

    public final static String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    public final static String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    public final static String PASSWORD_RECOVERY_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public static final int EXPLICIT_TIMEOUT = 3;

    public static final String INCORRECT_PASSWORD_REGISTER_ERROR_MSG = "Некорректный пароль";

    public static final String INGREDIENTS_SECTION_NAME_BUNS = "Булки";
    public static final String INGREDIENTS_SECTION_NAME_SAUCES = "Соусы";
    public static final String INGREDIENTS_SECTION_NAME_FILLINGS = "Начинки";
}
