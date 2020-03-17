package ru.ac.uniyar.testingcourse;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class RussianNameValidatorTest {
    RussianNameValidator validator = new RussianNameValidator()
;//exist two ways test true and false ways
//first true

//1.Корректные данные
    //1.1 Два слова
    //Иванов Иван
    //Иванов-Петров Иван
    //Иванов Иван-Петров
    //Иванов-Петров Петр-Иванов
    @Test
    public void TwoWords(){
        assertEquals(true,"Петров Петр");
    }

    //1.2 Три слова
    //Иванов Иван Иванович
    //Иванов-Петров Иван Иванович
    //Иванов Иван-Петров Иванович
    //Иванов-Петров Петр-Иванов Иванович
    public void ThreeWords(){
        assertEquals(true,"Петров Петр");
    }
    //second false
    //2.Некорректные данные
    //2.0 null
    @Test
    public void NullString(){
        assertThatThrownBy(()->validator.validate(null)).isInstanceOf(NullPointerException.class);
    }

    //Хотелось бы иметь такое
    //т.е параметризованный тест (параметры беруться из вне)
    @Test
    public void TestValidNames(String name){
        assertThat(validator.validate(name)).isTrue()
    }
    //2.1 Коллво слов < 2
        //2.1.1 ""
        //2.1.2 " "
        //2.1.3 Иванов
        //2.1.4 " Иванов"
        //1.1.5 " Иванов "
    //2.2 Два слова
        //2.2.1 Нерусские буквы
        //EnglishName
        //Иван@в Ив@н
        //123456 Иван
        //Иванов ;******???
        //2.2.2"Нарушен регистр символов
        //Иванвов иван
        //иванвов Иван
        //ИВанвов Иван
        //2.2.3 Неправильное положение дефисов
            //-иванов Иван
            // Иванов -иван
            //Иванов Иван -
            //Иванов-петров Иван
            //Иванов-петров Иван
            //Иванов Иван-петр
            //иВАНОВ - Иван
            //Иванов--Петров Иван
    //2.3 Три слова
    //Это все хрень,надо делать другой принцип потому что комбинаторный взрыв
    //берем варианты из пункта 1.1 и комбинируем их с неправильными отчествами
    //2.3.1 иванович
    //2.3.2 Иванович-Петрович
    //2.3.3 ивАнович
    //2.3.4 ив@нови4

    //2.4 Колл-во слов > 3
    //Иванов Иван Иванович Лалалович

}