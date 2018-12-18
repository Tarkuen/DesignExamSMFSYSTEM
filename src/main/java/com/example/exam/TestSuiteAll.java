package com.example.exam;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@RunWith(Suite.class)

@Suite.SuiteClasses(
        {ExamApplicationTests.class,
        ConfigurationTest.class,
        MediatorTest.class}
)

public class TestSuiteAll {
}
