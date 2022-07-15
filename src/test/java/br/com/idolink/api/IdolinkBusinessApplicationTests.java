package br.com.idolink.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.idolink.api.api.OneSignalApi;
import br.com.idolink.api.api.PagarmeAPI;
import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.config.WebConfig;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")

class IdolinkBusinessApplicationTests {

	@MockBean
	WebConfig webConfig;
	
	@MockBean
	OneSignalApi oneSignalApi;
	
	@MockBean
	PagarmeAPI pagarmeAPI;
	
	@MockBean
	StorageApi storageApi;

	@Test
	void contextLoads() {
		
	}

}
