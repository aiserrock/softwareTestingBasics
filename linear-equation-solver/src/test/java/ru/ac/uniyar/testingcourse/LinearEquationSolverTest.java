package ru.ac.uniyar.testingcourse;

import org.junit.Test;

import javax.sound.sampled.Line;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class LinearEquationSolverTest {
    // 1. Если нет решений возвращает null
    //Вход
    //a==0
    //b==14
    //выход : null
    @Test
    public void IsNoneRoots() {
        assertThat(LinearEquationSolver.solve(0, 14)).isNull();
    }

    // 2. Если несколько решений(много) возвращает exeption
    //Вход
    //a==0
    //b==0
    //Выход:выброшено исключение LinearEquationSolver.AnyNumberIsRootException
    @Test
    public void IsMoreRoots() {
        //assertThat(LinearEquationSolver.solve(0,0));
        assertThatExceptionOfType(LinearEquationSolver.AnyNumberIsRootException.class).isThrownBy(() -> {
            throw new LinearEquationSolver.AnyNumberIsRootException();
        }).withMessage("Any number is a root");
    }
    // 3. Если одно решение
    //Вход
    //a==60
    //b==-3
    //Выход:возвращает -0.25 с погрешностью 0.01
    @Test
    public void IsOneRoot(){
        assertThat(LinearEquationSolver.solve(60, -3)).isCloseTo(-0.05,within(0.01));
    }
}