
package vikrant.khaiwal.displayimage.restModel;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class ResponseHeader {

    @Expose private Integer status;
    @Expose private Integer QTime;
    @Expose private Params params;
    @Expose private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The QTime
     */
    public Integer getQTime() {
        return QTime;
    }

    /**
     * 
     * @param QTime
     *     The QTime
     */
    public void setQTime(Integer QTime) {
        this.QTime = QTime;
    }

    /**
     * 
     * @return
     *     The params
     */
    public Params getParams() {
        return params;
    }

    /**
     * 
     * @param params
     *     The params
     */
    public void setParams(Params params) {
        this.params = params;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
