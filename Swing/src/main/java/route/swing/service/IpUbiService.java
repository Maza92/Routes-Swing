/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import route.swing.http.HttpClientIPGPS;
import route.swing.model.jxmap.Region;

/**
 *
 * @author Luis
 */
public class IpUbiService {

    HttpClientIPGPS apiGPS;
    public IpUbiService() {
        apiGPS = new HttpClientIPGPS();
    }
    public Region getUbication() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ipAddress = inetAddress.getHostAddress();
        
        System.out.println(ipAddress);
        
        return apiGPS.getLocation(ipAddress);
    }
}
