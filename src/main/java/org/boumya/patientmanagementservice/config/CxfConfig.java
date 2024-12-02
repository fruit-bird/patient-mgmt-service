package org.boumya.patientmanagementservice.config;

import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.boumya.patientmanagementservice.web.PatientSoapService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class CxfConfig {
    private Bus bus;
    private PatientSoapService patientSoapService;

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, patientSoapService);
        endpoint.publish("/PatientSoapService");
        return endpoint;
    }
}
