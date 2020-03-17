package ru.ac.uniyar.testingcourse;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class CounterTest {

    Counter counter = new Counter();

// 1. После инициализации значения счетчика равно 0

    @Test
    public void ValueIs0AfterInstantiation(){
        //int actual = counter.getValue();
        //assertEquals(1,counter.getValue());
        assertThat(counter.getValue()).isZero();
    }

    // 2. Метод increase() увеличивает значение счетчика на 1
    @Test
    public void IncreaseAdds1ToCounter(){
        counter.increase();
        //assertEquals(1,counter.getValue());
        assertThat(counter.getValue()).isEqualTo(1);
        counter.increase();
        //assertEquals(2,counter.getValue());
        assertThat(counter.getValue()).isEqualTo(2);
    }

    // 3.a Метод reset() сбрасывает значение счетчика в 0 полсе вызова increase()
    @Test
    public void ResetChangesValueTo0AfterIncrease(){
        counter.increase();
        counter.reset();
        //assertEquals(1,counter.getValue());
        assertThat(counter.getValue()).isZero();
    }
    // 3.b  Метод reset() сбрасывает значения счетчика в 0 непосредственно после вызова конструктора
    public void ResetChangesvalueTo0AfterInstantiation(){
        counter.reset();
        assertThat(counter.getValue()).isZero();
    }

}