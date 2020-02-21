package pl.miroslawbrz.czartery.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.api.response.GeoIpResponse;
import pl.miroslawbrz.czartery.service.LocationService;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Service
public class LocationServiceImpl implements LocationService {

    private DatabaseReader dbReader;

    public LocationServiceImpl() throws IOException {
        File database = new File("GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    @Override
    public GeoIpResponse getLocation(String ip)
        throws IOException, GeoIp2Exception {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = dbReader.city(ipAddress);

            String cityName = response.getCity().getName();
            String latitude =
                    response.getLocation().getLatitude().toString();
            String longitude =
                    response.getLocation().getLongitude().toString();
            return new GeoIpResponse(ip, cityName, latitude, longitude);
    }

    @Override
    public String getHostIp(){

        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        assert inetAddress != null;
        return inetAddress.getHostAddress();
    }


}
