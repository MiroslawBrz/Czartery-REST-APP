package pl.miroslawbrz.czartery.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import pl.miroslawbrz.czartery.api.response.GeoIpResponse;

import java.io.IOException;
import java.net.UnknownHostException;

public interface LocationService {

    GeoIpResponse getLocation(String ip) throws IOException, GeoIp2Exception;
    String getHostIp() throws UnknownHostException;
}
