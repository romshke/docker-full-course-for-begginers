import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

    public static void printMonthCalendar(int year, int month) {
        if (month < 1 || month > 12) {
            System.out.println("Неверный номер месяца: " + month);
            return;
        }

        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Используем java.time для вывода именительного падежа месяца ("октябрь 2025")
        LocalDate date = LocalDate.of(year, month, 1);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("LLLL yyyy", Locale.forLanguageTag("ru"));

        // Заглавная первая буква
        String title = date.format(fmt);
        if (!title.isEmpty()) {
            title = title.substring(0, 1).toUpperCase(Locale.forLanguageTag("ru")) + title.substring(1);
        }

        // Центрирование относительно ширины календаря (4 символа на день, 7 дней)
        int calendarWidth = 4 * 7; // 28
        int padding = (calendarWidth - title.length()) / 2;
        if (padding < 0) padding = 0;
        System.out.println(String.format("%" + padding + "s", "") + title);

        // Формируем заголовок дней недели на русском (Пн..Вс), по 4 символа на столбец
        String[] days = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        StringBuilder header = new StringBuilder();
        for (String d : days) {
            header.append(String.format("%3s ", d));
        }
        System.out.println(header.toString());

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1 = Sunday, ..., 7 = Saturday
        int offset = (dayOfWeek + 5) % 7; // convert so Monday=0 ... Sunday=6

        for (int i = 0; i < offset; i++) {
            System.out.print("    ");
        }

        int pos = offset;
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%3d ", day);
            pos++;
            if (pos == 7) {
                System.out.println();
                pos = 0;
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в календарь");

        int year, month;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Пожалуйста введите год: ");
            year = scanner.nextInt();

            System.out.print("Введите номер любого месяца: ");
            month = scanner.nextInt();
        }
        
        printMonthCalendar(year, month);

        System.out.println("Всего хорошего!");
    }
}