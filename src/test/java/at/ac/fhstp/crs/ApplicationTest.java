package at.ac.fhstp.crs;

import at.ac.fhstp.crs.controller.TokenController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TokenController.class)
public class ApplicationTest {

  @Autowired
  TokenController cut;

  @Test
  public void contextLoads() {
    Assertions.assertThat(cut).isNot(null);
  }
}
