
package vikrant.khaiwal.displayimage.restModel;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class MainClass {

    @Expose private ResponseHeader responseHeader;
    @Expose private Response response;
    @Expose private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The responseHeader
     */
    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    /**
     * 
     * @param responseHeader
     *     The responseHeader
     */
    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    /**
     * 
     * @return
     *     The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     *     The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
