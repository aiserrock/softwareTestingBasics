package ru.ac.uniyar.testingcourse.grades;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class AverageGradeCalculatorTest {


     /*
     * 1.calculateAverageForStudent
     *  1.1 shouldReturnNotNullCalculateAverageForStudent
     *  input: STUDENT_PETR, MONTH
     *  output: AVG_PETR
     *
     *  1.2 shouldReturnNullCalculateAverageForStudent
     *  input: STUDENT_SERGEY,MONTH
     *  output: null
     *
     *
     * 2.recalculateAndStoreBestStudent
     *  2.1 shouldReturnNotNullRecalculateAndStoreBestStudent
     *  input: MONTH
     *  output: null
     *
     *  2.2 shouldReturnOneRecalculateAndStoreBestStudent
     *  input: MONTH
     *  output: "Alex"
     *
     *  2.3 shouldReturnMultipleRecalculateAndStoreBestStudent
     *  input: MONTH
     *  output: "Petr, Alex"  or "Alex, Petr"
     *
     * */

    private static final String STUDENT_PETR = "Petr";
    private static final String STUDENT_ALEX = "Alex";
    private static final String STUDENT_SERGEY = "Sergey";
    private static final Double AVG_PETR = 3.8;
    private static final Integer MONTH = 5;
    private ImmutableList<Integer> gradesPetr = ImmutableList.of(5,4,3,4,3);
    private ImmutableList<Integer> gradesAlex = ImmutableList.of(5,5,5,5,5);
    private ImmutableList<Integer> gradesSergey = ImmutableList.of();
    private ImmutableMap<String,ImmutableList<Integer>> studentsAndHisGrades = ImmutableMap.of(STUDENT_PETR,gradesPetr,STUDENT_ALEX,gradesAlex);
    private ImmutableMap<String,ImmutableList<Integer>> emptyMap = ImmutableMap.of();
    private ImmutableMap<String,ImmutableList<Integer>> studentsAndHisGradesForMultipleTest = ImmutableMap.of(STUDENT_PETR,gradesPetr,STUDENT_ALEX,gradesPetr);


    private Dao dao  = mock(Dao.class);
    private AverageGradeCalculator averageGradeCalculator = new AverageGradeCalculator(dao);


    //1.1
    @Test
    public void shouldReturnNotNullCalculateAverageForStudent(){
        when(dao.retrieveGradesForStudent(STUDENT_PETR,MONTH)).thenReturn(gradesPetr);
        assertThat(averageGradeCalculator.calculateAverageForStudent(STUDENT_PETR,MONTH)).isEqualTo(AVG_PETR);
        verify(dao).retrieveGradesForStudent(STUDENT_PETR,MONTH);

    }
    //1.2
    @Test
    public void shouldReturnNullCalculateAverageForStudent(){
        when(dao.retrieveGradesForStudent(STUDENT_SERGEY,MONTH)).thenReturn(gradesSergey);
        assertThat(averageGradeCalculator.calculateAverageForStudent(STUDENT_SERGEY,MONTH)).isNull();
        verify(dao).retrieveGradesForStudent(STUDENT_SERGEY,MONTH);

    }
    //2.2
    @Test
    public void shouldReturnOneRecalculateAndStoreBestStudent(){
        when(dao.retrieveAllGrades(MONTH)).thenReturn(studentsAndHisGrades);
        assertThat(averageGradeCalculator.recalculateAndStoreBestStudent(MONTH)).isEqualTo("Alex");
        verify(dao).retrieveAllGrades(MONTH);
        verify(dao).storeBestStudentName(STUDENT_ALEX);
    }
    //2.1
    @Test
    public void shouldReturnNotNullRecalculateAndStoreBestStudent(){
        when(dao.retrieveAllGrades(MONTH)).thenReturn(emptyMap);
        assertThat(averageGradeCalculator.recalculateAndStoreBestStudent(MONTH)).isNull();
        verify(dao).retrieveAllGrades(MONTH);

    }
    //2.3
    @Test
    public void shouldReturnMultipleRecalculateAndStoreBestStudent(){
        when(dao.retrieveAllGrades(MONTH)).thenReturn(studentsAndHisGradesForMultipleTest);
        assertThat(averageGradeCalculator.recalculateAndStoreBestStudent(MONTH)).isEqualTo("Alex, Petr");
        verify(dao).retrieveAllGrades(MONTH); //!!!!
        verify(dao).storeBestStudentName("Alex, Petr");

    }



}