package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class LocalizationServiceImplTest {

    @Mock
    private LocalizationServiceImpl localizationService;

    @BeforeEach
    void SetUp() {
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
    }

    @Test
    void getAdvice() {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.USA));

    }
}
