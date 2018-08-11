package org.rib.updateclient.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@UtilityClass
public class HttpUtil {

   public static MultiValueMap<String, String> getHeaders() {
      MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
      headers.add("Content-Type", "application/json");
      return headers;
   }

}
