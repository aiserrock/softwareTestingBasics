package ru.ac.uniyar.testingcourse;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
@RunWith(JUnitParamsRunner.class)
public class CarMovementDecisionMakerTest {

    private CarMovementDecisionMaker sut = new CarMovementDecisionMaker();

    private boolean str2bool(String str){
        switch (str){
            case "0": return false;
            case "1": return true;
        }
        throw new IllegalArgumentException(str);
    }

    private Ternar str2Ternar(String str){
        switch (str){
            case "0":return Ternar.OFF;
            case "1":return Ternar.ON;
            case "2":return Ternar.BLINKING;
        }
        throw  new IllegalArgumentException(str);
    }

    @Test
    @FileParameters(value = "classpath:decision_rules.csv", mapper = CsvWithHeaderMapper.class)
    public void testDecisions(String name, String red, String yellow, String green,String go, String withCaution,String bePrepared){
        sut.setTrafficLightState(str2bool(red),str2Ternar(yellow),str2Ternar(green));
        assertThat(sut.isToGo()).isEqualTo(str2bool(go));
        assertThat(sut.isToBeCautious()).isEqualTo(str2bool(withCaution));
        assertThat(sut.isToBePrepared()).isEqualTo(str2bool(bePrepared));
    }

}