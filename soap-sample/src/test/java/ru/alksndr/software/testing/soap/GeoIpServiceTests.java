package ru.alksndr.software.testing.soap;

import com.lavasoft.GeoIPService;
import org.junit.Test;

import javax.xml.ws.Service;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        Service ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("80.246.31.13");

    }
}
