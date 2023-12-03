package Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;

public class Converters {

//    @TypeConverter
//    public static Paciente fromStringPaciente(String pacienteString) {
//        // Use o mesmo caractere de delimitação utilizado no método pacienteToString
//        char delimiter = '|';
//        String[] campos = pacienteString.split(String.valueOf(delimiter));
//
//        // Certifique-se de validar o tamanho do array para evitar IndexOutOfBoundsException
//        if (campos.length == 11) {
//            return new Paciente(
//                    campos[0], campos[1], campos[2], campos[3], campos[4],
//                    campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]),
//                    campos[8], campos[9]
//            );
//        } else {
//            return null;
//        }
//    }
//
//    @TypeConverter
//    public static String pacienteToString(Paciente paciente) {
//        if (paciente != null) {
//            return paciente.toString();
//        }
//        return null;
//    }
//
//    @TypeConverter
//    public static Nutricionista fromStringNutricionista(String nutricionistaString) {
//        // Use o mesmo caractere de delimitação utilizado no método pacienteToString
//        char delimiter = '|';
//        String[] campos = nutricionistaString.split(String.valueOf(delimiter));
//
//        // Certifique-se de validar o tamanho do array para evitar IndexOutOfBoundsException
//        if (campos.length == 7) {
//            return new Nutricionista(
//                    campos[0], campos[1], campos[2], campos[3], campos[4],
//                    campos[5], campos[6]
//            );
//        } else {
//            return null;
//        }
//    }
//    @TypeConverter
//    public static String nutricionistaToString(Nutricionista nutricionista) {
//        if (nutricionista != null) {
//            return nutricionista.toString();
//        }
//        return null;
//    }
//    @TypeConverter
//    public static Calendar fromTimestamp(Long value) {
//        if (value == null) return null;
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(value);
//        return calendar;
//    }
//
//    @TypeConverter
//    public static Long calendarToTimestamp(Calendar calendar) {
//        return calendar == null ? null : calendar.getTimeInMillis();
//    }

    @TypeConverter
    public static String pacienteToString(Paciente paciente) {
        if (paciente != null) {
            Gson gson = new Gson();
            return gson.toJson(paciente);
        }
        return null;
    }

    @TypeConverter
    public static Paciente stringToPaciente(String pacienteJson) {
        if (pacienteJson == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Paciente>() {}.getType();
        return gson.fromJson(pacienteJson, type);
    }

    @TypeConverter
    public static String nutricionistaToString(Nutricionista nutricionista) {
        if (nutricionista != null) {
            Gson gson = new Gson();
            return gson.toJson(nutricionista);
        }
        return null;
    }

    @TypeConverter
    public static Nutricionista stringToNutricionista(String nutricionistaJson) {
        if (nutricionistaJson == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Nutricionista>() {}.getType();
        return gson.fromJson(nutricionistaJson, type);
    }

    @TypeConverter
    public static String calendarToString(Calendar calendar) {
        if (calendar != null) {
            Gson gson = new Gson();
            return gson.toJson(calendar);
        }
        return null;
    }

    @TypeConverter
    public static Calendar stringToCalendar(String calendarJson) {
        if (calendarJson == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Calendar>() {}.getType();
        return gson.fromJson(calendarJson, type);
    }

    @TypeConverter
    public static String agendamentoToString(Agendamento agendamento) {
        if (agendamento != null) {
            Gson gson = new Gson();
            return gson.toJson(agendamento);
        }
        return null;
    }

    @TypeConverter
    public static Agendamento stringToAgendamento(String agendamentoJson) {
        if (agendamentoJson == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Agendamento>() {}.getType();
        return gson.fromJson(agendamentoJson, type);
    }
}
