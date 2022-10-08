package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class MessageSenderImplTest {

    GeoServiceImpl geoService;
    LocalizationServiceImpl localService;
    MessageSenderImpl messageSender;
    Map<String, String> map = new HashMap<>();

    String ipRus = "172.0.32.11";
    String ipNotRus = "96.44.183.149";

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localService);
    }

    @Test
    void textRus() {
        Mockito.when(geoService.byIp(ipRus))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipRus);
        String actual = messageSender.send(map);
        Assertions.assertEquals("Добро пожаловать", actual);
    }

    @Test
    void textNotRus() {
        Mockito.when(geoService.byIp(ipNotRus))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localService.locale(Country.USA)).thenReturn("Welcome");
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipNotRus);
        String actual = messageSender.send(map);
        Assertions.assertEquals("Welcome", actual);
    }
}