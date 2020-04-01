package ru.ac.uniyar.testingcourse;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

//@RunWith(JUnitParamsRunner.class)

public class CourseTest {
    Course course = new Course(5);
    //TEST CASES
    /*
    1. CourseIsNotFull
        1.1. enroll(enrolled)
        In: double studentId 1
        Out: EnrollmentList size equal to 1

        1.2. enroll(unenrolled) if [#students < max-1] that enroll
        In:six different students
        Out: EnrollmentList size equal to 5

        1.3. unenroll(unenrolled) (remove the second student twice)
        In: two different students
        Out:one students

        1.4 unendroll(enrolled)(enroll and unenroll same student)
        In: one student
        Out: zero student
    2. CourseIsNotFull --> CourseIsFull
    same that 1.2
    3. CourseIsFull --> CourseIsNotFull
    In: five different students in EnrollList(full)
    Out: four different students in EnrollList(not full)
    4. CourseIsFull
        4.1 enroll(enrolled)
        same that 1.1 but enrollList is full and nothing happened
        In: five different students
        Out:five students
        4.2 unenroll(unenrolled)
        same that 1.3 but enrollList is full and nothing happened
        In: five different students
        Out:five students
    5. CourseIsFull --> WaitingListIsNotEmpty
    enroll unenrolled (make waiting)
    In:six different students
    Out:enrollList is full and waitingList is not empty
    6. WaitingListIsNotEmpty --> CourseIsFull
        6.1 unenroll enrolled and if waitingList == 1 then make first waiting enrolled
        In: six different students (ennrollList is full and waitingList is not Empty)
        Out:five different students (enrollList is full nad waitingList is empty)
        6.2 unenroll waiting and if waiting == 1 then unenroll waiting
    7. WaitingListIsNotEmpty
        7.1 enroll enrolled
        In: six different students
        Out: six different students
        7.2 enroll waiting
        In: six different students
        Out: six different students
        7.3 unenroll unenrolled
        In: six different students
        Out: six different students
        7.4 unenroll waiting if waiting > 1
        In: seven different students
        Out: six different students (5 in enrollList and 1 in waitingList)
        7.5 unenroll enrolled if waiting > 1 and make first waiting enrolled
        In: seven different students
        Out: six different students (5 in enrollList and 1 in waitingList)


    * */
    //1.1
    @Test
    public void enrollEnrolled(){
        course.enroll(1);
        course.enroll(1);
        assertThat(course.getEnrollmentList().size()).isEqualTo(1);
    }

    //1.2
    @Test
    public void enrollUnenrolled(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        assertThat(course.isFullyEnrolled()).isTrue();

        course.enroll(6);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);

    }

    //1.3
    @Test
    public void unenrollUnenrolled(){
        course.enroll(1);
        course.enroll(2);
        course.unenroll(2);
        assertThat(course.getEnrollmentList().size()).isEqualTo(1);

        course.unenroll(2);
        assertThat(course.getEnrollmentList().size()).isEqualTo(1);
    }

    //1.4
    @Test
    public void unenrollEnrolled(){
        course.enroll(1);
        assertThat(course.getEnrollmentList().size()).isEqualTo(1);
        course.unenroll(1);
        assertThat(course.getEnrollmentList().size()).isEqualTo(0);
    }

    //3
    @Test
    public void fromCourseIsFullToCourseIsNotFull(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        assertThat(course.isFullyEnrolled()).isTrue();

        course.unenroll(5);
        assertThat(course.getEnrollmentList().size()).isEqualTo(4);
    }
    //4.1
    @Test
    public void enrollEnrolledWhenCourseIsFull(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        assertThat(course.isFullyEnrolled()).isTrue();

        course.enroll(5);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
    }

    //4.2
    @Test
    public void unenrollUnenrolledWhenCourseIsFull(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        assertThat(course.isFullyEnrolled()).isTrue();

        course.unenroll(6);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
    }

    //5
    @Test
    public void fromCourseIsFullToWaitingCourseIsNotEmpty(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();
    }

    //6.1
    @Test
    public void fromWaitingCourseIsNotEmptyToCourseIsFullParth1(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.unenroll(3);
        assertThat(course.isFullyEnrolled()).isTrue();
        assertThat((course.hasWaitingList())).isFalse();
    }

    //6.2
    @Test
    public void fromWaitingCourseIsNotEmptyToCourseIsFullParth2(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.unenroll(6);
        assertThat(course.isFullyEnrolled()).isTrue();
        assertThat((course.hasWaitingList())).isFalse();
    }

    //7.1
    @Test
    public void waitingListIsNotEmptyEnrollEnrolled(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.enroll(3);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
        assertThat(course.getWaitingList().size()).isEqualTo(1);
    }
    //7.2
    @Test
    public void waitingListIsNotEmptyEnrollWaiting(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.enroll(6);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
        assertThat(course.getWaitingList().size()).isEqualTo(1);
    }
    //7.3
    @Test
    public void waitingListIsNotEmptyUnenrollUnenrolled(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.unenroll(7);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
        assertThat(course.getWaitingList().size()).isEqualTo(1);
    }
    //7.4
    @Test
    public void waitingListIsNotUnenrollWaiting(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        course.enroll(7);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.unenroll(6);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
        assertThat(course.getWaitingList().size()).isEqualTo(1);
    }
    //7.5
    @Test
    public void waitingListIsNotUnenrollEnrolled(){
        course.enroll(1);
        course.enroll(2);
        course.enroll(3);
        course.enroll(4);
        course.enroll(5);
        course.enroll(6);
        course.enroll(7);
        assertThat(course.isFullyEnrolled() && course.hasWaitingList()).isTrue();

        course.unenroll(3);
        assertThat(course.getEnrollmentList().size()).isEqualTo(5);
        assertThat(course.getWaitingList().size()).isEqualTo(1);
    }




}