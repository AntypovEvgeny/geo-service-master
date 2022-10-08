package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    GeoService geoService = new GeoServiceImpl();

    @DisplayName("Тестирование локации по Ip")
    @ParameterizedTest
    @MethodSource("sources")
    protected void GeoServiceImplTest(String ip, Location expected) {
        Location location = geoService.byIp(ip);
        Assertions.assertEquals(expected.getCity(), location.getCity());
        Assertions.assertEquals(expected.getCountry(), location.getCountry());
    }

    private static Stream<Arguments> sources() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
        Arguments.of("172.0.8.0", new Location("Moscow", Country.RUSSIA, "", 0)),
        Arguments.of("96.44.52.62", new Location("New York", Country.USA, "", 0)));
    }
}
