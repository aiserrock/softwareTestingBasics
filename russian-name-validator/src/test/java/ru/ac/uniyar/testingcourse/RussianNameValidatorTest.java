package ru.ac.uniyar.testingcourse;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class RussianNameValidatorTest {
    RussianNameValidator validator = new RussianNameValidator();
////exist two ways test true and false ways
////first true
//
////1.Корректные данные
//    //1.1 Два слова
//      //Иванов Иван
//      //Иванов-Петров Иван
//      //Иванов Иван-Петров
//      //Иванов-Петров Петр-Иванов
//    //1.2 Три слова. Варианты из п. 1.1 с правильным отчеством:
//      //Иванов Иван Иванович
//      //Иванов-Петров Иван Иванович
//      //Иванов Иван-Петров Иванович
//      //Иванов-Петров Петр-Иванов Иванович
////2.Некорректные данные
//    //2.0 null
//    //2.1 Коллво слов < 2
//        //""
//        //" "
//        //Иванов
//        //" Иванов"
//        //" Иванов "
//    //2.2 Два слова
//          //2.2.1 Нерусские буквы
//          //EnglishName
//          //Иван@в Ив@н
//          //123456 Иван
//          //Иванов ;******???
//        //2.2.2"Нарушен регистр символов
//          //Иванвов иван
//          //иванвов Иван
//          //ИВанвов Иван
//        //2.2.3 Неправильное положение дефисов
//            //-иванов Иван
//            // Иванов -иван
//            //Иванов Иван -
//            //Иванов-петров Иван
//            //Иванов-петров Иван
//            //Иванов Иван-петр
//            //иВАНОВ - Иван
//            //Иванов--Петров Иван
//    //2.3 Три слова
            //2.3.1 Варианты из п. 1.1 с неправильными отчествами:
//              //иванович
//              //Иванович-Петрович
//              //ивАнович
//              ив@нови4
            //2.3.2. Варианты из п.2.2 с правильным отчеством
                //Васильевич
//
//    //2.4 Колл-во слов > 3
//    //Иванов Иван Иванович Лалалович
    @Test
    public void testNull(){
        assertThatThrownBy(()->validator.validate(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Parameters(method = "validNamesOf2Words, validNamesOf3Words")
    public void testValidNames(String name){
        assertThat(validator.validate(name)).isTrue();
    }

    @Test
    @Parameters(method = "invalidNamesOf2Words,invalidNamesOf2WordsWithValidPatronymic,validNamesOf2WordsWithInvalid")
    public void testInvalidNames(String name){
        assertThat(validator.validate(name)).isFalse();
    }

    String[] validNamesOf2Words(){
        return new String[]{
                "Иванов Иван",
                "Иванов-Петров Иван",
                "Иванов Иван-Пётр",
                "Иванов-Петров Иван-Пётр"
        };
    }

    String[] invalidNamesOf2Words(){
        return new String[]{
                "English Name",
                "Иванов Иван",
                "Иванов Ив@н",
                "Иванов *****",
                "12345 Иван",
                "Иванов иван",
                "ивановИван",
                "ИВанов Иван",
                "Иванов ИВан",
                "-иванов Иван",
                "Иванов -иван",
                "Иванов-Петров Смирнов Иван"
        };
    }

    LinkedList<String> validNamesOf3Words(){
        LinkedList<String> result = new LinkedList<>();
        for (String name: validNamesOf2Words()){
            result.add(name+" Иванович");
        }
        return result;
    }

    LinkedList<String> invalidNamesOf2WordsWithValidParonymic(){
        LinkedList<String> result = new LinkedList<>();
        for(String name: invalidNamesOf2Words()){
            result.add(name+" Васильевич");
        }
        return result;
    }

    String[] invalidPatronymics(){
        return  new String[]{
                "иванович",
                "Иванович Петрович",
                "иваНович",
                "Ив*нови4",
                "123"
        };
    }

    Object[] otherInvalidNames(){
        return new Object[]{
                "Иванов Иван Иванович Подыванович",
                "",
                " ".toCharArray(),
                "Иванов ".toCharArray(),
                " Иванов".toCharArray(),
                " Иванов ".toCharArray()
        };
    }


}