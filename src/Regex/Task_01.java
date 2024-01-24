package Regex;
/*
1.Телефон
2. Дата
3. Личный ИД номер
 */

import java.util.regex.Pattern;

public class Task_01 {

    public static void main(String[] args) {
        printResult(validate("PHone NUMber", "+111(202)555-0125"));
        printResult(validate("daTe", "30.04.2024"));
        printResult(validate("DAte","04.30.2024"));
        printResult(validate("DAte","05.31.2024"));
        printResult(validate("DAte","05.05.2024"));
        printResult(validate("DAte","29.02.2024"));
        printResult(validate("DAte","29.01.2024"));
        printResult(validate("DAte","29.02.2023"));
        printResult(validate("DAte","02.29.2024"));
        printResult(validate("DAte","02.29.2023"));
        printResult(validate("DAte","04.31.2024"));
        printResult(validate("dAte","77.55.3014"));
        printResult(validate("Id","1488322X666OK4"));

    }

    static boolean validate(String type, String input) {
        type = type.toLowerCase();
        System.out.println(type + " " + input);
        switch (type) {
            case ("phone number"):
                return Pattern
                        .matches("\\+\\d{3}\\(\\d{3}\\)\\d{3}-\\d{4}", input);
            //Определяем стиль написания даты (взял обычный и американский) по первым и вторым цифрам
            case ("date"):
                int firstTwo = Integer.parseInt(input.substring(0, 2));
                int secondTwo = Integer.parseInt(input.substring(3, 5));
            /*в случаях, где дата подходит под оба стиля, проверять не нужно - идем по обычному, ибо
                ТЗ: определить, подходит ли строка под шаблон, а не вывести конкретную дату
                */
                if ((firstTwo <= 31) && (secondTwo <= 12)) {
                    return checkGeneral(firstTwo, secondTwo, input);

                } else if ((firstTwo <= 12) && (secondTwo <= 31)) {
                    return checkAmerican(firstTwo,secondTwo,input);
                }
            case ("id"):
                return Pattern
                        .matches("\\d{7}[A-Z]\\d{3}[A-Z]{2}\\d",input);
        }
        return false;
    }

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    static void printResult(boolean result) {
        System.out.println(result);
        System.out.println();
    }

    static boolean checkGeneral(int day, int month, String input) {
        int[] months31 = {1, 3, 5, 7, 8, 10, 12};
        boolean result;
        //если день 31, то месяц должен подпадать под months31
        if (day == 31) {
            for (int number : months31) {
                //не нашел способа добавить больший диапазон - пришлось разделять месяцы на 0-9 и 10+
                if (number == month && month > 9) {
                    result = Pattern
                            .matches("31[.]1[0-2][.]\\d{4}", input);
                    return result;
                } else if (number == month && month < 10) {
                    result = Pattern
                            .matches("31[.]0[1-9][.]\\d{4}", input);
                    return result;
                }
            }
        } else if (day == 30) {
            //под 30-й день попадают все месяцы, кроме 2-го
            if (month != 2) {
                if (month > 9) {
                    result = Pattern
                            .matches("30[.]1[0-2][.]\\d{4}", input);
                    return result;
                } else if (month < 10) {
                    result = Pattern
                            .matches("30[.]0[1-9][.]\\d{4}", input);
                    return result;
                }
            } else {
                return false;
            }
        } else if (day == 29) {
            //если день 29-й, то проверяем месяц, если он 2-й, то проверяем, високосный ли он
            if (month == 2) {
                int year = Integer.parseInt(input.substring(6));
                if (isLeapYear(year)) {
                    result = Pattern
                            .matches("29[.]02[.]\\d{4}", input);
                    return result;
                } else {
                    return false;
                }
            }
            if (month > 9) {
                result = Pattern
                        .matches("[0-2][0-9][.]1[0-2][.]\\d{4}", input);
                return result;
            } else if (month < 10) {
                result = Pattern
                        .matches("[0-2][0-9][.]0[0-9][.]\\d{4}", input);
                return result;
            }
        } else {
            if (month > 9) {
                result = Pattern
                        .matches("[0-2][0-9][.]1[0-2][.]\\d{4}", input);
                return result;
            } else if (month < 10) {
                result = Pattern
                        .matches("[0-2][0-9][.]0[0-9][.]\\d{4}", input);
                return result;
            }
        }
        return false;
    }

    static boolean checkAmerican(int month, int day, String input) {
        //то же самое, но месяцы и дни в противоположных местах
        int[] months31 = {1, 3, 5, 7, 8, 10, 12};
        boolean result;
        if (day == 31) {
            for (int number : months31) {
                if (number == month && month > 9) {
                    result = Pattern
                            .matches("1[0-2][.]31[.]\\d{4}", input);
                    return result;
                } else if (number == month && month < 10) {
                    result = Pattern
                            .matches("0[1-9][.]31[.]\\d{4}", input);
                    return result;
                }
            }

        } else if (day == 30) {
            if (month != 2) {
                if (month > 9) {
                    result = Pattern
                            .matches("1[0-2][.]30[.]\\d{4}", input);
                    return result;
                } else if (month < 10) {
                    result = Pattern
                            .matches("0[1-9][.]30[.]\\d{4}", input);
                    return result;
                }
            } else {
                return false;
            }

        } else if (day == 29) {
            if (month == 2) {
                int year = Integer.parseInt(input.substring(6));
                if (isLeapYear(year)) {
                    result = Pattern
                            .matches("02[.]29[.]\\d{4}", input);
                    return result;
                } else {
                    return false;
                }
            }
            if (month > 9) {
                result = Pattern
                        .matches("1[0-2][.][0-2][0-9][.]\\d{4}", input);
                return result;
            } else if (month < 10) {
                result = Pattern
                        .matches("0[0-9][.][0-2][0-9][.]\\d{4}", input);
                return result;
            }
        } else {
            if (month > 9) {
                result = Pattern
                        .matches("1[0-2][.][0-2][0-9][.]\\d{4}", input);
                return result;
            } else if (month < 10) {
                result = Pattern
                        .matches("0[0-9][.][0-2][0-9][.]\\d{4}", input);
                return result;
            }
        }
        return false;
    }
}
